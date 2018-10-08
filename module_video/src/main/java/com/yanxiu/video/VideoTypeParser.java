package com.yanxiu.video;

import android.net.Uri;

/**
 * 获取视频格式类型
 */

public class VideoTypeParser {
    public enum Type {
        UNKNOWN,
        MP4,
        M3U8,
    }

    public static Type getTypeForUri(Uri uri) {
        try {
            String filename = uri.getLastPathSegment().toLowerCase();
            if (filename.endsWith(".mp4")) {
                return Type.MP4;
            }

            if (filename.endsWith(".m3u") || filename.endsWith(".m3u8")) {
                return Type.M3U8;
            }
        } catch (Exception e) {
            //uri可能为空（url为空时，会有这种情况，然后返回Type.UNKNOWN,播放器会显示未找到到该视频）
        }
        return Type.UNKNOWN;
    }
}
