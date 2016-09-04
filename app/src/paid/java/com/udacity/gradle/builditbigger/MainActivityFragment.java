package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.myapplication.pranavj7.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.jokeactivity.RandomJokes;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.EndpointsAsyncResponse {

    private ProgressBar mLoadingBar;
    private EndpointsAsyncTask mEndpointsAsyncTask = null;
    private MyApi mApiService;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mLoadingBar = (ProgressBar) root.findViewById(R.id.loading);
        Button tellJoke = (Button) root.findViewById(R.id.tell_joke_button);

        tellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke();
            }
        });

        mApiService = new MyApi
                .Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setRootUrl("https://firebase-build-it-bigger.appspot.com/_ah/api/")
                .build();

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        endAsyncTask();
    }

    public void tellJoke() {
        endAsyncTask();
        mLoadingBar.setVisibility(View.VISIBLE);
        mEndpointsAsyncTask = new EndpointsAsyncTask(this, mApiService);
        mEndpointsAsyncTask.execute();
    }

    @Override
    public void processFinish(String result) {
        mLoadingBar.setVisibility(View.GONE);
        Intent intent = new Intent(getActivity(), RandomJokes.class);
        intent.putExtra(RandomJokes.JOKE_INTENT, result);
        startActivity(intent);
    }

    private void endAsyncTask() {
        if (mEndpointsAsyncTask != null) {
            mEndpointsAsyncTask.onDestroyed();
            mEndpointsAsyncTask = null;
        }
    }
}
