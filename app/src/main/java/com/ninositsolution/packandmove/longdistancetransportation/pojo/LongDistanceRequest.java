package com.ninositsolution.packandmove.longdistancetransportation.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LongDistanceRequest
{
    @SerializedName("user_id")
    @Expose
    public String user_id;

    @SerializedName("pickup_from")
    @Expose
    public String pickup_from;

    @SerializedName("delivery_to")
    @Expose
    public String delivery_to;

    @SerializedName("pickup_floor")
    @Expose
    public String pickup_floor;

    @SerializedName("deliver_floor")
    @Expose
    public String deliver_floor;

    @SerializedName("pickup_elevator")
    @Expose
    public String pickup_elevator;

    @SerializedName("deliver_elevator")
    @Expose
    public String deliver_elevator;

    @SerializedName("pickup_date")
    @Expose
    public String pickup_date;

    @SerializedName("delivery_date")
    @Expose
    public String delivery_date;

    @SerializedName("no_of_packages")
    @Expose
    public String no_of_packages;

    @SerializedName("volume")
    @Expose
    public String volume;

    @SerializedName("weight")
    @Expose
    public String weight;

    @SerializedName("quantity")
    @Expose
    public String quantity;

    @SerializedName("type_of_goods")
    @Expose
    public String type_of_goods;

    @SerializedName("package")
    @Expose
    public String packagee;



    @SerializedName("labour")
    @Expose
    public String labour;



    @SerializedName("special_instruction")
    @Expose
    public String special_instruction;

    @SerializedName("upload_photos")
    @Expose
    public String upload_photos;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setPickup_from(String pickup_from) {
        this.pickup_from = pickup_from;
    }

    public void setDelivery_to(String delivery_to) {
        this.delivery_to = delivery_to;
    }

    public void setPickup_floor(String pickup_floor) {
        this.pickup_floor = pickup_floor;
    }

    public void setDeliver_floor(String deliver_floor) {
        this.deliver_floor = deliver_floor;
    }

    public void setPickup_elevator(String pickup_elevator) {
        this.pickup_elevator = pickup_elevator;
    }

    public void setDeliver_elevator(String deliver_elevator) {
        this.deliver_elevator = deliver_elevator;
    }

    public void setPickup_date(String pickup_date) {
        this.pickup_date = pickup_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public void setNo_of_packages(String no_of_packages) {
        this.no_of_packages = no_of_packages;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setType_of_goods(String type_of_goods) {
        this.type_of_goods = type_of_goods;
    }

    public void setPackagee(String packagee) {
        this.packagee = packagee;
    }


    public void setLabour(String labour) {
        this.labour = labour;
    }


    public void setSpecial_instruction(String special_instruction) {
        this.special_instruction = special_instruction;
    }

    public void setUpload_photos(String upload_photos) {
        this.upload_photos = upload_photos;
    }
}
