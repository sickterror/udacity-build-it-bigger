package com.timelesssoftware.showjoke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_INTENT_EXTRA = "joke_intent_extra";
    private TextView mJokeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_activity_layout);
        mJokeTv = (TextView) findViewById(R.id.joke);
        String joke = getIntent().getStringExtra(JOKE_INTENT_EXTRA);
        mJokeTv.setText(joke);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
