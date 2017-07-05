package com.cn.wct.data;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class HttpUrlDownloadUtil {
	/*
	 * 从网络Url中下载文件
	 * 
	 * @param urlStr
	 * 
	 * @param fileName
	 * 
	 * @param savePath
	 * 
	 * @throws IOException
	 */
	public static void downLoadFromUrl(String urlStr, String fileName,
			String savePath) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setConnectTimeout(3 * 1000);

		conn.setRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

		InputStream inputStream = conn.getInputStream();

		byte[] getData = readInputStream(inputStream);

		File saveDir = new File(savePath);
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}
		File file = new File(saveDir + File.separator + fileName);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(getData);
		if (fos != null) {
			fos.close();
		}
		if (inputStream != null) {
			inputStream.close();
		}

		System.out.println("info:" + url + " download success");

	}

	/**
	 * 浏览器直接下载
	 * 
	 * @param urlStr
	 * @param fileName
	 * @param response
	 * @throws IOException
	 */
	public static void downLoadFromUrl(String urlStr, String fileName,
			HttpServletResponse response) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		if (200 != conn.getResponseCode()) {
			System.out.println("info:" + url + " download responseCode="
					+ conn.getResponseCode());
			return;
		}
		// 设置超时间为8秒
		conn.setConnectTimeout(8 * 1000);
		// 防止屏蔽程序抓取而返回403错误
		// conn.setRequestProperty("User-Agent",
		// "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		try { // /把响应头设置成一样的.
			for (Map.Entry<String, List<String>> m : conn.getHeaderFields()
					.entrySet()) {
				if (m != null && m.getKey() != null && m.getValue() != null
						&& m.getValue().size() > 0) {
					response.setHeader(m.getKey(), m.getValue().get(0));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

		// 得到输入流
		InputStream inputStream = conn.getInputStream();

		// response.addHeader("Content-Disposition",
		// "attachment;filename="+fileName);

		int b;
		OutputStream out = response.getOutputStream();

		while ((b = inputStream.read()) != -1) {
			out.write(b);
		}
		if (null != inputStream) {
			inputStream.close();
		}
		if (null != out) {
			out.close();
		}
		System.out.println("info:" + url + " download success");

	}

	/**
	 * 从输入流中获取字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	public static void main(String[] args) {
		try {
			downLoadFromUrl("http://kele.bb149.com/vod_water/z2083.mp4",
					"z2083.mp4", "d:/");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
