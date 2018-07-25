package com.sfu.cmpt470.DAO;


import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.database.RowMapper.ReportMapper;
import com.sfu.cmpt470.pojo.Dish;
import com.sfu.cmpt470.pojo.Restaurant;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import jersey.repackaged.com.google.common.collect.Iterables;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import com.sfu.cmpt470.database.*;
import java.util.ArrayList;
import com.sfu.cmpt470.database.RowMapper.RestaurantRowMapper;
import java.sql.SQLException;
import static org.junit.Assert.*;


public class ReportGeneratingTest {



    @Test
    public void sampleTest() throws SQLException, ClassNotFoundException{

        ReportMapper rm = new ReportMapper();
        String xml = rm.getXml("restaurant");
        Assert.assertNotNull(xml);
    }


}
