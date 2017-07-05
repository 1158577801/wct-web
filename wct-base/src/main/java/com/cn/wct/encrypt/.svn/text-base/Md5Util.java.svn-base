/*
 * Md5Util.java    2012-12-28
 * 上海生物信息技术研究中心拥有完全的版权
 * 使用者必须经过许可
 */
package com.cn.wct.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * MD5加密类
 *
 * @author 王冬辉
 * @version 2012-12-28 下午04:50:10
 * @since JDK1.6
 */
public final class Md5Util {

    public static void main( String[] args )
    {
    	String inputStr = "2222";
    	System.out.println( Md5Util.hmacSign(inputStr,"12345678"));//自定义Key
        System.out.println(Md5Util.md5Encode("admin"));
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String ENCODE = "UTF-8";  //GBK
    public static String hmacSign(String aValue, String aKey)
    {
        byte k_ipad[] = new byte[64];
        byte k_opad[] = new byte[64];
        byte keyb[];
        byte value[];
        try
        {
            keyb = aKey.getBytes(ENCODE);
            value = aValue.getBytes(ENCODE);
        }
        catch(UnsupportedEncodingException e)
        {
            keyb = aKey.getBytes();
            value = aValue.getBytes();
        }
        Arrays.fill(k_ipad, keyb.length, 64, (byte)54);
        Arrays.fill(k_opad, keyb.length, 64, (byte)92);
        for(int i = 0; i < keyb.length; i++)
        {
            k_ipad[i] = (byte)(keyb[i] ^ 0x36);
            k_opad[i] = (byte)(keyb[i] ^ 0x5c);
        }

        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance("MD5");
        }
        catch(NoSuchAlgorithmException e)
        {
         e.printStackTrace();
            return null;
        }
        md.update(k_ipad);
        md.update(value);
        byte dg[] = md.digest();
        md.reset();
        md.update(k_opad);
        md.update(dg, 0, 16);
        dg = md.digest();
        return byteArrayToHexString(dg);
    }


    

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 将字节数组转换为十六进制字符串
     * @designer 高占 2013-3-12
     * @developer 高占
     * @modify
     * @param b MD5字节
     * @return 加密字段
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            int a = b[i];
            if (a < 0) {
                a += 256;
            }
            if (a < 16) {
                resultSb.append("0");
            }
            resultSb.append(Integer.toHexString(a));
        }
        return resultSb.toString();
    }

    /**
     * 将字段转为MD5字节
     * @designer 高占 2013-3-12
     * @developer 高占
     * @modify
     * @param origin 要转换的字符串
     * @return MD5字节
     */
    public static String md5Encode(String origin) {
        String resultString = null;

        try {
            resultString = new String(origin);
            // MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString
                    .getBytes("UTF-8")));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return resultString;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
