package com.test.yanxiu.common_base.utils;

import com.yanxiu.lib.yx_basic_library.util.YXDateFormatUtil;


/**
 * Created by frc on 17-11-3.
 */

public class DateFormatUtil {
    /**
     * 回复里，时间需要把时间转化一下。
     * 如：2017年9月25日11:59:55，
     * 1.超过发布时间1天的显示具体日期和时间 2017.9.25 11:59
     * 2.未超过发布时间1天的，并且大于1小时的显示距离发布时间的小时
     * 3.未超过发布时间1天的，并且距离发布时间小于1小时的显示分钟
     * 4.小于1分钟的，显示刚刚
     *
     * @return
     */
    public static String getReplyTime(String date) {
        final long seconds = 1;
        final long minutes = seconds * 60;
        final long hour = minutes * 60;
        final long day = hour * 24;
        try {
            String result;
            long serverTime = YXDateFormatUtil.dateStringToTimeStamp(date, YXDateFormatUtil.FORMAT_ONE);
            long nowTime = System.currentTimeMillis();
            long time = (nowTime - serverTime) / 1000;//秒
            if (time < 0) {
                return date;
            }
            if (time <= minutes) {
                result = "刚刚";
            } else if (time < hour) {
                result = time / minutes + "分钟前";
            } else if (time < day) {
                result = time / hour + "小时前";
            } else {
                result = YXDateFormatUtil.translationBetweenTwoFormat(date, YXDateFormatUtil.FORMAT_ONE, YXDateFormatUtil.FORMAT_TWO);
            }
            return result;

        } catch (Exception e) {
            return date;
        }
    }


    /**
     * 获取课程的开始 - 截止时间
     * 如：2017.11.3.10:57:19 - 19:00
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String getCourseTime(String startTime, String endTime) {
        return YXDateFormatUtil.translationBetweenTwoFormat(startTime, YXDateFormatUtil.FORMAT_ONE, YXDateFormatUtil.FORMAT_TWO) + " - "
                + YXDateFormatUtil.translationBetweenTwoFormat(endTime, YXDateFormatUtil.FORMAT_ONE, YXDateFormatUtil.FORMAT_TWO);
    }
}
