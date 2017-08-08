package com.example.hungnguyenbasv.d7_loginform.activity.model;

import java.util.List;

/**
 * Created by hung.nguyenba.sv on 8/8/2017.
 */

public class ListProjectResponse {

    private int status;

    private String message;

    private List<Data> data;

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
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }

    public static class Data
    {
        private String roleUser;

        private int canView;

        private int user_id;

        private int project_id;

        private String title;

        private String description;

        private String image_url;

        private String video_id;

        private String pitch;

        private String start_date;

        private String end_date;

        private String owner;

        private String address;

        private String category;

        private int category_id;

        private int is_liked;

        private int total_role;

        private int total_role_joined;

        private List<List_roles> list_roles;

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
        public void setUser_id(int user_id){
            this.user_id = user_id;
        }
        public int getUser_id(){
            return this.user_id;
        }
        public void setProject_id(int project_id){
            this.project_id = project_id;
        }
        public int getProject_id(){
            return this.project_id;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }
        public void setDescription(String description){
            this.description = description;
        }
        public String getDescription(){
            return this.description;
        }
        public void setImage_url(String image_url){
            this.image_url = image_url;
        }
        public String getImage_url(){
            return this.image_url;
        }
        public void setVideo_id(String video_id){
            this.video_id = video_id;
        }
        public String getVideo_id(){
            return this.video_id;
        }
        public void setPitch(String pitch){
            this.pitch = pitch;
        }
        public String getPitch(){
            return this.pitch;
        }
        public void setStart_date(String start_date){
            this.start_date = start_date;
        }
        public String getStart_date(){
            return this.start_date;
        }
        public void setEnd_date(String end_date){
            this.end_date = end_date;
        }
        public String getEnd_date(){
            return this.end_date;
        }
        public void setOwner(String owner){
            this.owner = owner;
        }
        public String getOwner(){
            return this.owner;
        }
        public void setAddress(String address){
            this.address = address;
        }
        public String getAddress(){
            return this.address;
        }
        public void setCategory(String category){
            this.category = category;
        }
        public String getCategory(){
            return this.category;
        }
        public void setCategory_id(int category_id){
            this.category_id = category_id;
        }
        public int getCategory_id(){
            return this.category_id;
        }
        public void setIs_liked(int is_liked){
            this.is_liked = is_liked;
        }
        public int getIs_liked(){
            return this.is_liked;
        }
        public void setTotal_role(int total_role){
            this.total_role = total_role;
        }
        public int getTotal_role(){
            return this.total_role;
        }
        public void setTotal_role_joined(int total_role_joined){
            this.total_role_joined = total_role_joined;
        }
        public int getTotal_role_joined(){
            return this.total_role_joined;
        }
        public void setList_roles(List<List_roles> list_roles){
            this.list_roles = list_roles;
        }
        public List<List_roles> getList_roles(){
            return this.list_roles;
        }
    }

    public class List_roles
    {
        private int count;

        private int id;

        private Role role;

        private String role_es;

        private String role_ja;

        private int quantity;

        public void setCount(int count){
            this.count = count;
        }
        public int getCount(){
            return this.count;
        }
        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setRole(Role role){
            this.role = role;
        }
        public Role getRole(){
            return this.role;
        }
        public void setRole_es(String role_es){
            this.role_es = role_es;
        }
        public String getRole_es(){
            return this.role_es;
        }
        public void setRole_ja(String role_ja){
            this.role_ja = role_ja;
        }
        public String getRole_ja(){
            return this.role_ja;
        }
        public void setQuantity(int quantity){
            this.quantity = quantity;
        }
        public int getQuantity(){
            return this.quantity;
        }
    }

    public class Role
    {
        private int id;

        private String role;

        private String role_es;

        private String role_ja;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setRole(String role){
            this.role = role;
        }
        public String getRole(){
            return this.role;
        }
        public void setRole_es(String role_es){
            this.role_es = role_es;
        }
        public String getRole_es(){
            return this.role_es;
        }
        public void setRole_ja(String role_ja){
            this.role_ja = role_ja;
        }
        public String getRole_ja(){
            return this.role_ja;
        }
    }
}
