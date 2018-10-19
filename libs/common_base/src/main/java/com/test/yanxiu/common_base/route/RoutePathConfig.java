package com.test.yanxiu.common_base.route;

import com.test.yanxiu.common_base.route.data.RouteSearchData;

/**
 * Created by Hu Chao on 18/9/28.
 */
public class RoutePathConfig {
    //region app
    //MainActivty
    public static final String App_Main = "/app/main";
    //CourseFragment
    public static final String Course_Fragment = "/course/index";
    //CourseListFragment
    public static final String Course_List_Fragment = "/course/list";

    //region course
    /**
     * 课程详情页
     * invokeData:{@link com.test.yanxiu.common_base.route.data.CourseDetailData}
     */
    public static final String App_Course_Detail = "/app/CourseDetailActivity";

    /**
     * 课程详情页
     * invokeData:{@link com.test.yanxiu.common_base.route.data.CourseDetailTabData}
     */
    public static final String App_Course_Detail_Tab = "/app/CourseDetailTabActivity";

    /**
     * 课程详情页里去评论页面
     */
    public static final String App_Course_Detail_Go_Evaluation = "/app/GoEvaluationActivity";

    //endregion courses


    //region search
    //SearchActivity
    public static final String Search_Activity = "/search/index";
    /**
     * 搜索结果
     * invokeData:{@link RouteSearchData}
     */
    public static final String Search_Result_Fragment = "/search/result";
    //endregion

    //endregion

    //region module_signin
    //LoginActivity
    public static final String SIGNIN_LOGIN_ACTIVITY = "/signin/login";
    //LoginByCodeActivity
    public static final String SIGNIN_LOGIN_BY_CODE_ACTIVITY = "/signin/loginByCode";
    //RegisterActivity
    public static final String SIGNIN_REGISTER_ACTIVITY = "/signin/register";
    //SetPasswordActivity
    public static final String SIGNIN_SET_PASSWORD_ACTIVITY = "/signin/setPassword";
    //SetSubjectActivity
    public static final String SIGNIN_SET_SUBJECT_ACTIVITY = "/signin/setSubject";
    //endregion

    //region module_message
    //MessageFragment 消息页
    public static final String Message_Fragment = "/message/index";
    //MessageUserFragment 互动消息
    public static final String Message_User_Fragment = "/message/user";
    //MessageSystemFragment 系统消息
    public static final String Message_System_Fragment = "/message/system";
    //endregion


    //region mine

    // tab"我的"入口页
    public static final String Mine_Fragment = "/mine/index";
    // "设置" 页
    public static final String Mine_Setting_Activity = "/mine/setting";
    // "移动网络视频播放清晰度" 页
    public static final String Mine_Video_Resolution_Activity = "/mine/setting/video_resolution";
    // "更换手机号" 旧手机号 页
    public static final String Mine_Change_Pwd_Step1_Activity = "/mine/change_pwd/step1";
    // "更换手机号" 新手机号 页
    public static final String Mine_Change_Pwd_Step2_Activity = "/mine/change_pwd/step2";
    // "我的课程" 页
    public static final String Mine_My_Course_Activity = "/mine/my_course";
    // "我的证书" 页
    public static final String Mine_My_Cert_Activity = "/mine/my_cert";
    // "我的财富" 页
    public static final String Mine_My_Money_Activity = "/mine/my_money";

    //endregion mine
}
