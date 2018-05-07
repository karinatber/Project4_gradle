import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;

/**
 * Created by karina.bernice on 10/04/2018.
 */

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    @Before
    public void setUpFlavor(){
        Context contexr = InstrumentationRegistry.getContext();
//        if ("com.udacity.gradle.builditbigger.free".equals(contexr)){
//            ActivityTestRule<MainActivityFree> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivityFree.class, false, true);
//        } else {
//            ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, true);
//        }
    }
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, true);

    @Test
    public void clickJokeButtonTest(){
        onView(ViewMatchers.withId(R.id.instructions_text_view))
                .perform(ViewActions.click());
        onView(ViewMatchers.withId(R.id.tv_joke)).check(ViewAssertions.matches(Matchers.not(Matchers.nullValue())));
    }
}
