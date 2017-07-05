package com.cn.wct.system.interceptor;

import org.apache.log4j.Logger;

import com.cn.wct.coreUtil.Result;
import com.cn.wct.exception.BusinessException;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class ExceptionLogInterceptor implements Interceptor {

	/**
	 * 获取具体执行controller的日记记录器
	 * 
	 * @param inv
	 * @return
	 * @author zhouzb
	 */
	private final Logger getLogger(Invocation inv) {
		return Logger.getLogger(inv.getController().getClass());
	}

	/**
	 * 记录方法抛错日志
	 * 
	 * @param inv
	 * @param loginfo
	 * @author zhouzb
	 */
	private final void doLogError(Invocation inv, Exception e) {

		getLogger(inv).error(e);
	}

	@Override
	public void intercept(Invocation inv) {
		try {
			inv.invoke();
		} catch (Exception e) {

			doLogError(inv, e);

			e.printStackTrace();

			Result r = new Result(Result.SYS_ERRPR);
			if (e instanceof BusinessException)
				r.setMessage(e.getMessage());
			inv.getController().renderJson(r);

		}
	}
}
