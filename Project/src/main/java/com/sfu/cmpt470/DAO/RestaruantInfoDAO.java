package com.sfu.cmpt470.DAO;


import com.sfu.cmpt470.pojo.Day;
import com.sfu.cmpt470.pojo.Information;
import com.sfu.cmpt470.pojo.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

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
        em.clear();
        Information result = null;
        String getInfo = "select i from Information i where restaurantId = :rid" ;
        Query query = em.createQuery(getInfo, Information.class);
        query.setParameter("rid", restaurantId);
        result = (Information)query.getSingleResult();
        return result;
    }

   /* public String getName(int restaurantId){
        String getName = "select r.restaurant_name from publiic.restaurant r where restaurant_id =" + restaurantId;
        Query query = em.createQuery(getName);
        String restaurantName = (String)query.getSingleResult();
        return restaurantName;
    }*/

    public void closeConn(){
        em.close();
    }





}
