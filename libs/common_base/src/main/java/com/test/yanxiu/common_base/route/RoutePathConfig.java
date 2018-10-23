package com.test.yanxiu.common_base.route;

import com.test.yanxiu.common_base.route.data.RouteCourseCategoryData;
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
     * 课程分类Fragment
     * invokeData:{@link RouteCourseCategoryData}
     */
    public static final String Course_Category_Fragment = "/course/CategoryFragment";
    /**
     * 课程专题页
     */
    public static final String Course_Topic_Activity = "/course/topicActivity";
    /**
     * 课程专题Fragment
     */
    public static final String Course_Topic_Fragment = "/course/topicFragment";
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

    //region video

    /**
     * 视频页
     */
    public static final String Video_Activity = "/app/VideoActiivity";
    /**
     * 视频信息fragment
     * invokeData:{@link com.test.yanxiu.common_base.route.data.CourseDetailData}
     */
    public static final String Video_Activity_Fragment = "/video/VideoInfoFragment";

    //endregion video


    //region 搜索 search
    //SearchActivity
    public static final String Search_Activity = "/search/index";
    /**
     * 搜索结果
     * invokeData:{@link RouteSearchData}
     */
    public static final String Search_Result_Fragment = "/search/result";
    //endregion

    //region 完善个人资料 complete_info
    //设置学段学科 UserinfoSubjectActivity
    public static final String User_Info_Subject_Activity = "/completeInfo/subject";
    //设置密码/修改密码 UserinfoPasswordActivity
    public static final String User_Info_Password_Activity = "/completeInfo/password";
    //endregion

    //endregion

    //region module_signin
    //LoginActivity
    public static final String Signin_Login_Activity = "/signin/login";
    //LoginByCodeActivity
    public static final String Signin_Login_By_Code_Activity = "/signin/loginByCode";
    //RegisterActivity
    public static final String Signin_Register_Activity = "/signin/register";
    //SetPasswordActivity
    public static final String Signin_Set_Password_Activity = "/signin/setPassword";
    //个人资料必填项 SetRequiredInfoActivity
    public static final String Signin_Set_Required_Info_Activity = "/signin/required_info";
    //endregion

    //region module_message
    //MessageFragment 消息页
    public static final String Message_Fragment = "/message/index";
    //MessageInteractFragment 互动消息
    public static final String Message_Interact_Fragment = "/message/interact";
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
    // "证书详情" 页
    public static final String Mine_My_Cert_Detail_Activity = "/mine/my_cert/detail";

    // "我的财富" 页
    public static final String Mine_My_Money_Activity = "/mine/my_money";
    // "我的财富" 页 2
    public static final String My_Wealth_Activity = "/my_wealth/index";

    //endregion mine
}
