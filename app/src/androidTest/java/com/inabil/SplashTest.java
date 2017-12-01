package com.inabil;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;


public class SplashTest {

    @Rule
    public ActivityTestRule<Mainsplash> activityTestRule = new ActivityTestRule<Mainsplash>(Mainsplash.class);
    private Mainsplash mainsplashActivity = null;

    @Before
    public void setUp() throws Exception {
        mainsplashActivity = activityTestRule.getActivity();
    }

    @Test
    public void testSplash() {
        View view1 = mainsplashActivity.findViewById(R.id.image_splash);
        assertNotNull(view1);

        View view2 = mainsplashActivity.findViewById(R.id.pb_splash);
        assertNotNull(view2);

        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
        MainActivity mainActivity = (MainActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(mainActivity);
        mainActivity.finish();

    }

    @After
    public void tearDown() throws Exception {
        mainsplashActivity = null;
    }

}
