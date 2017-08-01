package com.example.hungnguyenbasv.d7_loginform;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.hungnguyenbasv.d7_loginform.activity.activity.LoginActivity;
import com.example.hungnguyenbasv.d7_loginform.activity.activity.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by hung.nguyenba.sv on 7/21/2017.
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    public static final String TAG = "MyTest TAG";

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            LoginActivity.class);

    /*
    Test chuyển LoginActivity -> RegisterActivity
     */
    @Test
    public void testClickRegister() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor =
                getInstrumentation().addMonitor(RegisterActivity.class.getName(), null, false);

        // open current activity.
        LoginActivity myActivity = (LoginActivity) mActivityRule.getActivity();
        onView(withId(R.id.tvSignup)).perform(click());

        //Watch for the timeout
        //example values 5000 if in ms, or 5 if it's in seconds.
        Activity nextActivity =
                getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        assertTrue(nextActivity instanceof RegisterActivity);
        nextActivity.finish();
    }

    @Test
    public void testLogin0() throws Throwable {
        final String email = "hung@gmail.com";
        final String password = "baHung12";
        final String[] volleyRes = new String[1];
        final CountDownLatch signal = new CountDownLatch(1);

        mActivityRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Volley test
//                String url = "https://smeportal.org/api/users/login.json";
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e(TAG, "onResponse: " + response);
//                        Gson gson = new Gson();
//                        Message message = gson.fromJson(response, Message.class);
//                        volleyRes[0] = message.getMessage();
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, "onErrorResponse: " + error.getMessage());
////                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
//                        volleyRes[0] = error.toString();
//                    }
//                }) {
//                    @Override
//                    protected Map<String, String> getParams() throws AuthFailureError {
//                        HashMap<String, String> params = new HashMap<>();
//                        params.put("email", email);
//                        params.put("password", password);
//                        return params;
//                    }
//
//                    @Override
//                    public Request.Priority getPriority() {
//                        return Priority.HIGH;
//                    }
//                };
//                RequestQueue mRequestQueue = Volley.newRequestQueue(mActivityRule.getActivity());
//                mRequestQueue.add(stringRequest);
            }
        });
        try {
            signal.await(30, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            // Test your jsonResult here
            assertEquals("Đăng nhập thành công", volleyRes[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLogin1() throws Throwable {
        final String email = "hung@gmail.com";
        final String password = "baHung12";
        final CountDownLatch signal = new CountDownLatch(1);

        mActivityRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                onView(withId(R.id.edtEmail)).perform(typeText(email), closeSoftKeyboard());
//                onView(withId(R.id.edtPassword)).perform(typeText(password), closeSoftKeyboard());
//                onView(withId(R.id.btnSignIn)).perform(click());
//                signal.countDown();
                LoginActivity activity = (LoginActivity) mActivityRule.getActivity();
//                activity.loginUsingVolley(email, password);
            }
        });
        try {
            signal.await(30, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            // Test your jsonResult here
            onView(withText("Đăng nhập thành công")).
                    inRoot(withDecorView(
                            not(is(mActivityRule.getActivity().
                                    getWindow().getDecorView())))).
                    check(matches(isDisplayed()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
