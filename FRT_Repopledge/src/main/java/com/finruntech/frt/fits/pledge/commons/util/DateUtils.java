package com.finruntech.frt.fits.pledge.commons.util;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by lenovo on 2017/11/17.
 */
public class DateUtils {

    public static final String STARTTIME = " 00:00:00";
    public static final String ENDTIME = " 23:59:59";


    /**
     * 获取当前时间精确到毫秒
     * @Author songcao
     * @method getCurrentTimeAndMS
     * @Date: 2017/11/17 14:49
     * @param
     * @return
     *
     */
    public static String getCurrentTimeAndMS(){
         LocalTime time = LocalTime.now();
         DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HHmmssSSS");
        String dateTime = time.format(formatTime);
        return dateTime;
    }

    /**
     * 获取当前时间
     * @Author songcao
     * @method getCurrentTime
     * @Date: 2017/11/17 15:11
     * @param
     * @return
     *
     */
    public static String getCurrentTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 获取当前有效开始时间
     * @Author songcao
     * @method getStartValidTime
     * @Date: 2017/11/17 15:20
     * @param
     * @return
     *
     */
    public static String getStartValidTime(){
        LocalDate localDate = LocalDate.now();
        StringBuffer sb = new StringBuffer(localDate.toString() + STARTTIME);
        return sb.toString();
    }

    /**
     * 获取当前有效结束时间
     * @Author songcao
     * @method getEndValidTime
     * @Date: 2017/11/17 15:21
     * @param
     * @return
     *
     */
    public static String getEndValidTime(){
        LocalDate localDate = LocalDate.now();
        StringBuffer sb = new StringBuffer(localDate.toString() + ENDTIME);
        return sb.toString();
    }


    public static final String getCurrentTimeHhmmss(){
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     *  10位字符串日期转换成8位
     * @param param
     */
    public static final String parseTenToEightStr(String param){
        if(StringUtils.isBlank(param)){
            return "";
        }
        return param.trim().replaceAll("-","");
    }

}
