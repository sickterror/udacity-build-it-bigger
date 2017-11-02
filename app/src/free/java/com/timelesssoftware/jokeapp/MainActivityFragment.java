package com.timelesssoftware.jokeapp;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private View mShowJoke;
    private View mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mShowJoke = view.findViewById(R.id.show_joke);
        mProgressBar = view.findViewById(R.id.loading_joke);

        mShowJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShowJoke.setEnabled(false);
                mProgressBar.setVisibility(View.VISIBLE);
                new JokeAsyncTask(getContext(), new JokeAsyncTask.IJokeAsyncListener() {
                    @Override
                    public void onSuccess() {
                        mShowJoke.setEnabled(true);
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(int error) {
                        mShowJoke.setEnabled(true);
                        Snackbar.make(mShowJoke, R.string.error_snack, Snackbar.LENGTH_LONG).show();
                    }
                }).execute("");
            }
        });
    }
}
