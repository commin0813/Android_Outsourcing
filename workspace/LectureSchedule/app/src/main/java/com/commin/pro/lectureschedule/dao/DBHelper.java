package com.commin.pro.lectureschedule.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.commin.pro.lectureschedule.ApplicationProperty;

/**
 * Created by user on 2016-12-15.
 */
public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //최초에 새로운 Database 를 생성합니다.
        db.execSQL("CREATE TABLE "+ ApplicationProperty.DATABASE_TABLE_NAME+"(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id TEXT NOT NULL," +
                "groupid INTEGER," +
                "classname TEXT," +
                "professorname TEXT," +
                "classroomname TEXT," +
                "starttime TEXT," +
                "endtime TEXT," +
                "position TEXT," +
                "memotitle TEXT," +
                "memo TEXT," +
                "ismemo TEXT," +
                "isevent TEXT," +
                "isdata TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE LECTURE");
        onCreate(db);
    }
}
