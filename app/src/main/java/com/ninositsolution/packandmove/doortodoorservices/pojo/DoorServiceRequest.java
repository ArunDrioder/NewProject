package com.ninositsolution.packandmove.doortodoorservices.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DoorServiceRequest {

    @SerializedName("user_id")
    @Expose
    public String user_id;

    @SerializedName("pickup_from")
    @Expose
    public String pickup_from;

    @SerializedName("pickup_date")
    @Expose
    public String pickup_date;

    @SerializedName("pickup_time")
    @Expose
    public String pickup_time;

    @SerializedName("delivery_to")
    @Expose
    public String delivery_to;

    @SerializedName("delivery_date")
    @Expose
    public String delivery_date;

    @SerializedName("delivery_time")
    @Expose
    public String delivery_time;

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

    @SerializedName("upload_photos")
    @Expose
    public ArrayList<String> upload_photos;



    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPickup_from() {
        return pickup_from;
    }

    public void setPickup_from(String pickup_from) {
        this.pickup_from = pickup_from;
    }

    public String getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(String pickup_date) {
        this.pickup_date = pickup_date;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(String pickup_time) {
        this.pickup_time = pickup_time;
    }

    public String getDelivery_to() {
        return delivery_to;
    }

    public void setDelivery_to(String delivery_to) {
        this.delivery_to = delivery_to;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
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



    public void setUpload_photos(ArrayList<String> upload_photos) {
        this.upload_photos = upload_photos;
    }
}
