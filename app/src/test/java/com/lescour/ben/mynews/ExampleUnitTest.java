package com.lescour.ben.mynews;

import com.bumptech.glide.RequestManager;
import com.lescour.ben.mynews.model.Article;
import com.lescour.ben.mynews.utils.AlarmReceiver;
import com.lescour.ben.mynews.view.BaseRecyclerViewAdapter;
import com.lescour.ben.mynews.view.ViewHolder;

import org.junit.Test;

import java.util.Calendar;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /**
     * rawDate is different for every API
     * example :
     * Search Article : 2019-02-11T11:26:01+0000
     * Most Popular : 2019-02-05
     * Top Stories : 2019-02-10T12:00:43-05:00
     * but always starts with a same format "yyyy-MM-dd"
     */
    @Test
    public void rawDateOfArticle_IsCorrectlyTransform() {
        BaseRecyclerViewAdapter recyclerViewAdapter = new BaseRecyclerViewAdapter() {
            @Override
            protected void updateWithArticle(Article article, RequestManager glide, ViewHolder holder) {

            }

            @Override
            protected String getRawDate(Article article) {
                return null;
            }
        };

        assertEquals("10/02/2019", recyclerViewAdapter.getDateWhitNewFormat("2019-02-10T12:00:43-05:00"));
    }

    @Test
    public void beginDateNeedYesterday() {
        AlarmReceiver alarmReceiver = new AlarmReceiver();

        Calendar calendar = Calendar.getInstance();

        assertEquals("20190212", alarmReceiver.setYesterdayToBeginDate(calendar));
    }
}


