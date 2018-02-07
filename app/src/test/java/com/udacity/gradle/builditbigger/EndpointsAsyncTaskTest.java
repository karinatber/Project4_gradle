package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.v4.util.Pair;

import com.example.JokeTeller;

import junit.framework.Assert;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;


/**
 * Created by karina.bernice on 02/02/2018.
 */
public class EndpointsAsyncTaskTest {
    MainActivity main = new MainActivity();

    @Test
    public void verifyAsyncTaskTest() throws InterruptedException {
        JokeTeller teller = new JokeTeller();
        String joke = teller.getJoke();

        EndpointAsyncTask endpointAsyncTask = new EndpointAsyncTask();
        CountDownLatch taskCountDown = endpointAsyncTask.getCount();

        endpointAsyncTask.execute(new Pair<Context, String>(null, joke));
        taskCountDown.await();

        Assert.assertNotNull(endpointAsyncTask.mJokeText);
    }
}