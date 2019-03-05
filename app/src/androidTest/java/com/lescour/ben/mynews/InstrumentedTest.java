package com.lescour.ben.mynews;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.lescour.ben.mynews.controller.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class InstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Check if PagerAdapter work.
     */
    @Test
    public void TabsIsDisplayed() {
        ViewInteraction horizontalScrollView = onView(
                allOf(withId(R.id.activity_main_tabs),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main_drawer_layout),
                                        0),
                                1),
                        isDisplayed()));
        horizontalScrollView.check(matches(isDisplayed()));
    }

    /**
     * Try many swipes on the ViewPager.
     */
    @Test
    public void checkViewPager() {
        wait(700);
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        wait(700);
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        wait(700);
        onView(withId(R.id.activity_main_viewpager)).perform(swipeRight());
        wait(700);
        onView(withId(R.id.activity_main_viewpager)).perform(swipeRight());
    }

    @Test
    public void searchTool_ValidForm(){
        onView(withId(R.id.menu_activity_main_search)).perform(click());

        onView(withId(R.id.search_query_term)).perform(typeText("Trump"));
        onView(withId(R.id.search_query_term)).perform(closeSoftKeyboard());
        onView(withId(R.id.checkbox_politics)).perform(scrollTo(), click());

        onView(withId(R.id.launch_search_button)).perform(click());
        onView(withId(R.id.custom_activity)).check(matches(isDisplayed()));
     }

    private void wait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}
