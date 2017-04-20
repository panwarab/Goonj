package com.abhiroj.goonj.data;

/**
 * Created by ruthless on 20/4/17.
 */

public class EventData {

    private String event_name;
    private String event_type;
    private String event_description;
    private String event_from_time;
    private String event_to_time;
    private String event_day;
    private String event_venue;

    public EventData()
    {

    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getEvent_from_time() {
        return event_from_time;
    }

    public void setEvent_from_time(String event_from_time) {
        this.event_from_time = event_from_time;
    }

    public String getEvent_to_time() {
        return event_to_time;
    }

    public void setEvent_to_time(String event_to_time) {
        this.event_to_time = event_to_time;
    }

    public String getEvent_day() {
        return event_day;
    }

    public void setEvent_day(String event_day) {
        this.event_day = event_day;
    }

    public String getEvent_venue() {
        return event_venue;
    }

    public void setEvent_venue(String event_venue) {
        this.event_venue = event_venue;
    }
}
