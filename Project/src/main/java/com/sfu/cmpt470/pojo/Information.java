package com.sfu.cmpt470.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Administrator on 7/17/2018.
 */

@Entity
@Table(name="restaurant_info")
public class Information {
    @Column(name="phone")
    private String phone;
    @Column(name="address")
    private String address;
    @Id
    @Column(name="restaurant_id")
    private int restaurantId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
