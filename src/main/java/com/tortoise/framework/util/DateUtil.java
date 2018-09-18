package com.tortoise.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


/**
 * 时间转化类
 * 
 * @author chensheng
 */
public class DateUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATETIME_PATTERN_DASH = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy/MM/dd HH:mm:ss
	 */
	public static final String DATETIME_PATTERN_SLASH = "yyyy/MM/dd HH:mm:ss";

	/**
	 * MM月dd日 HH时mm分
	 */
	public static final String DATETIME_PATTERN_CHINA = "MM月dd日 HH时mm分";

	/**
	 * yyyyMMddHHmmss
	 */
	public static final String DATETIME_PATTERN_UNSIGN = "yyyyMMddHHmmss";
	/**
	 * yyyyMMdd
	 */
	public static final String DATE_PATTERN_UNSIGN = "yyyyMMdd";
	/**
	 * yyyy-MM-dd
	 */
	public static final String DATE_PATTERN_DASH = "yyyy-MM-dd";

	/**
	 * 字符串转日期(yyyyMMddHHmmss)
	 */
	public static Date stringToDate14(String str) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = null;
		try {
			if (!StringUtils.isEmpty(str)) {
				date = sdf.parse(str);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return date;
	}

	/**
	 * 字符串转日期(yyyyMMdd)
	 */
	public static Date stringToDate8(String str) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			if (!StringUtils.isEmpty(str)) {
				date = sdf.parse(str);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return date;
	}

	/**
	 * 字符串转日期
	 */
	public static Date stringToDateByPattern(String str, String pattern) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			if (!StringUtils.isEmpty(str)) {
				date = sdf.parse(str);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return date;
	}

	/**
	 * 日期转字符串(yyyy-MM-dd HH:mm:ss)
	 */
	public static String dateToString19(Date date) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = "";
		if (date != null) {
			dateStr = sdf.format(date);
		}
		return dateStr;
	}

	/**
	 * 日期转字符串(yyyyMMddHHmmss)
	 */
	public static String dateToString14(Date date) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = "";
		if (date != null) {
			dateStr = sdf.format(date);
		}
		return dateStr;
	}

	/**
	 * 日期转字符串(yyyyMMdd)
	 */
	public static String dateToString8(Date date) throws RuntimeException {
		return getStringFromDate(date, "yyyyMMdd");
	}

	/**
	 * 日期转字符串
	 */
	public static String dateToStringPattern(Date date, String pattern) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dateStr = "";
		if (date != null) {
			dateStr = sdf.format(date);
		}
		return dateStr;
	}

	/**
	 * 格式化时间(yyyy-MM-dd HH:mm)
	 */
	public static String getTime(Date date) throws RuntimeException {
		return getStringFromDate(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 格式化时间(MM/dd HH:mm)
	 */
	public static String getTime2(Date date) throws RuntimeException {
		return getStringFromDate(date, "MM/dd HH:mm");
	}

	/**
	 * 格式化时间(yyyyMMddHHmm)
	 */
	public static String getTime3(Date date) throws RuntimeException {
		return getStringFromDate(date, "yyyyMMddHHmm");
	}

	/**
	 * 格式化日期(yyyy-MM-dd)
	 */
	public static String getDate(Date date) throws RuntimeException {
		return getStringFromDate(date, "yyyy-MM-dd");
	}

	/**
	 * 日期转字符串
	 */
	public static String getStringFromDate(Date date, String pattern) throws RuntimeException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String result = "";
		if (date != null) {
			result = dateFormat.format(date);
		}
		return result;
	}

	/**
	 * 获取距离当前时间
	 */
	public static String getPassTime(Date date) throws RuntimeException {
		String str = "";

		long now = new Date().getTime();
		long difference = now - date.getTime();
		long second = 1000l;
		long minute = 60000l;
		long hour = 3600000l;
		long day = 86400000l;
		long month = 2592000000l;
		long year = 31536000000l;

		if (difference < minute) {
			str = difference / second + "秒前";
		} else if (difference < hour) {
			str = difference / minute + "分" + difference % minute / second + "秒前";
		} else if (difference < day) {
			str = difference / hour + "小时" + difference % hour / minute + "分前";
		} else if (difference < month) {
			str = difference / day + "天" + difference % day / hour + "小时前";
		} else if (difference < year) {
			str = difference / month + "月" + difference % month / day + "天前";
		} else {
			str = difference / year + "年" + difference % year / month + "月前";
		}
		return str;
	}

	/**
	 * 分钟转时间字符串
	 */
	public static String minute2Time(long num) {
		String str = "";
		long difference = num;
		long hour = 60l;
		long day = 1440l;
		long month = 43200l;
		long year = 525600l;

		if (difference < hour) {
			str = num + "分钟";
		} else if (difference < day) {

			String min_val = difference % hour != 0 ? (difference % hour + "分钟") : "";
			str = (difference / hour + "小时") + min_val;

		} else if (difference < month) {

			String min_val = difference % hour != 0 ? (difference % hour + "分钟") : "";
			String hour_val = (difference % day / hour) != 0 ? (difference % day / hour) + "小时" : "";
			str = (difference / day + "天") + hour_val + min_val;

		} else if (difference < year) {

			String hour_val = (difference % day / hour) != 0 ? (difference % day / hour) + "小时" : "";
			String day_val = (difference % month / day) != 0 ? (difference % month / day) + "天" : "";
			str = (difference / month + "月") + day_val + hour_val;

		} else {

			String day_val = (difference % month / day) != 0 ? (difference % month / day) + "天" : "";
			String month_val = (difference % year / month) != 0 ? (difference % year / month) + "月" : "";
			str = difference / year + "年" + month_val + day_val;

		}
		return str;
	}

	/**
	 * 字符串转Calendar(yyyyMMddHHmmss)
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static Calendar getCalendar(String str) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtil.stringToDate14(str));
		return cal;
	}

	/**
	 * 计算两个日期字符串相隔天数(yyyyMMdd)
	 */
	public static int daysBetween(String sDate, String eDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(sDate));
		long time1 = cal.getTimeInMillis();

		cal.setTime(sdf.parse(eDate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算两个日期字符串相隔天数(yyyyMMddHHmmss)
	 */
	public static int daysBetween2(String sDate, String eDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(sDate));
		long time1 = cal.getTimeInMillis();

		cal.setTime(sdf.parse(eDate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	public static int daysBetween(Date sDate, Date eDate) throws ParseException {
		long time1 = sDate.getTime();
		long time2 = eDate.getTime();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
	
	
	/**
	 * 向上取整
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	public static int daysBetweenTakeUp(Date sDate, Date eDate){
		long time1 = sDate.getTime();
		long time2 = eDate.getTime();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		if((time2 - time1) % (1000 * 3600 * 24)>0){
			between_days++;
		}
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 获取去年年份
	 */
	public static List<Integer> getLatestYear(int num) {
		List<Integer> years = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < num; i++) {
			years.add(calendar.get(Calendar.YEAR));
			calendar.add(Calendar.YEAR, -1);
		}
		return years;
	}

	/**
	 * 获取年份列表
	 */
	public static List<Integer> getYearList() {
		List<Integer> years = new ArrayList<Integer>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < 50; i++) {
			years.add(calendar.get(Calendar.YEAR));
			calendar.add(Calendar.YEAR, -1);
		}
		return years;
	}

	/**
	 * 获取月份列表
	 */
	public static List<String> getMonthList() {
		List<String> months = new ArrayList<String>();
		for (int i = 1; i <= 12; i++) {
			if (i < 10) {
				months.add("0" + i);
			} else {
				months.add(String.valueOf(i));
			}
		}
		return months;
	}

	/**
	 * 获取日期列表
	 */
	public static List<String> getDayList() {
		List<String> days = new ArrayList<String>();
		for (int i = 1; i <= 31; i++) {
			if (i < 10) {
				days.add("0" + i);
			} else {
				days.add(String.valueOf(i));
			}
		}
		return days;
	}

	/**
	 * 获取日期列表
	 */
	public static List<String> getDayList(int day) {
		List<String> days = new ArrayList<String>();
		for (int i = 1; i <= day; i++) {
			if (i < 10) {
				days.add("0" + i);
			} else {
				days.add(String.valueOf(i));
			}
		}
		return days;
	}

	/**
	 * 获取昨天日期
	 */
	public static String getYesterday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);

		return sdf.format(cal.getTime());
	}

	/**
	 * 时间天数计算
	 */
	public static Date addDays(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}

	/**
	 * 获取一月中的第几天
	 */
	public static int getDayByCalenday(Calendar cal) {
		int month = -1;
		if (cal != null) {
			month = cal.get(Calendar.DAY_OF_MONTH);
		}
		return month;
	}

	/**
	 * 获得当前时间，格式为：yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * getCurrentYmd(获取年月日)
	 * 
	 * @Title: getCurrentYmd
	 * @Description:
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getCurrentYmd() {
		Date currentTime = new Date();
		SimpleDateFormat formatterYdm = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatterYdm.format(currentTime);
		return dateString;
	}

	/**
	 * 时间转换
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 * @throws RuntimeException
	 */
	public static String dateFormat(Object date, String dateFormat) throws RuntimeException {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String dateStr = "";
		if (date != null) {
			dateStr = sdf.format(date);
		}
		return dateStr;
	}

	/**
	 * 当前时间小时数（24时）
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 * @throws RuntimeException
	 */
	public static int getCurrentHours() throws RuntimeException {
		Date ct = new Date();
		return ct.getHours();
	}

	/**
	 * 指定时间小时数（24时）
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 * @throws RuntimeException
	 */
	public static int getCurrentHours(String date) throws RuntimeException {
		Date ct = new Date();
		return ct.getHours();
	}

	/**
	 * 当前时间小时数大于或等于指定小时数，返回下一天指定指定小时的时间，否则返回当前日期指定小时的时间
	 * 
	 * @param hours
	 *            指定的小时数
	 * @return
	 * @throws RuntimeException
	 */
	public static Date getEarmarkTime(int hours) throws RuntimeException {
		final GregorianCalendar calendar = new GregorianCalendar();
		if (calendar.get(calendar.HOUR_OF_DAY) >= hours) {
			calendar.add(GregorianCalendar.DATE, 1);
		}
		calendar.set(Calendar.HOUR_OF_DAY, hours);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		// System.out.println(calendar.getTime());
		return calendar.getTime();
	}

	/**
	 * 获取指定时间的时间截
	 * 
	 * @param date
	 * @return
	 * @throws RuntimeException
	 */
	public static long getEarmarkTimeLong(Date date) throws RuntimeException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		long timestamp = cal.getTimeInMillis();
		return timestamp;
	}

	/**
	 * 获取指定时间的时间截
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @throws RuntimeException
	 */
	public static long getEarmarkTimeLong(String date, String pattern) throws RuntimeException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(stringToDateByPattern(date, pattern));
		long timestamp = cal.getTimeInMillis();
		return timestamp;
	}

	/**
	 * 取日期的指定时刻
	 * 
	 * @param date
	 * @param hours
	 * @return
	 * @throws RuntimeException
	 */
	public static String getEarmarkDate(Date date, int hours) throws RuntimeException {
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(date);// 把当前时间赋给日历
		calendar.set(Calendar.HOUR_OF_DAY, hours); // 日期十点
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return DateUtil.dateFormat(calendar.getTime(), DATETIME_PATTERN_DASH); // 得到前一天的时间
	}

	/**
	 * 取日期的指定时刻
	 * 
	 * @param date
	 * @param hours
	 * @return
	 * @throws RuntimeException
	 */
	public static Date getEarmarkDateToDate(Date date, int hours) throws RuntimeException {
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(date);// 把当前时间赋给日历
		calendar.set(Calendar.HOUR_OF_DAY, hours); // 日期十点
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 取日期下一天的指定时刻
	 * 
	 * @param date
	 * @param hours
	 * @return
	 * @throws RuntimeException
	 */
	public static String getEarmarkTimeNextDateStr(Date date, int hours) throws RuntimeException {
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(date);// 把当前时间赋给日历
		calendar.add(GregorianCalendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, hours); // 日期十点
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return DateUtil.dateFormat(calendar.getTime(), DATETIME_PATTERN_DASH); // 得到前一天的时间
	}

	/**
	 * 取日期下一天的指定时刻
	 * 
	 * @param date
	 * @param hours
	 * @return
	 * @throws RuntimeException
	 */
	public static Date getEarmarkTimeNextDate(Date date, int hours) throws RuntimeException {
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(date);// 把当前时间赋给日历
		calendar.add(GregorianCalendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, hours); // 日期十点
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获得指定日期的前一天
	 * 
	 * @param specifiedDay
	 *            yyyy-MM-dd
	 * @return
	 * @throws Exception
	 */
	public static String getSpecifiedDayBefore(String specifiedDay) {// 可以用new Date().toLocalString()传递参数
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayBefore;
	}

	public static void main(String[] args) {
		System.out.println(getEarmarkTime(10));
		System.out.println(getEarmarkDate(new Date(), 10));
	}
}
