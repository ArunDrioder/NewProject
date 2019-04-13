package com.ninositsolution.packandmove.temporarystorage.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StorageRequest {

    @SerializedName("user_id")
    @Expose
    public String user_id;

    @SerializedName("storage_date")
    @Expose
    public String storage_date;

    @SerializedName("temporary_storage")
    @Expose
    public String temporary_storage;

    @SerializedName("long_storage")
    @Expose
    public String long_storage;

    @SerializedName("type_of_goods")
    @Expose
    public String type_of_goods;

    @SerializedName("volume")
    @Expose
    public String volume;

    @SerializedName("weight")
    @Expose
    public String weight;

    @SerializedName("pick_up_from")
    @Expose
    public String pick_up_from;

    @SerializedName("remark")
    @Expose
    public String remark;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStorage_date() {
        return storage_date;
    }

    public void setStorage_date(String storage_date) {
        this.storage_date = storage_date;
    }

    public String getTemporary_storage() {
        return temporary_storage;
    }

    public void setTemporary_storage(String temporary_storage) {
        this.temporary_storage = temporary_storage;
    }

    public String getLong_storage() {
        return long_storage;
    }

    public void setLong_storage(String long_storage) {
        this.long_storage = long_storage;
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

    public String getPick_up_from() {
        return pick_up_from;
    }

    public void setPick_up_from(String pick_up_from) {
        this.pick_up_from = pick_up_from;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
