package com.cn.wct.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 多线程下载 和 断点续传
 * 
 *
 */
public class MuchThreadDown {

	// 下载路径
	private String path = "";
	private String targetFilePath = "/"; // 下载文件存放目录
	private int threadCount = 3; // 线程数量

	/**
	 * 构造方法
	 * 
	 * @param path
	 *            要下载文件的网络路径
	 * @param targetFilePath
	 *            保存下载文件的目录
	 * @param threadCount
	 *            开启的线程数量,默认为 3
	 */
	public MuchThreadDown(String path, String targetFilePath, int threadCount) {
		this.path = path;
		this.targetFilePath = targetFilePath;
		this.threadCount = threadCount;
	}

	public HttpURLConnection getHttpURLConnection() throws IOException {
		// 连接资源
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(10000);
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept-Encoding", "identity");

		connection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.3) Gecko/2008092510 Ubuntu/8.04 (hardy) Firefox/3.0.3");
		connection.setRequestProperty("Accept-Language", "en-us,en;q=0.7,zh-cn;q=0.3");
		connection.setRequestProperty("Accept-Encoding", "aa");
		connection.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
		connection.setRequestProperty("Keep-Alive", "300");
		connection.setRequestProperty("Connection", "keep-alive");
		connection.setRequestProperty("If-Modified-Since", "Fri, 02 Jan 2009 17:00:05 GMT");
		connection.setRequestProperty("If-None-Match", "\"1261d8-4290-df64d224\"");
		connection.setRequestProperty("Cache-Control", "max-age=0");
		connection.setRequestProperty("Referer", "http://www.skycn.com/soft/14857.html");

		return connection;
	}

	/**
	 * 下载文件
	 */
	public void download() throws Exception {
		HttpURLConnection connection = getHttpURLConnection();
		int code = connection.getResponseCode();
		if (code == 200) {
			// 获取资源大小
			int connectionLength = connection.getContentLength();
			System.out.println(connectionLength);
			if (connectionLength < 0) {
				throw new Exception("======file resource size=0=========");
			}

			/*
			 * 将下载任务分配给每个线程
			 */
			int blockSize = connectionLength / threadCount;// 计算每个线程理论上下载的数量.
			for (int threadId = 0; threadId < threadCount; threadId++) {// 为每个线程分配任务
				int startIndex = threadId * blockSize; // 线程开始下载的位置
				int endIndex = (threadId + 1) * blockSize - 1; // 线程结束下载的位置
				if (threadId == (threadCount - 1)) { // 如果是最后一个线程,将剩下的文件全部交给这个线程完成
					endIndex = connectionLength - 1;
				}

				new DownloadThread(threadId, startIndex, endIndex).start();// 开启线程下载

			}
		}

	}

	// 下载的线程
	private class DownloadThread extends Thread {

		private int threadId;
		private int startIndex;
		private int endIndex;

		public DownloadThread(int threadId, int startIndex, int endIndex) {
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		public void run() {
			try {
				// 加载下载位置的文件
				File downThreadFile = new File(targetFilePath, "downThread_" + threadId + ".dt");
				RandomAccessFile downThreadStream = null;
				if (downThreadFile.exists()) {// 如果文件存在
					downThreadStream = new RandomAccessFile(downThreadFile, "rwd");
					String startIndex_str = downThreadStream.readLine();
					if (null == startIndex_str || "".equals(startIndex_str)) { // 网友
																				// imonHu
																				// 2017/5/22
						// this.startIndex = startIndex;
					} else {
						this.startIndex = Integer.parseInt(startIndex_str) - 1;// 设置下载起点
					}
				} else {
					downThreadStream = new RandomAccessFile(downThreadFile, "rwd");
				}

				HttpURLConnection connection = getHttpURLConnection();

				// 设置分段下载的头信息。 Range:做分段数据请求用的。格式: Range bytes=0-1024 或者
				// bytes:0-1024
				connection.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);

				System.out.println("线程_" + threadId + "的下载起点是 " + startIndex + "  下载终点是: " + endIndex);

				if (connection.getResponseCode() == 206) {// 200：请求全部资源成功，
															// 206代表部分资源请求成功
					InputStream inputStream = connection.getInputStream();// 获取流
					RandomAccessFile randomAccessFile = new RandomAccessFile(
							new File(targetFilePath, getFileName(connection.getURL())), "rw");// 获取前面已创建的文件.
					randomAccessFile.seek(startIndex);// 文件写入的开始位置.

					/*
					 * 将网络流中的文件写入本地
					 */
					byte[] buffer = new byte[1024];
					int length = -1;
					int total = 0;// 记录本次下载文件的大小
					while ((length = inputStream.read(buffer)) > 0) {
						randomAccessFile.write(buffer, 0, length);
						total += length;
						/*
						 * 将当前现在到的位置保存到文件中
						 */
						downThreadStream.seek(0);
						downThreadStream.write((startIndex + total + "").getBytes("UTF-8"));
					}

					downThreadStream.close();
					inputStream.close();
					randomAccessFile.close();
					cleanTemp(downThreadFile);// 删除临时文件
					System.out.println("线程" + threadId + "下载完毕");
				} else {
					System.out.println("响应码是" + connection.getResponseCode() + ". 服务器不支持多线程下载");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	// 删除线程产生的临时文件
	private synchronized void cleanTemp(File file) {
		file.delete();
	}

	// 获取下载文件的名称
	private String getFileName(URL url) {
		String filename = url.getFile();
		return filename.substring(filename.lastIndexOf("/") + 1);
	}

	public static void main(String[] args) {
		try {

			new MuchThreadDown("http://kkkk9.bb149.com/z2366.mp4", "d://ss//", 10).download();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}