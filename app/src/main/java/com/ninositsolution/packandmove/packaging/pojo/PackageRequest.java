package com.ninositsolution.packandmove.packaging.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageRequest {

    @SerializedName("user_id")
    @Expose

    public String user_id;



    @SerializedName("dop")
    @Expose
    public String dop;

    @SerializedName("location")
    @Expose

    public String location;

    @SerializedName("type_of_goods")
    @Expose
    public String type_of_goods;

    @SerializedName("volume")
    @Expose
    public String volume;

    @SerializedName("weight")
    @Expose
    public String weight;

    @SerializedName("remark")
    @Expose

    public String remark;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }



    public String getDop() {
        return dop;
    }

    public void setDop(String dop) {
        this.dop = dop;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType_of_goods() {
        return type_of_goods;
    }

    public void setType_of_goods(String type_of_goods) {
        this.type_of_goods = type_of_goods;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
