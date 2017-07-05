package com.cn.wct.system.controller;

import com.cn.wct.coreUtil.BaseController;
import com.cn.wct.system.configRoute.SysConstant;

public class IndexController extends BaseController{

	public void index(){
		setSessionAttr(SysConstant.CACHE_KEY, "sss");
		renderJsp("index.jsp");
	}
}
