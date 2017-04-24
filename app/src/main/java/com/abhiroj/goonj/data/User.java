package com.abhiroj.goonj.data;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by ruthless on 24/4/17.
 */

public class User {

    private String uname;
    private String uid;
    private String umail;
    private Boolean isAdmin=false;
    private String provider;


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUmail() {
        return umail;
    }

    public void setUmail(String umail) {
        this.umail = umail;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
