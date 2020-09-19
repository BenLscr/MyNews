package com.lescour.ben.mynews.controller.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lescour.ben.mynews.controller.fragment.ArticleSearchFragment;
import com.lescour.ben.mynews.controller.fragment.MostPopularFragment;
import com.lescour.ben.mynews.controller.fragment.TopStoriesFragment;

/**
 * Created by benja on 07/01/2019.
 */
public class PageAdapter extends FragmentPagerAdapter {

    protected PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    /**
     * Return the Fragment associated with a specified position.
     * @param position Correspond to a specified number.
     * @return Fragment to show.
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return TopStoriesFragment.newInstance();
            case 1:
                return MostPopularFragment.newInstance();
            case 2:
                return ArticleSearchFragment.newInstance();
            default:
                throw new IllegalArgumentException("unexpected position: " + position);
        }
    }

    /**
     * @return Number of pages to show
     */
    @Override
    public int getCount() {
        return(3);
    }

    /**
     * Set titles to pages.
     * @param position Correspond to a specified number.
     * @return Title of page.
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0 :
                return "TOP STORIES";
            case 1 :
                return "MOST POPULAR";
            case 2 :
                return "SCIENCE";
            default:
                return null;
        }
    }
}
