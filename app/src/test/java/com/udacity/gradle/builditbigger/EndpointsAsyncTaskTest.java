package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.v4.util.Pair;

import com.example.JokeTeller;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


/**
 * Created by karina.bernice on 02/02/2018.
 */
public class EndpointsAsyncTaskTest {
    @Mock
    EndpointAsyncTask mockedAsyncTask;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void verifyAsyncTaskTest() throws InterruptedException {
        JokeTeller teller = new JokeTeller();
        String joke = teller.getJoke();


        String answer = mockedAsyncTask.doInBackground(new Pair<Context, String>(null, joke));
//        taskCountDown.await();

//        Mockito.verify(mockedAsyncTask).onPostExecute(joke);
        Assert.assertNotNull(answer);
        Mockito.verify(mockedAsyncTask).doInBackground(new Pair<Context, String>(null, joke));
    }
}