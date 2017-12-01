package com.inabil.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by NABIL on 30-11-2017.
 */

public class AuthLogin {

    private static String PREFS_NAME = "USER_DETAILS";
    private static String EMAIL = "email";

    public static void saveUser(Context context, String email) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(EMAIL, email);
        editor.commit();
    }

    public static String getUser(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(EMAIL, null);
    }

}
