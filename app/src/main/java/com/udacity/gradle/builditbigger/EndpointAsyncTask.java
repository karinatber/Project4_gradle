package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.example.JokeTeller;
import com.example.autotests.jokeactivity.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.example.karina.bernice.myapplication.backend.myApi.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


/**
 * Created by karina.bernice on 07/02/2018.
 */

public class EndpointAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private MyApi mApiService = null;
    private Context mContext;
    private CountDownLatch mCount;
    public String mJokeText;
    AsyncTaskListener mListener;

    public CountDownLatch getCount(){
        return mCount;
    }

    @Override
    protected void onPreExecute() {
        mCount = new CountDownLatch(1);
    }

    @Override
    protected String doInBackground(Pair<Context, String>... pairs) {
        if (mApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://projectgradle-193917.appspot.com/_ah/api/");
            mApiService = builder.build();
        }
        mContext = pairs[0].first;
        String name = pairs[0].second;
        try{
            mJokeText = mApiService.sayHi(name).execute().getData();
            return mJokeText;
        } catch (IOException e){
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        mJokeText = s;
        mCount.countDown();
        if (mContext != null) {
            Intent intent = new Intent(mContext, JokeActivity.class);
            intent.putExtra(JokeTeller.EXTRA_JOKE, s);
            mContext.startActivity(intent);
        }
        if (mListener != null){
            mListener.onComplete(s);
        }
    }

    public String getJokeText(){
        return mJokeText;
    }

    public void setListener(AsyncTaskListener listener){
        mListener = listener;
    }

    public interface AsyncTaskListener {
        void onComplete(String s);
    }
}

