package com.sfu.cmpt470.DAO;


import com.sfu.cmpt470.Util.DBManager;
import com.sfu.cmpt470.pojo.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 7/17/2018.
 */
public class RestaruantInfoDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public RestaruantInfoDAO(){
        try{
            emf = Persistence.createEntityManagerFactory("restaurantPU");
            em = emf.createEntityManager();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public List<Day> getAllDaysHour(int restaurantId){
        try{
            em.clear();
            List<Day> result = null;
            String getDay = "select d from Day d where restaurantId = :rid";
            Query query = em.createQuery(getDay, Day.class);
            query.setParameter("rid", restaurantId);
            result = query.getResultList();

            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Information getInfo(int restaurantId){
        try{
            em.clear();
            Information result = null;
            String getInfo = "select i from Information i where restaurantId = :rid" ;
            Query query = em.createQuery(getInfo, Information.class);
            query.setParameter("rid", restaurantId);
            result = (Information)query.getSingleResult();
            return result;
        }catch (PersistenceException pe){
            pe.printStackTrace();
            return null;
        }

    }

    public Set<Integer> getAvailableSeats(String time){
        DBManager dbm = new DBManager();
        Map<Integer, Integer> allTables = dbm.getAllTableID();
        List<Integer> allBookedTables = dbm.getAllTablesByTime(time);

        for(Integer tableId: allBookedTables){
            if(allTables.containsKey(tableId)){
                allTables.remove(tableId);
            }
        }

        List<Integer> seats = allTables.entrySet().stream().map(map -> map.getValue()).collect(Collectors.toList());
        Set<Integer> uniqueSeatsNumber = new HashSet<>();
        uniqueSeatsNumber.addAll(seats);

        return uniqueSeatsNumber;
    }

    public int addCustomer(Customer customer){
        DBManager dbm = new DBManager();
        return dbm.insertCustomer(customer);
    }

    public int getTableBySeatsNumber(DiningTable table){
        DBManager dbm = new DBManager();
        return dbm.getTableId(table);
    }

    public boolean addBookingTime(int tid, String time){
        DBManager dbm = new DBManager();
        return dbm.insertTime(tid, time);
    }

    public boolean createReservation(int cid, int tid){
        DBManager dbm = new DBManager();
        return dbm.insertIdsToReserve(cid, tid);
    }

    public void closeConn(){
        em.close();
        emf.close();
    }

    public int getRestaurantId(String name){
        DBManager dbm = new DBManager();
        return dbm.getRid(name);
    }






}
