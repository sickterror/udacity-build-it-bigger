package com.timelesssoftware.showjoke;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String JOKE_INTENT_EXTRA = "joke_intent_extra";
    private TextView mJokeTv;
    private String joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_activity_layout);
        mJokeTv = (TextView) findViewById(R.id.joke);
        joke = getIntent().getStringExtra(JOKE_INTENT_EXTRA);
        mJokeTv.setText(joke);


        findViewById(R.id.shareJoke).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, joke);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
