package com.test.yanxiu.common_base.route.data;

import com.yanxiu.lib.yx_basic_library.base.bean.YXBaseBean;

/**
 * 获取SearchResultFragment时，传入的值
 * Created by Hu Chao on 18/10/18.
 */

public class RouteSearchData extends YXBaseBean {
    private String searchKey;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
