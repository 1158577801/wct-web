package com.cn.wct;

import com.cn.wct.system.configRoute.SysRoute;
import com.cn.wct.system.interceptor.ExceptionLogInterceptor;
import com.cn.wct.system.interceptor.LoginInterceptor;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.json.Json;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByMethodRegex;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;

import net.sf.json.JSON;

/**
 * @author wct
 * 
 */
public class TissueConfig extends JFinalConfig {

	/*
	 * 定义路由
	 */
	public void configRoute(Routes me) {
		new SysRoute(me);
	}

	/*
	 * 定义全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		// 登录验证
		 me.add(new LoginInterceptor());
		// 全局异常拦截
		 me.addGlobalActionInterceptor(new ExceptionLogInterceptor());
		// TxByRegex拦截器可通过传入正则表达式对action进行拦截，当actionKey被匹配时开启事务
		 me.add(new TxByMethodRegex("(.*save.*|.*update.*|.*delete.*)")); 
	}

	@Override
	public void afterJFinalStart() {

		super.afterJFinalStart();
	}

	public void configPlugin(Plugins plugins) {
		Prop dataSource=PropKit.use("dataSource.properties");
		C3p0Plugin C3p0Plugin = new C3p0Plugin(dataSource.get("url"),dataSource.get("username"),dataSource.get("password"));
		plugins.add(C3p0Plugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
		plugins.add(arp);
		
		// 添加redis插件
		Prop redis=PropKit.use("redis.properties");
		RedisPlugin redisPlugin = new RedisPlugin(redis.get("cacheName"), redis.get("host"), redis.getInt("port"));
		plugins.add(redisPlugin);

	}

	@Override
	public void configConstant(Constants constants) {
		constants.setEncoding("UTF-8");
		constants.setDevMode(true);
		constants.setViewType(ViewType.JSP);

	}

	@Override
	public void configHandler(Handlers arg0) {
		// TODO Auto-generated method stub

	}

}
