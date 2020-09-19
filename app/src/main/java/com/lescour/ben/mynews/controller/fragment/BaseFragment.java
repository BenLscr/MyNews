package com.lescour.ben.mynews.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lescour.ben.mynews.R;
import com.lescour.ben.mynews.model.Article;
import com.lescour.ben.mynews.model.TheNewYorkTimesResponse;
import com.lescour.ben.mynews.model.UrlSplit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by benja on 01/02/2019.
 */
public abstract class BaseFragment extends Fragment {

    protected List<Article> articles;
    RecyclerView recyclerView;
    Disposable disposable;
    RecyclerView.Adapter mRecyclerViewAdapter;
    protected UrlSplit mUrlSplit;

    protected abstract void setUrlSplit();
    protected abstract void executeHttpRequestWithRetrofit();
    protected abstract void setAppropriateAdapter();

    /**
     * Called to have the fragment instantiate its user interface view.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment.
     * @param container This is the parent view that the fragment's
     * UI should be attached to.
     * @param savedInstanceState This fragment is being re-constructed from
     * a previous saved state as given here.
     * @return Return the View for the fragment's UI.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        this.articles = new ArrayList<>();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            this.setAppropriateAdapter();
        }

        this.setUrlSplit();
        this.executeHttpRequestWithRetrofit();

        return view;
    }

    /**
     * Called when the fragment is no longer in use.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    protected void updateUI(TheNewYorkTimesResponse theNewYorkTimesResponse) {
        articles.addAll(theNewYorkTimesResponse.getArticles());
        mRecyclerViewAdapter.notifyDataSetChanged();
    }

}
