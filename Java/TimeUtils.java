package com.eht.y1da.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.eht.y1da.Version;

/**
 * 日期时间工具类
 * 
 * @author xquan
 * @version 1.0 2016-05-13
 * @since 1.0
 */
public class TimeUtils implements Serializable{
	private static final long serialVersionUID = 8494167640471712987L;
	final static int oneDayContHor = 24;
	// 预定义格式字符串
	public static final String FORMAT_ONLY_DATE_EN = "yyyy-MM-dd";
	public static final String FORMAT_MONTH_DAY_EN = "MM-dd";
	public static final String FORMAT_MONTH_DAY_EN2 = "MM/dd";
	public static final String FORMAT_MONTH_DAY_CHS = "MM月dd日";
	public static final String FORMAT_ONLY_DATE_CHS = "yyyy年MM月dd日";
	public static final String FORMAT_FULL_DATETIME_EN = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_YMDHM_DATETIME_EN = "yyyy-MM-dd HH:mm";
	public static final String FORMAT_FULL_DATETIME_LONG_EN = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String FORMAT_FULL_DATETIME_CHS = "yyyy年MM月dd日 HH时mm分ss秒";
	public static final String FORMAT_HOUR_MIN_SEC_EN = "HH:mm:ss";
	public static final String FORMAT_MDHM_DATETIME_EN = "MM-dd HH:mm";
	public static final String FORMAT_HOUR_MIN_EN = "HH:mm";
	public static final String FORMAT_MONTH = "MM";
	
	
	public static final String SERIAL_LONG = "yyyyMMddHHmmssSSS";
	public static final String SERIAL_ONLY_DATE = "yyyyMMdd";
	public static final String SERIAL_ONLY_MONTH = "yyyyMM";
	public static final String SERIAL_FULL_DATETIME = "yyyyMMddHHmmss";
	public static final String SERIAL_YMDHM_DATETIME = "yyyyMMddHHmm";
	public static final String SERIAL_MONTH_DAY = "MMdd";
	public static final String SERIAL_HOUR_MIN_SEC = "HHmmss";
	public static final String SERIAL_NOW = "yyyyMMddHHmmss";
	// 预定义控制参数
	public static final int FIRST_DAY_OF_WEEK = 1; // 星期的第一天
	public static final int FIRST_DAY_OF_MONTH = 2; // 月份的第一天
	public static final int FIRST_DAY_OF_QUARTER = 3; // 季度的第一天
	public static final int FIRST_DAY_OF_YEAR = 4; // 年份的第一天
	public static final int LAST_DAY_OF_WEEK = 5; // 星期的最后一天
	public static final int LAST_DAY_OF_MONTH = 6; // 月份的最后一天
	public static final int LAST_DAY_OF_QUARTER = 7; // 季度的最后一天
	public static final int LAST_DAY_OF_YEAR = 8; // 年份的最后一天
	public static final int DAY_OF_NEXT_WEEK = 9; // 下周今日
	public static final int DAY_OF_NEXT_MONTH = 10; // 下月今日
	public static final int DAY_OF_NEXT_YEAR = 11; // 明年今日

	public static final int YEAR = 21; // 年
	public static final int MONTH = 22; // 月
	public static final int QUARTER = 23; // 季度
	public static final int DAY_OF_YEAE = 24; // 年中天数
	public static final int DAY_OF_WEEK_CHS = 25; // 周中天数 周一为第一天
	public static final int DAY_OF_WEEK_EN = 26; // 周中天数 西方习惯 周日为第一天
	
	/**
	 * 获取当前时间(yyyy-MM-dd HH:mm:ss)
	 * @author xquan
	 * @return
	 */
	public static String getCurrentTime(){
		DateTime date = new DateTime();
		String dateStr = date.toString("yyyy-MM-dd HH:mm:ss");
		return dateStr;
	}
	public static String getTodayTime(){
		DateTime date = new DateTime();
		String dateStr = date.toString("yyyy-MM-dd");
		return dateStr;
	}
	
	public static String getDDTime(){
		DateTime date = new DateTime();
		String dateStr = date.toString("dd");
		return dateStr;
	}
	
	public static String getDMTime(){
		DateTime date = new DateTime();
		String dateStr = date.toString("MM");
		return dateStr;
	}
	
	public static String getYearTime(){
		DateTime date = new DateTime();
		String dateStr = date.toString("yyyy");
		return dateStr;
	}
	
	/**
	 * 获取当前时间
	 * yyyyMMddHHmmss
	 * @author xfbian
	 * @return 
	 */
	public static String getCurrentTimeNow(){
		DateTime date = new DateTime();
		String dateStr = date.toString(SERIAL_NOW);
		return dateStr;
	}
	public static String getCurrentTimeNow2(){
		DateTime date = new DateTime();
		String dateStr = date.toString(FORMAT_YMDHM_DATETIME_EN);
		return dateStr;
	}
	
	
	/**
	 * 时间格式转换（yyyy-MM-dd HH:mm:ss or yyyy-MM-dd）（String 转 String）
	 * @author xquan
	 * @param {format1：传入的日期格式，format2：传出的日期格式，dateTimeStr：日期字符串}（日期格式从“预定义格式字符串”中取）
	 */
	public static String dateFormatStrToStr(String format1,String format2,String dateTimeStr)throws Exception {
		if (format1 == null || format2 == null || dateTimeStr == null)
			throw new Exception("日期格式化失败：参数为空");
		String timeStr = null;
		DateTimeFormatter df = DateTimeFormat.forPattern(format1);
		DateTime dateFormatTime = DateTime.parse( dateTimeStr, df);
		timeStr = dateFormatTime.toString(format2);
		return  timeStr;
	}
	
	/** 
	 * 根据指定模式格式化日期时间（yyyy-MM-dd HH:mm:ss）（DateTime 转 String）
	 * @param format
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String dateFormatTimeToStr(String format,DateTime date) throws Exception{
		String dateFormatted = null;
		if (date == null)
			throw new Exception("日期格式化失败：参数为空");
		DateTimeFormatter df = DateTimeFormat.forPattern(format);
		dateFormatted = date.toString(df);
		return dateFormatted;
	}
	
	/**
	 * 时间格式转化（yyyy-MM-dd HH:mm:ss）（DateTime 转 DateTime）
	 * @author xquan
	 * @param dateTime
	 * @return dateTime
	 */
	public static DateTime dateFormatTimeToTime(String format, DateTime date)throws Exception {
		DateTime dateFormatted = null;
		if (format == null || date == null)
			throw new Exception("日期格式化失败：参数为空");
		DateTimeFormatter df = DateTimeFormat.forPattern(format);
		String startDateStr = date.toString(df);
		dateFormatted = DateTime.parse(startDateStr, df);
		return dateFormatted;
	} 
	
	/**s
	 * 时间格式转化（yyyy-MM-dd HH:mm:ss）（String 转 DateTime）
	 * @author xquan
	 * @param String
	 * @return dateTime
	 */
	public static DateTime dateFormatStrToTime(String format, String dateTimeStr)throws Exception {
		DateTime dateFormatted = null;
		if (format == null || dateTimeStr == null)
			throw new Exception("日期格式化失败：参数为空");
		DateTimeFormatter df = DateTimeFormat.forPattern(format);
		dateFormatted = DateTime.parse(dateTimeStr, df);
		return dateFormatted;
	}
	
	/**
	 * 两个日期之间的相差(eDate - sDate)(type:1.年  2.月  3.日  4.时  5.分  6.秒  7.毫秒)
	 * @author xquan
	 * @param sDate
	 * @param eDate
	 * @return 小时数
	 */
	public static int subDate(DateTime sDate, DateTime eDate,int type) throws Exception{
		if(sDate == null || eDate == null ){
			throw new Exception("日期格式化失败：参数为空");
		}
		int subRes = 0;
		switch(type){
		case 1:
			Period p1 = new Period(sDate, eDate, PeriodType.years());
			subRes = p1.getYears();
			break;
		case 2:
			Period p2 = new Period(sDate, eDate, PeriodType.months());
			subRes = p2.getMonths();
			break;
		case 3: 
			Period p3 = new Period(sDate, eDate, PeriodType.days());
			subRes = p3.getDays();
			break;		
		case 4:
			Period p4 = new Period(sDate, eDate, PeriodType.hours());
			subRes = p4.getHours();
			break;
		case 5:
			Period p5 = new Period(sDate, eDate, PeriodType.minutes());
			subRes = p5.getMinutes();
			break;
		case 6:
			Period p6 = new Period(sDate, eDate, PeriodType.seconds());
			subRes = p6.getSeconds();
			break;
		case 7:
			Period p7 = new Period(sDate, eDate, PeriodType.millis());
			subRes = p7.getMillis();
			break;
		};
		return subRes;
	}
	
	/**获取传入的日期星期几
	 * 
	 * @param dt
	 * @return{"星期一" or  "星期二" or  "星期三" or  "星期四" or  "星期五" or  "星期六" or "星期日"}
	 */
	public static String getWeekOfDate(DateTime dt)
	{
		String result = "";
		String[] weekDays = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六","星期日" };
		switch(dt.getDayOfWeek()){
		case DateTimeConstants.SUNDAY: 
			result = weekDays[DateTimeConstants.SUNDAY - 1];
			break;
		case DateTimeConstants.MONDAY: 
			result = weekDays[DateTimeConstants.MONDAY - 1];
			break;
		case DateTimeConstants.TUESDAY: 
			result = weekDays[DateTimeConstants.TUESDAY - 1];
			break;
		case DateTimeConstants.WEDNESDAY: 
			result = weekDays[DateTimeConstants.WEDNESDAY - 1];
			break;
		case DateTimeConstants.THURSDAY: 
			result = weekDays[DateTimeConstants.THURSDAY - 1];
			break;
		case DateTimeConstants.FRIDAY: 
			result = weekDays[DateTimeConstants.FRIDAY - 1];
			break;
		case DateTimeConstants.SATURDAY: 
			result = weekDays[DateTimeConstants.SATURDAY - 1];
			break;
		}
		return result;
	}
	
	/**获取传入的日期星期几
	 * 
	 * @param dt
	 * @return {1 or 2 or 3 or 4 or 5 or 6 or 7}
	 */
	public static String getWeekOfDateWithNo(DateTime dt)
	{
		
		return Integer.toString(dt.getDayOfWeek());
	}
	
	
	/**
	 * 获取当前日期所在的年份、月份、季度数等要素
	 */
	public static int getCurrentDateElement(int console) throws Exception{
		int result = -1;
		DateTime currTime = new DateTime();
		switch (console)
		{
		case YEAR:
			result = currTime.getYear();
			break; // 当前日期所在年份
		case MONTH:
			result = currTime.getMonthOfYear();
			break; // 当前日期所在月份
		case QUARTER:
			result = getCurrentDateQuarter();
			break; // 当前日期所在季度
		case DAY_OF_YEAE:
			result = currTime.getDayOfYear();
			break; // 当前日期所在年中为第N天
		case DAY_OF_WEEK_CHS:
			result = currTime.getDayOfWeek();
			break; // 当前日期所在周中为第N天 周日为第七天
		}

		if (result == -1)
			throw new Exception("getCurrentDateElement()执行失败，控制参数非法");

		return result;

	}
	
	/** 
	 * 当前日期所在季度
	 * @return
	 * @throws Exception
	 */
	private static int getCurrentDateQuarter() throws Exception{
		int quarter = -1;
		Calendar c = Calendar.getInstance();
	
		switch (c.get(Calendar.MONTH))
		{
		case 0:
		case 1:
		case 2:
			quarter = 1;
			break;
		case 3:
		case 4:
		case 5:
			quarter = 2;
			break;
		case 6:
		case 7:
		case 8:
			quarter = 3;
			break;
		case 9:
		case 10:
		case 11:
			quarter = 4;
			break;
		}
		if (quarter == -1)
			throw new Exception("getCurrentDateQuarter()执行失败，未能正确获取季度");
	
		return quarter;
	}
	
	/**
	 *  格式化日期为英文完整格式(yyyy-MM-dd HH:mm:ss)
	 * @param date
	 * @return 
	 * @throws Exception
	 */
	public static String dateTimeFormattedEN(DateTime date) throws Exception
	{
		return dateFormatTimeToStr(FORMAT_FULL_DATETIME_EN, date);
	}
	
	/**
	 *  格式化日期为yyyy-MM-dd的格式
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static String dateTimeFormattedENDate(DateTime date){
		int yearStr = date.getYear();
		int monthStr = date.getMonthOfYear();
		int dayStr = date.getDayOfMonth();
		String dateTimeStr = yearStr + "-" + (monthStr < 10 ? "0" + monthStr : monthStr) + "-" + (dayStr < 10 ? "0" + dayStr : dayStr);
		return dateTimeStr;
	}

	/**
	 *  当天日期简单格式(yyyyMMddHHmmssSSS)
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentSingleDate() throws Exception{
		DateTime currTime = new DateTime();
		int yearStr = currTime.getYear();
		int monthStr = currTime.getMonthOfYear();
		int dayStr = currTime.getDayOfMonth();
		int hourStr = currTime.getHourOfDay();
		int minStr = currTime.getMinuteOfHour();
		int secStr = currTime.getSecondOfMinute();
		String dateTimeStr = yearStr + "" + (monthStr < 10 ? "0" + monthStr : monthStr) + "" + (dayStr < 10 ? "0" + dayStr : dayStr) + "" 
							+ (hourStr < 10 ? "0" + hourStr : hourStr) + "" + (minStr < 10 ? "0" + minStr : minStr ) + "" + (secStr < 10 ? "0" + secStr : secStr);
		return dateTimeStr;
	}	
		
	public static Map<String, Object> addCreateTmAndBy(Map<String, Object> params){
		params.put("createtm", ConvertDateTime.getCurrentTime());
		params.put("createby", Version.NOWVERSIONNAME);
		return params;
	}
	
	public static Map<String, Object> addUpdateTmAndBy(Map<String, Object> params){
		params.put("updtm", ConvertDateTime.getCurrentTime());
		params.put("updby", Version.NOWVERSIONNAME);
		return params;
	}
	
	/**
	 * 获取一段日期对应的（日期+星期几）list
	 * @param startTime （开始时间）
	 * @param days（持续时间）
	 * @return [{curDateStr:xxx,curdayofWeek:xxx},{curDateStr:xxx,curdayofWeek:xxx},...]
	 * @throws Exception
	 */
	public  static List<Map<String, Object>> getDateAndWeekDayData(DateTime startTime, int days) throws Exception{
		List<Map<String, Object>> paramsList = new ArrayList<>();
		for(int i = 0; i < days; i++ ){
			Map<String, Object> params = new HashMap<>();
			if(i != 0){
				startTime = startTime.plusDays(1);
			}
			DateTime curDateTime = startTime;
			String curDateStr = ConvertDateTime.dateFormatTimeToStr(ConvertDateTime.FORMAT_FULL_DATETIME_EN, curDateTime);
			String curdayofWeekStr = ConvertDateTime.getWeekOfDateWithNo(curDateTime);
			params.put("curDateStr", curDateStr);
			params.put("curdayofWeek", curdayofWeekStr);
			paramsList.add(params);
		}
		return paramsList;
	}	
	
	/**
	 * 获取当前日期（DateTime类型）
	 */
	public static DateTime getCurTime(){
		return new DateTime();
	}
	
	/**
	 * 获取当前日期（String）[yyyy-MM-dd]
	 */
	public static String getCurrentDate(){
		return new DateTime().toString(FORMAT_ONLY_DATE_EN);
	}
	
	/**
	 * 获取当前日期（DateTime）[yyyy-MM-dd]
	 */
	public static DateTime getCurDate(){
		DateTime dateFormatted = null;
		DateTimeFormatter df = DateTimeFormat.forPattern(FORMAT_ONLY_DATE_EN);
		String startDateStr = new DateTime().toString(df);
		dateFormatted = DateTime.parse(startDateStr, df);
		return dateFormatted;
	}
	
	/**
	 * 获取当前日期（String）
	 * @param {formatstr}时间格式
	 * @return string
	 */
	public static String getCurrentDateTime(String format){
		return new DateTime().toString(format);
	}
	
	/**
	 * 日期加减加数后，获得新的日期
	 * @param oldTime(传入的日期) addend(加数) type(类型： 1.秒   2.分   3.时   4.天  5.月  6.年)
	 * @param newTime(加后的日期)
	 */
	public static DateTime addDateTime(DateTime oldTime, int addend, int type)throws Exception {
		if(type > 6){
			throw new Exception("传入的类型不对");
		}
		DateTime newTime = null;
		switch(type){
			case 1:newTime = oldTime.plusSeconds(addend);break;
			case 2:newTime = oldTime.plusMinutes(addend);break;
			case 3:newTime = oldTime.plusHours(addend);break;
			case 4:newTime = oldTime.plusDays(addend);break;
			case 5:newTime = oldTime.plusMonths(addend);break;
			case 6:newTime = oldTime.plusYears(addend);break;
		}
		return newTime;
	}
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(gettodaymotch().toString());
		System.out.println(getmonthDays(05));
//		System.out.println(Integer.parseInt(getDDTime()));
//		System.out.println(getlistdatelaster(40));
//		DateTime startDate = dateFormatStrToTime(FORMAT_FULL_DATETIME_EN, "2016-06-20 16:00:00");
//		DateTime startDate1 = dateFormatStrToTime(FORMAT_FULL_DATETIME_EN, "2016-06-21 18:00:00");
//		DateTime startDate2 = dateFormatStrToTime(FORMAT_FULL_DATETIME_EN, "2016-06-20 20:00:00");
//		int subhour = subDate(new DateTime(), startDate, 5);
//		System.out.println("subhour:---->" + subhour);
//		String phonestr = "1232";
//		phonestr = subhour >= -2 * 60 ? phonestr : "tess";
//		System.out.println(subhour);
//		System.out.println(phonestr);
//		subhour = subDate(startDate1, new DateTime(), 5);
//		phonestr = subhour >= -2 * 60 ? phonestr : "tess";
//		System.out.println(subhour);
//		System.out.println(phonestr);
//		subhour = subDate(startDate2, new DateTime(), 5);
//		phonestr = subhour >= -2 * 60 ? phonestr : "test";
//		System.out.println(subhour);
//		System.out.println(phonestr);
		
	}
	
	public static void addCrtUpTmAndBy(Map<String, Object> params){
		String now = ConvertDateTime.getCurrentTime();
		params.put("updtm", now);
		params.put("updby", Version.NOWVERSIONNAME);
		params.put("createtm", now);
		params.put("createby", Version.NOWVERSIONNAME);
	}
	
	public static String getCurTime(DateTime dateTime)throws Exception {
	    String timeStr = null;
	    String dateTimeStr = dateFormatTimeToStr(FORMAT_FULL_DATETIME_EN, dateTime);
	    int subIndex = dateTimeStr.indexOf(" ");
	    timeStr = dateTimeStr.substring(subIndex + 1);
	    return timeStr;
	}
	
	/**
	 * 传入Second，然后返回“*时*分*秒”字符串。
	 * @param seconds
	 * @return str
	 */
	public static String convertSecondToStr(int seconds){
		int tmp = 0;
		String remainTmStr = "";
		if(seconds > 3600){
			tmp = seconds / 3600;
			remainTmStr += tmp + "时";
			seconds = seconds - tmp * 3600;
		}
		if(seconds > 60){
			tmp = seconds / 60;
			remainTmStr += tmp + "分";
			seconds = seconds - tmp * 60;
		}
		if(seconds < 60){
			remainTmStr += seconds + "秒";
		}
		return remainTmStr;
	}
	
	public static List<Map<String, Object>> gettodaymotch()throws Exception {
		System.out.println("getDDTime--->"+getDDTime());
		return getlistdatelaster(Integer.parseInt(getDDTime()));
	}
	/**
	 * 从今天往前倒退多少天  int就传入多少
	 * @author fengfeng
	 * @param i
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> getlistdatelaster(int i) throws Exception{
		List<Map<String, Object>> dateList=new ArrayList<Map<String,Object>>();
		DateTime startDate = new DateTime();
		for (int j = 0; j < i; j++) {
			Map<String, Object> map=new HashMap<String, Object>();
			if (j==0) { 
				map.put("date", dateFormatTimeToStr(FORMAT_ONLY_DATE_EN,startDate));
				dateList.add(map);
				continue;
			}
			DateTime newDate = ConvertDateTime.addDateTime(startDate, j*-1, 4);
			map.put("date", dateFormatTimeToStr(FORMAT_ONLY_DATE_EN,newDate));
			dateList.add(map);
			
		}
		return dateList;
	}
	

	public static List<Map<String, Object>> getMonthsmotch()throws Exception {
		return getlistMonthslaster(Integer.parseInt(getDMTime()));
	}
	
	/**
	 * 当前属于几月，往后倒推，  int就传入多少
	 * @author zxqu
	 * @param i
	 * @return
	 * @throws Exception 
	 */
	public static List<Map<String, Object>> getlistMonthslaster(int i) throws Exception{
		List<Map<String, Object>> dateList=new ArrayList<Map<String,Object>>();
		DateTime startDate = new DateTime();
		for (int j = 0; j < i; j++) {
			Map<String, Object> map=new HashMap<String, Object>();
			if (j==0) { 
				map.put("date", dateFormatTimeToStr(FORMAT_MONTH,startDate));
				dateList.add(map);
				continue;
			}
			DateTime newDate = ConvertDateTime.addDateTime(startDate, j*-1, 5);
			map.put("date", dateFormatTimeToStr(FORMAT_MONTH,newDate));
			dateList.add(map);
			
		}
		return dateList;
	}
	
    
    public static List<Map<String, Object>> getmonthDays(int month)throws Exception {
		System.out.println("getYearTime--->"+getYearTime());
		return getMonthFullDay(Integer.parseInt(getYearTime()),month);
	}
    
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * 手动输入月份获取当月的每一天的日期
     * @author zxqu
     * @param year,month
     * @return
     */
    public static List<Map<String, Object>> getMonthFullDay(int year , int month){
        List<Map<String, Object>> fullDayList = new ArrayList<Map<String, Object>>();
        // 当前月份
        int nowMonth = Integer.parseInt(ConvertDateTime.getDMTime());
        if(month > 0 && month != nowMonth){	// 接收到的月份不属于当前月份则计算整月的每一天日期
        	//if(day <= 0 ) day = 1;
        	Calendar cal = Calendar.getInstance(Locale.CHINA);// 获得当前日期对象
        	cal.clear();// 清除信息
        	cal.set(Calendar.YEAR, year);
        	cal.set(Calendar.MONTH, month - 1);// 1月从0开始
        	//cal.set(Calendar.DAY_OF_MONTH, day);// 设置为1号,当前日期既为本月第一天
        	int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        	for (int j = 0; j <= (count-1);) {
        		Map<String, Object> map=new HashMap<String, Object>();
        		if(sdf.format(cal.getTime()).equals(getLastDay(year, month)))
        			break;
        		cal.add(Calendar.DAY_OF_MONTH, j == 0 ? +0 : +1);
        		map.put("mday", sdf.format(cal.getTime()));
        		j++;
        		fullDayList.add(map);
        	}
        }else{	// 接收到的月份属于当前月份则计算当前日期到本月1日的日期
        	int nowDay = Integer.parseInt(ConvertDateTime.getDDTime());
        	//if(day <= 0 ) day = 1;
        	Calendar cal = Calendar.getInstance(Locale.CHINA);// 获得当前日期对象
        	cal.clear();// 清除信息
        	cal.set(Calendar.YEAR, year);
        	cal.set(Calendar.MONTH, month - 1);// 1月从0开始
        	cal.set(Calendar.DAY_OF_MONTH, nowDay);// 设置为本月第一天（或最后一天）,当前日期既为本月第一天
        	//int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        	for (int j = 0; j <= (nowDay-1);) {
        		Map<String, Object> map=new HashMap<String, Object>();
        		if(sdf.format(cal.getTime()).equals(getLastDay(year, month)))
        			break;
        		cal.add(Calendar.DAY_OF_MONTH, j == 0 ? +0 : -1);
        		map.put("mday", sdf.format(cal.getTime()));
        		j++;
        		fullDayList.add(map);
        	}
        }
        return fullDayList;
    }
    
    public static String getLastDay(int year,int month){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        return sdf.format(cal.getTime());
    }
}
