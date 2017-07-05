package com.cn.wct.http;

import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * 工具类【JSON串 与 Java对象之间转换】
 * 
 * @author GuoYF
 * @version 1.0
 */
public class Json2JavaUtil {

	protected static Log log = LogFactory.getLog(Json2JavaUtil.class);

    /**
     * Java对象 转 Json串
     * 
     * @param showNull 空值的字段是否输出，默认为输出
     *        空=输出空值 与 空串
     *        1=不输出空值，但输出空串
     *        2=不输出空值，也不输出空串
     *        
     */
	public static String java2Json(Object obj, Integer showNull) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		//设置序列化配置(全局),设置序列化时不输出空值.
		if(showNull != null) {
			if(showNull == 1) {
				mapper.setSerializationInclusion(Inclusion.NON_NULL);
			} else if(showNull == 2) {
				mapper.setSerializationInclusion(Inclusion.NON_EMPTY);
			}
		}
		//输出格式化后的字符串(有性能损耗)
//		mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);

		return mapper.writeValueAsString(obj);
	}

    /**
     * Json串 转 Java对象
     */
    public static Object Json2Java(String jsonStr, Class clazz) throws Exception {

    	ObjectMapper mapper = new ObjectMapper();
    	//解析器支持解析单引号
    	mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
    	//解析器支持解析结束符
    	mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    	return mapper.readValue(jsonStr, clazz);
    }
    
    public static <T> List<T> Json2List(String jsonStr, Class T) throws Exception {
        JSONArray jsonarray = JSONArray.fromObject(jsonStr); 
        List<T> list = JSONArray.toList(jsonarray, T); 
        return list;
    }
}
