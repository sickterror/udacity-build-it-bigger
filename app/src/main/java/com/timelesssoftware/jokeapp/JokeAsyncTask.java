package com.timelesssoftware.jokeapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.luka.myapplication.backend.myApi.MyApi;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.timelesssoftware.showjoke.JokeActivity;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by Luka on 2.11.2017.
 */

public class JokeAsyncTask extends AsyncTask<String, Void, String> {


    private Context mContext;
    private IJokeAsyncListener iJokeAsyncListener;
    private MyApi mJokeApi;
    private InterstitialAd mInterstitialAd;

    public JokeAsyncTask(Context mContext, IJokeAsyncListener iJokeAsyncListener) {
        this.mContext = mContext;
        this.iJokeAsyncListener = iJokeAsyncListener;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        if (this.mContext == null || this.iJokeAsyncListener == null) {
            return "mContext and iJoke interface are null";
        }
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("http://10.0.2.2:8080/_ah/api/");
        mJokeApi = builder.build();
        try {
            return mJokeApi.getJoke().execute().getData();
        } catch (IOException e) {
            Log.w("eerror", e);
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(final String s) {
        super.onPostExecute(s);
        Log.i("test string", s);

        mInterstitialAd = new InterstitialAd(JokeTellingApp.context);
        mInterstitialAd.setAdUnitId(mContext.getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                iJokeAsyncListener.onError(errorCode);
                startJokeActivity(s);
            }

            @Override
            public void onAdClosed() {
                iJokeAsyncListener.onSuccess();
                startJokeActivity(s);

            }
        });
        AdRequest ar = new AdRequest
                .Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(ar);
    }

    void startJokeActivity(String joke) {
        Intent intent = new Intent(mContext, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_INTENT_EXTRA, joke);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    interface IJokeAsyncListener {
        void onSuccess();

        void onError(int error);
    }
}
