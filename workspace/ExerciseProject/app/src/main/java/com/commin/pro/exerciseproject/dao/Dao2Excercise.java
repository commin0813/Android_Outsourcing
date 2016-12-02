package com.commin.pro.exerciseproject.dao;

import android.util.Log;

import com.commin.pro.exerciseproject.model.Model2Excercise;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by hyungwoo on 2016-11-30.
 */
public class Dao2Excercise {
    private static final String LOG_TAG ="Dao2Excercise";

    private static class Singleton_Map {
        private static final HashMap<Date, Model2Excercise> model_map = new HashMap<Date, Model2Excercise>();
    }

    public static HashMap<Date, Model2Excercise> getHashMap() {
        Log.d(LOG_TAG,"getHashMap --- ");
        return Singleton_Map.model_map;
    }

    public static void insertModel(Model2Excercise model) {
        Log.d(LOG_TAG,"insertModel --- ");
        Date date = model.getDate();
        Singleton_Map.model_map.put(date, model);
    }

    public static void updateModel(Date date, Model2Excercise model) {
        Log.d(LOG_TAG,"updateModel --- ");
        Singleton_Map.model_map.remove(date);
        insertModel(model);
    }

    public static Date queryDate(String d, SimpleDateFormat df) {
        for (Date date : Singleton_Map.model_map.keySet()) {
            if (df.format(date).equals(d)) {
                return date;
            }
        }
        Log.d(LOG_TAG,"queryDate --- ");
        return null;
    }

}
