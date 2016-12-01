package com.commin.pro.exerciseproject.model;

import com.commin.pro.exerciseproject.dao.Dao2Excercise;
import com.commin.pro.exerciseproject.page.menu_list.Page2MenuList;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by user on 2016-11-28.
 */
public class Model2Excercise implements Serializable{
    private Date date;
    private HashMap<String,Boolean> check;
    private boolean isBeginner;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public HashMap<String, Boolean> getCheck() {
        return check;
    }

    public void setCheck(HashMap<String, Boolean> check) {
        this.check = check;
    }

    public boolean isBeginner() {
        return isBeginner;
    }

    public void setBeginner(boolean beginner) {
        isBeginner = beginner;
    }

    public static Model2Excercise getModel(Date date){
        HashMap<Date,Model2Excercise> map = Dao2Excercise.getHashMap();
        Model2Excercise model=null;
        for(Date d : map.keySet()){
            if (d.getYear() == date.getYear()//
                    && d.getMonth() == date.getMonth()//
                    && d.getDay() == date.getDay()) //
            {
               return map.get(d);
            }
        }

        return null;
    }
}
