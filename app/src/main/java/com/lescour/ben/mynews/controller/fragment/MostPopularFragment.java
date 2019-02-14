package com.lescour.ben.mynews.controller.fragment;

import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.lescour.ben.mynews.model.TheNewYorkTimesResponse;
import com.lescour.ben.mynews.model.UrlSplit;
import com.lescour.ben.mynews.utils.TheNewYorkTimesStreams;
import com.lescour.ben.mynews.view.MostPopularRecyclerViewAdapter;

import io.reactivex.observers.DisposableObserver;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MostPopularFragment extends BaseFragment {

    private String period = "7";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MostPopularFragment() {
    }

    public static MostPopularFragment newInstance(int columnCount) {
        MostPopularFragment fragment = new MostPopularFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setUrlSplit() {
        mUrlSplit = new UrlSplit();
        mUrlSplit.setPeriod(period);
    }

    @Override
    protected void executeHttpRequestWithRetrofit(){
        this.disposable = TheNewYorkTimesStreams.streamFetchMostPopular(mUrlSplit.getPeriod(), mUrlSplit.getApiKey()).subscribeWith(new DisposableObserver<TheNewYorkTimesResponse>() {
            @Override
            public void onNext(TheNewYorkTimesResponse theNewYorkTimesResponse) {
                Log.e("TAG","On Next");
                updateUI(theNewYorkTimesResponse);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG","On Error"+Log.getStackTraceString(e));
            }

            @Override
            public void onComplete() {
                Log.e("TAG","On Complete !!");
            }
        });
    }

    @Override
    protected void setAppropriateAdapter() {
        this.mRecyclerViewAdapter = new MostPopularRecyclerViewAdapter(this.articles, mListener, Glide.with(this));
        recyclerView.setAdapter(this.mRecyclerViewAdapter);
    }
}
