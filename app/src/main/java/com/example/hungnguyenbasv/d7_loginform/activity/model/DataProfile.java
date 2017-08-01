package com.example.hungnguyenbasv.d7_loginform.activity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hung.nguyenba.sv on 7/25/2017.
 */

public class DataProfile {
    private Roles Roles;

    private Users Users;

    private List<Website> websites;

    private List<String> interests;


    private RoleArrays roles;

    private String avatarURL;

    private List<String> works;

    public void setRoles(Roles Roles){
        this.Roles = Roles;
    }
    public Roles getRoles(){
        return this.Roles;
    }
    public void setUsers(Users Users){
        this.Users = Users;
    }
    public Users getUsers(){
        return this.Users;
    }
    public void setWebsites(List<Website> websites){
        this.websites = websites;
    }
    public List<Website> getWebsites(){
        return this.websites;
    }
    public void setInterests(List<String> interests){
        this.interests = interests;
    }
    public List<String> getInterests(){
        return this.interests;
    }
    public void setAvatarURL(String avatarURL){
        this.avatarURL = avatarURL;
    }
    public String getAvatarURL(){
        return this.avatarURL;
    }
    public void setWorks(List<String> works){
        this.works = works;
    }
    public List<String> getWorks(){
        return this.works;
    }
    public void setRoles(RoleArrays roles) {
        this.roles = roles;
    }
    public RoleArrays getroles(){
        return this.roles;
    }

    public class Users {
        private int id;

        private String first_name;

        private String last_name;

        private String name;

        private String chat_status;

        private String biography;

        private int postal_code;

        private int country_id;

        private int state_id;

        private int district_id;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setFirst_name(String first_name){
            this.first_name = first_name;
        }
        public String getFirst_name(){
            return this.first_name;
        }
        public void setLast_name(String last_name){
            this.last_name = last_name;
        }
        public String getLast_name(){
            return this.last_name;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setChat_status(String chat_status){
            this.chat_status = chat_status;
        }
        public String getChat_status(){
            return this.chat_status;
        }
        public void setBiography(String biography){
            this.biography = biography;
        }
        public String getBiography(){
            return this.biography;
        }
        public void setPostal_code(int postal_code){
            this.postal_code = postal_code;
        }
        public int getPostal_code(){
            return this.postal_code;
        }
        public void setCountry_id(int country_id){
            this.country_id = country_id;
        }
        public int getCountry_id(){
            return this.country_id;
        }
        public void setState_id(int state_id){
            this.state_id = state_id;
        }
        public int getState_id(){
            return this.state_id;
        }
        public void setDistrict_id(int district_id){
            this.district_id = district_id;
        }
        public int getDistrict_id(){
            return this.district_id;
        }
    }

    public class Roles {
        private int role0;

        private int role1;

        public void setRole0(int role0) {
            this.role0 = role0;
        }

        public int getRole0() {
            return this.role0;
        }

        public void setRole1(int role1) {
            this.role1 = role1;
        }

        public int getRole1() {
            return this.role1;
        }

    }

    public class RoleArrays {
        @SerializedName("1")
        @Expose
        private String _1;
        @SerializedName("2")
        @Expose
        private String _2;
        @SerializedName("3")
        @Expose
        private String _3;
        @SerializedName("4")
        @Expose
        private String _4;
        @SerializedName("5")
        @Expose
        private String _5;
        @SerializedName("6")
        @Expose
        private String _6;
        @SerializedName("7")
        @Expose
        private String _7;

        public String get1() {
            return _1;
        }

        public void set1(String _1) {
            this._1 = _1;
        }

        public String get2() {
            return _2;
        }

        public void set2(String _2) {
            this._2 = _2;
        }

        public String get3() {
            return _3;
        }

        public void set3(String _3) {
            this._3 = _3;
        }

        public String get4() {
            return _4;
        }

        public void set4(String _4) {
            this._4 = _4;
        }

        public String get5() {
            return _5;
        }

        public void set5(String _5) {
            this._5 = _5;
        }

        public String get6() {
            return _6;
        }

        public void set6(String _6) {
            this._6 = _6;
        }

        public String get7() {
            return _7;
        }

        public void set7(String _7) {
            this._7 = _7;
        }

    }

    public class Website
    {
        private String name;

        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
    }

}
