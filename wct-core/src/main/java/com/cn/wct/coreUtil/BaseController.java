package com.cn.wct.coreUtil;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;


/**
 * @author wangzhen   time 2015年10月14日 下午4:38:06  description 【 公共base】
 *
 */
public class BaseController extends Controller{
	/**
	 * Record+Db模式，json分页查询
	 * @param Page<Record> record
	 * @return Map<String, Object>
	 */
	public Map<String, Object> dbOrPage(Page<Record> record){
		Map<String, Object> data=new HashMap<String, Object>();
		if(null!=record){
			data.put("total",record.getTotalRow());
			data.put("rows", record.getList());
			data.put("page",record.getPageNumber());
		}
		return data;
	}
	
	/**
	 * 成功操作返回
	 * @param message 返回提示
	 * @param value 返回值
	 * @return Result
	 */
	public Result successResult(String message , Object value){
		return new Result(message,value); 
	}
	
	/**
	 * 失败操作返回
	 * @param message 返回提示
	 * @return Result
	 */
	public Result errorResult(String message){
		return new Result(message); 
	}
	
	/**操作提示
	 * @param true 成功  flase 失败
	 * @return
	 */
	public void operateMessage(boolean bl){
		if(bl){
			renderJson(new Result());
		}else{
			renderJson(new Result("操作失败"));
		}
	}
	public void operateMessage(int bl){
		if(bl>0){
			renderJson(new Result());
		}else{
			renderJson(new Result("操作失败"));
		}
	}
}
