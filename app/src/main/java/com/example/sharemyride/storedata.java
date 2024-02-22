package com.example.sharemyride;

public class storedata {

    String name,mobile_no,email,password;

    public storedata() {
    }

    public storedata(String name, String mobile_no, String email, String password) {
        this.name = name;
        this.mobile_no = mobile_no;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
