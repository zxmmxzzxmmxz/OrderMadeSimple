package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Entity;
import java.util.List;


public class Restaurant {

    @SerializedName(value="restaurant_id")
    private long _restaurantID;
    @SerializedName(value="restaurant_name")
    private String _restaurantName;

    public Restaurant(long restaurantID, String restaurantName){
        _restaurantID = restaurantID;
        _restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return _restaurantName;
    }

}
