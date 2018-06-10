package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

public class Dish {
    @SerializedName(value="dish_id")
    private long _dishID;
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

    private Dish(){

    }

    private Dish(long dishID, String dishName, String description, String restaurantName, float price, String menuFlag){
        _dishID = dishID;
        _dishName = dishName;
        _description = description;
        _restaurantName = restaurantName;
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

    public String getRestaurantName(){
        return _restaurantName;
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
        dishBuilder.setRestaurant(this.getRestaurantName());
        dishBuilder.setDescription(this.getDescription());
        dishBuilder.setPrice(this.getPrice());
        dishBuilder.setMenuFlag(this.getMenuFlag());
        return dishBuilder;
    }


    public static class DishBuilder{
        private long _dishID;
        private String _dishName;
        private String _description;
        private String _restaurantName;
        private float _price;
        private String _menuFlag;
        public DishBuilder(){
            _dishID = -1;
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
        public DishBuilder setRestaurant(String restaurantName){
            _restaurantName = restaurantName;
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
            return new Dish(_dishID,_dishName,_description,_restaurantName,_price,_menuFlag);
        }
    }
}
