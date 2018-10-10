package com.test.yanxiu.common_base.route;

/**
 * Created by Hu Chao on 18/9/28.
 */
public class RoutePathConfig {
    //region app
    //MainActivty
    public static final String App_Main = "/app/main";
    public static final String Course_Fragment = "/course/index";

    //region course
    /**
     * 课程详情页
     * invokeData:{@link com.test.yanxiu.common_base.route.data.CourseDetailData}
     */
    public static final String App_Course_Detail = "/app/CourseDetailActivity";

    //endregion courses

    //endregion


    //region module_signin
    //LoginActivity
    public static final String SIGNIN_LOGIN_ACTIVITY = "/signin/login";
    //LoginByCodeActivity
    public static final String SIGNIN_LOGIN_BY_CODE_ACTIVITY = "/signin/login_by_code";
    //RegisterActivity
    public static final String SIGNIN_REGISTER_ACTIVITY = "/signin/register";
    //endregion

    //region module_message
    //MessageFragment
    public static final String Message_Fragment = "/message/index";
    //endregion



    //region mine
    /**
     * tab"我的"入口页
     */
    public static final String Mine_Fragment = "/mine/index";

    //endregion mine
}
