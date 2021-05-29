package com.demo.spring.boot.common.utils;

import com.demo.base.common.utils.DateUtils;
import com.demo.spring.boot.common.constant.CommonConstant;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

/**
 *  This class is used for system utility
 *
 * @author Vince Yuan
 * @date 03/02/2021
 */
@Component
public class SystemUtils {

    /**
     *  To trace current time in millisecond
     */
    private static long currentTimeMillis = System.currentTimeMillis();

    /**
     *  The calendar instance for auxiliary use
     */
    private static final Calendar calendar = Calendar.getInstance();

    /**
     *  Privatize the constructor
     */
    private SystemUtils() { }

    /**
     *  Get current time (in millisecond)
     *
     * @return
     */
    public static long currentTimeMillis() {
        return currentTimeMillis;
    }

    /**
     *  Get current date
     *
     * @return
     */
    public static Date currentDate() {
        calendar.setTimeInMillis(currentTimeMillis());
        return calendar.getTime();
    }

    /**
     *  Get current date-time string (with format "yyyy-MM-dd HH:mm:ss.SSS")
     *
     * @return
     */
    public static String currentDateTimeString() {
        return DateUtils.DATE_FORMAT_STANDARD_YEAR_TO_MILLIS.format(currentDate());
    }

    /**
     *  Get elapsed time in seconds
     *
     * @param startTimeMillis start time in milliseconds
     * @param endTimeMillis end time in milliseconds
     * @return
     */
    public static String elapsedTimeSeconds(long startTimeMillis, long endTimeMillis) {
        BigDecimal startTimeInBigDecimal = BigDecimal.valueOf(startTimeMillis);
        BigDecimal endTimeInBigDecimal = BigDecimal.valueOf(endTimeMillis);
        BigDecimal oneThousandInBigDecimal = BigDecimal.valueOf(CommonConstant.ONE_THOUSAND);
        return endTimeInBigDecimal.subtract(startTimeInBigDecimal).divide(oneThousandInBigDecimal, 3, RoundingMode.HALF_UP).doubleValue() + "s";
    }

    /**
     *  Periodical job to get system time
     */
    @Scheduled(fixedRate = 1)
    public void systemClock() {
        currentTimeMillis = System.currentTimeMillis();
    }
}
