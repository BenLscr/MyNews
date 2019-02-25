package com.lescour.ben.mynews;

import android.content.Context;

import com.lescour.ben.mynews.controller.CustomActivity;
import com.lescour.ben.mynews.model.UrlSplit;
import com.lescour.ben.mynews.utils.NotificationsWorker;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.work.WorkerParameters;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by benja on 19/02/2019.
 */
public class IntegrationTest {

    private UrlSplit mUrlSplit;
    private Calendar calendar;

    @Before
    public void setUp(){
        mUrlSplit = new UrlSplit();
        //mUrlSplit.setQuery("Trump");
        mUrlSplit.setFilter_query("news_desk:(\"business\")");

        calendar = Calendar.getInstance();
    }

    @Test
    public void beginDateNeedYesterday() {
        NotificationsWorker notificationsWorker = new NotificationsWorker(mock(Context.class), mock(WorkerParameters.class));

        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String beginDate = format.format(calendar2.getTime());

        assertEquals(beginDate, notificationsWorker.setYesterdayToBeginDate(calendar));
    }


    @Test
    public void labelTest() {
        CustomActivity customActivity = new CustomActivity();

        if (mUrlSplit.getQuery() != null) {
            assertEquals(mUrlSplit.getQuery(),customActivity.getCorrectLabel(mUrlSplit));
        } else {
            assertEquals("Business",customActivity.getCorrectLabel(mUrlSplit));
        }
    }
}


