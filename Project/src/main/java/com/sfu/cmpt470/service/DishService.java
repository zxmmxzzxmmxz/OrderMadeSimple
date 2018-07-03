package com.sfu.cmpt470.service;

public interface DishService{
    String getDishesFor(long restaurantID);
    String getDishesFor(String restaurantName);

    String getDish(long dishID);
}
