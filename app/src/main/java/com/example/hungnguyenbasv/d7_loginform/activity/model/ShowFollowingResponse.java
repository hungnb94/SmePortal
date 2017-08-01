package com.example.hungnguyenbasv.d7_loginform.activity.model;

import java.util.List;

/**
 * Created by hung.nguyenba.sv on 7/28/2017.
 */

public class ShowFollowingResponse {
    private int status;

    private String message;

    private Data data;

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
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }

    public class Data
    {
        private List<User> user;

        public void setUser(List<User> user){
            this.user = user;
        }
        public List<User> getUser(){
            return this.user;
        }
    }
    public class User
    {
        private String roleUser;

        public User(String roleUser, int canView, int userid, String name, String avatar, String address, String roles, int connection) {
            this.roleUser = roleUser;
            this.canView = canView;
            this.userid = userid;
            this.name = name;
            this.avatar = avatar;
            this.address = address;
            this.roles = roles;
            this.connection = connection;
        }

        private int canView;

        private int userid;

        private String name;

        private String avatar;

        private String address;

        private String roles;

        private int connection;

        public void setRoleUser(String roleUser){
            this.roleUser = roleUser;
        }
        public String getRoleUser(){
            return this.roleUser;
        }
        public void setCanView(int canView){
            this.canView = canView;
        }
        public int getCanView(){
            return this.canView;
        }
        public void setUserid(int userid){
            this.userid = userid;
        }
        public int getUserid(){
            return this.userid;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setAvatar(String avatar){
            this.avatar = avatar;
        }
        public String getAvatar(){
            return this.avatar;
        }
        public void setAddress(String address){
            this.address = address;
        }
        public String getAddress(){
            return this.address;
        }
        public void setRoles(String roles){
            this.roles = roles;
        }
        public String getRoles(){
            return this.roles;
        }
        public void setConnection(int connection){
            this.connection = connection;
        }
        public int getConnection(){
            return this.connection;
        }
    }
}
