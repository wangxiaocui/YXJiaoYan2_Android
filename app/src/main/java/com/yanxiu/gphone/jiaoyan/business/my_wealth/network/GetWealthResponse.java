package com.yanxiu.gphone.jiaoyan.business.my_wealth.network;

import com.test.yanxiu.common_base.base.net.JYBaseResponse;

import java.util.List;

/**
 * Created By cailei on 2018/10/23
 */
public class GetWealthResponse extends JYBaseResponse {
    public class Data {
        public String Wealth;
        public String MonthIncome;
        public String MonthPay;
        public String Total;
        public class ListItem {
            public String ID;
            public String Type;
            public String Amount;
            public String Desc;
            public String CreateTime;
        }
        public List<ListItem> list;
    }
    Data Data;
}
