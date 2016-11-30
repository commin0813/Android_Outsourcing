package com.commin.pro.exerciseproject.model;

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
}
