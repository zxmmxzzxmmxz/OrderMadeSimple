package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 6/4/2018.
 */
public class Order {
    @SerializedName("name")
    private String customer;
    @SerializedName("drink")
    private String drink;

    public Order(){
        this.customer = "Andrew";
        this.drink = "Vanilla ice coffee";
    }

    public Order(String customer, String drink) {
        this.customer = customer;
        this.drink = drink;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }
}
