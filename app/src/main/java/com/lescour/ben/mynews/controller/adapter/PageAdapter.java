package com.lescour.ben.mynews.controller.adapter;

import com.lescour.ben.mynews.controller.fragment.MostPopularFragment;
import com.lescour.ben.mynews.controller.fragment.ArticleSearchFragment;
import com.lescour.ben.mynews.controller.fragment.TopStoriesFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by benja on 07/01/2019.
 */
public class PageAdapter extends FragmentPagerAdapter {

    protected PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: //Page number 1
                return TopStoriesFragment.newInstance(1);
            case 1: //Page number 2
                return MostPopularFragment.newInstance(1);
            case 2: //Page number 3
                return ArticleSearchFragment.newInstance(1);
            default:
                throw new IllegalArgumentException("unexpected position: " + position);
        }
    }

    @Override
    public int getCount() {
        return(3); //Number of page to show
    }

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
