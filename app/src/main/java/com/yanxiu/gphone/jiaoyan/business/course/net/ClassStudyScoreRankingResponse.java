package com.yanxiu.gphone.jiaoyan.business.course.net;

import com.test.yanxiu.common_base.base.net.JYBaseResponse;
import com.yanxiu.gphone.jiaoyan.business.course.bean.ClassStudyScoreRankingBean;

import java.util.List;

/**
 * 学习积分排名
 * Created by Hu Chao on 18/6/14.
 * wiki:http://wiki.yanxiu.com/pages/viewpage.action?pageId=12331399
 * 4.获取班级所有成员的学分排行
 */
public class ClassStudyScoreRankingResponse extends JYBaseResponse {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private ClazsUserScoreData userRank;

        public ClazsUserScoreData getUserRank() {
            return userRank;
        }

        public void setUserRank(ClazsUserScoreData userRank) {
            this.userRank = userRank;
        }

        public static class ClazsUserScoreData {

            private int totalElements;
            private List<ClassStudyScoreRankingBean> elements;

            public int getTotalElements() {
                return totalElements;
            }

            public void setTotalElements(int totalElements) {
                this.totalElements = totalElements;
            }

            public List<ClassStudyScoreRankingBean> getElements() {
                return elements;
            }

            public void setElements(List<ClassStudyScoreRankingBean> elements) {
                this.elements = elements;
            }

        }
    }
}
