package com.ninositsolution.packandmove.laboursupply.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LabourSupplyRequest {

    @SerializedName("user_id")
    @Expose
    public String user_id;

    @SerializedName("supply_type")
    @Expose
    public String supply_type;

    @SerializedName("container")
    @Expose
    public String container;

    @SerializedName("no_of_labour")
    @Expose
    public String no_of_labour;

    @SerializedName("no_of_hours")
    @Expose
    public String no_of_hours;

    @SerializedName("no_of_days")
    @Expose
    public String no_of_days;

    @SerializedName("floor")
    @Expose
    public String floor;

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

    @SerializedName("work_place")
    @Expose
    public String work_place;

    @SerializedName("distance")
    @Expose
    public String distance;

    @SerializedName("special_instruction")
    @Expose
    public String special_instruction;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSupply_type() {
        return supply_type;
    }

    public void setSupply_type(String supply_type) {
        this.supply_type = supply_type;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getNo_of_labour() {
        return no_of_labour;
    }

    public void setNo_of_labour(String no_of_labour) {
        this.no_of_labour = no_of_labour;
    }

    public String getNo_of_hours() {
        return no_of_hours;
    }

    public void setNo_of_hours(String no_of_hours) {
        this.no_of_hours = no_of_hours;
    }

    public String getNo_of_days() {
        return no_of_days;
    }

    public void setNo_of_days(String no_of_days) {
        this.no_of_days = no_of_days;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getType_of_goods() {
        return type_of_goods;
    }

    public void setType_of_goods(String type_of_goods) {
        this.type_of_goods = type_of_goods;
    }

    public String getWork_place() {
        return work_place;
    }

    public void setWork_place(String work_place) {
        this.work_place = work_place;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSpecial_instruction() {
        return special_instruction;
    }

    public void setSpecial_instruction(String special_instruction) {
        this.special_instruction = special_instruction;
    }
}
