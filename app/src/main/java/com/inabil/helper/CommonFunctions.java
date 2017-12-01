package com.inabil.helper;

import android.support.design.widget.Snackbar;
import android.view.View;

import java.util.regex.Pattern;

/**
 * Created by NABIL on 30-11-2017.
 */

public class CommonFunctions {

    public static void showMessage(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }

    //ex: 60112345678 or 0112345678
    public static boolean isValidMobile(String phone) {
        return Pattern.matches("^6?01\\d{8}", phone) ? true : false;
    }
}
