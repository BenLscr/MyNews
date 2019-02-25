package com.lescour.ben.mynews;

import android.content.Context;

import com.bumptech.glide.RequestManager;
import com.lescour.ben.mynews.controller.CustomActivity;
import com.lescour.ben.mynews.controller.fragment.BaseFragment;
import com.lescour.ben.mynews.model.Article;
import com.lescour.ben.mynews.model.UrlSplit;
import com.lescour.ben.mynews.utils.NotificationsWorker;
import com.lescour.ben.mynews.view.ArticleSearchRecyclerViewAdapter;
import com.lescour.ben.mynews.view.TopStoriesRecyclerViewAdapter;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
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
    private Article mArticle;
    private List<Article> articles;
    private RequestManager glide;

    @Before
    public void setUp(){
        mUrlSplit = new UrlSplit();
        //mUrlSplit.setQuery("Trump");
        mUrlSplit.setFilter_query("news_desk:(\"business\")");

        calendar = Calendar.getInstance();

        mArticle = new Article();
        mArticle.setSection("World");
        mArticle.setSubsection("Asia Pacific");
        mArticle.setSectionName("U.S.");
        mArticle.setSubsectoinName("Politics");
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

    @Test
    public void checkSectionAndSubsection_TopStories() {
        TopStoriesRecyclerViewAdapter topStoriesRecyclerViewAdapter = new TopStoriesRecyclerViewAdapter(articles, mock(BaseFragment.OnListFragmentInteractionListener.class), glide);

        assertEquals("World > Asia Pacific", topStoriesRecyclerViewAdapter.getSectionAndSubsection(mArticle));
    }

    @Test
    public void checkSectionAndSubSection_ArticleSearch() {
        ArticleSearchRecyclerViewAdapter articleSearchRecyclerViewAdapter = new ArticleSearchRecyclerViewAdapter(articles, mock(BaseFragment.OnListFragmentInteractionListener.class), glide);

        assertEquals("U.S.  Politics", articleSearchRecyclerViewAdapter.getSectionAndSubsection(mArticle));
    }
}


