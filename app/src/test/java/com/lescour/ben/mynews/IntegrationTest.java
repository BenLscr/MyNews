package com.lescour.ben.mynews;

import com.lescour.ben.mynews.controller.CustomActivity;
import com.lescour.ben.mynews.model.UrlSplit;
import com.lescour.ben.mynews.utils.NotificationsWorker;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by benja on 19/02/2019.
 */
public class IntegrationTest {

    /**@Test
    public void beginDateNeedYesterday() {
    NotificationsWorker notificationsWorker = mock(NotificationsWorker.class);

    Calendar calendar = Calendar.getInstance();

    Calendar calendar2 = Calendar.getInstance();
    calendar2.add(Calendar.DAY_OF_MONTH, -1);
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    String beginDate = format.format(calendar2.getTime());

    assertEquals(beginDate, notificationsWorker.setYesterdayToBeginDate(calendar));
    }*/
}


