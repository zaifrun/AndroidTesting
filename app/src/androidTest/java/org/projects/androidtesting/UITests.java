package org.projects.androidtesting;

import android.graphics.Color;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

//This is our test class for instrumentation tests (UI in this case)

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITests {

    /**
     * Returns a matcher that matches {@link TextView}s based on text property value. Note: View's
     * text property is never null. If you setText(null) it will still be "". Do not use null
     * matcher.
     *
     * @param integerMatcher {@link Matcher} of {@link String} with text to match
     */
    public static Matcher<View> withCurrentTextColor(final Matcher<Integer> integerMatcher) {
        checkNotNull(integerMatcher);
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with text color: ");
                integerMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(TextView textView) {
                return integerMatcher.matches(textView.getCurrentTextColor());
            }
        };
    }

    /**
     * Returns a matcher that matches {@link TextView} based on it's text property value. Note:
     * View's Sugar for withTextColor(is("string")).
     */
    public static Matcher<View> withCurrentTextColor(int color) {
        return withCurrentTextColor(is(color));
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    String mStringToBetyped;

    @Before
    public void initValidString() {
        mStringToBetyped = "martin";
    }

    //This method test the spinner to change the color.
    @Test
    public void changeColor()
    {
        //Find the spinner - click on it
        onView(withId(R.id.spinner)).perform(click());
        //Oo through all strings (onData), click on the one that is "Blue"
        onData(allOf(is(instanceOf(String.class)), is("Blue"))).perform(click());
        //Check that the textcolor textview now actually has the textcolor set to blu
        //note - the withCurrentTextColor is a custom defined matcher - see elsewhere in this file
        onView(withId(R.id.textColor)).check(matches(withCurrentTextColor(Color.BLUE)));

    }

    //This test will input some text into the text field, then click the button
    //to save and and test that it was actually saved.
    @Test
    public void changeText() {
        // Type text and then press the button.

        onView(withId(R.id.editText))
                .perform(typeText(mStringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.saveButton)).perform(click());

        onView(withId(R.id.textView)).check(matches(withText(mStringToBetyped)));

    }
}
