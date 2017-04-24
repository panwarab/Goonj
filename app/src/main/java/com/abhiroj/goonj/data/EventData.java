package com.abhiroj.goonj.data;

import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Date;

/**
 * Created by ruthless on 20/4/17.
 */

public class EventData {

    private String crit;
    private String name;
    private String point;
    private String rules;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getCrit() {
        return crit;
    }

    public void setCrit(String crit) {
        this.crit = crit;
    }
}
