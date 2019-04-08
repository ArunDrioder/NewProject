package com.ninositsolution.packandmove.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static final String user_id = "user_id";
    private static final String otp = "otp";
    private static final String token = "token";


public Session(Context context)
{

    preferences = PreferenceManager.getDefaultSharedPreferences(context);

    editor = preferences.edit();
}



    public static String getUserId(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(user_id, "");
    }

    public static void setUserId(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(user_id, value).apply();
    }

    public static String getOtp(Context context)
    {
        return context.getSharedPreferences("Session",Context.MODE_PRIVATE).getString(otp,"");
    }

    public static void setOtp(String value, Context context)
    {
        context.getSharedPreferences("Session", Context.MODE_PRIVATE).edit().putString(otp, value).apply();
    }

    public static String getToken(Context context)
    {
        return context.getSharedPreferences("Session", Context.MODE_PRIVATE).getString(token,"");
    }

    public static void setToken(String value, Context context)
    {
        context.getSharedPreferences("Session",Context.MODE_PRIVATE).edit().putString(token,value).apply();
    }








}
