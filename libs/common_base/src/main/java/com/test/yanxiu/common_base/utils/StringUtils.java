package com.test.yanxiu.common_base.utils;

import android.text.TextUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by frc on 16-6-20.
 */
public class StringUtils {
    /**
     * 课程里，时间需要把时间转化一下。
     * 如：2017年9月25日11:59:55，需要把最后的秒钟部分去掉转化后的结果为：2017年9月25日11:59
     *
     * @return
     */
    public static String getCourseTime(String date) {
        try {
            final String colon = ":";
            if (!TextUtils.isEmpty(date) && date.contains(colon)) {
                String result;
                int totalLength = date.length();
                int firstIndex = date.indexOf(colon);
                int lasetIndex = date.lastIndexOf(colon);
                if (firstIndex != lasetIndex) {
                    //后面的截掉
                    if (firstIndex + 2 < totalLength) {
                        result = date.substring(0, firstIndex + 3);
                    } else if (firstIndex + 1 < totalLength) {
                        result = date.substring(0, firstIndex + 2);
                    } else {
                        return date;
                    }
                } else {
                    return date;
                }
                return result;

            } else {
                return date;
            }
        } catch (Exception e) {
            return date;
        }
    }

    /**
     * 评论里，时间需要把时间转化一下。
     * 如：2017年9月25日11:59:55，
     * 1.超过发布时间1天的显示具体日期和时间
     * 2.未超过发布时间1天的，并且大于1小时的显示距离发布时间的小时
     * 3.未超过发布时间1天的，并且距离发布时间小于1小时的显示分钟
     * 4.小于1分钟的，显示刚刚
     *
     * @return
     */
    public static String getDiscussTime(String date) {
        final long seconds = 1;
        final long minutes = seconds * 60;
        final long hour = minutes * 60;
        final long day = hour * 24;
        try {
            String result;
            long serverTime = dateToStamp(date);
            long nowTime = System.currentTimeMillis();
            long time = (nowTime - serverTime) / 1000;//秒
            if (time < 0)
                return date;
            if (time <= minutes) {
                result = "刚刚";
            } else if (time < hour) {
                result = time / minutes + "分钟前";
            } else if (time < day) {
                result = time / hour + "小时前";
            } else {
                result = date;
            }
            return result;

        } catch (Exception e) {
            return date;
        }
    }

    /**
     * 根据时间是 刚刚还是多久之前
     * @param date
     * @param serverCurrentDate
     * @return
     */
    public static String getDiscussTime(String date, long serverCurrentDate) {
        final long seconds = 1;
        final long minutes = seconds * 60;
        final long hour = minutes * 60;
        final long day = hour * 24;
        try {
            String result;
            long serverTime = dateToStamp(date);
            long nowTime = serverCurrentDate;
            long time = (nowTime - serverTime) / 1000;//秒
            if (time < 0) {
                return "刚刚";
            }
            if (time <= minutes) {
                result = "刚刚";
            } else if (time < hour) {
                result = time / minutes + "分钟前";
            } else if (time < day) {
                result = time / hour + "小时前";
            } else {
                result = date;
            }
            return result;

        } catch (Exception e) {
            return date;
        }
    }

    /*
     * 将时间转换为时间戳
     */
    public static long dateToStamp(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }

    /**
     * List<String>转成字符串，以split为分隔符
     *
     * @param list
     * @param split
     * @return
     */
    public static String listToString(List<String> list, String split) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(split);
            }
        }
        return sb.toString();
    }
}
