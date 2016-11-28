package com.commin.pro.exerciseproject.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by user on 2016-03-21.
 */
public class UtilDialog {
    public static void openError(Activity activity, String message, AlertDialog.OnClickListener ok_listener) {
        AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, ok_listener)
                .setCancelable(false)
                .create();
        alertDialog.show();
    }

    public static void openConfirm(Activity activity, String message, AlertDialog.OnClickListener ok_listener, AlertDialog.OnClickListener cancel_listener) {
        AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, ok_listener)
                .setNegativeButton(android.R.string.cancel, cancel_listener)
                .setCancelable(false)
                .create();
        alertDialog.show();
    }

    public static void showToast(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

}
