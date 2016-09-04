package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.myapplication.pranavj7.backend.myApi.MyApi;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    private MyApi mApiService = null;

    private EndpointsAsyncResponse mResponse = null;

    public EndpointsAsyncTask(EndpointsAsyncResponse response, MyApi apiService) {
        mResponse = response;
        mApiService = apiService;
    }

    @Override
    protected final String doInBackground(Void... params) {
        try {
            return mApiService.joke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (mResponse != null) {
            mResponse.processFinish(result);
        }
    }

    public void onDestroyed() {
        mResponse = null;
    }

    public interface EndpointsAsyncResponse {
        void processFinish(String result);
    }
}
