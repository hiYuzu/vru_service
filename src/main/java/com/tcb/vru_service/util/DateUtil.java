package com.tcb.vru_service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Wanglei on 2020/06/03.
 */
public class DateUtil {

    /**
     * 日志输出标记
     */
    private static final String LOG = "DateUtil";
    /**
     * 声明日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 标准日期时间类型(yyyy-MM-dd HH:mm:ss)
     */
    public static final String DATA_TIME_SECOND = "yyyy-MM-dd HH:mm:ss";

    /**
     * 标准日期时间类型(yyyy-MM-dd)
     */
    public static final String DATA_DAY = "yyyy-MM-dd";

    /**
     * 协议时间戳字符串格式
     */
    public static final String PRO_TIME = "yyyyMMddHHmmss";

    /**
     * <p>
     * [功能描述]：获取系统时间
     * </p>
     *
     * @param millionSecond ：当前系统时间减去此参数
     * @return
     * @author 王垒, 2016年4月1日上午9:29:05
     * @since env_gateway 1.0.0
     */
    public static Timestamp GetSystemDateTime(int millionSecond) {
        return new Timestamp(Calendar.getInstance().getTimeInMillis() - millionSecond);
    }

    /**
     * 获取17位时间字符串
     *
     * @return
     */
    public static String GetSystemDateTime(String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        String strTime = fmt.format(new Date());
        return strTime;
    }

    /**
     * 将时间戳转换为标准时间
     *
     * @param timestamp
     * @param format
     * @return
     */
    public static String TimestampToString(Timestamp timestamp, String format) {
        try {
            if (timestamp != null) {
                DateFormat dateFormat = new SimpleDateFormat(format);
                return dateFormat.format(timestamp);
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error(LOG + ":" + "转换日期失败，失败原因：" + e.getMessage());
            return null;
        }
    }

    /**
     * 将字符串转换成时间戳
     *
     * @param datetime
     * @param format
     * @return
     */
    public static Timestamp StringToTimestamp(String datetime, String format) {
        try {
            if (datetime != null && !datetime.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                Date date = sdf.parse(datetime);
                return new Timestamp(date.getTime());
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error(LOG + ":" + "转换日期失败，失败原因：" + e.getMessage());
            return null;
        }
    }

    /**
     * 将时间戳字符串转换成时间戳类型
     *
     * @param timestamp
     * @param millionSecond
     * @return
     */
    public static Timestamp ConvertToTimestamp(String timestamp, int millionSecond) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(PRO_TIME);
            Date date = sdf.parse(timestamp);
            return new Timestamp(date.getTime() - millionSecond);
        } catch (Exception e) {
            logger.error(LOG + ":" + "转换日期失败，失败原因：" + e.getMessage());
            return null;
        }
    }

    /**
     * 将字符串转换成时间戳（yyyy-MM-dd HH:mm:ss）
     *
     * @param datetime
     * @return
     */
    public static Timestamp StringToTimestampSecond(String datetime) {
        try {
            if (datetime != null && !datetime.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat(DATA_TIME_SECOND);
                if(datetime.length() == 10){
                    datetime += " 00:00:00";
                }
                Date date = sdf.parse(datetime);
                return new Timestamp(date.getTime());
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error(LOG + ":" + "转换日期失败，失败原因：" + e.getMessage());
            return null;
        }
    }

    /**
     * <p>[功能描述]：判断是否在最近时间内</p>
     *
     * @param timestamp
     * @param days
     * @return
     * @author 王垒, 2017年8月8日下午2:39:22
     * @since envdust 1.0.0
     */
    public static boolean isRecentlyData(Timestamp timestamp, int days) {
        boolean flag = false;
        if (timestamp != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -days);
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            String timeDate = formatDate.format(calendar.getTime());
            Timestamp recentTime = StringToTimestamp(timeDate, DATA_DAY);
            flag = (timestamp.after(recentTime) || timestamp.equals(recentTime));
        }
        return flag;
    }

}
