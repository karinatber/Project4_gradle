import android.content.Context;
import android.support.v4.util.Pair;
import android.test.mock.MockContext;

import com.example.JokeTeller;
import com.udacity.gradle.builditbigger.EndpointAsyncTask;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by karina.bernice on 13/04/2018.
 */


public class UnitGradleProjTest {
    Context mContext;
    String jokeText;

    @Test
    public void testCheckContext(){
//        mContext = InstrumentationRegistry.getTargetContext();
        mContext = new MockContext();
        Assert.assertEquals("com.udacity.gradle.builditbigger", mContext);

    }

    @Test
    public void testEndpointAsynTask() throws Throwable{
//        EndpointAsyncTask mockTask = Mockito.mock(EndpointAsyncTask.class);
        JokeTeller teller = new JokeTeller();
        String joke = teller.getJoke();

        final String[] response = new String[1];
//        when(mockTask.execute(new Pair<Context, String>(mContext, joke))).thenCallRealMethod();
        EndpointAsyncTask testingTask = new EndpointAsyncTask();
        testingTask.setListener(new EndpointAsyncTask.AsyncTaskListener() {
            @Override
            public void onComplete(String s) {
                response[0] = s;
            }
        });
        testingTask.execute(new Pair<Context, String>(mContext, joke));

//        mockTask.execute(new Pair<Context, String>(mContext, joke));
//
//        verify(mockTask).execute(new Pair<Context, String>(mContext, joke));
//        org.junit.Assert.assertNotNull(jokeText);
    }
}
