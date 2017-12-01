package com.inabil;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.FrameLayout;

import com.inabil.auth.LoginFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;


public class MainTest {

    @Rule
    public ActivityTestRule<TestActivity> activityTestRule = new ActivityTestRule<TestActivity>(TestActivity.class);
    private TestActivity testActivity = null;

    @Before
    public void setUp() throws Exception {
        testActivity = activityTestRule.getActivity();
    }

    @Test
    public void testMainLogin() {
        FrameLayout frame = (FrameLayout) testActivity.findViewById(R.id.frame);
        assertNotNull(frame);

        LoginFragment loginFragment = new LoginFragment();
        testActivity.getSupportFragmentManager().beginTransaction().add(frame.getId(), loginFragment).commit();
        getInstrumentation().waitForIdleSync();

        View view1 = loginFragment.getView().findViewById(R.id.email_edit);
        assertNotNull(view1);

        View view2 = loginFragment.getView().findViewById(R.id.pass_edit);
        assertNotNull(view2);

        View view3 = loginFragment.getView().findViewById(R.id.login_button);
        assertNotNull(view3);
    }

    @After
    public void tearDown() throws Exception {
        testActivity = null;
    }

}
