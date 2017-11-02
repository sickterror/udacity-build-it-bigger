package com.timelesssoftware.jokeapp;


import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestJokeAsyncTaskNonNullString extends InstrumentationTestCase {


    @Test
    public void test() {
        String result = null;
        JokeAsyncTask endpointsAsyncTask = new JokeAsyncTask(InstrumentationRegistry.getTargetContext(), new JokeAsyncTask.IJokeAsyncListener() {
            @Override
            public void onSuccess() {
                //
            }

            @Override
            public void onError(int error) {
                //
            }
        });
        endpointsAsyncTask.execute();
        try {
            result = endpointsAsyncTask.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }
}
