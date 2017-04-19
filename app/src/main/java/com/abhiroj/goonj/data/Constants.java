package com.abhiroj.goonj.data;

import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * Created by ruthless on 19/4/17.
 */

public class Constants {

    public static final String image_placeholder="https://unsplash.it/512/512?random";
    public static final String IMAGE_SLIDER_TAG="position";
    public static final int IMAGE_COUNT=4;
    public static final int HANDLER_POST_DELAYED_TIME=5000; // In milli seconds
    public static HashMap<String,Fragment> fragtag=new HashMap<>(); // Holds the reference of fragments, can be used to call member variables
    public static int SPAN_COUNT=2;
    public static final String card_titles[]=new String[]{"Events","Latest Updates","Team","Register"};


}
