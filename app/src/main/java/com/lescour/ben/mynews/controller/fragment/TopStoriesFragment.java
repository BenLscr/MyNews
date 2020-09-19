package com.lescour.ben.mynews.controller.fragment;

import android.util.Log;

import com.bumptech.glide.Glide;
import com.lescour.ben.mynews.model.TheNewYorkTimesResponse;
import com.lescour.ben.mynews.model.UrlSplit;
import com.lescour.ben.mynews.utils.TheNewYorkTimesStreams;
import com.lescour.ben.mynews.view.TopStoriesRecyclerViewAdapter;

import io.reactivex.observers.DisposableObserver;

/**
 * A fragment representing a list of Items.
 */
public class TopStoriesFragment extends BaseFragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TopStoriesFragment() {
    }

    /**
     * Initialise a new instance of this fragment.
     * @return This new fragment
     */
    public static TopStoriesFragment newInstance() {
        return new TopStoriesFragment();
    }

    @Override
    protected void setUrlSplit() {
        mUrlSplit = new UrlSplit();
        mUrlSplit.setSection("home");
    }

    @Override
    protected void executeHttpRequestWithRetrofit(){
        this.disposable = TheNewYorkTimesStreams.streamFetchTopStories(mUrlSplit.getSection(), mUrlSplit.getApiKey()).subscribeWith(new DisposableObserver<TheNewYorkTimesResponse>() {
            @Override
            public void onNext(TheNewYorkTimesResponse theNewYorkTimesResponse) {
                Log.e("TopStories", "On Next");
                updateUI(theNewYorkTimesResponse);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TopStories", "On Error" + Log.getStackTraceString(e));
            }

            @Override
            public void onComplete() {
                Log.e("TopStories", "On Complete !!");
            }
        });
    }

    @Override
    protected void setAppropriateAdapter() {
        this.mRecyclerViewAdapter = new TopStoriesRecyclerViewAdapter(
                this.articles, Glide.with(this));
        recyclerView.setAdapter(this.mRecyclerViewAdapter);
    }

}
