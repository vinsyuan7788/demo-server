package com.demo.server.application.utils;

import com.demo.base.common.exception.bean.BusinessException;

/**
 * @author Vince Yuan
 * @date 09/03/2021
 */
public class TimeFormatUtils {

    private static final long MILLIS = 1L;
    public static final long SECOND = 1000 * MILLIS;
    private static final long MINUTE = 60 * SECOND;
    private static final long HOUR = 60 * MINUTE;

    private static final long MAX_HOUR_FOR_HH_MM_SS = 99L;
    private static final long MAX_MINUTE_FOR_HH_MM_SS = 59L;
    private static final long MAX_SECOND_FOR_HH_MM_SS = 59L;

    private static final String ENG_COLON = ":";

    private TimeFormatUtils() {}

    /**
     *  将秒解析为 HH:mm:ss <br/>
     *  -- 比如 80s 转为 00:01:20
     *  -- 最多表示到 99:59:59
     *
     * @param seconds
     * @return
     */
    public static String parseSecondsToHMS(long seconds) {
        // 计算时分秒
        long millis = seconds * SECOND;
        long numOfHour = millis / HOUR;
        if (numOfHour > MAX_HOUR_FOR_HH_MM_SS) {
            numOfHour = MAX_HOUR_FOR_HH_MM_SS;
        }
        millis = millis - numOfHour * HOUR;
        long numOfMinute = millis / MINUTE;
        if (numOfMinute > MAX_MINUTE_FOR_HH_MM_SS) {
            numOfMinute = MAX_MINUTE_FOR_HH_MM_SS;
        }
        millis = millis - numOfMinute * MINUTE;
        long numOfSecond = millis / SECOND;
        if (numOfSecond > MAX_SECOND_FOR_HH_MM_SS) {
            numOfSecond = MAX_SECOND_FOR_HH_MM_SS;
        }
        // 返回字符串
        return getTimeString(numOfHour, numOfMinute, numOfSecond);
    }

    /**
     *  获取时间字符串
     *
     * @param numOfHour
     * @param numOfMinute
     * @param numOfSecond
     * @return
     */
    private static String getTimeString(long numOfHour, long numOfMinute, long numOfSecond) {
        return new StringBuffer()
                .append(getTwoDigitString(numOfHour))
                .append(ENG_COLON)
                .append(getTwoDigitString(numOfMinute))
                .append(ENG_COLON)
                .append(getTwoDigitString(numOfSecond))
                .toString();
    }

    /**
     *  获取两位长度的字符串
     *
     * @param time
     * @return
     */
    private static String getTwoDigitString(long time) {
        if (time >= 0 && time < 10) {
            return "0" + time;
        } else if (time >= 10 && time < 100) {
            return String.valueOf(time);
        } else {
            throw new BusinessException("时间超过范围");
        }
    }
}
