package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

public class Dish {
    @SerializedName(value="dish_id")
    private long _dishID;
    @SerializedName(value="dish_name")
    private String _dishName;
    @SerializedName(value="description")
    private String _description;
    @SerializedName(value="restaurant_id")
    private long _restaurantID;
    @SerializedName(value="price")
    private float _price;
    @SerializedName(value="menu_flag")
    private String _menuFlag;

    private Dish(){

    }

    private Dish(long dishID, String dishName, String description, long restaurantID, float price, String menuFlag){
        _dishID = dishID;
        _dishName = dishName;
        _description = description;
        _restaurantID = restaurantID;
        _price = price;
        _menuFlag = menuFlag;
    }

    public long getDishID(){
        return _dishID;
    }

    public String getDishName() {
        return _dishName;
    }

    public String getDescription(){
        return _description;
    }

    public long getRestaurantID(){
        return _restaurantID;
    }

    public float getPrice(){
        return _price;
    }

    public String getMenuFlag(){
        return _menuFlag;
    }

    public DishBuilder toBuilder(){
        DishBuilder dishBuilder = new DishBuilder();
        dishBuilder.setDishID(this.getDishID());
        dishBuilder.setDishName(this.getDishName());
        dishBuilder.setDescription(this.getDescription());
        dishBuilder.setRestaurant(this.getRestaurantID());
        dishBuilder.setDescription(this.getDescription());
        dishBuilder.setPrice(this.getPrice());
        dishBuilder.setMenuFlag(this.getMenuFlag());
        return dishBuilder;
    }


    public static class DishBuilder{
        private long _dishID;
        private String _dishName;
        private String _description;
        private long _restaurantID;
        private float _price;
        private String _menuFlag;
        public DishBuilder(){
            _dishID = -1;
            _restaurantID = -1;
            _price = -1;
        }
        public DishBuilder setDishID(long dishID){
            _dishID = dishID;
            return this;
        }
        public DishBuilder setDishName(String dishName){
            _dishName = dishName;
            return this;
        }
        public DishBuilder setDescription(String description){
            _description = description;
            return this;
        }
        public DishBuilder setRestaurant(long restaurantID){
            _restaurantID = restaurantID;
            return this;
        }
        public DishBuilder setPrice(float price){
            _price = price;
            return this;
        }
        public DishBuilder setMenuFlag(String menuFlag){
            _menuFlag = menuFlag;
            return this;
        }
        public Dish build(){
            return new Dish(_dishID,_dishName,_description,_restaurantID,_price,_menuFlag);
        }
    }
}
