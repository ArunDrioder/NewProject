package com.ninositsolution.packandmove.truckrental.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TruckRentalRequest
{
    @SerializedName("user_id")
    @Expose

    public String user_id;

    @SerializedName("truck_type")
    @Expose

    public String truck_type;

    @SerializedName("rental_pack_type")
    @Expose
    public String rental_pack_type;

    @SerializedName("pickup_from")
    @Expose
    public String pickup_from;

    @SerializedName("delivery_to")
    @Expose
    public String delivery_to;

    @SerializedName("delivery_address")
    @Expose
    public String delivery_address;

    @SerializedName("labour_supply")
    @Expose
    public String labour_supply;

    @SerializedName("no_of_labour")
    @Expose
    public String no_of_labour;

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

    @SerializedName("distance_meter")
    @Expose
    public String distance_meter;

    @SerializedName("special_instruction")
    @Expose
    public String special_instruction;

    @SerializedName("upload_photos")
    @Expose
    public ArrayList<String> upload_photos;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setTruck_type(String truck_type) {
        this.truck_type = truck_type;
    }

    public void setRental_pack_type(String rental_pack_type) {
        this.rental_pack_type = rental_pack_type;
    }

    public void setPickup_from(String pickup_from) {
        this.pickup_from = pickup_from;
    }

    public void setDelivery_to(String delivery_to) {
        this.delivery_to = delivery_to;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public void setLabour_supply(String labour_supply) {
        this.labour_supply = labour_supply;
    }

    public void setNo_of_labour(String no_of_labour) {
        this.no_of_labour = no_of_labour;
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

    public void setDistance_meter(String distance_meter) {
        this.distance_meter = distance_meter;
    }

    public void setSpecial_instruction(String special_instruction) {
        this.special_instruction = special_instruction;
    }

    public void setUpload_photos(ArrayList<String> upload_photos) {
        this.upload_photos = upload_photos;
    }
}
