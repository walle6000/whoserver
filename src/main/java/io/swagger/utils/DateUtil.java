package io.swagger.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 * Title: 日期时间工具类
 */
public class DateUtil {
	/**
	 * 设置起使天
	 * @param calender
	 * @return
	 */
	public static Calendar setStartDay(Calendar calender) {
		calender.set(11, 0);
		calender.set(12, 0);
		calender.set(13, 0);
		return calender;
	}

	/**
	 * 设置结束
	 * @param calender
	 * @return
	 */
	public static Calendar setEndDay(Calendar calender) {
		calender.set(11, 23);
		calender.set(12, 59);
		calender.set(13, 59);
		return calender;
	}

	/**
	 * 拷贝年月日
	 * @param calender1
	 * @param calender2
	 */
	public static void copyYearMonthDay(Calendar calender1, Calendar calender2) {
		calender1.set(1, calender2.get(1));
		calender1.set(2, calender2.get(2));
		calender1.set(5, calender2.get(5));
	}

	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	public static String formatEnDate(Date date) {
		if(date==null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		return sdf.format(date).replaceAll("AM", "上午").replaceAll("PM", "下午");
	}

	/**
	 * 把当前日期转换成String
	 * @return
	 */
	public static Date strToDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(false);
		String strDate = formatter.format(new Date());
		Date strtodate = null;
		try {
			strtodate = formatter.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strtodate;
	}
	
	
	public static String toStr(Object o) {
		if(o==null){
			return "";
		}
		if ((o instanceof Date)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return format.format((Date) o);
			} catch (RuntimeException e) {
				return "";
			}
		}
		return "";
	}
	
	public static String toStrYMD(Object o) {
		if(o==null){
			return "";
		}
		if ((o instanceof Date)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return format.format((Date) o);
			} catch (RuntimeException e) {
				return "";
			}
		}else if((o instanceof Long)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return format.format(new Date((Long)o));
			} catch (RuntimeException e) {
				return "";
			}
		}
		return "";
	}
	
	public static String toStrYM(Object o) {
		if ((o instanceof Date)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			try {
				return format.format((Date) o);
			} catch (RuntimeException e) {
				return "";
			}
		}
		return "";
	}
	
	
	
	public static Long getSpanDays(Date d1,Date d2) {
		long diffDays = (d1.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24);
		return diffDays;
	}
	
	public static Long getSpanDays(Long d1,Long d2) {
		if(d1 == null || d2 == null){
			return 0l;
		}
		long diffDays = (d1 - d1) / (1000 * 60 * 60 * 24);
		return diffDays;
	}
}