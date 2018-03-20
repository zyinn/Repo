package com.finruntech.frt.fits.pledge.service;


import com.finruntech.frt.fits.pledge.commons.enums.MarketType;
import com.finruntech.frt.fits.pledge.commons.util.DateUtils;
import com.finruntech.frt.fits.pledge.commons.util.Utils;
import com.finruntech.frt.fits.pledge.model.FrtFitsSystemStatusEntity;
import com.finruntech.frt.fits.pledge.repository.SystemStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by lenovo on 2017/12/7.
 */
@Service
public class SystemStatusService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String MARKETTYPE = MarketType.INTERBANK_MARKET.getCode();

    @Autowired
    private SystemStatusMapper systemStatusMapper;

    /**
     * 查询得到当前日期
     * @Author songcao
     * @method getCurrentTimeAndMS
     * @Date: 2017/11/17 14:49
     * @param
     * @return
     *
     */
    public String currDate(){
        String date = "";
        try {
            FrtFitsSystemStatusEntity systemStatusEntity = systemStatusMapper.selectSystemStatus(MARKETTYPE);
            date = systemStatusEntity.getCurrBizdate();
        } catch (Exception e) {
            logger.error("systemStatusEntity is null:"+e);
        }
        return date;
    }

    /**
     * 查询得到当前日期的下一个日期
     * @Author songcao
     * @method nextBizDate
     * @Date: 2017/12/20 16:51
     * @param
     * @return
     *
     */
    public String nextBizDate(){
        String date = "";
        try {
            FrtFitsSystemStatusEntity systemStatusEntity = systemStatusMapper.selectSystemStatus(MARKETTYPE);
            date = systemStatusEntity.getNextBizdate();
        } catch (Exception e) {
            logger.error("systemStatusEntity is null:"+e);
        }
        return date;
    }

    /**
     * 获取当前时间的下一个时间
     * @Author songcao
     * @method nextBizDate
     * @Date: 2017/12/20 16:40
     * @param
     * @return
     *
     */
    public String getNextBizDate(){
        String nextBizDate = nextBizDate();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String dateTime = time.format(formatTime);
        StringBuffer sb = new StringBuffer();
        sb.append(nextBizDate);
        sb.append(" ");
        sb.append(dateTime);
        return sb.toString();
    }

    /**
     * 获取当前时间精确到毫秒
     * @Author songcao
     * @method getCurrentTimeAndMS
     * @Date: 2017/11/17 14:49
     * @param
     * @return
     *
     */
    public String getCurrentTimeAndMS(){
        String currDate = currDate();
        String date = currDate.replaceAll("-","");
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HHmmssSSS");
        String dateTime = time.format(formatTime);
        StringBuffer sb = new StringBuffer();
        sb.append(date);
        sb.append(dateTime);
        return sb.toString();
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
    public String getCurrentTime(){
        String currDate = currDate();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String dateTime = time.format(formatTime);
        StringBuffer sb = new StringBuffer();
        sb.append(currDate);
        sb.append(" ");
        sb.append(dateTime);
        return sb.toString();
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
    public String getStartValidTime(){
        StringBuffer sb = new StringBuffer(currDate() + DateUtils.STARTTIME);
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
    public String getEndValidTime(){
        StringBuffer sb = new StringBuffer(currDate() + DateUtils.ENDTIME);
        return sb.toString();
    }



    /**
     * 根据委托序号，生成委托编号
     * 委托编号:ORD+yyMMDD+序号（010701111）
     * @param ordSeq
     * @return
     */
    public static String getOrdNum(String currentDate, String ordSeq){
        currentDate = Utils.parseTenToEightStr(currentDate);
        return "ORD" + currentDate + toLenStr(ordSeq, 6 );
    }



    /**
     * 根据委托序号，生成成交编号
     * 委托编号:DE+yyMMDD+序号（010701111）
     * @param deSeq
     * @return
     */
    public static String getDeNum(String currentDate, String deSeq){
        currentDate = Utils.parseTenToEightStr(currentDate);
        return "DE" + currentDate + toLenStr(deSeq, 6 );
    }



    /**
     * 根据委托序号，生成结算指令编号
     * 委托编号:DE+yyMMDD+序号（010701111）
     * @param deSeq
     * @return
     */
    public static String getInstNum(String currentDate, String deSeq){
        currentDate = Utils.parseTenToEightStr(currentDate);
        return "INST" + currentDate + toLenStr(deSeq, 6 );
    }


    /**
     * 根据指定原字符串，生成指定长度字符串，
     * 不够位数，向前补0
     * @param oriStr
     * @param len
     * @return
     */
    public static String toLenStr(String oriStr, int len){
        String targetStr = oriStr;
        if(oriStr == null){
            return null;
        }
        if(oriStr.length() <len){
            for(int i=0 ; i < len - oriStr.length(); i++){
                targetStr = "0" + targetStr;
            }
        }else {
            targetStr = oriStr.substring(oriStr.length()- len);
        }
        return targetStr;
    }

}
