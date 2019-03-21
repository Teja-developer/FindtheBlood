package com.example.findtheblood;

import com.google.firebase.firestore.Exclude;

public class PostItems {
    String post_date;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender_str() {
        return gender_str;
    }

    public void setGender_str(String gender_str) {
        this.gender_str = gender_str;
    }

    public String getHiv() {
        return hiv;
    }

    public void setHiv(String hiv) {
        this.hiv = hiv;
    }

    public String getChron_str() {
        return chron_str;
    }

    public void setChron_str(String chron_str) {
        this.chron_str = chron_str;
    }

    String post_month;
    String post_content;
    String post_time;
    String name;
    String age;
    String city;
    String gender_str;
    String hiv;
    String chron_str;
    String re_month;
    String blood;
    String phone;
    String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRe_month() {
        return re_month;
    }

    public void setRe_month(String re_month) {
        this.re_month = re_month;
    }

    @Exclude
    private String id;
    private String pid;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Exclude
    private String title;

    public PostItems() {
    } //Added PostItems empty constructor to avoid  "users does not define a no-argument constructor. If you are using ProGuard, make sure these constructors are not stripped." error


    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PostItems(String id, String pid, String post_time, String post_date, String post_month, String name, String age, String city, String gender_str, String hiv, String chron_str, String re_month, String blood, String address, String phone) {

        this.id = id;
        this.pid = pid;
        this.post_date = post_date;
        this.post_month = post_month;
        this.name = name;
        this.age = age;
        this.post_time = post_time;
        this.city = city;
        this.gender_str = gender_str;
        this.hiv = hiv;
        this.chron_str = chron_str;
        this.re_month = re_month;
        this.blood = blood;
        this.address=address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
    }

    public String getPost_month() {
        return post_month;
    }

    public void setPost_month(String post_month) {
        this.post_month = post_month;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }


}
