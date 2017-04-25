package com.abhiroj.goonj.data;

import com.google.firebase.database.ServerValue;

import java.util.Map;

/**
 * Created by ruthless on 25/4/17.
 */

public class UpdateData {

    private String title;
    private String message;
    private String update_by;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

}
