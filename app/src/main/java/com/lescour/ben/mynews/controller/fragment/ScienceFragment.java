package com.lescour.ben.mynews.controller.fragment;

import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.lescour.ben.mynews.model.TheNewYorkTimesResponse;
import com.lescour.ben.mynews.utils.TheNewYorkTimesStreams;

import io.reactivex.observers.DisposableObserver;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ScienceFragment extends BaseFragment {

    private ScienceRecyclerViewAdapter mScienceRecyclerViewAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ScienceFragment() {
    }

    public static ScienceFragment newInstance(int columnCount) {
        ScienceFragment fragment = new ScienceFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setAppropriateAdapter() {
        this.mScienceRecyclerViewAdapter = new ScienceRecyclerViewAdapter(this.articles, mListener, Glide.with(this));
        recyclerView.setAdapter(this.mScienceRecyclerViewAdapter);
    }

    @Override
    protected void updateUI(TheNewYorkTimesResponse theNewYorkTimesResponse) {
        articles.addAll(theNewYorkTimesResponse.getResponse().getArticles());
        mScienceRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void executeHttpRequestWithRetrofit(){
        this.disposable = TheNewYorkTimesStreams.streamFetchArticleSearch("science", "newest", apiKey).subscribeWith(new DisposableObserver<TheNewYorkTimesResponse>() {
            @Override
            public void onNext(TheNewYorkTimesResponse theNewYorkTimesResponse) {
                Log.e("TAG", "On Next");
                updateUI(theNewYorkTimesResponse);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "On Error" + Log.getStackTraceString(e));
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "On Complete !!");
            }
        });
    }

}
