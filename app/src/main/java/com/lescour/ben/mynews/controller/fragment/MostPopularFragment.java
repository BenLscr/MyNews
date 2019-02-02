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
public class MostPopularFragment extends BaseFragment {

    private MostPopularRecyclerViewAdapter mMostPopularRecyclerViewAdapter;

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
    protected void setAppropriateAdapter() {
        this.mMostPopularRecyclerViewAdapter = new MostPopularRecyclerViewAdapter(this.articles, mListener, Glide.with(this));
        recyclerView.setAdapter(this.mMostPopularRecyclerViewAdapter);
    }

    @Override
    protected void updateUI(TheNewYorkTimesResponse theNewYorkTimesResponse) {
        articles.addAll(theNewYorkTimesResponse.getArticles());
        mMostPopularRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void executeHttpRequestWithRetrofit(){
        this.disposable = TheNewYorkTimesStreams.streamFetchMostPopular("7", apiKey).subscribeWith(new DisposableObserver<TheNewYorkTimesResponse>() {
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
}
