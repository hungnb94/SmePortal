package com.example.hungnguyenbasv.d7_loginform;

import com.example.hungnguyenbasv.d7_loginform.activity.activity.LoginActivity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hung.nguyenba.sv on 7/20/2017.
 */
public class LoginUnitTest {

    @Test
    public void testCheckParams1() throws Exception {
        String email = "hungnb94@gmail.com";
        String password = "baHung12";
        LoginActivity loginActivity = new LoginActivity();
        assertEquals(true, loginActivity.checkParam(email, password));
    }

    @Test
    public void testCheckParams2() throws Exception {
        String email = "hungnb94@gmail.com";
        String password = "";
        LoginActivity loginActivity = new LoginActivity();
        assertEquals(false, loginActivity.checkParam(email, password));
    }

    @Test
    public void testCheckParams3() throws Exception {
        String email = null;
        String password = "baHung12";
        LoginActivity loginActivity = new LoginActivity();
        assertEquals(false, loginActivity.checkParam(email, password));
    }

    @Test
    public void testCheckParams4() throws Exception {
        String email = null;
        String password = "";
        LoginActivity loginActivity = new LoginActivity();
        assertEquals(false, loginActivity.checkParam(email, password));
    }

}
