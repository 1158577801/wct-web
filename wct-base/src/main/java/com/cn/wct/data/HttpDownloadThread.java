package com.cn.wct.data;

import java.util.Vector;

import org.apache.commons.collections.CollectionUtils;

public class HttpDownloadThread extends Thread{
	volatile Vector<String> downLoadList;//下载List

	String downLoadUrl;
	String savePath;
	public HttpDownloadThread(Vector<String> downLoadList,String downLoadUrl,String savePath){
		this.downLoadList=downLoadList;
		this.downLoadUrl=downLoadUrl;
		this.savePath=savePath;
	}

	@Override
	public  void run() {
		
			 if(CollectionUtils.isNotEmpty(downLoadList)){
					String fileName=downLoadList.firstElement();;
					try {
						HttpDownload.download(downLoadUrl+fileName, savePath+fileName);
						System.out.println(Thread.currentThread().getName()+"=============downLoad success======================="+downLoadUrl+fileName);
						downLoadList.remove(fileName);
						sleep(1000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.err.println("=============downLoad error======================="+downLoadUrl+fileName);
					}
				}
	
	}

}
