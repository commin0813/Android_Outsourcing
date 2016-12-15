package com.commin.pro.lectureschedule.model;

/**
 * Created by hyungwoo on 2016-12-13.
 */
public class Model2Lecture {
    private boolean isData;
    private String name_value;
    private String id;
    private int row_index;
    private int column_index;
    private String class_name;
    private String professor_name;
    private String room_name;
    private boolean isEvents;

    public boolean isData() {
        return isData;
    }

    public void setData(boolean data) {
        isData = data;
    }

    public String getName_value() {
        return name_value;
    }

    public void setName_value(String name_value) {
        this.name_value = name_value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getProfessor_name() {
        return professor_name;
    }

    public void setProfessor_name(String professor_name) {
        this.professor_name = professor_name;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getRow_index() {
        return row_index;
    }

    public void setRow_index(int row_index) {
        this.row_index = row_index;
    }

    public int getColumn_index() {
        return column_index;
    }

    public void setColumn_index(int column_index) {
        this.column_index = column_index;
    }

    public boolean isEvents() {
        return isEvents;
    }

    public void setEvents(boolean events) {
        isEvents = events;
    }
}
