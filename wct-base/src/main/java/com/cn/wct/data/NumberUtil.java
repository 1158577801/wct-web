package com.cn.wct.data;
/**
 * 数值工具
 * @author  朱国俊
 * @version 2012-12-13
 * @since   JDK1.6
 */
public final class NumberUtil {
    public static final String ILLEGAL_CAl = "nan";
    public static final String ILLEGAL_CAl_OTHER = "infinity";
    /**
     * 构造方法
     */
    private NumberUtil() { }

    /**
     * 数值类型转换
     * @param value 待转换值
     * @return Double 数值
     */
    public static Double integerToDouble(Integer value) {
        try {
			return Double.parseDouble(String.valueOf(value));
		} catch (Exception e) {
			return null;
		}
    }
    
    /**
     * 数值类型转换
     * @param value 待转换值
     * @return Double 数值
     */
    public static Double floatToDouble(Float value) {
        try {
			return Double.parseDouble(String.valueOf(value));
		} catch (Exception e) {
			return null;
		}
    }
    
    /**
     * 数值类型转换
     * @param value 待转换值
     * @return Double 数值
     */
    public static Double getDouble(Object value) {
        try {
			return Double.parseDouble(String.valueOf(value));
		} catch (Exception e) {
			return null;
		}
    }

    /**
     * 数值类型转换
     * @param value 待转换值
     * @return Double 数值
     */
    public static Integer getInteger(Object value) {
        try {
        	String str = String.valueOf(value);
        	if (str.endsWith(".0")) {
        		str = str.substring(0, str.indexOf(".0"));
        	}
			return Integer.parseInt(str);
		} catch (Exception e) {
			return null;
		}
    }

    /**
     * 非法字符计算
     * @designer 高占 2013-7-30
     * @developer 高占
     * @modify
     * @param num 字符计算结果
     * @return 是否非法
     */
    public static boolean  illegalNumber(Number num) {
        if (String.valueOf(num).toLowerCase().equals(NumberUtil.ILLEGAL_CAl_OTHER) || String.valueOf(num).toLowerCase().equals(NumberUtil.ILLEGAL_CAl)) {
            return true;
        }
        return false;
    }
}
