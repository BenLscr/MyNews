package com.lescour.ben.mynews.controller.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.controller.fragment.dummy.DummyContent;
import com.lescour.ben.mynews.controller.fragment.dummy.DummyContent.DummyItem;
import com.lescour.ben.mynews.model.Article;
import com.lescour.ben.mynews.model.TheNewYorkTimesResponse;
import com.lescour.ben.mynews.utils.TheNewYorkTimesStreams;

import java.util.ArrayList;
import java.util.List;

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
    protected void notifyRecyclerView() {
        mMostPopularRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void executeHttpRequestWithRetrofit(){
        this.disposable = TheNewYorkTimesStreams.streamFetchMostPopular("7").subscribeWith(new DisposableObserver<TheNewYorkTimesResponse>() {
            @Override
            public void onNext(TheNewYorkTimesResponse response) {
                Log.e("TAG","On Next");
                updateUI(response);
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
