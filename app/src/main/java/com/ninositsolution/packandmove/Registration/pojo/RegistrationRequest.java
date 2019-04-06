package com.ninositsolution.packandmove.Registration.pojo;

import com.google.gson.annotations.SerializedName;

public class RegistrationRequest {

    @SerializedName("name")
    private String name;

    @SerializedName("lname")
    private String lastname;

    @SerializedName("email")
    private String email;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("password")
    private String password;

    @SerializedName("confirm_password")
    private String confirm_password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
}
