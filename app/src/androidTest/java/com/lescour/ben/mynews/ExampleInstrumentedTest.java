package com.lescour.ben.mynews;

import android.content.Context;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Test;

import androidx.test.InstrumentationRegistry;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.lescour.ben.mynews", appContext.getPackageName());
    }

    @Test
    @LargeTest
    public void checkMostPopularFragment() {
        onView(withId(R.id.activity_main_nav_view)).perform(click());

        onView(withId(R.id.activity_main_drawer_most_popular)).perform(click());

        //onView(withId()).check(matches(isDisplayed()));
    }
}
