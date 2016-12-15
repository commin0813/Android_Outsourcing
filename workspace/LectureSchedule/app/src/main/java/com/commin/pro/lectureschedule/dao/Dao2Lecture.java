package com.commin.pro.lectureschedule.dao;

import android.util.Log;

import com.commin.pro.lectureschedule.model.Model2Lecture;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by hyungwoo on 2016-12-13.
 */
public class Dao2Lecture {
    private static final String LOG_TAG ="Dao2Lecture";
    private static class Singleton_Map {
        private static final HashMap<String,Model2Lecture> model_map =new HashMap<String,Model2Lecture>();
    }
    public static HashMap<String,Model2Lecture> getHashMap(){
        Log.d(LOG_TAG,"getHashMap ---- ");
        return Singleton_Map.model_map;
    }
    public static void insertModel(Model2Lecture model){
        String id = model.getId();
        Singleton_Map.model_map.put(id,model);
    }
    public static void updateModel(String id,Model2Lecture model){
        Singleton_Map.model_map.remove(id);
        insertModel(model);
    }


}
