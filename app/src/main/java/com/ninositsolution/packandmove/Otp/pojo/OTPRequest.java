package com.ninositsolution.packandmove.Otp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTPRequest {

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("otp")
    @Expose
    private String otp;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}

