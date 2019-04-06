package com.ninositsolution.packandmove.pojo;

import com.google.gson.annotations.SerializedName;

public class POJOClass {

    @SerializedName("status")
    private String status;

    @SerializedName("message")

    private String message;

    @SerializedName("otp")

    private Integer otp;


    @SerializedName("user_id")
    private String user_id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @SerializedName("token")
    private String token;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
