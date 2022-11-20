package com.jun.my_mango.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 日期时间相关工具
 * @author: Liusu
 * @date: 2022年11月10日16:28
 */
public class DateTimeUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前标准格式化日期时间
     * @param
     * @return
     */
    public static String getDateTime() {
        return getDateTime(new Date());
    }

    /**
     * 标准格式化日期时间
     * @param date
     * @return
     */
    public static String getDateTime(Date date) {
        return (new SimpleDateFormat(DATE_FORMAT)).format(date);
    }
}
