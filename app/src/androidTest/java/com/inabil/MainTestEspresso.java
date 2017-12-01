package com.inabil;

import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


public class MainTestEspresso {

    @Rule
    public ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<TestActivity>(TestActivity.class);
    private TestActivity testActivity = null;

    @Before
    public void setUp() throws Exception {

        testActivity = activityTestRule.getActivity();
    }

    @Test
    public void testMainEspresso() {

        onView(withId(R.id.email_edit))
                .perform(typeText("test@test.test"));
        onView(withId(R.id.pass_edit))
                .perform(typeText("test"));
        onView(withId(R.id.login_button))
                .perform(click());
        onView(withId(R.id.email_edit))
                .check(matches(hasErrorText(testActivity.getString(R.string.email_error))));

    }

    @After
    public void tearDown() throws Exception {
        testActivity = null;
    }

}
