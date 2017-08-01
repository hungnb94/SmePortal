package com.example.hungnguyenbasv.d7_loginform.activity.model;

/**
 * Created by hung.nguyenba.sv on 7/25/2017.
 */

public class GetProfileResponse {
    private int status;

    private String message;

    private DataProfile data;

    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setData(DataProfile data){
        this.data = data;
    }
    public DataProfile getData(){
        return this.data;
    }
}
