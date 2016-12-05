package com.commin.pro.exerciseproject.model;

import android.graphics.Bitmap;

import com.commin.pro.exerciseproject.dao.Dao2Excercise;
import com.commin.pro.exerciseproject.page.menu_list.Page2MenuList;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by user on 2016-11-28.
 * 이 클래스는 앱의 데이터를 Model 형태로 저장하여 이동시키는 역할입니다.
 * 모든 데이터는 이처럼 Model 형태로 만들어서 이동시키는게 좋은 코딩방법입니다.
 * 앞으로 코딩하시면서 참고하셔서 사용하세요^^
 */
public class Model2Excercise implements Serializable {
    private Date date;
    private HashMap<String, Boolean> check;
    private String user_photo_path;
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

    public String getUser_photo_path() {
        return user_photo_path;
    }

    public void setUser_photo_path(String user_photo_path) {
        this.user_photo_path = user_photo_path;
    }

    public static Model2Excercise getModel(Date date) {
        HashMap<Date, Model2Excercise> map = Dao2Excercise.getHashMap();
        Model2Excercise model = null;
        for (Date d : map.keySet()) {
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
