package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

public class Dish {
    @SerializedName(value="dish_id")
    private long _dishID;
    private long _dishVerID;
    @SerializedName(value="dish_name")
    private String _dishName;
    @SerializedName(value="description")
    private String _description;
    @SerializedName(value="restaurant_name")
    private String _restaurantName;
    @SerializedName(value="price")
    private float _price;
    @SerializedName(value="menu_flag")
    private String _menuFlag;
    private int _versionNumber;

    private Dish(long dishID, long dishVerID, String dishName, String description, String restaurantName, float price, String menuFlag, int versionNumber) {
        _dishID = dishID;
        _dishVerID = dishVerID;
        _dishName = dishName;
        _description = description;
        _restaurantName = restaurantName;
        _price = price;
        _menuFlag = menuFlag;
        _versionNumber = versionNumber;
    }

    public long getDishVerID() {
        return _dishVerID;
    }

    public float getPrice() {
        return _price;
    }

    public long getDishID() {
        return _dishID;
    }

    public String getDescription() {
        return _description;
    }

    public String getDishName() {
        return _dishName;
    }

    public String getMenuFlag() {
        return _menuFlag;
    }

    public String getRestaurantName() {
        return _restaurantName;
    }

    public int getVersionNumber() {
        return _versionNumber;
    }

    public Builder toBuilder() {
        Builder builder = newBuilder();
        builder.setDishID(_dishID)
                .setDescription(_description)
                .setDishName(_dishName)
                .setDishVerID(_dishVerID)
                .setMenuFlag(_menuFlag)
                .setPrice(_price)
                .setRestaurantName(_restaurantName)
                .setVersionNumber(_versionNumber);
        return builder;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        private long _dishID;
        private long _dishVerID;
        private String _dishName;
        private String _description;
        private String _restaurantName;
        private float _price;
        private String _menuFlag;
        private int _versionNumber;

        private Builder() {

        }

        public Builder setDishVerID(long _dishVerID) {
            this._dishVerID = _dishVerID;
            return this;
        }

        public Builder setDescription(String _description) {
            this._description = _description;
            return this;
        }

        public Builder setDishID(long _dishID) {
            this._dishID = _dishID;
            return this;
        }

        public Builder setDishName(String _dishName) {
            this._dishName = _dishName;
            return this;
        }

        public Builder setMenuFlag(String _menuFlag) {
            this._menuFlag = _menuFlag;
            return this;
        }

        public Builder setPrice(float _price) {
            this._price = _price;
            return this;
        }

        public Builder setRestaurantName(String _restaurantName) {
            this._restaurantName = _restaurantName;
            return this;
        }

        public void setVersionNumber(int _versionNumber) {
            this._versionNumber = _versionNumber;
        }

        public Dish build() {
            return new Dish(_dishID, _dishVerID, _dishName, _description, _restaurantName, _price, _menuFlag, _versionNumber);
        }
    }
}
