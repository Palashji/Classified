package com.bluewebspark.classified.model;

public class AllCategoriesModel {

    int Profile;
    String Totalpac;

    public AllCategoriesModel(int profile, String totalpac) {
        Profile = profile;
        Totalpac = totalpac;
    }

    public int getProfile() {
        return Profile;
    }

    public void setProfile(int profile) {
        Profile = profile;
    }

    public String getTotalpac() {
        return Totalpac;
    }

    public void setTotalpac(String totalpac) {
        Totalpac = totalpac;
    }
}
