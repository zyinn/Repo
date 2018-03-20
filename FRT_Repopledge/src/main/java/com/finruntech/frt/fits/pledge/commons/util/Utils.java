package com.finruntech.frt.fits.pledge.commons.util;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by yinan.zhang on 2017/12/27.
 */
public final class Utils<T> {
    /**
     * 获取某一日期的时间
     * @param date 日期
     * @return 格式化后字符串类型的日期
     */
    public static final String dateToStringFormat(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    /**
     * 获取
     * @param date
     * @return String
     */
    public static final String dateToStringNextFormatEnd(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c2 = new GregorianCalendar();
        c2.set(Calendar.DATE, 1);
        c2.set(Calendar.HOUR_OF_DAY, 23);
        c2.set(Calendar.MINUTE, 59);
        c2.set(Calendar.SECOND, 59);
        return sdf.format(date);
    }
    public static final String stringToDateString(String param){
        if(param.isEmpty()){
            return null;
        }
        Date date = stringToDate(param);
        return dateToDateString(date);
    }
    public static final String dateToDateString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    public static final Date stringToDate(String param){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
        if(param.isEmpty()){
            return null;
        }
        try {
            return sdf.parse(param);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
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


    public static final String getCurrentTimeHhmmss(){
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    public static final String getCurrentTimeMMSS(){
        SimpleDateFormat sdf=new SimpleDateFormat("HHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
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

    public static Map<String,Object> templateOut(PageInfo<?> pageInfo){
        Map<String,Object> map = new HashMap<>();
        List<?> list = pageInfo.getList();
        List<Object> lista = new ArrayList<>();
        list.forEach(l -> {
            Object o = JSON.toJSON(l);
            lista.add(o);
        });
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("recordsTotal",10);
        map.put("data", lista);
        return map;
    }
    public static String getStrDateAndTime() {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = sd.format(new Date());
        return d;
    }
}
