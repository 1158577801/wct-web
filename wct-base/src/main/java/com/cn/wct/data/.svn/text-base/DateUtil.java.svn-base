package com.cn.wct.data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
/**
 * 日期工具类
 * @author 沈海生
 * @version 2011-4-12 下午09:49:56
 * @since JDK1.6
 */
public final class DateUtil {

    /**
     * 构造函数
     */
    private DateUtil() { }

    /**
     * 获取偏倚后的日期
     * @param day
     *            日期
     * @param offset
     *            偏移天数
     * @return Date
     */
    public static Date changeDay(Date day, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.DAY_OF_YEAR,
                (calendar.get(Calendar.DAY_OF_YEAR) + offset));
        return calendar.getTime();
    }

    /**
     * 通过出生日期获得年龄
     * @param string
     *            出生日期
     * @return 年龄
     */
    public static Integer getAgeByBirth(String string) {
        Date birthDate = stringToDate(string);
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);
        Calendar thisyear = Calendar.getInstance();
        thisyear.setTime(new Date());
        return thisyear.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
    }

    /**
     * 获取指定日期的指定时间
     * @param day
     *            日期
     * @param givenTime
     *            时间，24小时制
     * @return Date
     */
    public static Date getGivenTimeInDay(Date day, int givenTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, givenTime);
        return calendar.getTime();
    }

    /**
     * 获得指定某天后的指定时间天数的时间点
     * @param calendar
     *            Calendar
     * @param appoint
     *            指定时间
     * @param hour
     *            小时
     * @param minute
     *            分
     * @param second
     *            秒
     * @return Calendar
     */
    public static Calendar getAppointDateAfterOneDate(Calendar calendar,
            int appoint, int hour, int minute, int second) {
        calendar = Calendar.getInstance();
        if (appoint != 0) {
            calendar.add(Calendar.DATE, appoint);
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar;
    }

    /**
     * 获得指定某天后的指定时间天数的时间点
     * @param date
     *            日期
     * @param appoint
     *            指定时间
     * @param hour
     *            小时
     * @param minute
     *            分
     * @param second
     *            秒
     * @return Date
     */
    public static Date getAppointDateAfterOneDate(Date date, int appoint,
            int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        if(null==date){
        	return null;
        }
        calendar.setTime(date);
        if (appoint != 0) {
            calendar.add(Calendar.DATE, appoint);
        }
        if(hour<0){
        	  calendar.set(Calendar.HOUR_OF_DAY, hour);
        }
        if(minute<0){
         	calendar.set(Calendar.MINUTE, minute);
        }
        if(second<0){
        	 calendar.set(Calendar.SECOND, second);
        }
       
        return calendar.getTime();
    }

    /**
     * String转换Date
     * @param str
     *            字符串
     * @return 时间对象
     */
    public static Date stringToDate(String str) {
        String strFormat = null;
        SimpleDateFormat sdFormat = null;
        Date result = null;
        if (str == null || str.trim() == null) {
            return null;
        }
        final int length = 10;
        if (str.indexOf("-") != -1) {
        	str = str.replaceAll("T", " ");//GetDiagnosisInfo接口中的日期格式为‘2010-04-30T16:01:31’，需要替换T
            strFormat = "yyyy-MM-dd hh:mm:ss";
            if (str != null && str.length() <= length) {
                strFormat = "yyyy-MM-dd";
            }
            sdFormat = new SimpleDateFormat(strFormat);
            try {
                result = sdFormat.parse(str);
            } catch (Exception e) {
                result = null;
            }
        } else if (str.indexOf("/") != -1) {
            strFormat = "yyyy/MM/dd hh:mm:ss";
            if (str != null && str.length() <= length) {
                strFormat = "yyyy/MM/dd";
            }
            sdFormat = new SimpleDateFormat(strFormat);
            try {
                result = sdFormat.parse(str);
            } catch (Exception e) {
                result = null;
            }
        } else {
            strFormat = "yyyyMMdd";
            sdFormat = new SimpleDateFormat(strFormat);
            try {
                result = sdFormat.parse(str);
            } catch (Exception e) {
                result = null;
            }
        }
        return result;
    }

    /**
     * String转换Date
     * @param str
     *            字符串
     * @return 时间对象
     */
    public static Date stringToDate2(String str) {
        String strFormat = null;
        SimpleDateFormat sdFormat = null;
        Date result = null;
        if (str == null) {
            return null;
        }
        if (str.indexOf("/") != -1) {
            final int length = 10;
            strFormat = "yyyy/MM/dd hh:mm:ss";
            if (str != null && str.length() <= length) {
                strFormat = "yyyy/MM/dd";
            }
            sdFormat = new SimpleDateFormat(strFormat);
            if (str != null) {
                try {
                    result = sdFormat.parse(str);
                } catch (Exception e) {
                    result = null;
                }
            }
            return result;
        } else {
            strFormat = "yyyyMMdd";
            sdFormat = new SimpleDateFormat(strFormat);
            if (str != null) {
                try {
                    result = sdFormat.parse(str);
                } catch (Exception e) {
                    result = null;
                }
            }
            return result;
        }
    }

    /**
     * String转换8位的
     * @param str
     *            字符串
     * @return 时间对象
     */
    public static String formatStringTo8(String str) {
        String strFormat = null;
        SimpleDateFormat sdFormat = null;
        Date result = null;
        if (str == null) {
            return null;
        }
        str = str.trim();
        if (str.indexOf("-") != -1) {
            final int length = 10;
            strFormat = "yyyy-MM-dd HH:mm";
            if (str != null && str.length() <= length) {
                strFormat = "yyyy-MM-dd";
            }
            sdFormat = new SimpleDateFormat(strFormat);
            if (str != null) {
                try {
                    result = sdFormat.parse(str);
                } catch (Exception e) {
                    result = null;
                }
            }
        }
        if (result != null) {
            sdFormat = new SimpleDateFormat("yyyyMMdd");
            return sdFormat.format(result);
        } else {
            return str;
        }
    }

    /**
     * 传入的时间与当天时间比较
     * @param sometime1
     *            需要比较的日期1
     * @param sometime2
     *            需要比较的日期2
     * @return 2个日期的相差数
     */
    public static int diff(Date sometime1, Date sometime2) {

        Calendar currentDay = Calendar.getInstance();
        Calendar otherDay = Calendar.getInstance();

        currentDay.setTime(sometime1);
        otherDay.setTime(sometime2);

        if (currentDay.before(otherDay)) {
            return +countDiffDay(currentDay, otherDay) / 1;
        } else {
            return Integer.parseInt("-" + countDiffDay(otherDay, currentDay)) / 1;
        }
    }

    /**
     * 计算出2个日期相差的天数
     * @param c1
     *            日期1
     * @param c2
     *            日期2
     * @return 日期差数
     */
    private static int countDiffDay(Calendar c1, Calendar c2) {
        int returnInt = 0;
        while (!c1.after(c2)) {
            c1.add(Calendar.DAY_OF_MONTH, 1);
            returnInt++;
        }

        if (returnInt > 0) {
            returnInt = returnInt - 1;
        }

        return (returnInt);
    }

    /**
     * 传date为String yyyy-mm-dd
     * @param date 日期
     * @return String
     */
    public static String date2String(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 传date为String yyyy-mm-dd hh:mm:ss
     * @designer 董宁波 2012-12-3
     * @developer 董宁波 2012-12-3
     * @Modified by
     * @param date date
     * @return String
     */
    public static String dateTimeToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 日期补零 把2012-1-1 变成 2012-01-01
     * @param str
     *            日期字符串
     * @return String
     * @throws ParseException
     */
    public static String dateStrAddZero(String str) throws ParseException {
        SimpleDateFormat sdf = null;
        if (str.contains("/")) {
            sdf = new SimpleDateFormat("yyyy/MM/dd");
        } else if (str.contains("-")) {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        return sdf.format(sdf.parse(str));
    }

    /**
     * 比较出院日期，得到初诊日期
     * @param viewAge
     * @param firstViewAge
     * @return
     */
    public static boolean compareViewAge(String viewAge, String firstViewAge) {
        int monthDay = 30;
        int month = 12;
        boolean flag = false;
        if (firstViewAge.contains("天") && !firstViewAge.contains("月")) {
            firstViewAge = firstViewAge.split("天")[0];
        } else if (firstViewAge.contains("月")) {
            firstViewAge = firstViewAge.split("个月")[0];
            int firstViewAges = Integer.parseInt(firstViewAge);
            firstViewAges = Integer.parseInt(firstViewAge.split("个月")[0])
                    * monthDay;
            firstViewAge = String.valueOf(firstViewAges);
        } else if (firstViewAge.contains("岁")) {
            firstViewAge = firstViewAge.split("岁")[0];
            int firstViewAges = Integer.parseInt(firstViewAge);
            firstViewAges = Integer.parseInt(firstViewAge.split("岁")[0])
                    * month * monthDay;
            firstViewAge = String.valueOf(firstViewAges);
        }
        if (viewAge.contains("天") && !viewAge.contains("月")) {
            viewAge = viewAge.split("天")[0];
        } else if (viewAge.contains("月")) {
            viewAge = viewAge.split("个月")[0];
            int firstViewAges = Integer.parseInt(viewAge);
            firstViewAges = Integer.parseInt(viewAge.split("个月")[0]) * monthDay;
            viewAge = String.valueOf(firstViewAges);
        } else if (viewAge.contains("岁")) {
            viewAge = viewAge.split("岁")[0];
            int firstViewAges = Integer.parseInt(viewAge);
            firstViewAges = Integer.parseInt(viewAge.split("岁")[0]) * month
                    * monthDay;
            viewAge = String.valueOf(firstViewAges);
        }
        if (Integer.parseInt(firstViewAge) < Integer.parseInt(viewAge)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 日期转换
     * @designer 王冬辉 2012-11-2
     * @developer
     * @Modified by
     * @param d d
     * @param day day
     * @return date
     */
    public static Date addDate(Date d, long day) {
        long time = d.getTime();
        day = day * 24 * 60 * 60 * 1000;
        time += day;
        return new Date(time);
    }

    /**
     * String转换Date
     * @param str 字符串
     * @return 时间对象
     */
    public static Date stringToDateTypely(String str) {
    	if(StringUtils.isBlank(str)){
    		return null;
    	}
    	try {
    		String strFormat = "";
			if (str.length() == 10) {
				strFormat = "yyyy-MM-dd";
			} else if (str.length() == 17 || str.length() == 16) {
				strFormat = "yyyy-MM-dd HH:mm";
			} else if (str.length() == 19) {
				strFormat = "yyyy-MM-dd HH:mm:ss";
			}
			SimpleDateFormat sdFormat = new SimpleDateFormat(strFormat);
			return sdFormat.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    /**
     * value转换Date
     * @param value 值
     * @return 时间对象
     */
    public static Date getDate(Object value) {
    	Date dateValue = null;
    	if (value instanceof Date) {
    		dateValue = (Date) value;
    	} else if (value instanceof String) {
    		dateValue = DateUtil.stringToDateTypely((String) value);
    	}
    	return dateValue;
    }

    /**
     * 转换date为string
     * @param date 日期时间
     * @param pattern 格式
     * @return String
     */
    public static String dateToString(Date date, String pattern) {
    	if(null==date){
    		return ""; 
    	}
    	try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
		} catch (Exception e) {
            e.printStackTrace();
            return "";
		}
    }

    /**
     * 计算当前季度的开始和结束时间
     * @return Date
     * @param beginOrEnd 开始或结束
     */
    public static Date getThisSeasonTime(boolean beginOrEnd) {
        Calendar ca = Calendar.getInstance();
        Date time = null;
        if (beginOrEnd) {
            int currentMonth = ca.get(Calendar.MONTH) + 1;
            //计算当前季度的开始时间
            if (currentMonth >= 1 && currentMonth <= 3)  {
                ca.set(Calendar.MONTH, 0);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                ca.set(Calendar.MONTH, 3);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                ca.set(Calendar.MONTH, 6);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                ca.set(Calendar.MONTH, 9);
            }
            ca.set(Calendar.DATE, 1);
          //当前季度的开始时间
            time = ca.getTime();
        } else {
            int currentMonth = ca.get(Calendar.MONTH) + 1;
            //计算当前季度的结束时间
            if (currentMonth >= 1 && currentMonth <= 3)  {
                ca.set(Calendar.MONTH, 2);
                ca.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                ca.set(Calendar.MONTH, 5);
                ca.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                ca.set(Calendar.MONTH, 8);
                ca.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                ca.set(Calendar.MONTH, 11);
                ca.set(Calendar.DATE, 31);
            }
            ca.add(Calendar.DATE, 1);
          //当前季度的结束时间
            time = ca.getTime();
        }
        return time;
    }

    /**
     * 计算当前月的开始和结束时间
     * @return Date
     * @param beginOrEnd 开始或结束
     */
    public static Date getThisMonthTime(boolean beginOrEnd) {
        Calendar ca = Calendar.getInstance();
        Date time = null;
        if (beginOrEnd) {
            ca.set(Calendar.DATE, 1);
            time = ca.getTime();
        } else {
            ca.set(Calendar.DATE, 1);
            ca.add(Calendar.MONTH, 1);
            time = ca.getTime();
        }
        return time;
    }

    /**
     * 计算半年或者一年的开始时间
     * @return Date
     * @param halfOrOneYear 半年或一年
     */
    public static Date getHalfOrOneYearBeforeTime(boolean halfOrOneYear) {
        Calendar ca = Calendar.getInstance();
        Date time = null;
        if (halfOrOneYear) {
            ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) - 6);
        } else {
            ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) - 12);
        }
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        time = ca.getTime();
        return time;
    }

    /**
     * 获取当前日期加一天后的时间
     * @designer 郭望伟 2013-3-6
     * @developer 郭望伟2013-3-6
     * @Modified by 朱国俊 参数改为Date型增加通用性 2013-3-6
     * @param currentDate 当前日期
     * @return Date 当前日期加一天后的时间
     */
    public static Date getAddOneDayDate(Date currentDate) {
        Date date = null;
        if (currentDate != null) {
             Calendar ca = Calendar.getInstance();
             ca.setTime(currentDate);
             ca.add(Calendar.DATE, 1);
             date = ca.getTime();
        }
        return date;
    }

    public static void main(String[] args) {
        System.out.println(stringToDate("2012-3-4 21:12:23"));
    }
}
