package com.cn.wct.encrypt;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class SignUtil {
		//签名实现
		public static String createSign(Map<String, Object> params, boolean encode,String md5Key)
	             {
	        Set<String> keysSet = params.keySet();
	        Object[] keys = keysSet.toArray();
	        Arrays.sort(keys);
	        StringBuffer temp = new StringBuffer();
	        boolean first = true;
	        for (Object key : keys) {
	            if (first) {
	                first = false;
	            } else {
	                temp.append("&");
	            }
	            temp.append(key).append("=");
	            Object value = params.get(key);
	            String valueString = "";
	            if (null != value) {
	                valueString = String.valueOf(value);
	            }
	            if (encode) {
	                try {
						temp.append(URLEncoder.encode(valueString, "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            } else {
	                temp.append(valueString);
	            }
	        }

	        return Md5Util.hmacSign(temp.toString(),md5Key).toUpperCase();
	    }
}
