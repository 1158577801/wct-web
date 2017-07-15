package com.cn.wct.data;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String[] args) {
		Vector<String> fileList=new Vector<String>();
		/*for(int i=1855;i<1862;i++){
			fileList.add("p"+i+".mp4");
		}*/
		fileList.add("1.jpg");
		fileList.add("2.jpg");
		fileList.add("3.jpg");
		fileList.add("4.jpg");
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);  
		for(int i=0;i<fileList.size();i++){
			executorService.execute(new HttpDownloadThread(fileList, "http://localhost:8080/downLoadImage/images/", "D:\\图片\\"));
		}
		executorService.shutdown();;
	}
}
