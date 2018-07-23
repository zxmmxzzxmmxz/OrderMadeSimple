package com.sfu.cmpt470.service;

import com.google.gson.*;

import com.sfu.cmpt470.DAO.RestaruantInfoDAO;
import com.sfu.cmpt470.pojo.Day;
import com.sfu.cmpt470.pojo.Information;

import java.util.List;

/**
 * Created by Administrator on 7/17/2018.
 */
public class RestInfoServiceImpl implements RestInfoService {
    @Override
    public String getAllInformation(int restaurantId) {
        RestaruantInfoDAO dao = new RestaruantInfoDAO();
        // get all open hour
        List<Day> allDays = dao.getAllDaysHour(restaurantId);
        // get contact information
        Information information = dao.getInfo(restaurantId);
        dao.closeConn();

        return buildJson(allDays, information);
    }

    private String buildJson(List<Day> days, Information info){
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        JsonElement restDays = gson.toJsonTree(days).getAsJsonArray();

        JsonObject restInfo = new JsonObject();
        restInfo.add("hours", restDays);
        restInfo.add("contact", gson.toJsonTree(info));

        return gson.toJson(restInfo);
    }
}
