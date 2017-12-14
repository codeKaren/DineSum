package com.a0xffffffff.dinesum;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.scrollTo;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.text.InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
import static android.text.InputType.TYPE_TEXT_VARIATION_NORMAL;
import static android.text.InputType.TYPE_TEXT_VARIATION_FILTER;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static org.hamcrest.CoreMatchers.anything;

import android.support.test.uiautomator.*;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RequesterRemoveTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void removeRequest() {
        UiDevice device = UiDevice.getInstance(getInstrumentation());

        pauseTestFor(2000);
        // refresh the request feed
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);

        onData(anything()).inAdapterView(withId(R.id.requester_request_list)).atPosition(3).perform(scrollTo());

        // select the user 'spending request to be displayed
        UiObject pendingRequest = device.findObject(new UiSelector().textContains("Chick-fil-A"));
        try {
            pendingRequest.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        pauseTestFor(500);

        // check request information for the correct info
        pauseTestFor(500);

        // remove this request
        onView(withId(R.id.request_info_button2)).perform(click());
        pauseTestFor(500);

        /*
        // check that request is in the outgoing requests page
        onView(withId(R.id.menu_acceptor)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);
        UiObject claimedRequest = device.findObject(new UiSelector().textContains("Father's"));
        try {
            claimedRequest.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        pauseTestFor(500);
        // verify that the claimed contains the correct data
        // get request data
        String restaurant_name = "Father's Office";
        String party_size = "Party of 2";
        String price = "$5";
        String status = "Claimed";
        onView(withId(R.id.request_info_restaurant_name)).check(matches(withText(restaurant_name)));
        onView(withId(R.id.request_info_party_size)).check(matches(withText(party_size)));
        onView(withId(R.id.request_info_price)).check(matches(withText(price)));
        onView(withId(R.id.request_info_status)).check(matches(withText(status)));
        */

        pauseTestFor(2000);
    }

    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}