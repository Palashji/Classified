package com.bluewebspark.classified.model;

public class PremiumModel {
    private  String id;
    private  String Profile;
    private  String name;
    private String subname;
    private String city;
    private  String time;
    private String btnamount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile() {
        return Profile;
    }

    public void setProfile(String profile) {
        Profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBtnamount() {
        return btnamount;
    }

    public void setBtnamount(String btnamount) {
        this.btnamount = btnamount;
    }
}
