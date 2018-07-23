package com.sfu.cmpt470.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Administrator on 7/17/2018.
 */

@Entity
@Table(name="restaurant_hour")
public class Day implements Serializable {
    @Id
    @Column(name="restaurant_id")
    private int restaurantId;
    @Column(name = "open_hr")
    private String openHr;
    @Column(name = "close_hr")
    private String closeHr;
    @Id
    @Column(name = "open_day")
    private String day;

    public Day(){

    }

    public Day(String openHr, String closeHr, String day) {
        this.openHr = openHr;
        this.closeHr = closeHr;
        this.day = day;
    }

    public String getOpenHr() {
        return openHr;
    }

    public void setOpenHr(String openHr) {
        this.openHr = openHr;
    }

    public String getCloseHr() {
        return closeHr;
    }

    public void setCloseHr(String closeHr) {
        this.closeHr = closeHr;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Day{" +
                "restaurantId=" + restaurantId +
                ", openHr='" + openHr + '\'' +
                ", closeHr='" + closeHr + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
