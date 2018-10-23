package com.test.yanxiu.common_base.bean.xueduanxueke;

import com.test.yanxiu.common_base.base.net.JYBaseResponse;

import java.util.List;

/**
 * Created by Hu Chao on 18/10/23.
 */
public class XueduanXuekeBean extends JYBaseResponse {
    private List<LevelBean> data;

    public List<LevelBean> getData() {
        return data;
    }

    public void setData(List<LevelBean> data) {
        this.data = data;
    }
}
