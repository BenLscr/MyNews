package com.lescour.ben.mynews.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.model.UrlSplit;
import com.lescour.ben.mynews.model.TheNewYorkTimesResponse;
import com.lescour.ben.mynews.utils.TheNewYorkTimesStreams;
import com.lescour.ben.mynews.view.ArticleSearchRecyclerViewAdapter;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.observers.DisposableObserver;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ArticleSearchFragment extends BaseFragment {

    private String query = "science";
    private String sort = "newest";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArticleSearchFragment() {
    }

    public static ArticleSearchFragment newInstance(int columnCount) {
        ArticleSearchFragment fragment = new ArticleSearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }



    /**@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        this.articles = new ArrayList<>();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            this.setAppropriateAdapter();
        }

        Bundle bundle = getArguments();
        if (bundle.containsKey("CustomToArticleSearchFragment")) {
            mUrlSplit = bundle.getParcelable("CustomToArticleSearchFragment");
            Log.e("bundle", "il y a un bundle" + mUrlSplit.getQuery());
        } else {
            mUrlSplit = new UrlSplit();
            mUrlSplit.setQuery(query);
            mUrlSplit.setSort(sort);
            Log.e("bundle", "il n'y a pas de bundle");
        }

        this.executeHttpRequestWithRetrofit();

        return view;
    }*/

    @Override
    protected void setUrlSplit() {
        Bundle bundle = getArguments();
        if (bundle.containsKey("CustomToArticleSearchFragment")) {
            mUrlSplit = bundle.getParcelable("CustomToArticleSearchFragment");
            Log.e("bundle", "il y a un bundle" + mUrlSplit.getQuery());
        } else {
            mUrlSplit = new UrlSplit();
            mUrlSplit.setQuery(query);
            mUrlSplit.setSort(sort);
            Log.e("bundle", "il n'y a pas de bundle");
        }
    }

    @Override
    protected void setAppropriateAdapter() {
        this.mRecyclerViewAdapter = new ArticleSearchRecyclerViewAdapter(this.articles, mListener, Glide.with(this));
        recyclerView.setAdapter(this.mRecyclerViewAdapter);
    }

    @Override
    protected void updateUI(TheNewYorkTimesResponse theNewYorkTimesResponse) {
        articles.addAll(theNewYorkTimesResponse.getResponse().getArticles());
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void executeHttpRequestWithRetrofit(){
        this.disposable = TheNewYorkTimesStreams.streamFetchArticleSearch(mUrlSplit.getBeginDate(),
                mUrlSplit.getEndDate(), mUrlSplit.getFilter_query(), mUrlSplit.getQuery(),
                mUrlSplit.getSort(), mUrlSplit.getApiKey()).subscribeWith(new DisposableObserver<TheNewYorkTimesResponse>() {
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
