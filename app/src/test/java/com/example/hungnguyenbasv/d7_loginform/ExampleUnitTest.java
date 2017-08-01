package com.example.hungnguyenbasv.d7_loginform;

import com.example.hungnguyenbasv.d7_loginform.activity.activity.LoginActivity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void testCheckParams1() throws Exception {
        String email = "hungnb94@gmail.com";
        String password = "baHung12";
        LoginActivity loginActivity = new LoginActivity();
        assertEquals(true, loginActivity.checkParam(email, password));

    }
}