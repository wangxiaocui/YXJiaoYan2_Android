package com.yanxiu.gphone.jiaoyan.business.my_wealth.presenter;

import com.yanxiu.gphone.jiaoyan.business.base.recycler_base.IDataFetcher;
import com.yanxiu.gphone.jiaoyan.business.base.recycler_base.IDataFetcherCallback;
import com.yanxiu.gphone.jiaoyan.business.mine.mock.MockListRequest;
import com.yanxiu.gphone.jiaoyan.business.mine.mock.MockListResponse;
import com.yanxiu.lib.yx_basic_library.network.IYXHttpCallback;
import com.yanxiu.lib.yx_basic_library.network.YXRequestBase;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created By cailei on 2018/10/23
 */
public class MockDataFetcher implements IDataFetcher {
    public IDataFetcherCallback callback = null;

    private boolean hasMoreData = false;
    private List<Object> datas = new ArrayList<>();

    @Override
    public void fetchFirstPage() {
        MockListRequest request = new MockListRequest();
        request.startRequest(MockListResponse.class, new IYXHttpCallback<MockListResponse>() {
            @Override
            public void onRequestCreated(Request request) {

            }

            @Override
            public void onSuccess(YXRequestBase request, MockListResponse ret) {
                datas.clear();
                datas.addAll(ret.data);
                if (ret.data.size() == 0) {
                    hasMoreData = false;
                } else {
                    hasMoreData = true;
                }

                callback.onFirstPageSuccess();
            }

            @Override
            public void onFail(YXRequestBase request, Error error) {
                callback.onFirstPageError(error);
            }
        });
    }

    @Override
    public void fetchNextPage() {
        MockListRequest request = new MockListRequest();
        request.startRequest(MockListResponse.class, new IYXHttpCallback<MockListResponse>() {
            @Override
            public void onRequestCreated(Request request) {

            }

            @Override
            public void onSuccess(YXRequestBase request, MockListResponse ret) {
                datas.addAll(ret.data);
                if (ret.data.size() == 0) {
                    hasMoreData = false;
                } else {
                    hasMoreData = true;
                }

                callback.onNextPageSuccess();
            }

            @Override
            public void onFail(YXRequestBase request, Error error) {
                callback.onNextPageError(error);
            }
        });
    }

    @Override
    public List<Object> getData() {
        return datas;
    }

    @Override
    public boolean hasMoreData() {
        return hasMoreData;
    }
}
