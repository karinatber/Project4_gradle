package com.example.autotests.jokeactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.JokeTeller;

public class JokeActivity extends AppCompatActivity {
    TextView mJokeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        mJokeText = (TextView)findViewById(R.id.tv_joke);

        String jokeString = getIntent().getStringExtra(JokeTeller.EXTRA_JOKE);

        mJokeText.setText(jokeString);
    }
}
