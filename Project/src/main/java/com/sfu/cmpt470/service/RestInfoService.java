package com.sfu.cmpt470.service;

import com.sfu.cmpt470.pojo.Restaurant;

/**
 * Created by Administrator on 7/17/2018.
 */
public interface RestInfoService {
    String getAllInformation(String restaurantId);
    Boolean storeReservation(String data);
    String getSeats(String time);
}
