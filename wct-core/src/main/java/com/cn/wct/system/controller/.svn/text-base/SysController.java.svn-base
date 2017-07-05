package com.cn.wct.system.controller;

import java.io.IOException;

import com.cn.wct.coreUtil.BaseController;
import com.cn.wct.data.HttpUrlDownloadUtil;

public class SysController extends BaseController {
	
	public void downLoadFromUrl() throws IOException {
		HttpUrlDownloadUtil.downLoadFromUrl(
						"http://yule.rmzxb.com.cn/upload/resources/image/2017/01/05/1458017_500x500.jpg",
						"1458017_500x500.jpg", getResponse());
		renderNull();
	}
}
