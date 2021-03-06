package com.test.yanxiu.common_base;


import com.test.yanxiu.common_base.utils.SystemUtil;
import com.yanxiu.lib.yx_basic_library.util.YXSystemUtil;

/**
 * Created by dyf
 * 存储常量
 */

public class Constants {
    public static final String TAG = "yanxiuAPP";
    //server配置 开始
    public static final String URL_SERVER_FILE_NAME = "env_config.json"; //存放server配置的文件名
    public static final String MULTICONFIG = "multiConfig"; //多环境节点配置模式
    //server配置 结束


    public static final String PLATFORM = "android";
    public static final String osType = "0";
    public static final String pcode = "010110000";
    public static final String BRAND = SystemUtil.getBrandName();
    public static final String OPERTYPE = "app.upload.log";
    public static String deviceId = "-";
    public static String version = String.valueOf(YXSystemUtil.getVersionCode());
    public static String versionName = YXSystemUtil.getVersionName();

    //talkingData key
//    public static final String TCAGENT_KEY = BuildConfig.TCAGENT_KEY;
//
//    public static final String PRODUCTLINE = BuildConfig.PRODUCTLINE;
    /**
     * default it is 0,when the loginactivity checks for updata,it is 1,at this time the mainactivity does't to check for updata
     */
    public static int UPDATA_TYPE = 0;

    public static final char CHARACTER_SLASH = '/';
    public static final String DIR_ROOT = "/FaceShow";
    public static final String DIR_APP = "/app";
    public static final String DIR_IMAGE = "/image";
//    public static final String DIR_PDF = DIR_ROOT + DIR_APP + "/pdf/";
    public static final String DIR_APK = DIR_ROOT + DIR_APP + "/apk/";

    //eventbus 传递hashcode
//    public final static String HASHCODE = "hashCode";
//    public final static String POSITON = "position";

}
