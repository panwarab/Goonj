package com.abhiroj.goonj.utils;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.abhiroj.goonj.data.EventData;

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

    public static ArrayList<EventData> generateFakeData()
    {
        ArrayList<EventData> eventDatas=new ArrayList<>();
        for (int i=0;i<5;i++)
        {
            EventData eventData=new EventData();
            eventData.setName("ABC");
            eventData.setRules("Event X");
            eventData.setVenue("Hall");
            Calendar calendar=Calendar.getInstance();
            eventData.setTime(calendar.getTime().toString());
            Date date=new Date();
            eventData.setDate(date.toString());
            eventDatas.add(eventData);
        }
        return eventDatas;
    }

    public static void showSnackBar(Activity activity, int resID){
        View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(rootView, resID, Snackbar.LENGTH_LONG).show();
    }

    public static void showToast(Activity activity,int resId)
    {
        Toast.makeText(activity,resId,Toast.LENGTH_SHORT).show();
    }

}
