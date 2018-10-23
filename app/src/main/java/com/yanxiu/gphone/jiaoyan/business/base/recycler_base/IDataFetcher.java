package com.yanxiu.gphone.jiaoyan.business.base.recycler_base;

import java.util.List;

/**
 * Created By cailei on 2018/10/23
 */
public interface IDataFetcher {
    void fetchFirstPage();
    void fetchNextPage();

    // 只负责取到列表中格式相同的数据
    List<Object> getData();

    // 不是最后一页则返回true
    boolean hasMoreData();
}
