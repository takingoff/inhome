 /**   
* projectName: InnInfrastructure
*
* fileName: DateTimeUtil.java 
*
* author : tangli <tanglidehaizi@gamil.com>
*
* createTime :2014 2014-5-4 下午12:06:47 
*
* version : V1.0 
*/
package tang.li.inn.infrastructure.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *<description>
 *@author tangli <tanglidehaizi@gamil.com>
 *@version V1.0 
 *@see 
 *@since
 */
public class DateTimeUtil
{
	
	
	public static String convertCalendarToInnDateTimeFormat(Calendar cl)
	{
		DateFormat format  = new SimpleDateFormat(InnConstant.INN_DATE_TIME_FORMAT);
		return format.format(cl.getTime());
	}
	
	/**
	 * 现在时间转换成相应格式的字符串
	 * @param formatString   比如 ："yyyy-MM-dd HH:mm:ss"
	 * @param dayDistance	  和现在间隔的天数
	 * @param hourDistance	和现在间隔的小时数
	 * @param minuteDistance 和现在间隔的分钟数
	 * @return
	 */
	public static String convertNowToInnDateTimeFormat(int dayDistance,int hourDistance,int minuteDistance)
	{
		DateFormat format  = new SimpleDateFormat(InnConstant.INN_DATE_TIME_FORMAT);
		Calendar cl = Calendar.getInstance(TimeZone.getTimeZone(InnConstant.TIME_ZONE));
		cl.add(Calendar.DATE,dayDistance);
		cl.add(Calendar.HOUR,hourDistance);
		cl.add(Calendar.MINUTE,minuteDistance);
		return format.format(cl.getTime());
	}
	
	/**
	 * 现在时间转换成相应格式的字符串
	 * @param dayDistance 和现在间隔的天数
	 * @return
	 */
	public static String convertNowToInnDateFormat(int dayDistance)
	{
		DateFormat format  = new SimpleDateFormat(InnConstant.INN_DATE_FORMAT);
		Calendar cl = Calendar.getInstance(TimeZone.getTimeZone(InnConstant.TIME_ZONE));
		cl.add(Calendar.DATE,dayDistance);
		return format.format(cl.getTime());
	}
	
	
	/**
	 * @param dateString 2002-2-2
	 * @param dayDistance
	 * @return
	 */
	public static String convertDateStringToInnDateFormat(String dateString,int dayDistance)
	{
		DateFormat format  = new SimpleDateFormat(InnConstant.INN_DATE_FORMAT);
		try
		{
			Date date = format.parse(dateString);
			Calendar cal=Calendar.getInstance(TimeZone.getTimeZone(InnConstant.TIME_ZONE)); 
			cal.setTime(date); 
			cal.add(Calendar.DATE,dayDistance);
			return format.format(cal.getTime());
		}
		catch (ParseException e)
		{
			return convertNowToInnDateFormat(0);
		}
		
	}
	
	
	/**
	 * 
	 * @param dateTimeString  yyyy-MM-dd HH:mm:ss
	 * @param dayDistance
	 * @param hourDistance
	 * @param minuteDistance
	 * @return
	 */
	public static String convertDateTimeStringToInnDateTimeFormat(String dateTimeString,int dayDistance,int hourDistance,int minuteDistance)
	{
		DateFormat format  = new SimpleDateFormat(InnConstant.INN_DATE_TIME_FORMAT);
		try
		{
			Date date = format.parse(dateTimeString);
			Calendar cal=Calendar.getInstance(TimeZone.getTimeZone(InnConstant.TIME_ZONE)); 
			cal.setTime(date); 
			cal.add(Calendar.DATE,dayDistance);
			cal.add(Calendar.HOUR,hourDistance);
			cal.add(Calendar.MINUTE,minuteDistance);
			return format.format(cal.getTime());
		}
		catch (ParseException e)
		{
			return convertNowToInnDateFormat(0);
		}
		
	}
	
	
	public static int countDayDistance(Calendar cl1, Calendar cl2)
	{
		// 保证 cl1 在cl2之前
		if (cl2.before(cl1))
		{
			Calendar cl = cl1;
			cl1 = cl2;
			cl2 = cl;
		}
		int dayDistance = 0; // 存放入住日期和现在日期的相隔天数 可能是小数 （半天！）
		int year = cl1.get(Calendar.YEAR);
		int yearDistance = cl2.get(Calendar.YEAR) - year; // 相隔年数
		// 客户跨年入住 情况下获取 间隔天数！
		if (yearDistance != 0)
		{
			// 获取入住当年总共入住天数
			dayDistance += cl1.getActualMaximum(Calendar.DAY_OF_YEAR)
					- cl1.get(Calendar.DAY_OF_YEAR);
			// 获取以后几年的全年天数
			for (int i = 1; i < yearDistance; i++)
			{
				cl1.set(Calendar.YEAR, year + i);
				dayDistance += cl1.getActualMaximum(Calendar.DAY_OF_YEAR);
			}
			// 获取今年的入住天数
			dayDistance += cl2.get(Calendar.DAY_OF_YEAR);
		}
		// 未跨年住获取间隔天数
		else
		{
			dayDistance = cl2.get(Calendar.DAY_OF_YEAR) - cl1.get(Calendar.DAY_OF_YEAR);
		}
		return dayDistance;
	}

	public static int countMinuteDistance(int beforeMinute, int beforeHour, int afterMinute,
			int afterHour)
	{
		return Math.abs((afterHour - beforeHour) * 60 + (afterMinute - beforeMinute));
	}
	
	public static Calendar convertInnDateTimeStringToCalendar(String dateString)
	{
		DateFormat format  = new SimpleDateFormat(InnConstant.INN_DATE_TIME_FORMAT);
		Calendar cal=Calendar.getInstance(TimeZone.getTimeZone(InnConstant.TIME_ZONE)); 
		try
		{
			Date date = format.parse(dateString);
			cal.setTime(date); 
		}
		catch (ParseException e)
		{
		}
		return cal;
	}
	
}
