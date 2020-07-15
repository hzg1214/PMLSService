package cn.com.eju.deal.reportbase.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import cn.com.eju.deal.core.util.StringUtil;

/**
 * Date Utility Class used to convert Strings to Dates and Timestamps
 * 
 */
public final class DateUtil {
	private static Log log = LogFactory.getLog(DateUtil.class);
	public static final String BUNDLE_KEY = "ApplicationResources";
	private static final String TIME_PATTERN = "HH:mm";

	/**
	 * Checkstyle rule: utility classes should not have public constructor
	 */
	private DateUtil() {
	}

	/**
	 * Return default datePattern (MM/dd/yyyy)
	 * 
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		Locale locale = LocaleContextHolder.getLocale();
		String defaultDatePattern;
		try {
			defaultDatePattern = ResourceBundle.getBundle(BUNDLE_KEY, locale)
					.getString("date.format");
		} catch (MissingResourceException mse) {
			defaultDatePattern = "MM/dd/yyyy";
		}

		return defaultDatePattern;
	}

	public static String getDateTimePattern() {
		return DateUtil.getDatePattern() + " HH:mm:ss.S";
	}

	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 * dd-MMM-yyyy to mm/dd/yyyy.
	 * 
	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the ui
	 */
	public static String getDate(Date aDate) {
		SimpleDateFormat df;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}
	
	public static String formatLableDate(String date, String patten){
		String dates="";
		try {
			Date d=DateUtil.getDate(date, patten);
			dates=DateUtil.getDate(d, patten);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dates;
	}

	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 * @see java.text.SimpleDateFormat
	 */
	public static Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df;
		Date date;
		df = new SimpleDateFormat(aMask);

		if (log.isDebugEnabled()) {
			log.debug("converting '" + strDate + "' to date with mask '"
					+ aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format: MM/dd/yyyy HH:MM
	 * a
	 * 
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(TIME_PATTERN, theTime);
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 * 
	 * @return the current date
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * @see java.text.SimpleDateFormat
	 */
	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}
	private static final int[] dayArray = new int[] { 31, 28, 31, 30, 31, 30,
		31, 31, 30, 31, 30, 31 };

private int weeks = 0;
private int MaxDate;// 一月最大天数
private int MaxYear;// 一年最大天数

public static String transformDate(String date, String old_pattern,
		String new_pattern) {
	try {
		return getDate(getDate(date, old_pattern), new_pattern);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	return null;
}

public static Date getDate(String date, String patten)
		throws ParseException {
	if (date == null) {
		return null;
	}
	SimpleDateFormat sf = new SimpleDateFormat(patten);
	return sf.parse(date);
}

public static String getDate(Date date, String patten) {
	SimpleDateFormat sf = new SimpleDateFormat(patten);
	return sf.format(date);
}

public static Calendar getCalendar() {
	return GregorianCalendar.getInstance();
}

/**
 * @return String
 */
public static String getDateMilliFormat() {
	Calendar cal = Calendar.getInstance();
	return getDateMilliFormat(cal);
}

/**
 * @param cal
 * @return String
 */
public static String getDateMilliFormat(Calendar cal) {
	String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
	return getDateFormat(cal, pattern);
}

/**
 * @param date
 * @return String
 */
public static String getDateMilliFormat(Date date) {
	String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
	return getDateFormat(date, pattern);
}

/**
 * @param strDate
 * @return java.util.Calendar
 */
public static Calendar parseCalendarMilliFormat(String strDate) {
	String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
	return parseCalendarFormat(strDate, pattern);
}

/**
 * @param strDate
 * @return java.util.Date
 */
public static Date parseDateMilliFormat(String strDate) {
	String pattern = "yyyy-MM-dd HH:mm:ss,SSS";
	return parseDateFormat(strDate, pattern);
}

/**
 * @return String
 */
public static String getDateSecondFormat() {
	Calendar cal = Calendar.getInstance();
	return getDateSecondFormat(cal);
}

/**
 * @param cal
 * @return String
 */
public static String getDateSecondFormat(Calendar cal) {
	String pattern = "yyyy-MM-dd HH:mm:ss";
	return getDateFormat(cal, pattern);
}

/**
 * @param date
 * @return String
 */
public static String getDateSecondFormat(Date date) {
	String pattern = "yyyy-MM-dd HH:mm:ss";
	return getDateFormat(date, pattern);
}

/**
 * @param strDate
 * @return java.util.Calendar
 */
public static Calendar parseCalendarSecondFormat(String strDate) {
	String pattern = "yyyy-MM-dd HH:mm:ss";
	return parseCalendarFormat(strDate, pattern);
}

/**
 * @param strDate
 * @return java.util.Date
 */
public static Date parseDateSecondFormat(String strDate) {
	String pattern = "yyyy-MM-dd HH:mm:ss";
	return parseDateFormat(strDate, pattern);
}

/**
 * @return String
 */
public static String getDateMinuteFormat() {
	Calendar cal = Calendar.getInstance();
	return getDateMinuteFormat(cal);
}

/**
 * @param cal
 * @return String
 */
public static String getDateMinuteFormat(Calendar cal) {
	String pattern = "yyyy-MM-dd HH:mm";
	return getDateFormat(cal, pattern);
}

/**
 * @param date
 * @return String
 */
public static String getDateMinuteFormat(Date date) {
	String pattern = "yyyy-MM-dd HH:mm";
	return getDateFormat(date, pattern);
}

/**
 * @param strDate
 * @return java.util.Calendar
 */
public static Calendar parseCalendarMinuteFormat(String strDate) {
	String pattern = "yyyy-MM-dd HH:mm";
	return parseCalendarFormat(strDate, pattern);
}

/**
 * @param strDate
 * @return java.util.Date
 */
public static Date parseDateMinuteFormat(String strDate) {
	String pattern = "yyyy-MM-dd HH:mm";
	return parseDateFormat(strDate, pattern);
}

/**
 * @param patten
 * @return java.lang.String
 */
public static String getDate(String patten) {
	SimpleDateFormat sf = new SimpleDateFormat(patten, Locale.US);
	return sf.format(new Date());
}

/**
 * @return String
 */
public static String getDateDayFormat() {
	Calendar cal = Calendar.getInstance();
	return getDateDayFormat(cal);
}

/**
 * @param cal
 * @return String
 */
public static String getDateDayFormat(Calendar cal) {
	String pattern = "yyyy-MM-dd";
	return getDateFormat(cal, pattern);
}

/**
 * @param date
 * @return String
 */
public static String getDateDayFormat(Date date) {
	String pattern = "yyyy-MM-dd";
	return getDateFormat(date, pattern);
}


public static Date parseDateMinuteFormat1(String strDate) {
	String pattern = "yyyyMMdd";
	return parseDateFormat(strDate, pattern);
}

public static String format(long ms) {// 将毫秒数换算成x天x时x分x秒x毫秒
	int ss = 1000;
	int mi = ss * 60;
	int hh = mi * 60;
	int dd = hh * 24;

	long day = ms / dd;
	long hour = (ms - day * dd) / hh;
	long minute = (ms - day * dd - hour * hh) / mi;
	long second = (ms - day * dd - hour * hh - minute * mi) / ss;
	long milliSecond = ms - day * dd - hour * hh - minute * mi - second
			* ss;

	String strDay = day < 10 ? "0" + day : "" + day;
	String strHour = hour < 10 ? "0" + hour : "" + hour;
	String strMinute = minute < 10 ? "0" + minute : "" + minute;
	String strSecond = second < 10 ? "0" + second : "" + second;
	String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : ""
			+ milliSecond;
	strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : ""
			+ strMilliSecond;
	return /* strDay + " " + */strHour + ":" + strMinute + ":" + strSecond/*
																			 * +
																			 * " "
																			 * +
																			 * strMilliSecond
																			 */;
}

public static String formatLongToTime(Long l) {
	int hour = 0;
	int minute = 0;
	int second = 0;

	second = l.intValue() / 1000;

	if (second > 60) {
		minute = second / 60;
		second = second % 60;
	}
	if (minute > 60) {
		hour = minute / 60;
		minute = minute % 60;
	}
	return (hour + "小时" + minute + "分钟" + second + "秒");
}

/**
 * @param strDate
 * @return java.util.Calendar
 */
public static Calendar parseCalendarDayFormat(String strDate) {
	String pattern = "yyyy-MM-dd";
	return parseCalendarFormat(strDate, pattern);
}

/**
 * @param strDate
 * @return java.util.Date
 */
public static Date parseDateDayFormat(String strDate) {
	String pattern = "yyyy-MM-dd";
	return parseDateFormat(strDate, pattern);
}

/**
 * @return String
 */
public static String getDateFileFormat() {
	Calendar cal = Calendar.getInstance();
	return getDateFileFormat(cal);
}

/**
 * @param cal
 * @return String
 */
public static String getDateFileFormat(Calendar cal) {
	String pattern = "yyyy-MM-dd_HH-mm-ss";
	return getDateFormat(cal, pattern);
}

/**
 * @param date
 * @return String
 */
public static String getDateFileFormat(Date date) {
	String pattern = "yyyy-MM-dd HH-mm-ss";
	return getDateFormat(date, pattern);
}

/**
 * @param strDate
 * @return java.util.Calendar
 */
public static Calendar parseCalendarFileFormat(String strDate) {
	String pattern = "yyyy-MM-dd_HH-mm-ss";
	return parseCalendarFormat(strDate, pattern);
}

/**
 * @param strDate
 * @return java.util.Date
 */
public static Date parseDateFileFormat(String strDate) {
	String pattern = "yyyy-MM-dd_HH-mm-ss";
	return parseDateFormat(strDate, pattern);
}

/**
 * @return String
 */
public static String getDateW3CFormat() {
	Calendar cal = Calendar.getInstance();
	return getDateW3CFormat(cal);
}

/**
 * @param cal
 * @return String
 */
public static String getDateW3CFormat(Calendar cal) {
	String pattern = "yyyy-MM-dd HH:mm:ss";
	return getDateFormat(cal, pattern);
}

/**
 * @param date
 * @return String
 */
public static String getDateW3CFormat(Date date) {
	String pattern = "yyyy-MM-dd HH:mm:ss";
	return getDateFormat(date, pattern);
}

/**
 * @param strDate
 * @return java.util.Calendar
 */
public static Calendar parseCalendarW3CFormat(String strDate) {
	String pattern = "yyyy-MM-dd HH:mm:ss";
	return parseCalendarFormat(strDate, pattern);
}

/**
 * @param strDate
 * @return java.util.Date
 */
public static Date parseDateW3CFormat(String strDate) {
	String pattern = "yyyy-MM-dd HH:mm:ss";
	return parseDateFormat(strDate, pattern);
}

/**
 * @param cal
 * @return String
 */
public static String getDateFormat(Calendar cal) {
	String pattern = "yyyy-MM-dd HH:mm:ss";
	return getDateFormat(cal, pattern);
}

/**
 * @param date
 * @return String
 */
public static String getDateFormat(Date date) {
	String pattern = "yyyy-MM-dd HH:mm:ss";
	return getDateFormat(date, pattern);
}

/**
 * @param strDate
 * @return java.util.Calendar
 */
public static Calendar parseCalendarFormat(String strDate) {
	String pattern = "yyyy-MM-dd HH:mm:ss";
	return parseCalendarFormat(strDate, pattern);
}

/**
 * @param strDate
 * @return java.util.Date
 */
public static Date parseDateFormat(String strDate) {
	String pattern = "yyyy-MM-dd HH:mm:ss";
	return parseDateFormat(strDate, pattern);
}

/**
 * @param cal
 * @param pattern
 * @return String
 */
public static String getDateFormat(Calendar cal, String pattern) {
	return getDateFormat(cal.getTime(), pattern);
}

/**
 * @param date
 * @param pattern
 * @return String
 */
public static String getDateFormat(Date date, String pattern) {
	if (date == null) {
		return "";
	}
	SimpleDateFormat sdf = new SimpleDateFormat();
	String str = null;
	sdf.applyPattern(pattern);
	str = sdf.format(date);
	return str;
}

/**
 * @param strDate
 * @param pattern
 * @return java.util.Calendar
 */
public static Calendar parseCalendarFormat(String strDate, String pattern) {
	SimpleDateFormat sdf = new SimpleDateFormat();
	Calendar cal = null;
	sdf.applyPattern(pattern);
	try {
		sdf.parse(strDate);
		cal = sdf.getCalendar();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return cal;

}

/**
 * @param strDate
 * @param pattern
 * @return java.util.Date
 */
public static Date parseDateFormat(String strDate, String pattern) {
	SimpleDateFormat sdf = new SimpleDateFormat();
	Date date = null;
	sdf.applyPattern(pattern);
	try {
		date = sdf.parse(strDate);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return date;

}

public static int getLastDayOfMonth(int month) {
	if (month < 1 || month > 12) {
		return -1;
	}
	int retn = 0;
	if (month == 2) {
		if (isLeapYear()) {
			retn = 29;
		} else {
			retn = dayArray[month - 1];
		}
	} else {
		retn = dayArray[month - 1];
	}
	return retn;
}

public static boolean isLeapYear() {
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	return isLeapYear(year);
}

public static boolean isLeapYear(int year) {
	/**
	 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
	 * 3.能被4整除同时能被100整除则不是闰年
	 */
	if ((year % 400) == 0)
		return true;
	else if ((year % 4) == 0) {
		if ((year % 100) == 0)
			return false;
		else
			return true;
	} else
		return false;
}

/**
 * 判断指定日期的年份是否是闰年
 * 
 * @param date
 *            指定日期。
 * @return 是否闰年
 */
public static boolean isLeapYear(Date date) {
	/**
	 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
	 * 3.能被4整除同时能被100整除则不是闰年
	 */
	// int year = date.getYear();
	GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	gc.setTime(date);
	int year = gc.get(Calendar.YEAR);
	return isLeapYear(year);
}

public static boolean isLeapYear(Calendar gc) {
	/**
	 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
	 * 3.能被4整除同时能被100整除则不是闰年
	 */
	int year = gc.get(Calendar.YEAR);
	return isLeapYear(year);
}

/**
 * 得到指定日期的前一个工作日
 * 
 * @param date
 *            指定日期。
 * @return 指定日期的前一个工作日
 */
public static Date getPreviousWeekDay(Date date) {
	/**
	 * 详细设计： 1.如果date是星期日，则减3天 2.如果date是星期六，则减2天 3.否则减1天
	 */
	GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	gc.setTime(date);
	return getPreviousWeekDay(gc);
}

public static Date getPreviousWeekDay(java.util.Calendar gc) {
	{
		/**
		 * 详细设计： 1.如果date是星期日，则减3天 2.如果date是星期六，则减2天 3.否则减1天
		 */
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case (Calendar.MONDAY):
			gc.add(Calendar.DATE, -3);
			break;
		case (Calendar.SUNDAY):
			gc.add(Calendar.DATE, -2);
			break;
		default:
			gc.add(Calendar.DATE, -1);
			break;
		}
		return gc.getTime();
	}
}

/**
 * 得到指定日期的后一个工作日
 * 
 * @param date
 *            指定日期。
 * @return 指定日期的后一个工作日
 */
public static Date getNextWeekDay(Date date) {
	/**
	 * 详细设计： 1.如果date是星期五，则加3天 2.如果date是星期六，则加2天 3.否则加1天
	 */
	GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	gc.setTime(date);
	switch (gc.get(Calendar.DAY_OF_WEEK)) {
	case (Calendar.FRIDAY):
		gc.add(Calendar.DATE, 3);
		break;
	case (Calendar.SATURDAY):
		gc.add(Calendar.DATE, 2);
		break;
	default:
		gc.add(Calendar.DATE, 1);
		break;
	}
	return gc.getTime();
}

public static Calendar getNextWeekDay(Calendar gc) {
	/**
	 * 详细设计： 1.如果date是星期五，则加3天 2.如果date是星期六，则加2天 3.否则加1天
	 */
	switch (gc.get(Calendar.DAY_OF_WEEK)) {
	case (Calendar.FRIDAY):
		gc.add(Calendar.DATE, 3);
		break;
	case (Calendar.SATURDAY):
		gc.add(Calendar.DATE, 2);
		break;
	default:
		gc.add(Calendar.DATE, 1);
		break;
	}
	return gc;
}



/**
 * 取得指定日期的下一个月
 * 
 * @param date
 *            指定日期。
 * @return 指定日期的下一个月
 */
public static Date getNextMonth(Date date) {
	/**
	 * 详细设计： 1.指定日期的月份加1
	 */
	GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	gc.setTime(date);
	gc.add(Calendar.MONTH, 1);
	return gc.getTime();
}

public static Calendar getNextMonth(Calendar gc) {
	/**
	 * 详细设计： 1.指定日期的月份加1
	 */
	gc.add(Calendar.MONTH, 1);
	return gc;
}

/**
 * 取得指定日期的下一天
 * 
 * @param date
 *            指定日期。
 * @return 指定日期的下一天
 */
public static Date getNextDay(Date date) {
	/**
	 * 详细设计： 1.指定日期加1天
	 */
	GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	gc.setTime(date);
	gc.add(Calendar.DATE, 1);
	return gc.getTime();
}

public static Calendar getNextDay(Calendar gc) {
	/**
	 * 详细设计： 1.指定日期加1天
	 */
	gc.add(Calendar.DATE, 1);
	return gc;
}

/**
 * 取得指定日期的下一个星期
 * 
 * @param date
 *            指定日期。
 * @return 指定日期的下一个星期
 */
public static Date getNextWeek(Date date) {
	/**
	 * 详细设计： 1.指定日期加7天
	 */
	GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	gc.setTime(date);
	gc.add(Calendar.DATE, 7);
	return gc.getTime();
}

public static Calendar getNextWeek(Calendar gc) {
	/**
	 * 详细设计： 1.指定日期加7天
	 */
	gc.add(Calendar.DATE, 7);
	return gc;
}

/**
 * 取得指定日期的所处星期的最后一天
 * 
 * @param date
 *            指定日期。
 * @return 指定日期的所处星期的最后一天
 */
public static Date getLastDayOfWeek(Date date) {
	/**
	 * 详细设计： 1.如果date是星期日，则加6天 2.如果date是星期一，则加5天 3.如果date是星期二，则加4天
	 * 4.如果date是星期三，则加3天 5.如果date是星期四，则加2天 6.如果date是星期五，则加1天
	 * 7.如果date是星期六，则加0天
	 */
	GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	gc.setTime(date);
	switch (gc.get(Calendar.DAY_OF_WEEK)) {
	case (Calendar.SUNDAY):
		gc.add(Calendar.DATE, 6);
		break;
	case (Calendar.MONDAY):
		gc.add(Calendar.DATE, 5);
		break;
	case (Calendar.TUESDAY):
		gc.add(Calendar.DATE, 4);
		break;
	case (Calendar.WEDNESDAY):
		gc.add(Calendar.DATE, 3);
		break;
	case (Calendar.THURSDAY):
		gc.add(Calendar.DATE, 2);
		break;
	case (Calendar.FRIDAY):
		gc.add(Calendar.DATE, 1);
		break;
	case (Calendar.SATURDAY):
		gc.add(Calendar.DATE, 0);
		break;
	}
	return gc.getTime();
}

/**
 * 取得指定日期的所处星期的第一天
 * 
 * @param date
 *            指定日期。
 * @return 指定日期的所处星期的第一天
 */
public static Date getFirstDayOfWeek(Date date) {
	/**
	 * 详细设计： 1.如果date是星期日，则减0天 2.如果date是星期一，则减1天 3.如果date是星期二，则减2天
	 * 4.如果date是星期三，则减3天 5.如果date是星期四，则减4天 6.如果date是星期五，则减5天
	 * 7.如果date是星期六，则减6天
	 */
	GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	gc.setTime(date);
	switch (gc.get(Calendar.DAY_OF_WEEK)) {
	case (Calendar.SUNDAY):
		gc.add(Calendar.DATE, 0);
		break;
	case (Calendar.MONDAY):
		gc.add(Calendar.DATE, -1);
		break;
	case (Calendar.TUESDAY):
		gc.add(Calendar.DATE, -2);
		break;
	case (Calendar.WEDNESDAY):
		gc.add(Calendar.DATE, -3);
		break;
	case (Calendar.THURSDAY):
		gc.add(Calendar.DATE, -4);
		break;
	case (Calendar.FRIDAY):
		gc.add(Calendar.DATE, -5);
		break;
	case (Calendar.SATURDAY):
		gc.add(Calendar.DATE, -6);
		break;
	}
	return gc.getTime();
}

public static Calendar getFirstDayOfWeek(Calendar gc) {
	/**
	 * 详细设计： 1.如果date是星期日，则减0天 2.如果date是星期一，则减1天 3.如果date是星期二，则减2天
	 * 4.如果date是星期三，则减3天 5.如果date是星期四，则减4天 6.如果date是星期五，则减5天
	 * 7.如果date是星期六，则减6天
	 */
	switch (gc.get(Calendar.DAY_OF_WEEK)) {
	case (Calendar.SUNDAY):
		gc.add(Calendar.DATE, 0);
		break;
	case (Calendar.MONDAY):
		gc.add(Calendar.DATE, -1);
		break;
	case (Calendar.TUESDAY):
		gc.add(Calendar.DATE, -2);
		break;
	case (Calendar.WEDNESDAY):
		gc.add(Calendar.DATE, -3);
		break;
	case (Calendar.THURSDAY):
		gc.add(Calendar.DATE, -4);
		break;
	case (Calendar.FRIDAY):
		gc.add(Calendar.DATE, -5);
		break;
	case (Calendar.SATURDAY):
		gc.add(Calendar.DATE, -6);
		break;
	}
	return gc;
}

public String del_fh(String re_time) {
	String back_time = "";
	back_time = re_time.toString().replaceAll("/", "").replaceAll("-", "")
			.replaceAll(" ", "").replaceAll(":", "");
	return back_time;
}

public String getMondayOFWeek(String bs) {
	int weeks = 0;
	int mondayPlus = this.getMondayPlus();
	GregorianCalendar currentDate = new GregorianCalendar();
	currentDate.add(GregorianCalendar.DATE, mondayPlus);
	Date monday = currentDate.getTime();
	DateFormat df = DateFormat.getDateInstance();
	String preMonday = df.format(monday);

	String[] arr_time = preMonday.toString().split("-");
	if (arr_time[1].length() == 1) {
		arr_time[1] = "0" + arr_time[1];
	}
	if (arr_time[2].length() == 1) {
		arr_time[2] = "0" + arr_time[2];
	}
	// ls add
	if (bs.equals("1")) {
		preMonday = arr_time[0] + "-" + arr_time[1] + "-" + arr_time[2];
		preMonday = del_fh(preMonday) + "000000";

	} else if (bs.equals("0")) {
		preMonday = arr_time[0] + "/" + arr_time[1] + "/" + arr_time[2];
		preMonday = preMonday + " 00:00:00";

	}

	System.out.println("本周第一天====" + preMonday);
	return preMonday;
}

/**
 * 取得指定日期的所处月份的最后一天
 * 
 * @param date
 *            指定日期。
 * @return 指定日期的所处月份的最后一天
 */
public static Date getLastDayOfMonth(Date date) {
	/**
	 * 详细设计： 1.如果date在1月，则为31日 2.如果date在2月，则为28日 3.如果date在3月，则为31日
	 * 4.如果date在4月，则为30日 5.如果date在5月，则为31日 6.如果date在6月，则为30日
	 * 7.如果date在7月，则为31日 8.如果date在8月，则为31日 9.如果date在9月，则为30日
	 * 10.如果date在10月，则为31日 11.如果date在11月，则为30日 12.如果date在12月，则为31日
	 * 1.如果date在闰年的2月，则为29日
	 */
	GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	gc.setTime(date);
	switch (gc.get(Calendar.MONTH)) {
	case 0:
		gc.set(Calendar.DAY_OF_MONTH, 31);
		break;
	case 1:
		gc.set(Calendar.DAY_OF_MONTH, 28);
		break;
	case 2:
		gc.set(Calendar.DAY_OF_MONTH, 31);
		break;
	case 3:
		gc.set(Calendar.DAY_OF_MONTH, 30);
		break;
	case 4:
		gc.set(Calendar.DAY_OF_MONTH, 31);
		break;
	case 5:
		gc.set(Calendar.DAY_OF_MONTH, 30);
		break;
	case 6:
		gc.set(Calendar.DAY_OF_MONTH, 31);
		break;
	case 7:
		gc.set(Calendar.DAY_OF_MONTH, 31);
		break;
	case 8:
		gc.set(Calendar.DAY_OF_MONTH, 30);
		break;
	case 9:
		gc.set(Calendar.DAY_OF_MONTH, 31);
		break;
	case 10:
		gc.set(Calendar.DAY_OF_MONTH, 30);
		break;
	case 11:
		gc.set(Calendar.DAY_OF_MONTH, 31);
		break;
	}
	// 检查闰年
	if ((gc.get(Calendar.MONTH) == Calendar.FEBRUARY)
			&& (isLeapYear(gc.get(Calendar.YEAR)))) {
		gc.set(Calendar.DAY_OF_MONTH, 29);
	}
	return gc.getTime();
}

public static Calendar getLastDayOfMonth(Calendar gc) {
	/**
	 * 详细设计： 1.如果date在1月，则为31日 2.如果date在2月，则为28日 3.如果date在3月，则为31日
	 * 4.如果date在4月，则为30日 5.如果date在5月，则为31日 6.如果date在6月，则为30日
	 * 7.如果date在7月，则为31日 8.如果date在8月，则为31日 9.如果date在9月，则为30日
	 * 10.如果date在10月，则为31日 11.如果date在11月，则为30日 12.如果date在12月，则为31日
	 * 1.如果date在闰年的2月，则为29日
	 */
	switch (gc.get(Calendar.MONTH)) {
	case 0:
		gc.set(Calendar.DAY_OF_MONTH, 31);
		break;
	case 1:
		gc.set(Calendar.DAY_OF_MONTH, 28);
		break;
	case 2:
		gc.set(Calendar.DAY_OF_MONTH, 31);
		break;
	case 3:
		gc.set(Calendar.DAY_OF_MONTH, 30);
		break;
	case 4:
		gc.set(Calendar.DAY_OF_MONTH, 31);
		break;
	case 5:
		gc.set(Calendar.DAY_OF_MONTH, 30);
		break;
	case 6:
		gc.set(Calendar.DAY_OF_MONTH, 31);
		break;
	case 7:
		gc.set(Calendar.DAY_OF_MONTH, 31);
		break;
	case 8:
		gc.set(Calendar.DAY_OF_MONTH, 30);
		break;
	case 9:
		gc.set(Calendar.DAY_OF_MONTH, 31);
		break;
	case 10:
		gc.set(Calendar.DAY_OF_MONTH, 30);
		break;
	case 11:
		gc.set(Calendar.DAY_OF_MONTH, 31);
		break;
	}
	// 检查闰年
	if ((gc.get(Calendar.MONTH) == Calendar.FEBRUARY)
			&& (isLeapYear(gc.get(Calendar.YEAR)))) {
		gc.set(Calendar.DAY_OF_MONTH, 29);
	}
	return gc;
}

/**
 * 取得指定日期的所处月份的第一天
 * 
 * @param date
 *            指定日期。
 * @return 指定日期的所处月份的第一天
 */
public static Date getFirstDayOfMonth(Date date) {
	/**
	 * 详细设计： 1.设置为1号
	 */
	GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
	gc.setTime(date);
	gc.set(Calendar.DAY_OF_MONTH, 1);
	return gc.getTime();
}

public static Calendar getFirstDayOfMonth(Calendar gc) {
	/**
	 * 详细设计： 1.设置为1号
	 */
	gc.set(Calendar.DAY_OF_MONTH, 1);
	return gc;
}

/**
 * 将日期对象转换成为指定ORA日期、时间格式的字符串形式。如果日期对象为空，返回 一个空字符串对象，而不是一个空对象。
 * 
 * @param theDate
 *            将要转换为字符串的日期对象。
 * @param hasTime
 *            如果返回的字符串带时间则为true
 * @return 转换的结果
 */
public static String toOraString(Date theDate, boolean hasTime) {
	/**
	 * 详细设计： 1.如果有时间，则设置格式为getOraDateTimeFormat()的返回值
	 * 2.否则设置格式为getOraDateFormat()的返回值 3.调用toString(Date theDate, DateFormat
	 * theDateFormat)
	 */
	DateFormat theFormat;
	if (hasTime) {
		theFormat = getOraDateTimeFormat();
	} else {
		theFormat = getOraDateFormat();
	}
	return toString(theDate, theFormat);
}

/**
 * 将日期对象转换成为指定日期、时间格式的字符串形式。如果日期对象为空，返回 一个空字符串对象，而不是一个空对象。
 * 
 * @param theDate
 *            将要转换为字符串的日期对象。
 * @param hasTime
 *            如果返回的字符串带时间则为true
 * @return 转换的结果
 */
public static String toString(Date theDate, boolean hasTime) {
	/**
	 * 详细设计： 1.如果有时间，则设置格式为getDateTimeFormat的返回值 2.否则设置格式为getDateFormat的返回值
	 * 3.调用toString(Date theDate, DateFormat theDateFormat)
	 */
	DateFormat theFormat;
	if (hasTime) {
		theFormat = getDateTimeFormat();
	} else {
		theFormat = getDateFormat();
	}
	return toString(theDate, theFormat);
}

/**
 * 标准日期格式
 */
private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
		"MM/dd/yyyy");
/**
 * 标准时间格式
 */
private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
		"MM/dd/yyyy HH:mm");
/**
 * 带时分秒的标准时间格式
 */
private static final SimpleDateFormat DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat(
		"MM/dd/yyyy HH:mm:ss");
/**
 * ORA标准日期格式
 */
private static final SimpleDateFormat ORA_DATE_FORMAT = new SimpleDateFormat(
		"yyyyMMdd");
/**
 * ORA标准时间格式
 */
private static final SimpleDateFormat ORA_DATE_TIME_FORMAT = new SimpleDateFormat(
		"yyyyMMddHHmm");
/**
 * 带时分秒的ORA标准时间格式
 */
private static final SimpleDateFormat ORA_DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat(
		"yyyyMMddHHmmss");

/**
 * 创建一个标准日期格式的克隆
 * 
 * @return 标准日期格式的克隆
 */
public static DateFormat getDateFormat() {
	/**
	 * 详细设计： 1.返回DATE_FORMAT
	 */
	SimpleDateFormat theDateFormat = (SimpleDateFormat) DATE_FORMAT.clone();
	theDateFormat.setLenient(false);
	return theDateFormat;
}

/**
 * 创建一个标准时间格式的克隆
 * 
 * @return 标准时间格式的克隆
 */
public static DateFormat getDateTimeFormat() {
	/**
	 * 详细设计： 1.返回DATE_TIME_FORMAT
	 */
	SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) DATE_TIME_FORMAT
			.clone();
	theDateTimeFormat.setLenient(false);
	return theDateTimeFormat;
}

/**
 * 创建一个标准ORA日期格式的克隆
 * 
 * @return 标准ORA日期格式的克隆
 */
public static DateFormat getOraDateFormat() {
	/**
	 * 详细设计： 1.返回ORA_DATE_FORMAT
	 */
	SimpleDateFormat theDateFormat = (SimpleDateFormat) ORA_DATE_FORMAT
			.clone();
	theDateFormat.setLenient(false);
	return theDateFormat;
}

/**
 * 创建一个标准ORA时间格式的克隆
 * 
 * @return 标准ORA时间格式的克隆
 */
public static DateFormat getOraDateTimeFormat() {
	/**
	 * 详细设计： 1.返回ORA_DATE_TIME_FORMAT
	 */
	SimpleDateFormat theDateTimeFormat = (SimpleDateFormat) ORA_DATE_TIME_FORMAT
			.clone();
	theDateTimeFormat.setLenient(false);
	return theDateTimeFormat;
}

/**
 * 将一个日期对象转换成为指定日期、时间格式的字符串。 如果日期对象为空，返回一个空字符串，而不是一个空对象。
 * 
 * @param theDate
 *            要转换的日期对象
 * @param theDateFormat
 *            返回的日期字符串的格式
 * @return 转换结果
 */
public static String toString(Date theDate, DateFormat theDateFormat) {
	/**
	 * 详细设计： 1.theDate为空，则返回"" 2.否则使用theDateFormat格式化
	 */
	if (theDate == null) {
		return "";
	}
	return theDateFormat.format(theDate);
}

/**
 * 判断二个时间是否在同一个周
 * 
 * @param date1
 * @param date2
 * @return
 */
public static boolean isSameWeekDates(Date date1, Date date2) {
	Calendar cal1 = Calendar.getInstance();
	Calendar cal2 = Calendar.getInstance();
	cal1.setTime(date1);
	cal2.setTime(date2);
	int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
	if (0 == subYear) {
		if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
				.get(Calendar.WEEK_OF_YEAR))
			return true;
	} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
		// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
		if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
				.get(Calendar.WEEK_OF_YEAR))
			return true;
	} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
		if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
				.get(Calendar.WEEK_OF_YEAR))
			return true;
	}
	return false;
}

/**
 * 产生周序列,即得到当前时间所在的年度是第几周
 * 
 * @return
 */
public static String getSeqWeek() {
	Calendar c = Calendar.getInstance(Locale.CHINA);
	String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
	if (week.length() == 1)
		week = "0" + week;
	String year = Integer.toString(c.get(Calendar.YEAR));
	return year + week;
}

public static boolean isDate(String date, String format) {
	DateFormat df = new SimpleDateFormat(format);
	Date d = null;
	try {
		d = df.parse(date);
	} catch (Exception e) {
		// 如果不能转换,肯定是错误格式
		return false;
	}
	String str = df.format(d);
	// 转换后的日期再转换回String,如果不等,逻辑错误.如format为"yyyy-MM-dd",date为
	// "2006-02-31",转换为日期后再转换回字符串为"2006-03-03",说明格式虽然对,但日期
	// 逻辑上不对.
	return date.equals(str);
}

/**
 * 将Date类型转换为字符串
 * 
 * @param date
 *            日期类型
 * @return 日期字符串
 */
public static String format(Date date) {
	return format(date, "yyyy-MM-dd HH:mm:ss");
}

/**
 * 将Date类型转换为字符串
 * 
 * @param date
 *            日期类型
 * @param pattern
 *            字符串格式
 * @return 日期字符串
 */
public static String format(Date date, String pattern) {
	if (date == null) {
		return "null";
	}
	if (pattern == null || pattern.equals("") || pattern.equals("null")) {
		pattern = "yyyy-MM-dd HH:mm:ss";
	}
	return new java.text.SimpleDateFormat(pattern).format(date);
}

/**
 * 将字符串转换为Date类型
 * 
 * @param date
 *            字符串类型
 * @return 日期类型
 */
public static Date format(String date) {
	return format(date, null);
}

/**
 * 将字符串转换为Date类型
 * 
 * @param date
 *            字符串类型
 * @param pattern
 *            格式
 * @return 日期类型
 */
public static Date format(String date, String pattern) {
	if (pattern == null || pattern.equals("") || pattern.equals("null")) {
		pattern = "yyyy-MM-dd HH:mm:ss";
	}
	if (date == null || date.equals("") || date.equals("null")) {
		return new Date();
	}
	Date d = null;
	try {
		d = new java.text.SimpleDateFormat(pattern).parse(date);
	} catch (ParseException pe) {
	}
	return d;
}

/**
 * 得到二个日期间的间隔天数
 */
public static String getTwoDay(String sj1, String sj2) {
	SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
	long day = 0;
	try {
		java.util.Date date = myFormatter.parse(sj1);
		java.util.Date mydate = myFormatter.parse(sj2);
		day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
	} catch (Exception e) {
		return "";
	}
	return day + "";
}

/**
 * 将短时间格式字符串转换为时间 yyyy-MM-dd
 * 
 * @param strDate
 * @return
 */
public static Date strToDate(String strDate) {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	ParsePosition pos = new ParsePosition(0);
	Date strtodate = formatter.parse(strDate, pos);
	return strtodate;
}

/**
 * 两个时间之间的天数
 * 
 * @param date1
 * @param date2
 * @return
 */
public static long getDays(String date1, String date2) {
	if (date1 == null || date1.equals(""))
		return 0;
	if (date2 == null || date2.equals(""))
		return 0;
	// 转换为标准时间
	SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
	java.util.Date date = null;
	java.util.Date mydate = null;
	try {
		date = myFormatter.parse(date1);
		mydate = myFormatter.parse(date2);
	} catch (Exception e) {
	}
	long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
	return day;
}

// 计算当月最后一天,返回字符串
public String getDefaultDay() {
	String str = "";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	Calendar lastDate = Calendar.getInstance();
	lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
	lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
	lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

	str = sdf.format(lastDate.getTime());
	return str;
}

// 上月第一天
public String getPreviousMonthFirst() {
	String str = "";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	Calendar lastDate = Calendar.getInstance();
	lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
	lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
	// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天

	str = sdf.format(lastDate.getTime());
	return str;
}

// 获取当月第一天
public String getFirstDayOfMonth() {
	String str = "";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	Calendar lastDate = Calendar.getInstance();
	lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
	str = sdf.format(lastDate.getTime());
	return str;
}

// 获得本周星期日的日期
public String getCurrentWeekday() {
	weeks = 0;
	int mondayPlus = this.getMondayPlus();
	GregorianCalendar currentDate = new GregorianCalendar();
	currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
	Date monday = currentDate.getTime();

	DateFormat df = DateFormat.getDateInstance();
	String preMonday = df.format(monday);
	return preMonday;
}

// 获取当天时间
public String getNowTime() {
	Date now = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 可以方便地修改日期格式
	String hehe = dateFormat.format(now);
	return hehe;
}

// 获得当前日期与本周日相差的天数
private int getMondayPlus() {
	Calendar cd = Calendar.getInstance();
	// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
	int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
	if (dayOfWeek == 1) {
		return 0;
	} else {
		return 1 - dayOfWeek;
	}
}

// 获得本周一的日期
public String getMondayOFWeek() {
	weeks = 0;
	int mondayPlus = this.getMondayPlus();
	GregorianCalendar currentDate = new GregorianCalendar();
	currentDate.add(GregorianCalendar.DATE, mondayPlus);
	Date monday = currentDate.getTime();

	DateFormat df = DateFormat.getDateInstance();
	String preMonday = df.format(monday);
	return preMonday;
}

// 获得相应周的周六的日期
public String getSaturday() {
	int mondayPlus = this.getMondayPlus();
	GregorianCalendar currentDate = new GregorianCalendar();
	currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
	Date monday = currentDate.getTime();
	DateFormat df = DateFormat.getDateInstance();
	String preMonday = df.format(monday);
	return preMonday;
}

// 获得上周星期日的日期
public String getPreviousWeekSunday() {
	weeks = 0;
	weeks--;
	int mondayPlus = this.getMondayPlus();
	GregorianCalendar currentDate = new GregorianCalendar();
	currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks);
	Date monday = currentDate.getTime();
	DateFormat df = DateFormat.getDateInstance();
	String preMonday = df.format(monday);
	return preMonday;
}

// 获得上周星期一的日期
public String getPreviousWeekday() {
	weeks--;
	int mondayPlus = this.getMondayPlus();
	GregorianCalendar currentDate = new GregorianCalendar();
	currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
	Date monday = currentDate.getTime();
	DateFormat df = DateFormat.getDateInstance();
	String preMonday = df.format(monday);
	return preMonday;
}

// 获得下周星期一的日期
public String getNextMonday() {
	weeks++;
	int mondayPlus = this.getMondayPlus();
	GregorianCalendar currentDate = new GregorianCalendar();
	currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
	Date monday = currentDate.getTime();
	DateFormat df = DateFormat.getDateInstance();
	String preMonday = df.format(monday);
	return preMonday;
}

// 获得下周星期日的日期
public String getNextSunday() {

	int mondayPlus = this.getMondayPlus();
	GregorianCalendar currentDate = new GregorianCalendar();
	currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
	Date monday = currentDate.getTime();
	DateFormat df = DateFormat.getDateInstance();
	String preMonday = df.format(monday);
	return preMonday;
}

private int getMonthPlus() {
	Calendar cd = Calendar.getInstance();
	int monthOfNumber = cd.get(Calendar.DAY_OF_MONTH);
	cd.set(Calendar.DATE, 1);// 把日期设置为当月第一天
	cd.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
	MaxDate = cd.get(Calendar.DATE);
	if (monthOfNumber == 1) {
		return -MaxDate;
	} else {
		return 1 - monthOfNumber;
	}
}

// 获得上月最后一天的日期
public String getPreviousMonthEnd() {
	String str = "";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	Calendar lastDate = Calendar.getInstance();
	lastDate.add(Calendar.MONTH, -1);// 减一个月
	lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
	lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
	str = sdf.format(lastDate.getTime());
	return str;
}

// 获得下个月第一天的日期
public String getNextMonthFirst() {
	String str = "";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	Calendar lastDate = Calendar.getInstance();
	lastDate.add(Calendar.MONTH, 1);// 减一个月
	lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
	str = sdf.format(lastDate.getTime());
	return str;
}

// 获得下个月最后一天的日期
public String getNextMonthEnd() {
	String str = "";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	Calendar lastDate = Calendar.getInstance();
	lastDate.add(Calendar.MONTH, 1);// 加一个月
	lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
	lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
	str = sdf.format(lastDate.getTime());
	return str;
}

// 获得明年最后一天的日期
public String getNextYearEnd() {
	String str = "";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	Calendar lastDate = Calendar.getInstance();
	lastDate.add(Calendar.YEAR, 1);// 加一个年
	lastDate.set(Calendar.DAY_OF_YEAR, 1);
	lastDate.roll(Calendar.DAY_OF_YEAR, -1);
	str = sdf.format(lastDate.getTime());
	return str;
}

// 获得明年第一天的日期
public String getNextYearFirst() {
	String str = "";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	Calendar lastDate = Calendar.getInstance();
	lastDate.add(Calendar.YEAR, 1);// 加一个年
	lastDate.set(Calendar.DAY_OF_YEAR, 1);
	str = sdf.format(lastDate.getTime());
	return str;

}

// 获得本年有多少天
private int getMaxYear() {
	Calendar cd = Calendar.getInstance();
	cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
	cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
	int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
	return MaxYear;
}

private int getYearPlus() {
	Calendar cd = Calendar.getInstance();
	int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
	cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
	cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
	int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
	if (yearOfNumber == 1) {
		return -MaxYear;
	} else {
		return 1 - yearOfNumber;
	}
}

// 获得本年第一天的日期
public String getCurrentYearFirst() {
	int yearPlus = this.getYearPlus();
	GregorianCalendar currentDate = new GregorianCalendar();
	currentDate.add(GregorianCalendar.DATE, yearPlus);
	Date yearDay = currentDate.getTime();
	DateFormat df = DateFormat.getDateInstance();
	String preYearDay = df.format(yearDay);
	return preYearDay;
}

// 获得本年最后一天的日期 *
public String getCurrentYearEnd() {
	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
	String years = dateFormat.format(date);
	return years + "-12-31";
}

// 获得上年第一天的日期 *
public String getPreviousYearFirst() {
	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
	String years = dateFormat.format(date);
	int years_value = Integer.parseInt(years);
	years_value--;
	return years_value + "-1-1";
}

// 获得上年最后一天的日期
public String getPreviousYearEnd() {
	weeks--;
	int yearPlus = this.getYearPlus();
	GregorianCalendar currentDate = new GregorianCalendar();
	currentDate.add(GregorianCalendar.DATE, yearPlus + MaxYear * weeks
			+ (MaxYear - 1));
	Date yearDay = currentDate.getTime();
	DateFormat df = DateFormat.getDateInstance();
	String preYearDay = df.format(yearDay);
	getThisSeasonTime(11);
	return preYearDay;
}

// 获得本季度
public String getThisSeasonTime(int month) {
	int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
	int season = 1;
	if (month >= 1 && month <= 3) {
		season = 1;
	}
	if (month >= 4 && month <= 6) {
		season = 2;
	}
	if (month >= 7 && month <= 9) {
		season = 3;
	}
	if (month >= 10 && month <= 12) {
		season = 4;
	}
	int start_month = array[season - 1][0];
	int end_month = array[season - 1][2];

	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
	String years = dateFormat.format(date);
	int years_value = Integer.parseInt(years);

	int start_days = 1;// years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);
	int end_days = getLastDayOfMonth(years_value, end_month);
	String seasonDate = years_value + "-" + start_month + "-" + start_days
			+ ";" + years_value + "-" + end_month + "-" + end_days;
	return seasonDate;

}

/**
 * 获取某年某月的最后一天
 * 
 * @param year
 *            年
 * @param month
 *            月
 * @return 最后一天
 */
private int getLastDayOfMonth(int year, int month) {
	if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
			|| month == 10 || month == 12) {
		return 31;
	}
	if (month == 4 || month == 6 || month == 9 || month == 11) {
		return 30;
	}
	if (month == 2) {
		if (isLeapYear(year)) {
			return 29;
		} else {
			return 28;
		}
	}
	return 0;
}
	
	/**
	 * This method generates a string representation of a date based on the
	 * System Property 'dateFormat' in the format you specify on input
	 * 
	 * @param aDate
	 *            A date to convert
	 * @return a string representation of the date
	 */
	public static String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 * 
	 * @param strDate
	 *            the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * @throws ParseException
	 *             when String doesn't match the expected format
	 */
	public static Date convertStringToDate(String strDate)
			throws ParseException {
		Date aDate = null;

		try {
			if (log.isDebugEnabled()) {
				log.debug("converting date with pattern: " + getDatePattern());
			}

			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate
					+ "' to a date, throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return aDate;
	}

	/**
	 * ��ȡ����ʱ�䡣 
	 * @param beginDateTime
	 * @param timeExpiry     ��λΪ�롣
	 * @return
	 * @throws ParseException
	 */
	public static Date getExpiryDateTime(String beginDateTime, long timeExpiry)
	throws ParseException {
		
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date d1 = sim.parse(beginDateTime);
		long time = d1.getTime();
		
		timeExpiry = timeExpiry * 1000;
		
		time += timeExpiry;
		
		return new Date(time);
	}
	/**
	 * 过期时间
	 * @param beginDate
	 * @param timeExpiry
	 * @return
	 * @throws ParseException
	 */
	public static Date getExpiryDateTime(Date beginDate, long timeExpiry)
			throws ParseException {
		long time = beginDate.getTime();
		timeExpiry = timeExpiry * 1000;
		time += timeExpiry;
		return new Date(time);
	}
	
    /**
     * 从一个日期取得间隔一段时间的另一个日期
     * @param date
     *           参照日期，从该日期开始计算
     * @param distance
     *           间隔时间量，- 表示之前，+ 表示之后
     * @param type
     *           间隔单位：年(year)，月(month)，日(date)，周(week)
     * @return date
     */
    public static Date addDate(Date date,int distance,String type) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	if ("date".equalsIgnoreCase(type)) {
    		cal.add(Calendar.DATE, distance);
    	}
    	if ("month".equalsIgnoreCase(type)) {
    		cal.add(Calendar.MONTH, distance);
    	}
    	if ("year".equalsIgnoreCase(type)) {
    		cal.add(Calendar.YEAR, distance);
    	}
    	if ("week".equalsIgnoreCase(type)) {
    		cal.add(Calendar.DATE, distance*7);
    	}
    	return cal.getTime();
    }
    
    /**
     * @param date
     * @return X秒前，Y分钟前，a月b日
     */
    public static String dateInterval2(Date myDate){
    	//TODO:如果是空，就用当前时间
    	if(myDate == null)
    		myDate = new Date();
		final long _SECOND = 1000L;//1秒
		final long _MINUTES = _SECOND*60L;//1分钟
		final long _HOUR = _MINUTES*60L;//1小时
		final long _DAY = _HOUR*24L;//1天
		
		Calendar myCalendar = Calendar.getInstance();
		myCalendar.setTime(myDate);
	    int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
	    int day = myCalendar.get(Calendar.DAY_OF_MONTH);
 		String minute=(myCalendar.get(Calendar.MINUTE)>9)?""+myCalendar.get(Calendar.MINUTE):"0" + myCalendar.get(Calendar.MINUTE);
		
 		Calendar calendar = Calendar.getInstance();
 		calendar.setTimeInMillis(System.currentTimeMillis());
// 		int nowHours = calendar.get(Calendar.HOUR_OF_DAY);
 		int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
 		long longtime =calendar.getTime().getTime()- myDate.getTime();
	    String str="";
	    if (longtime > _DAY){  //>24小时
	    	str = getDateTime("MM-dd HH:mm",myDate);
	    }else if (longtime > _HOUR){//1~24小时:a在今天，b不在今天
	    	if(day < nowDay)
	    		str = getDateTime("MM-dd HH:mm",myDate);
	    	else
	    		str = hour+":"+minute+"d";
	    }else if (longtime > _MINUTES){ //时间在分钟的范围内 1~60分钟
	    	str = Math.round(longtime/_MINUTES)+"m";
	    }else if (longtime > _SECOND){ //时间在秒的范围内   1~60秒
	    	str = Math.round(longtime/_SECOND)+"s";
	    }else{
	    	str = 1+"s";
	    }		
    	return str;
    }
    
    public static Long  dateInterval(Date date){
		Long dateInterval = null;
		Calendar calendar = Calendar.getInstance();
		Date begin = calendar.getTime();
		dateInterval = (begin.getTime()-date.getTime())/(60000); 
		return dateInterval;
	}
 
	public static Boolean isSameDay(Date date){
		Boolean isSameDay = false;
		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date);
		int year =calendar.get(Calendar.YEAR);
		int year1 =calendar1.get(Calendar.YEAR);
		if(year!=year1){
			return isSameDay;
		}
		int day =calendar.get(Calendar.DAY_OF_YEAR);
		int day1 =calendar1.get(Calendar.DAY_OF_YEAR);
		if(day==day1){
			isSameDay=true;
		}
		
		return isSameDay;
	}
	
	public static Boolean checkYear(String date){
		if(StringUtil.isEmpty(date)||date.length()!=4){
		    return false;
		}
	    
		String format = "((19|20)[0-9]{2})";
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}
	
	
	   
    public static Boolean checkYearAndMonth(String date){
        if(StringUtil.isEmpty(date)||date.length()!=6){
            return false;
        }
        
        String year= date.substring(0,4);
        String month = date.substring(4,2);
        //校验年
        String format = "((19|20)[0-9]{2})";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(year);
        if(!matcher.matches()){
            return false;
        }
        try{
           if( Integer.valueOf(month).intValue()>12){
               return false;
           }
        }catch(Exception e){
            return false;
        }
        return true;
    }
    
    
    public static List<String> getDatesBetweenTwoDate(String beginDate, String endDate) {  
        List<String> lDate = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date beginDates=null;
        Date endDates=null;
		try {
			beginDates = sdf.parse(beginDate);
			 endDates=sdf.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        lDate.add(beginDate);// 把开始时间加入集合  
        Calendar cal = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间  
        cal.setTime(beginDates);  
        boolean bContinue = true;  
        while (bContinue) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
            cal.add(Calendar.DAY_OF_MONTH, 1);  
            // 测试此日期是否在指定日期之后  
            if (endDates.after(cal.getTime())) {  
                lDate.add(DateUtil.getDate(cal.getTime(),"yyyyMMdd"));  
            } else {  
                break;  
            }  
        }  
        lDate.add(endDate);// 把结束时间加入集合  
        return lDate;  
    } 
    
    /**
     * 根据开始和结束时间返回周期
     * @param beginDate 开始时间
     * @param endDate 结束时间
     * @param dateType 1:周一 2:周二   3:周三 4:周四  5:周五 6:周六 7:周天
     * @return
     */
    public static String[][] getPeriod(String beginDate, String endDate,int dateType){
    	
    	
    	String arrStr[][]=new String[10][2];
    	
    	//获取开始结束时间内日期
    	List<String> listDate=DateUtil.getDatesBetweenTwoDate(beginDate, endDate);
    	int max=0;
    	//数据总大小
    	int count=0;
    	//初始赋值
    	int aa=0;
    	//二维数据下标
    	int arr=0;
    	if(dateType== 1){
    		max=6;
    	}else if(dateType ==2){
    		max=5;
    	}else if(dateType ==3){
    		max=4;
    	}else if(dateType ==4){
    		max=3;
    	}else if(dateType ==5){
    		max=2;
    	}else if(dateType ==6){
    		max=1;
    	}else if(dateType ==7){
    		max=0;
    	}
    	String bDate="";
    	String eDates="";
    	if(listDate != null && listDate.size()>0){
    		count=listDate.size();
    		for(int i=0;i<listDate.size();i++){
    			 if(i<count){
    				 bDate=listDate.get(i);
    				 arrStr[arr][0]=bDate;
    			 }
    			 if(aa == 0){
    				 i = max;
    				 aa = 1;
    			 }else{
    				 i=i+6;
    			 }
    			 if(i < count){
    				 eDates = listDate.get(i);
    				 arrStr[arr][1] = eDates;
    			 }else{
    				 eDates=endDate;
    				 arrStr[arr][1] = eDates;
    				 break;
    			 }
    			 arr++;
    		}
    	}
    	return arrStr;
    }
    
    
	
//        //定义二维数组  
//        int[ ] [ ] arr={{1,2},{4,3},{4,5},{4,6}};  
//        //静态初始化  
//           
//        //打印出二维数组  
//          
//        for(int i=0;i<arr.length;i++){  
//              
//            for(int j=0;j<arr[i].length;j++){  
//                      
//                System.out.print(arr[i][j]+" ");  
//                  
//            }  
//            //输出一列后就回车空格  
//            System.out.println();  
//              
//        }  
		
//		String [][] str=DateUtil.getPeriod("20160901","20160930",3);
//		for(int i=0;i<str.length;i++){
//			String s=str[i][0];
//			String sd=str[i][1];
//			if(StringUtils.isNotEmpty(s) && StringUtils.isNotEmpty(sd)){
//			System.out.println(s+"==="+sd);
//			}
//		}
		
		
		
		
//		  Date da=DateUtil.getDate("20170104", "yyyyMMdd");
////		  System.out.println(da);
//		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式  
//	        Calendar cal = Calendar.getInstance();  
//	        cal.setTime(da);  
//	        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  
//	        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
//	        if (1 == dayWeek) {  
//	            cal.add(Calendar.DAY_OF_MONTH, -1);  
//	        }  
//	        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期  
//	        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  
//	        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天  
//	        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值  
//	        String imptimeBegin = sdf.format(cal.getTime());  
//	        System.out.println("所在周星期一的日期：" + imptimeBegin);  
//	        cal.add(Calendar.DATE, 6);  
//	        String imptimeEnd = sdf.format(cal.getTime());  
//	        System.out.println("所在周星期天的日期：" + imptimeEnd);  
//	        
//	        System.out.println(getMondayOfWeek("20170104"));
//	        System.out.println(getSundayOfWeek("20170104"));
//	        System.out.println(getFirstDayOfQuarter(da));
//	        System.out.println(getLastDayOfQuarter(da));

	
	/**
	 * 根据日期取得对应周周一日期 
	 * @param date
	 * @return
	 */
	public static String getMondayOfWeek(String date) {  
		
		
		
		
		Date da=DateUtil.parseDateMinuteFormat1(date);
		
        Calendar monday = Calendar.getInstance();  
        monday.setTime(da);  
        monday.setFirstDayOfWeek(Calendar.MONDAY);  
        monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
        return  DateUtil.getDate(monday.getTime(),"yyyyMMdd"); 
    }  
	
	/**
	 * 根据日期取得对应周周日日期 
	 * @param date
	 * @return
	 */
	public static String getSundayOfWeek(String date) {  
		Date da=DateUtil.parseDateMinuteFormat1(date);
        Calendar sunday = Calendar.getInstance();  
        sunday.setTime(da);  
        sunday.setFirstDayOfWeek(Calendar.MONDAY);  
        sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  
        return  DateUtil.getDate(sunday.getTime(),"yyyyMMdd"); 
    }
	
	
	public static String getFirstDayOfQuarter(Date date)   {     
        Calendar cDay = Calendar.getInstance();     
        cDay.setTime(date);  
        int curMonth = cDay.get(Calendar.MONTH);  
        if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH){    
            cDay.set(Calendar.MONTH, Calendar.JANUARY);  
        }  
        if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE){    
            cDay.set(Calendar.MONTH, Calendar.APRIL);  
        }  
        if (curMonth >= Calendar.JULY && curMonth <= Calendar.AUGUST) {    
            cDay.set(Calendar.MONTH, Calendar.JULY);  
        }  
        if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {    
            cDay.set(Calendar.MONTH, Calendar.OCTOBER);  
        }  
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMinimum(Calendar.DAY_OF_MONTH));  
        
        return  DateUtil.getDate(cDay.getTime(),"yyyyMMdd"); 
	}
	 /** 
     * 得到本季度最后一天的日期 
     * @Methods Name getLastDayOfQuarter 
     * @return Date 
     */  
    public static String getLastDayOfQuarter(Date date)   {     
        Calendar cDay = Calendar.getInstance();     
        cDay.setTime(date);  
        int curMonth = cDay.get(Calendar.MONTH);  
        if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH){    
            cDay.set(Calendar.MONTH, Calendar.MARCH);  
        }  
        if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE){    
            cDay.set(Calendar.MONTH, Calendar.JUNE);  
        }  
        if (curMonth >= Calendar.JULY && curMonth <= Calendar.AUGUST) {    
            cDay.set(Calendar.MONTH, Calendar.AUGUST);  
        }  
        if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {    
            cDay.set(Calendar.MONTH, Calendar.DECEMBER);  
        }  
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return  DateUtil.getDate(cDay.getTime(),"yyyyMMdd"); 
    }  
    

    
    public static String getSpecifiedDayBefore(String specifiedDay) {//可以用new Date().toLocalString()传递参数  
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
  
        String dayBefore = new SimpleDateFormat("yyyyMMdd").format(c  
                .getTime());  
        return dayBefore;  

    } 
    
    public static void main(String[] args) {
		Date da=DateUtil.parseDateMinuteFormat("1990-01-01 01:01");
		
		Date dd = format("2019-02-19 13:44:55","yyyy-MM-dd HH:mm:ss");
	    System.out.println(da.getTime());
	    System.out.println(dd);
	    
	    Date date=new Date(631126860000l);
	    System.out.println(date);
    }
}
