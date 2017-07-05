package com.cn.wct.data;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


/**
 * @author wangzhen
 * 2015年10月24日 下午7:52:24
 * description 【Properties读写 】
 */
public class PropertiesUtil {
	/**
	 * 读properties
	 * @param filePath 文件路径
	 * @return 
	 */
	public static Map<String, String> readProperties(String filePath){
		Map<String, String> resMap=new HashMap<String, String>();
		try {
			InputStream in = new BufferedInputStream (new FileInputStream(filePath));
			Properties prop = new Properties();    
			prop.load(in);///加载属性列表
			Iterator<String> it=prop.stringPropertyNames().iterator();
			while(it.hasNext()){
			      String key=it.next();
			      resMap.put(key, prop.getProperty(key));     
			  }
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return resMap;
	}
	
	public static Map<String, String> readProperties(String filePath,String autofilePath){
	
		Map<String, String> resMap=new HashMap<String, String>();
		try {
			InputStream in = new BufferedInputStream (new FileInputStream(filePath));
			InputStream in2 =new BufferedInputStream (new FileInputStream(autofilePath));
			Properties prop = new Properties();  
			Properties prop2 =new Properties(); 
			prop.load(in);///加载属性列表
			prop2.load(in2);
			Iterator<String> it=prop.stringPropertyNames().iterator();
			Iterator<String> it2=prop2.stringPropertyNames().iterator();
			while(it.hasNext()){
			      String key=it.next();
			      resMap.put(key, prop.getProperty(key));     
			  }
			in.close();
			
			while(it2.hasNext()){
			      String key=it2.next();
			      resMap.put(key, prop2.getProperty(key));     
			  }
			in2.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return resMap;
	}
	/**
	 * 写入配置文件
	 * @param filePath 文件路径
	 * @param prop 
	 * @param comments 
	 * @return
	 */
	public static boolean wirteProperties(String filePath,Properties prop,String comments,boolean bl){
		try {
			OutputStream oFile = new FileOutputStream(filePath,bl);
			prop.store(oFile, comments);
			oFile.flush();
			oFile.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	/**
     * 写入配置文件 liuyang
     * @param filePath 文件路径
     * @param parameterKey 主键
     * @param parameterValue 值
     */
    public static boolean wirteProperties(String filePath, String parameterKey, String parameterValue) {
        Properties prop = new Properties();
        try {
               InputStream fis = new FileInputStream(filePath);
               //从输入流中读取属性列表（键和元素对）
               prop.load(fis);
               //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
               //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
               OutputStream fos = new FileOutputStream(filePath);
               prop.setProperty(parameterKey, parameterValue);
               //以适合使用 load 方法加载到 Properties 表中的格式，
               //将此 Properties 表中的属性列表（键和元素对）写入输出流
               prop.store(fos, "Update '" + parameterKey + "' value");
               fos.flush();
               fis.close();
               fos.close();
               return true;
           } catch (IOException e) {
               e.printStackTrace();
               return false;
           }
    }
    
	/**
     * 从配置文件中获得原有属性
     * @param filePath 文件路径
     * @return 配置信息
     */
    public static Properties getPropObjFromFile(String filePath) {
        Properties objProp = new Properties();
        File file = new File(filePath);
        InputStream inStream;
        try {
            inStream = new FileInputStream(file);
            objProp.load(inStream);
            inStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objProp;
    }
    /**
     * 从配置文件中得到属性值
     * @param fileName 文件名常量
     * @param key 键名
     * @return 属性值
     */
    public static String getPropValue(String fileName, String key) {
    	try {
//          String workPath = PropertieUtil.class.getResource("/").getPath();
//          workPath = URLDecoder.decode(workPath);
          Properties prop = getPropObjFromFile(fileName);
          return prop.getProperty(key);
		} catch (Exception e) {
			//e.printStackTrace();
			return "";
		}
    }
}
