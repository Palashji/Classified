package com.bluewebspark.classified.model;

public class SubCategoryModel {
    private String sub_cat_id;
    private String main_cat_id;
    private String sub_cat_name;
    private String slug;
    private String cat_order;
    private String photo_show;
    private String price_show;
    private String count ;


    public String getSub_cat_id() {
        return sub_cat_id;
    }

    public void setSub_cat_id(String sub_cat_id) {
        this.sub_cat_id = sub_cat_id;
    }

    public String getMain_cat_id() {
        return main_cat_id;
    }

    public void setMain_cat_id(String main_cat_id) {
        this.main_cat_id = main_cat_id;
    }

    public String getSub_cat_name() {
        return sub_cat_name;
    }

    public void setSub_cat_name(String sub_cat_name) {
        this.sub_cat_name = sub_cat_name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCat_order() {
        return cat_order;
    }

    public void setCat_order(String cat_order) {
        this.cat_order = cat_order;
    }

    public String getPhoto_show() {
        return photo_show;
    }

    public void setPhoto_show(String photo_show) {
        this.photo_show = photo_show;
    }

    public String getPrice_show() {
        return price_show;
    }

    public void setPrice_show(String price_show) {
        this.price_show = price_show;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
