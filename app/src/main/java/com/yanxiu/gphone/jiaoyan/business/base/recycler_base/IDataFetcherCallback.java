package com.yanxiu.gphone.jiaoyan.business.base.recycler_base;

/**
 * Created By cailei on 2018/10/23
 */
public interface IDataFetcherCallback {
    void onFirstPageSuccess();
    void onFirstPageError(Error error);
    void onNextPageSuccess();
    void onNextPageError(Error error);
}
