package com.abhiroj.goonj.utils;

import com.abhiroj.goonj.data.EventData;

import java.util.ArrayList;

/**
 * Created by ruthless on 19/4/17.
 */

public class Utility {

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
            eventData.setEvent_name("ABC");
            eventData.setEvent_day("Monday");
            eventData.setEvent_description("Event X");
            eventData.setEvent_type("Type Y");
            eventData.setEvent_from_time("Time A");
            eventData.setEvent_to_time("Time B");
            eventData.setEvent_venue("Hall");
            eventDatas.add(eventData);
        }
        return eventDatas;
    }

}
