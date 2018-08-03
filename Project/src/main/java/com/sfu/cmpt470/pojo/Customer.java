package com.sfu.cmpt470.pojo;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 7/30/2018.
 */

public class Customer {

    private int cid;
    @SerializedName(value="fname")
    private String fname;
    @SerializedName(value="lname")
    private String lname;
    @SerializedName(value="phone")
    private String phone;

    public Customer(){

    }

    public Customer(String firstName, String lastName, String phone) {
        this.fname = firstName;
        this.lname = lastName;
        this.phone = phone;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPhone() {
        return phone;
    }
}
