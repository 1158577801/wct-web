package com.cn.wct.coreUtil;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.cn.wct.data.CloneUtil;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.redis.Redis;

@SuppressWarnings("rawtypes")
public class BaseModel<M extends Model> extends Model<M> {

	private static final long serialVersionUID = 1702469565872353932L;

	public static final int DELETE_FLAG_YES = 1;
	public static final int DELETE_FLAG_NO = 0;

	@Override
	public List<M> findByCache(String cacheName, Object key, String sql) {
		try {
			List<M> result = Redis.use(cacheName).get(key);
			if (result == null) {
				result = find(sql);
				Redis.use(cacheName).set(key, result);
			}
			return CloneUtil.clone(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<M> findByCache(String cacheName, Object key, String sql,
			Object... paras) {
		try {
			List<M> result = Redis.use(cacheName).get(key);
			if (result == null) {
				result = find(sql, paras);
				Redis.use(cacheName).set(key, result);
			}
			return CloneUtil.clone(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询当前Model所有数据
	 * 
	 * @return
	 */
	public List<M> queryAll() {
		return super.find("select * from "
				+ this.getClass().getSimpleName().toLowerCase());
	}

	/**
	 * 查询当前Model所有数据并Cache
	 * 
	 * @return
	 */
	public List<M> queryAllByCache() {
		String sql = "select * from "
				+ this.getClass().getSimpleName().toLowerCase();
		return findByCache(BaseCache.SER, sql, sql);
	}

	/**
	 * 根据主键获取对象(cache 3 min)
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public M getByCache(Object id) {
		String key = this.getClass().getSimpleName() + "_" + id;
		// get by cache
		M me = (M) BaseCache.getSer(key);
		if (me == null) {
			// get by db
			me = super.findById(id);
			if (me != null) {
				// add to service cache
				BaseCache.putSer(key, me);
			}
		}
		try {
			return CloneUtil.clone(me);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询自动缓存
	 * 
	 * @param sql
	 * @return
	 */
	public List<M> queryByCache(String sql) {
		// 查询SQL作为Key值
		return findByCache(BaseCache.SER, sql, sql);
	}

	/**
	 * 查询自动缓存
	 * 
	 * @param sql
	 * @param paras
	 * @return
	 */
	public List<M> queryByCache(String sql, Object... paras) {
		// sql_xx_xx_xx
		String key = sql;
		for (Object obj : paras) {
			key += "_" + obj.toString();
		}
		return findByCache(BaseCache.SER, key, sql, paras);
	}

	/**
	 * 缓存查询第一项
	 * 
	 * @param sql
	 * @return
	 */
	public M queryFisrtByCache(String sql) {
		List<M> list = queryByCache(sql);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 缓存查询第一项
	 * 
	 * @param sql
	 * @param paras
	 * @return
	 */
	public M queryFisrtByCache(String sql, Object... paras) {
		List<M> list = queryByCache(sql, paras);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 缓存分页查询
	 * 
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            数量
	 * @param select
	 *            查询前缀
	 * @param sqlExceptSelect
	 *            查询条件
	 * @return
	 */
	public Page<M> pagerByCache(int pageNumber, int pageSize, String select,
			String sqlExceptSelect) {
		String key = select + sqlExceptSelect + "_" + pageNumber + "_"
				+ pageSize;
		try {
			return CloneUtil.clone(super.paginateByCache(BaseCache.SER, key,
					pageNumber, pageSize, select, sqlExceptSelect));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 缓存分页查询
	 * 
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            数量
	 * @param select
	 *            查询前缀
	 * @param sqlExceptSelect
	 *            查询条件
	 * @param paras
	 *            SQL参数
	 * @return
	 */
	public Page<M> pagerByCache(int pageNumber, int pageSize, String select,
			String sqlExceptSelect, Object... paras) {
		String key = select + sqlExceptSelect + "_" + pageNumber + "_"
				+ pageSize;
		for (Object obj : paras) {
			key += "_" + obj.toString();
		}
		try {
			return CloneUtil.clone(super.paginateByCache(BaseCache.SER, key,
					pageNumber, pageSize, select, sqlExceptSelect, paras));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 是否存在
	 * 
	 * @param sql
	 * @param paras
	 * @return
	 */
	public boolean isExist(String sql, Object... paras) {
		String configName = DbKit.getConfig(this.getClass()).getName();
		long count = Db.use(configName).queryLong(sql, paras);
		if (count != 0) {
			return true;
		}
		return false;
	}

	/**
	 * Db模式查询
	 * 
	 * @param
	 * @return
	 * @author wangzhen
	 */
	public List<Record> findByCacheDb(String cacheName, Object key, String sql) {
		try {
			List<Record> result = Redis.use(cacheName).get(key);
			if (result == null) {
				result = Db.find(sql);
				Redis.use(cacheName).set(key, result);
			}
			return CloneUtil.clone(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Db模式查询
	 * 
	 * @param
	 * @return
	 * @author wangzhen
	 */
	public List<Record> findByCacheDb(String cacheName, Object key, String sql,
			Object... paras) {
		try {
			List<Record> result = Redis.use(cacheName).get(key);
			if (result == null) {
				result = Db.find(sql, paras);
				Redis.use(cacheName).set(key, result);
			}
			return CloneUtil.clone(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
