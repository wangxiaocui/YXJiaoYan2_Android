package com.yanxiu.video;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程视频数据
 */

public class CourseVideoModel {
    public String headUrl;//片头广告url
    public long headPosition;//片头播放时间点

    public String bodyUrl;//正片url
    public long bodyPosition;//正片播放时间点
    public long bodyDuration = -1;//正片总时长
    public String clarity;//正片清晰度--为空时，不显示清晰度view
    public String title;//视频title

    public List<SuiTangLianModel> suiTangLianItems = new ArrayList<>();//随堂练数据
    public boolean isHeadFinished;//片头播放完毕

    /**
     * 随堂练状态
     * 1.不再显示
     * 2.回答正确
     * 3.回答错误
     */
    public enum SuiTangLian_State {
        Not_Shown_Yet,
        Answer_Correct,
        Answer_Wrong
    }

    /**
     * 随堂练数据
     */
    public static class SuiTangLianModel {
        public String name;
        public long position;//随堂练显示时间点
        public SuiTangLian_State state;
    }
}
