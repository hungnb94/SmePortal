package com.example.hungnguyenbasv.d7_loginform.activity.model;

/**
 * Created by hung.nguyenba.sv on 7/24/2017.
 */

public class LoginSuccessResponse extends Message {
    private Data data;

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    public class Data {
        private int activated;

        private int adminType;

        private String token;

        private int number_notifiation;

        public void setActivated(int activated) {
            this.activated = activated;
        }

        public int getActivated() {
            return this.activated;
        }

        public void setAdminType(int adminType) {
            this.adminType = adminType;
        }

        public int getAdminType() {
            return this.adminType;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return this.token;
        }

        public void setNumber_notifiation(int number_notifiation) {
            this.number_notifiation = number_notifiation;
        }

        public int getNumber_notifiation() {
            return this.number_notifiation;
        }
    }

}
