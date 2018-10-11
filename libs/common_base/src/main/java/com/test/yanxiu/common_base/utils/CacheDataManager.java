package com.test.yanxiu.common_base.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by Cai Lei on 2018/10/11.
 */
public class CacheDataManager {
    /**
     * 计算文件大小（Long型）
     * @param dir
     * @return
     */
    private static long getFileSize(File dir){
        long size = 0;
        File[] fileList = dir.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            if(fileList[i].isDirectory()){
                size=+getFileSize(fileList[i]);//迭代计算文件大小
            }else{
                size = fileList[i].length();
            }

        }

        return size;
    }

    /**
     * 删除文件或者文件夹
     * @param dir
     */
    private static void deleteFile(File dir){
        if(dir!=null && dir.isDirectory()){
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFile(files[i]);  //迭代删除
            }
        }else if(dir != null && dir.isFile()){
            dir.delete();
        }
    }

    /**
     * 清除所有缓存
     * @param context
     */
    public static void ClearAllCache(Context context){
        File dir1 = context.getCacheDir();
        deleteFile(dir1);
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){//判断sd卡是否挂载
            File dir2 = context.getExternalCacheDir();//获取sd卡缓存
            deleteFile(dir2);
        }
    }

    /**
     *
     * 获取所用缓存大小
     * @param context
     * @return
     */
    public static long getAllCacheSize(Context context){
        long size = 0;
        File dir1 = context.getCacheDir();//获取内存缓存
        size = getFileSize(dir1);
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File dir2 = context.getExternalCacheDir();
            size += getFileSize(dir2);
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            // return size + "Byte";
            return "0 KB";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + " KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + " MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + " GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + " TB";
    }
}
