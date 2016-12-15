package com.commin.pro.lectureschedule.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.commin.pro.lectureschedule.ApplicationProperty;
import com.commin.pro.lectureschedule.model.Model2Lecture;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by hyungwoo on 2016-12-13.
 */
public class Dao2Lecture {
    private static final String LOG_TAG = "Dao2Lecture";

    private static class Singleton_Map {
        private static final HashMap<String, Model2Lecture> model_map = new HashMap<String, Model2Lecture>();
        private static DBHelper db = null;

    }

    public static void setDatabase(final DBHelper db) {
        Singleton_Map.db = db;
    }

    public static HashMap<String, Model2Lecture> getHashMap() {
        Log.d(LOG_TAG, "getHashMap ---- ");
        return Singleton_Map.model_map;
    }

    public static void insertModel(Model2Lecture model) {
        String id = model.getId();
        Singleton_Map.model_map.put(id, model);
    }

    public static void updateModel(String id, Model2Lecture model) {
        Singleton_Map.model_map.remove(id);
        insertModel(model);
    }
//    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
//            "id TEXT NOT NULL," +
//            "classname TEXT," +
//            "professorname TEXT," +
//            "classroomname TEXT," +
//            "starttime TEXT," +
//            "endtime TEXT," +
//            "position TEXT," +
//            "isevent TEXT," +
//            "isdata TEXT)");

    public static void insertDatabase(Model2Lecture model) {
        String id = model.getId();
        int groupid = model.getGroupid();
        String classname = model.getClass_name();
        String classroomname = model.getClassroom_name();
        String professorname = model.getProfessor_name();
        String position = model.getPosition();
        String starttime = model.getStart_time();
        String endtime = model.getEnd_time();
        String isevent = String.valueOf(model.isEvents());
        String isdata = String.valueOf(model.isData());



        String sql = "insert into " + ApplicationProperty.DATABASE_TABLE_NAME
                + " (id,groupid,classname,professorname,classroomname,starttime,endtime,position,isevent,isdata)" +
                " values" +
                "('" + id + "'," +
                "'" + groupid + "'," +
                "'" + classname + "'," +
                "'" + professorname + "'," +
                "'" + classroomname + "'," +
                "'" + starttime + "'," +
                "'" + endtime + "'," +
                "'" + position + "'," +
                "'" + isevent + "'," +
                "'" + isdata + "');";

        SQLiteDatabase db = Singleton_Map.db.getWritableDatabase();
        db.execSQL(sql);

    }

    public static void insertDatabase(ArrayList<Model2Lecture> models) {
        for (Model2Lecture model : models) {
            insertDatabase(model);
        }
    }

    public static ArrayList<Model2Lecture> queryAllData() {
        String sql = "select * from " + ApplicationProperty.DATABASE_TABLE_NAME;
        SQLiteDatabase db = Singleton_Map.db.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Model2Lecture> models = new ArrayList<Model2Lecture>();
        if (cursor != null) {

            while (cursor.moveToNext()) {
                Model2Lecture model = new Model2Lecture();
                model.setId(cursor.getString(1));
                model.setGroupid(cursor.getInt(2));
                model.setClass_name(cursor.getString(3));
                model.setProfessor_name(cursor.getString(4));
                model.setClassroom_name(cursor.getString(5));
                model.setStart_time(cursor.getString(6));
                model.setEnd_time(cursor.getString(7));
                model.setPosition(cursor.getString(8));
                model.setEvents(Boolean.valueOf(cursor.getString(9)));
                model.setData(Boolean.valueOf(cursor.getString(10)));
                models.add(model);
            }

            return models;
        }
        return null;
    }

    public static void deleteData(Model2Lecture model){
        int groupid = model.getGroupid();
        String sql = "delete from "+ApplicationProperty.DATABASE_TABLE_NAME+" where groupid = '"+groupid+"';";
        SQLiteDatabase db = Singleton_Map.db.getWritableDatabase();
        db.execSQL(sql);

    }


}
