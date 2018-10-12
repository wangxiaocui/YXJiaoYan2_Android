package com.yanxiu.gphone.jiaoyan.business.course;

import com.test.yanxiu.common_base.base.net.JYBaseRequest;

/**
 * 学习积分排名
 * Created by Hu Chao on 18/6/14.
 * wiki:http://wiki.yanxiu.com/pages/viewpage.action?pageId=12331399
 * 4.获取班级所有成员的学分排行
 */
public class ClassStudyScoreRankingRequest extends JYBaseRequest {

    public String osType = "0";
    public String pcode = "010110000";

    public String version = "4";

    public String clazsId = "682";

    public String offset = "0";

    public String pageSize = "20";

    public String method = "app.clazs.getUserTaskProgressRank";

    @Override
    protected String urlServer() {
        token = "d1698f5b1208bd5d269c92dcee74988e";
        return "http://hbyxb.ymd.yanxiu.com/pxt/platform/data.api";
    }

    @Override
    protected HttpType httpType() {
        return HttpType.GET;
    }

}