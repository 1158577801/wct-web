package com.cn.wct.coreUtil;


import com.cn.wct.data.CloneUtil;
import com.jfinal.plugin.redis.Redis;


/**
 * Cache基础类
 * 封装Redis，提供get/put/del方法
 * @author yanshi
 */
public class BaseCache {

	/** 本系统默认CacheName-空闲30Min超时,60Min有效,最少使用策略 **/
	public static final String SYS = "sysCache";
	/** Service CacheName-30s有效,最近最少使用策略 **/
	public static final String SER = "serviceCache";

	// 克隆数据，防止cache对象被引用
	private static Object getCache(String cacheName, String key) {
		try {
			return CloneUtil.clone(Redis.use(cacheName).get(key));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// System Cache方法
	public static Object get(String key) {
		return getCache(SYS, key);
	}

	public static void put(String key, Object value) {
		Redis.use(SYS).set(key,value);
	}

	public static void del(String key) {
		Redis.use(SYS).del(key);
	}

	// Service Cache方法
	public static Object getSer(String key) {
		return getCache(SER, key);
	}

	public static void putSer(String key, Object value) {
		Redis.use(SER).set(key,value);
	}

	public static void delSer(String key) {
		Redis.use(SER).del(key);
	}
}
