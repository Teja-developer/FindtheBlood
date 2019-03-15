package com.example.findtheblood;

import com.google.firebase.firestore.Exclude;

public class RequestPost {
    String rname;
    String rcontact;
    String radd;
    String rblood;
    String rstate;

    public String getCity_s() {
        return city_s;
    }

    public void setCity_s(String city_s) {
        this.city_s = city_s;
    }

    String city_s;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Exclude
    private String id;
    private String pid;
    public RequestPost(String id, String pid,String rname, String rcontact, String radd, String rblood, String rstate,String city_s) {

        this.id = id;
        this.pid = pid;
        this.rname = rname;
        this.rcontact = rcontact;
        this.radd = radd;
        this.rblood = rblood;
        this.rstate = rstate;
        this.city_s = city_s;
    }
    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRcontact() {
        return rcontact;
    }

    public void setRcontact(String rcontact) {
        this.rcontact = rcontact;
    }

    public String getRadd() {
        return radd;
    }

    public void setRadd(String radd) {
        this.radd = radd;
    }

    public String getRblood() {
        return rblood;
    }

    public void setRblood(String rblood) {
        this.rblood = rblood;
    }

    public String getRstate() {
        return rstate;
    }

    public void setRstate(String rstate) {
        this.rstate = rstate;
    }
}
