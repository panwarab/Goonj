package com.abhiroj.goonj.data;

import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * Created by ruthless on 19/4/17.
 */

public class Constants {



    public static final String DRAMATICS="Dramatics";
    public static final String LITERARY="Literary";
    public static final String ARTS="Arts";
    public static final String MUSIC="Music";
    public static final String DANCE="Dance";
    public static final String PHOTOGRAPHY="Photography";
    public static final String OTHERS="Other";
    public static final String ADMIN_ANIRUDH="aniruddha.agarwal18@gmail.com";
    public static final String ADMIN_SUMIT="esumit.mishra@gmail.com";
    public static final String image_placeholder="https://unsplash.it/512/512?random";
    public static final String IMAGE_SLIDER_TAG="position";
    public static final int IMAGE_COUNT=4;
    public static final int HANDLER_POST_DELAYED_TIME=5000; // In milli seconds
    public static HashMap<String,Fragment> fragtag=new HashMap<>(); // Holds the reference of fragments, can be used to call member variables
    public static int SPAN_COUNT=2;
    public static final String card_titles[]=new String[]{"Events","Latest Updates","Team","Register"};
    public static final String FRAG_KEY="fragment_state";
    public static final String events[]=new String[]{DRAMATICS,LITERARY,ARTS,MUSIC,DANCE,PHOTOGRAPHY,OTHERS};
    public static final String EVENT_PATH="events";
    public static final String KEY_EVENT_LIST="TypeOfEvent";
    public static final String SUBADMINPATH="sub_admins";
    public static final String ADMINPATH="admins";
    public static final String auth_mail[]=new String[]{ADMIN_ANIRUDH,ADMIN_SUMIT};
    public static final String RESULT_FROM_ADD="AddEventResult";
    public static final String USER_ROOT="Users";
    public static final String PREFS="Goonj_app";

}
