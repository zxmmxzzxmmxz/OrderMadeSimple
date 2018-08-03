package com.sfu.cmpt470.pojo;


import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 7/30/2018.
 */

public class DiningTable {
    private int tid;
    @SerializedName(value="seats")
    private int seats;
    @SerializedName(value="time")
    private String time;

    public DiningTable(){
    };

    public DiningTable(String time, int seats){
        this.time = time;
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }



    public String getTime() {
        return time;
    }
}
