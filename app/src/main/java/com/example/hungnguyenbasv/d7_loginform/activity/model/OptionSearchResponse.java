package com.example.hungnguyenbasv.d7_loginform.activity.model;

import java.util.List;

/**
 * Created by hung.nguyenba.sv on 8/8/2017.
 */

public class OptionSearchResponse {
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

    public class Data
    {
        private int object_id;

        private String object_name;

        private int group;

        private int type;

        public void setObject_id(int object_id){
            this.object_id = object_id;
        }
        public int getObject_id(){
            return this.object_id;
        }
        public void setObject_name(String object_name){
            this.object_name = object_name;
        }
        public String getObject_name(){
            return this.object_name;
        }
        public void setGroup(int group){
            this.group = group;
        }
        public int getGroup(){
            return this.group;
        }
        public void setType(int type){
            this.type = type;
        }
        public int getType(){
            return this.type;
        }

        @Override
        public String toString() {
            return this.getObject_name();
        }
    }
}
