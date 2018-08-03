package com.sfu.cmpt470.Util;

import com.sfu.cmpt470.pojo.Customer;
import com.sfu.cmpt470.pojo.DiningTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 7/31/2018.
 */
public class DBManager {
    private String user;
    private String password;
    private Connection conn = null;
    PreparedStatement pstmt = null;

    public DBManager() {
        this("projectuser", "password");
    }

    public DBManager(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public void connection(){
        try{
            // vagrant environment url
            String url = "jdbc:postgresql://localhost/projectdb";
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, this.user, this.password);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("connection fail...");
            e.getMessage();
        }
    }

    public void closeConnection() throws SQLException {
        if(pstmt != null){
            pstmt.close();
        }
        if(conn != null){
            conn.close();
        }
    }

    public Map<Integer, Integer> getAllTableID(){
        Map<Integer, Integer> allTables = null;
        String query= "SELECT t.tid, t.seats from dining_table t";
        try{

            this.connection();
            pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();
            allTables = new HashMap<>();
            while(rs.next()){
                allTables.put(rs.getInt("tid"), rs.getInt("seats"));
            }
            return allTables;
        }catch (SQLException se){
            se.printStackTrace();
            return allTables;
        }finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Integer> getAllTablesByTime(String time){
        List<Integer> result = null;
        String query= "SELECT d.tid from duration d WHERE d.isbooked = true and d.booking = ?;";
        try{
            this.connection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, time);

            ResultSet rs = pstmt.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                result.add(rs.getInt("tid"));
            }
            return result;
        }catch (SQLException se){
            se.printStackTrace();
            return result;
        }finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int insertCustomer(Customer customer){
        final int error = 0;
        PreparedStatement pstmt = null;

        String query = "INSERT INTO customer(fname, lname, phone) VALUES(?, ?, ?) returning cid;";
        int customerId = 0;
        try{
            this.connection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, customer.getFname());
            pstmt.setString(2, customer.getLname());
            pstmt.setString(3, customer.getPhone());

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                customerId = rs.getInt("cid");
            }

            if(customerId > 0){
                return customerId;
            }else{
                return error;
            }

        }catch (SQLException se){
            se.printStackTrace();
            return error;
        }finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public int getTableId(DiningTable table){
        String time = table.getTime();
        int seats = table.getSeats();
        List<Integer> bookedTables = getAllTablesByTime(time);

        // never booked by time, go looking by tables
        if(bookedTables.isEmpty()){
            int tid = getSingleTableBySeats(seats);
            if(tid >0){
                return tid;
            }else{
                return 0;
            }
        }
        // filter table by time
        else{
            List<Integer> tablesFilterBySeats = getTableBySeats(seats);
            if(!tablesFilterBySeats.isEmpty()){
                for(Integer tid: bookedTables){
                    if(tablesFilterBySeats.contains(tid)){
                        tablesFilterBySeats.remove(tid);
                    }
                }
                return tablesFilterBySeats.get(0);
            }else{
                return 0;
            }
        }
    }

    public boolean insertIdsToReserve(int cid, int tid){
        String query= "INSERT INTO reserve(cid, tid) VALUES(?, ?)";

        try{
            this.connection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, cid);
            pstmt.setInt(2, tid);

            int index = pstmt.executeUpdate();

            if(index > 0){
                return true;
            }else{
                return false;
            }
        }catch (SQLException se){
            se.getMessage();
            return false;
        }finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean insertTime(int tid, String time){
        String query= "INSERT INTO duration(tid, booking, isbooked) VALUES(?, ?, ?);";

        try{
            this.connection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, tid);
            pstmt.setString(2, time);
            pstmt.setBoolean(3, true);

             pstmt.executeUpdate();

            return true;
        }catch (SQLException se){
            se.printStackTrace();
            return false;
        }finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getRid(String name){
        int id = 0;
        String query= "SELECT r.restaurant_id from restaurant r where r.restaurant_name = ?;";

        try{
            this.connection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
            if(id > 0){
                return id;
            }
            return 0;
        }catch (SQLException se){
            se.printStackTrace();
            return 0;
        }finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private int getSingleTableBySeats(int seats){
        String query= "SELECT MIN(d.tid) from dining_table d where d.seats = ?;";

        try{
            this.connection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, seats);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            return 0;
        }catch (SQLException se){
            se.printStackTrace();
            return 0;
        }finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Integer> getTableBySeats(int seats){
        String query= "SELECT d.tid from dining_table d where d.seats = ?;";
        List<Integer> tables = new ArrayList<>();
        try{
            this.connection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, seats);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                tables.add(rs.getInt("tid"));
            }
            return tables;
        }catch (SQLException se){
            se.printStackTrace();
            return null;
        }finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
