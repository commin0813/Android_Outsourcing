package com.commin.pro.lectureschedule.util;

import android.content.Intent;

/**
 * Created by hyungwoo on 2016-12-15.
 */
public class UtilCheck {
    public static int checkPeriod(String start_time, String end_time) {
        return Integer.parseInt(end_time) - Integer.parseInt(start_time);
    }

    public static String checkTime(String time) {
        return Integer.parseInt(time) < 12 ? "오전" : "오후";
    }

    public static String checkDay(String id) {
        String str = id.charAt(2) + "";
        int a = Integer.parseInt(str);
        switch (a) {
            case 1:
                return "월";
            case 2:
                return "화";
            case 3:
                return "수";
            case 4:
                return "목";
            case 5:
                return "금";
            case 6:
                return "토";
            case 7:
                return "일";

        }
        return null;

    }
}
