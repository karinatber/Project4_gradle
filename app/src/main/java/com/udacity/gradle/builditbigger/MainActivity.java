package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.JokeTeller;
import com.example.autotests.jokeactivity.JokeActivity;
import com.google.android.gms.ads.MobileAds;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.example.karina.bernice.myapplication.backend.myApi.MyApi;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    public static String mJokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String adUnitID = getString(R.string.banner_ad_unit_id);
        MobileAds.initialize(this, adUnitID);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        JokeTeller jokeTeller = new JokeTeller();
        String joke = jokeTeller.getJoke();
        new EndpointAsyncTask().execute(new Pair<Context, String>(this, joke));
    }

    public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String>{
        private MyApi mApiService = null;
        private Context context;

        @Override
        protected String doInBackground(Pair<Context, String>... pairs) {
            if (mApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://projectgradle-193917.appspot.com/_ah/api/");
                mApiService = builder.build();
            }
            context = pairs[0].first;
            String name = pairs[0].second;
            try{
                return mApiService.sayHi(name).execute().getData();
            } catch (IOException e){
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            mJokeText = s;
            Intent intent = new Intent(getBaseContext(), JokeActivity.class);
            intent.putExtra(JokeTeller.EXTRA_JOKE, s);
            startActivity(intent);
        }
    }
}
