package com.lescour.ben.mynews.controller.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lescour.ben.mynews.model.TheNewYorkTimesResponse;
import com.lescour.ben.mynews.model.UrlSplit;
import com.lescour.ben.mynews.utils.TheNewYorkTimesStreams;
import com.lescour.ben.mynews.view.ArticleSearchRecyclerViewAdapter;

import io.reactivex.observers.DisposableObserver;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ArticleSearchFragment extends BaseFragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArticleSearchFragment() {
    }

    /**
     * Initialise a new instance of this fragment.
     * @param columnCount Column number
     * @return This new fragment
     */
    public static ArticleSearchFragment newInstance(int columnCount) {
        ArticleSearchFragment fragment = new ArticleSearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setUrlSplit() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey("CustomToArticleSearchFragment")) {
                mUrlSplit = bundle.getParcelable("CustomToArticleSearchFragment");
            } else {
                mUrlSplit = new UrlSplit();
                mUrlSplit.setQuery("science");
                mUrlSplit.setSort("newest");
            }
        }
    }

    @Override
    protected void setAppropriateAdapter() {
        this.mRecyclerViewAdapter = new ArticleSearchRecyclerViewAdapter(this.articles, mListener, Glide.with(this));
        recyclerView.setAdapter(this.mRecyclerViewAdapter);
    }

    @Override
    protected void updateUI(TheNewYorkTimesResponse theNewYorkTimesResponse) {
        if (theNewYorkTimesResponse.getResponse().getArticles().size() > 0) {
            articles.addAll(theNewYorkTimesResponse.getResponse().getArticles());
            mRecyclerViewAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "No articles found.", Toast.LENGTH_LONG).show();
        }
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
