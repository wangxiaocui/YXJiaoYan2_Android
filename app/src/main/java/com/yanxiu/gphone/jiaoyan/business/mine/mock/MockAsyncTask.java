package com.yanxiu.gphone.jiaoyan.business.mine.mock;

import java.util.Random;
import java.util.logging.Handler;

/**
 * Created by Cai Lei on 2018/10/9.
 */
public class MockAsyncTask extends Object {
    public interface Callback {
        void done();
    }

    static public void doTask(final Callback callback) {
        Random random = new Random();
        Integer delay = random.nextInt(4000) + 1000;

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.done();
            }
        }, delay);
    }
}
