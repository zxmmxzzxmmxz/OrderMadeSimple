package com.sfu.cmpt470.pojo.ValBridge;

import com.sfu.cmpt470.pojo.Dish;
import com.sfu.cmpt470.pojo.DishVal;
import com.sfu.cmpt470.pojo.DishVerVal;

public class DishBridge {
    public static Dish toDishFrom(DishVal dishVal){
        Dish.DishBuilder builder = new Dish.DishBuilder();
        builder.setDishID(dishVal.getDishID())
                .setMenuFlag(dishVal.getVerVal().getMenuFlag())
                .setDishID(dishVal.getDishID())
                .setDescription(dishVal.getDescription())
                .setDishName(dishVal.getDishName())
                .setPrice(dishVal.getPrice())
                .setRestaurant(dishVal.getVerVal().getRestaurantName());
        return builder.build();
    }

    public static DishVerVal toDishVerValFrom(Dish dish){
        DishVerVal.Builder builder = DishVerVal.newBuilder();
        return builder.setDishID(dish.getDishID())
                .setDishName(dish.getDishName())
                .setDescription(dish.getDescription())
                .setRestaurantName(dish.getRestaurantName())
                .setPrice(dish.getPrice())
                .setMenuFlag(dish.getMenuFlag())
                .build();
    }

    public static DishVal toDishValFrom(DishVerVal verVal){
        DishVal.DishBuilder builder = DishVal.newBuilder();
        builder.setDishID(verVal.getDishID())
                .setDescription(verVal.getDescription())
                .setDishName(verVal.getDishName())
                .setPrice(verVal.getPrice())
                .setVerVal(verVal.toBuilder().build());
        return builder.build();
    }

    public static Dish toDishFrom(DishVerVal verVal){
        Dish.DishBuilder builder = new Dish.DishBuilder();
        builder.setDishID(verVal.getDishID())
                .setMenuFlag(verVal.getMenuFlag())
                .setDishID(verVal.getDishID())
                .setDescription(verVal.getDescription())
                .setDishName(verVal.getDishName())
                .setPrice(verVal.getPrice())
                .setRestaurant(verVal.getRestaurantName());
        return builder.build();
    }
}
