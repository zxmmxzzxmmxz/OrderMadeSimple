package com.sfu.cmpt470.service;

import com.google.gson.*;

import com.sfu.cmpt470.DAO.RestaruantInfoDAO;
import com.sfu.cmpt470.pojo.Customer;
import com.sfu.cmpt470.pojo.Day;
import com.sfu.cmpt470.pojo.Information;
import com.sfu.cmpt470.pojo.DiningTable;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 7/17/2018.
 */
public class RestInfoServiceImpl implements RestInfoService {
    @Override
    public String getAllInformation(String name) {
        RestaruantInfoDAO dao = new RestaruantInfoDAO();
        int restaurantId = dao.getRestaurantId(name);
        // get all open hour
        List<Day> allDays = dao.getAllDaysHour(restaurantId);
        // get contact information
        Information information = dao.getInfo(restaurantId);
        dao.closeConn();

        return buildJson(allDays, information);
    }

    /**
     *
     * @param data imcoming JSON
     * sample JSON: {"customer":{"fname":"sdf","lname":"sdf","phone":"6048542569"},
     *             "table":{"time":"11:00 am ~ 12:30 am","seats":"3"}}
     * <p>
     *    1. get cid by inserting customer to customer-table
     *    2. get booked tid by querying duration-table with key: (booking, isbooked)
     *    3. get available tid based on the seats - tid from step 2
     *    4. insert (tid + cid) to reserve table
     * </p>
     * @return boolean type
     * @throws ClassCastException
     */
    @Override
    public Boolean storeReservation(String data) throws ClassCastException{
        Gson gson = new Gson();
        List<JsonObject> records = this.parseJson(data);

        JsonElement time = records.get(1).get("time");
        JsonElement seats = records.get(1).get("seats");
        Customer customer = gson.fromJson(records.get(0), Customer.class);
        DiningTable table = new DiningTable(time.getAsString(), seats.getAsInt());

        RestaruantInfoDAO dao = new RestaruantInfoDAO();
        // insert customer information
        int cid = dao.addCustomer(customer);
        // get table id
        int tid = dao.getTableBySeatsNumber(table);

        boolean result = dao.addBookingTime(tid, table.getTime());

        if(!result){
            return false;
        }

        if(cid > 0 && tid > 0){
            // insert to reserve
            boolean isInserted = dao.createReservation(cid, tid);
            return isInserted;

        }else{
            return false;
        }
    }

    public String getSeats(String time) {
        RestaruantInfoDAO dao = new RestaruantInfoDAO();
        Set<Integer> seats = dao.getAvailableSeats(time);

        Gson gson = new Gson();
        JsonObject jo = new JsonObject();
        JsonElement availSeats = gson.toJsonTree(seats).getAsJsonArray();
        jo.add("seatList", availSeats);

        return gson.toJson(jo);
    }

    private String buildJson(List<Day> days, Information info){
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        JsonElement restDays = gson.toJsonTree(days).getAsJsonArray();

        JsonObject restInfo = new JsonObject();
        restInfo.add("hours", restDays);
        restInfo.add("contact", gson.toJsonTree(info));

        return gson.toJson(restInfo);
    }

    private List<JsonObject> parseJson(String data){

        List<JsonObject> result = new ArrayList<>();
        JsonElement ele = new JsonParser().parse(data);
        JsonObject obj = ele.getAsJsonObject();

        JsonObject customer = obj.getAsJsonObject("customer");
        JsonObject table = obj.getAsJsonObject("table");

        result.add(customer);
        result.add(table);

        return result;
    }
}
