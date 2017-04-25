package com.abhiroj.goonj.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.abhiroj.goonj.activity.MainActivity;
import com.abhiroj.goonj.data.EventData;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by ruthless on 19/4/17.
 */

public class Utility {

    public static final int HANDLER_DELAY_TIME=1000; // 1 second

    public static boolean checkNotNull(Object o)
    {
        return o!=null;
    }


    public static void showSnackBar(final Activity activity, int message){
        View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar snackbar=Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static void showSnackBar(final Activity activity,String message){
        View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar snackbar=Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static void showToast(Activity activity,int resId)
    {
        Toast.makeText(activity,resId,Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Activity activity,String message)
    {
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, int no_call) {
        Toast.makeText(context,no_call,Toast.LENGTH_SHORT).show();
    }

    public static boolean detectConnection(Context context) {
        if (context == null) return false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
