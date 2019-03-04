package com.lescour.ben.mynews;

import com.lescour.ben.mynews.controller.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class InstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**@Test
    public void searchTool_ValidForm(){
    onView(withId(R.id.menu_activity_main_search)).perform(click());

    onView(withId(R.id.search_query_term)).perform(typeText("Trump"));
    onView(withId(R.id.search_query_term)).perform(closeSoftKeyboard());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.checkbox_politics)).perform(click());

    onView(withId(R.id.launch_search_button)).perform(click());
    onView(withId(R.id.custom_activity)).check(matches(isDisplayed()));
    }*/

    @Test
    public void checkSearchActivity(){
        onView(withId(R.id.menu_activity_main_search)).perform(click());
        onView(withId(R.id.checkbox_business)).perform(click());
        onView(withId(R.id.launch_search_button)).perform(click());
        onView(withId(R.id.search_activity)).check(matches(isDisplayed()));
    }
}
