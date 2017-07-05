package com.cn.wct.data;

import java.util.UUID;

/**
 * 
 * 项目名称：tissue-base 类名称：UUIDUtil 类描述： 创建人：严壹 创建时间：2015年10月15日 上午9:12:44
 * 修改人：DefaultAccount 修改时间：2015年10月15日 上午9:12:44 修改备注：
 * 
 * @version
 * 
 */
public class UUIDUtil {
	/**
	 * UUID,去掉中间-
	 * @param 
	 * @return 
	 * @author wangzhen
	 */
	public static String getUUID() {
		 return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
