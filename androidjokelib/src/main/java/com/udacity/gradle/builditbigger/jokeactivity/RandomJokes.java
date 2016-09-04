package com.udacity.gradle.builditbigger.jokeactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RandomJokes extends AppCompatActivity {

    public static final String JOKE_INTENT = "JOKE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jokes_random);
        TextView jokeTextView = (TextView) findViewById(R.id.jokes);

        String joke = getIntent().getStringExtra(RandomJokes.JOKE_INTENT);
        if (joke != null && joke.length() != 0) {
            jokeTextView.setText(joke);
        }

    }
}