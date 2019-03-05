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

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

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
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeRight());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeRight());
    }

    /**
     * Complete the form of search tool with query and category
     */
    @Test
    public void searchTool_ValidForm() {
        onView(withId(R.id.menu_activity_main_search)).perform(click());

        onView(withId(R.id.search_query_term)).perform(typeText("Trump"));
        onView(withId(R.id.search_query_term)).perform(closeSoftKeyboard());
        onView(withId(R.id.checkbox_politics)).check(matches(allOf( isEnabled(), isClickable()))).perform(doAClick());

        onView(withId(R.id.launch_search_button)).check(matches(allOf( isEnabled(), isClickable()))).perform(doAClick());
        onView(withId(R.id.custom_activity)).check(matches(isDisplayed()));
    }

    /**
     * Fail to complete the form of the search tool with no query selected.
     * Check the toast.
     */
    @Test
    public void searchTool_InvalidForm_NoQuery() {
        onView(withId(R.id.menu_activity_main_search)).perform(click());

        onView(withId(R.id.checkbox_politics)).check(matches(allOf( isEnabled(), isClickable()))).perform(doAClick());

        onView(withId(R.id.launch_search_button)).check(matches(allOf( isEnabled(), isClickable()))).perform(doAClick());
        onView(withText(R.string.no_query)).inRoot(withDecorView(not(mainActivityTestRule.getActivity()
                .getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    /**
     * Fail to complete the form of the search tool with no category selected.
     * Check the toast.
     */
    @Test
    public void searchTool_InvalidForm_NoCategory() {
        onView(withId(R.id.menu_activity_main_search)).perform(click());

        onView(withId(R.id.search_query_term)).perform(typeText("Trump"));
        onView(withId(R.id.search_query_term)).perform(closeSoftKeyboard());

        onView(withId(R.id.launch_search_button)).check(matches(allOf( isEnabled(), isClickable()))).perform(doAClick());
        onView(withText(R.string.no_category)).inRoot(withDecorView(not(mainActivityTestRule.getActivity()
                .getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    private ViewAction doAClick() {
       return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isEnabled(); // no constraints, they are checked above
            }

            @Override
            public String getDescription() {
                return "click plus button";
            }

            @Override
            public void perform(UiController uiController, View view) {
                view.performClick();
            }
        };
    }

    /**
     * Fail to complete the form of the search tool with no query and no category selected.
     * Check the toast.
     */
    @Test
    public void searchTool_InvalidForm_NoQuery_and_NoCategory() {
        onView(withId(R.id.menu_activity_main_search)).perform(click());

        onView(withId(R.id.launch_search_button)).check(matches(allOf( isEnabled(), isClickable()))).perform(doAClick());
        onView(withText(R.string.no_query_and_category)).inRoot(withDecorView(not(mainActivityTestRule.getActivity()
                .getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    /**
     * Start from the home screen, open the menu drawer and click on Business(from Other categories)
     * and open a new view (custom_view) and check if a list of article is displayed.
     */
    @Test
    public void tryAOtherCategory_InMenuDrawer() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.activity_main_toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.activity_main_nav_view),
                                        0)),
                        8),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        onView(withId(R.id.list)).check(matches(isDisplayed()));
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
