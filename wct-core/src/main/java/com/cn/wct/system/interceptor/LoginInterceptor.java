package com.cn.wct.system.interceptor;

import java.util.ArrayList;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.cn.wct.system.configRoute.SysConstant;

/**
 * 
 * @author wangzhen   time 2015-12-1 上午11:02:11  description 【登陆拦截器 】
 */
public class LoginInterceptor implements Interceptor {
	
	private static ArrayList<String> urls = new ArrayList<String>();
	static{
		urls.add("/");
		urls.add("/login");
		urls.add("/log/querySystemErrorLog");
	}
	public void intercept(Invocation inv) {
		String actionKey=inv.getActionKey();
		if (urls.contains(inv.getActionKey())) {
			inv.invoke();
			return;
		}
		Controller controller=inv.getController();
    	// 获取登录用户的角色
		Record record =controller.getSessionAttr(SysConstant.CACHE_KEY);
		if (record == null) {
			inv.getController().redirect("/index");
			return;	
		}
		inv.invoke();
	}
}