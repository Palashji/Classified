package com.bluewebspark.classified.utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("yakapi.php/?action=user_registration")
    Call<ResponseBody> user_registration(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("yakapi.php/?action=user_login")
    Call<ResponseBody> user_login(
            @Field("username") String username,
            @Field("password_hash") String password_hash
    );

    @GET("yakapi.php/?action=category")
    Call<ResponseBody> getCategories();

    @GET("yakapi.php/?action=premium_ads")
    Call<ResponseBody> getPremium();

    @GET("yakapi.php/?action=latest_ads")
    Call<ResponseBody> getLatest();

    @GET("yakapi.php/?action=get_city_list")
    Call<ResponseBody> getcity_list();

    @FormUrlEncoded
    @POST("yakapi.php/?action=sub_category")
    Call<ResponseBody> getSubCategories(
            @Field("main_cat_id") String main_cat_id
    );

    @FormUrlEncoded
    @POST("yakapi.php/?action=category_ad")
    Call<ResponseBody> getProduct(
            @Field("category") String category,
            @Field("sub_category") String sub_category
    );

    @FormUrlEncoded
    @POST("yakapi.php/?action=product_ad_detail")
    Call<ResponseBody> productaddetail(
            @Field("productID") String productID
    );

    @FormUrlEncoded
    @POST("yakapi.php/?action=productContactDetail")
    Call<ResponseBody> productContactDetail(
            @Field("user_id") String user_id

    );

    @FormUrlEncoded
    @POST("yakapi.php/?action=user_profile_update")
    Call<ResponseBody> updateprofile(
            @Field("id") String id,
            @Field("avatar") String avatar,
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("facebook") String facebook,
            @Field("twitter") String twitter,
            @Field("googleplus") String googleplus,
            @Field("instagram") String instagram,
            @Field("linkedin") String linkedin,
            @Field("youtube") String youtube

    );

    @FormUrlEncoded
    @POST("yakapi.php/?action=account_setting")
    Call<ResponseBody> change_password(
            @Field("id") String id,
            @Field("current_password") String current_password,
            @Field("new_password") String new_password

    );

    @FormUrlEncoded
    @POST("yakapi.php/?action=my_ads")
    Call<ResponseBody> My_Ads(
            @Field("id") String id,
            @Field("value") String value
    );

    @FormUrlEncoded
    @POST("yakapi.php/?action=hide_Item")
    Call<ResponseBody> Hide(
            @Field("pro_id") String pro_id
    );

    @FormUrlEncoded
    @POST("yakapi.php/?action=deleteMyAd")
    Call<ResponseBody> Delete(
            @Field("pro_id") String pro_id
    );

    @FormUrlEncoded
    @POST("yakapi.php/?action=transaction")
    Call<ResponseBody> getTransaction(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("yakapi.php/?action=ad_post")
    Call<ResponseBody> adPost(
            @Field("category") String category,
            @Field("sub_category") String sub_category,
            @Field("product_name") String product_name,
            @Field("description") String description,
            @Field("sellername") String sellername,
            @Field("email") String email,
            @Field("city") String city,
            @Field("state") String state,
            @Field("country") String country,
            @Field("latlong") String latlong,
            @Field("password") String password,
            @Field("screen_shot1") String screen_shot1,
            @Field("screen_shot2") String screen_shot2,
            @Field("screen_shot3") String screen_shot3
    );

}












