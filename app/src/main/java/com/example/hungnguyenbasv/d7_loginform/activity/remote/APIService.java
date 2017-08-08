package com.example.hungnguyenbasv.d7_loginform.activity.remote;

import com.example.hungnguyenbasv.d7_loginform.activity.model.DistrictResponse;
import com.example.hungnguyenbasv.d7_loginform.activity.model.ListProjectResponse;
import com.example.hungnguyenbasv.d7_loginform.activity.model.Message;
import com.example.hungnguyenbasv.d7_loginform.activity.model.ResetPasswordResponse;
import com.example.hungnguyenbasv.d7_loginform.activity.model.ShowFollowingResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hung.nguyenba.sv on 7/18/2017.
 */

public interface APIService {

    @POST("users/login.json")
    @FormUrlEncoded
    Call<ResponseBody> login(@Field("email") String email,
                             @Field("password") String password);

    @POST("users/register.json")
    @FormUrlEncoded
    Call<Message> register(@Field("first_name") String firstName,
                           @Field("last_name") String lastName,
                           @Field("email") String email,
                           @Field("password") String password);

    @POST("profile/getProfile.json")
    @FormUrlEncoded
    Call<ResponseBody> getProfile(@Field("token") String token,
                                  @Field("language") String language);


    @POST("profile/getStates.json")
    @FormUrlEncoded
    Call<ResponseBody> getState(@Field("country_id") String countryID,
                                @Field("token") String token);

    @POST("profile/getDistricts.json")
    @FormUrlEncoded
    Call<DistrictResponse> getDistrict(@Field("state_id") String stateID,
                                       @Field("token") String token);

    @POST("users/forgot_pass.json")
    @FormUrlEncoded
    Call<ResetPasswordResponse> resetPassword(@Field("email") String email);

    @POST("usersFollow/showFollowing.json")
    @FormUrlEncoded
    Call<ShowFollowingResponse> showFollowing(@Field("token") String token);

    @POST("usersFollow/showFollower.json")
    @FormUrlEncoded
    Call<ShowFollowingResponse> showFollower(@Field("token") String token);

    @POST("Discover/getListProjectsByOptionSearch.json")
    @FormUrlEncoded
    Call<ListProjectResponse> getListProjects(@Field("token") String token,
                                              @Field("filterCategory") String filterCategory,
                                              @Field("sortBy") String sortBy,
                                              @Field("filterRole") String filterRole,
                                              @Field("filterLocation") String filterLocation);
}
