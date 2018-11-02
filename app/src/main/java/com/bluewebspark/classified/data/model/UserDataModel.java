package com.bluewebspark.classified.data.model;

import android.database.sqlite.SQLiteDatabase;

public class UserDataModel {
    public static final String TABLE_NAME = "user_data_model";
    public static final String KEY_ID = "_id";
    public static final String KEY_UserID = "UserID";
    public static final String KEY_USERNAME = "UserName";
    public static final String KEY_UserEmail = "UserEmail";
    public static final String KEY_Name = "Name";
    public static final String KEY_UserProfile = "UserProfile";
    public static final String KEY_UserGroupID = "UserGroupID";
    public static final String KEY_UserType = "UserType";
    public static final String KEY_Password = "Password";
    public static final String KEY_Forgot = "Forgot";
    public static final String KEY_Confirm = "Confirm";
    public static final String KEY_Status = "Status";
    public static final String KEY_View = "views";
    public static final String KEY_CreatedAt = "CreatedAt";
    public static final String KEY_UpdatedAt = "UpdatedAt";
    public static final String KEY_TagLine = "TagLine";
    public static final String KEY_Description = "Description";
    public static final String KEY_Sex = "Sex";
    public static final String KEY_Phone = "Phone";
    public static final String KEY_PostCode = "PostCode";
    public static final String KEY_Address = "Address";
    public static final String KEY_Country = "Country";
    public static final String KEY_City = "City";
    public static final String KEY_LastActive = "LastActive";
    public static final String KEY_Facebook = "Facebook";
    public static final String KEY_Twitter = "Twitter";
    public static final String KEY_GooglePlus = "GooglePlus";
    public static final String KEY_Instagram = "Instagram";
    public static final String KEY_Linkedin = "Linkedin";
    public static final String KEY_Youtube = "Youtube";
    public static final String KEY_OauthProvider = "Oauth_Provider";
    public static final String KEY_OauthUid = "OauthUid";
    public static final String KEY_OauthLink = "OauthLink";
    public static final String KEY_Online = "Online";
    public static final String KEY_Notify = "Notify";
    public static final String KEY_NotifyCat = "NotifyCat";
    public static final String KEY_Coins = "Coins";
    public static final String KEY_UserfirbaseID = "UserfirbaseID";


    public static void creteTable(SQLiteDatabase db) {
        String CREATE_TABLE = "create table " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_UserID + " text,"
                + KEY_UserEmail + " text, "
                + KEY_UserProfile + " text, "
                + KEY_Name + " text, "
                + KEY_USERNAME + " text, "
                + KEY_UserGroupID + " text, "
                + KEY_UserType + " text, "
                + KEY_Password + " text, "
                + KEY_Forgot + " text, "
                + KEY_Confirm + " text, "
                + KEY_Status + " text, "
                + KEY_View + " text, "
                + KEY_CreatedAt + " text, "
                + KEY_UpdatedAt + " text, "
                + KEY_TagLine + " text, "
                + KEY_Description + " text, "
                + KEY_Sex + " text, "
                + KEY_Phone + " text, "
                + KEY_PostCode + " text, "
                + KEY_Address + " text, "
                + KEY_Country + " text, "
                + KEY_City + " text, "
                + KEY_LastActive + " text, "
                + KEY_Facebook + " text, "
                + KEY_Twitter + " text, "
                + KEY_GooglePlus + " text, "
                + KEY_Instagram + " text, "
                + KEY_Linkedin + " text, "
                + KEY_Youtube + " text, "
                + KEY_OauthProvider + " text, "
                + KEY_OauthUid + " text, "
                + KEY_OauthLink + " text, "
                + KEY_Online + " text, "
                + KEY_Notify + " text, "
                + KEY_NotifyCat + " text, "
                + KEY_Coins + " text, "
                + KEY_UserfirbaseID + " text " +
                ")";
        db.execSQL(CREATE_TABLE);
    }

    public static void dropTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    private String userId, userName, userEmail, Name, userProfile, UserGroupID, user_type, Password, Forgot, Confirm, Status, views, CreatedAt, UpdatedAt, TagLine, Description, Sex, Phone, PostCode, Address, Country, City, LastActive, Facebook, Twitter, GooglePlus, Youtube, Instagram, Linkedin, Oauth_Provider, OauthUid, OauthLink, Online, Notify, NotifyCat, Coins, fcm_id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getUserGroupID() {
        return UserGroupID;
    }

    public void setUserGroupID(String userGroupID) {
        UserGroupID = userGroupID;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getForgot() {
        return Forgot;
    }

    public void setForgot(String forgot) {
        Forgot = forgot;
    }

    public String getConfirm() {
        return Confirm;
    }

    public void setConfirm(String confirm) {
        Confirm = confirm;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

    public String getTagLine() {
        return TagLine;
    }

    public void setTagLine(String tagLine) {
        TagLine = tagLine;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getLastActive() {
        return LastActive;
    }

    public void setLastActive(String lastActive) {
        LastActive = lastActive;
    }

    public String getFacebook() {
        return Facebook;
    }

    public void setFacebook(String facebook) {
        Facebook = facebook;
    }

    public String getTwitter() {
        return Twitter;
    }

    public void setTwitter(String twitter) {
        Twitter = twitter;
    }

    public String getGooglePlus() {
        return GooglePlus;
    }

    public void setGooglePlus(String googlePlus) {
        GooglePlus = googlePlus;
    }

    public String getYoutube() {
        return Youtube;
    }

    public void setYoutube(String youtube) {
        Youtube = youtube;
    }

    public String getInstagram() {
        return Instagram;
    }

    public void setInstagram(String instagram) {
        Instagram = instagram;
    }

    public String getLinkedin() {
        return Linkedin;
    }

    public void setLinkedin(String linkedin) {
        Linkedin = linkedin;
    }

    public String getOauth_Provider() {
        return Oauth_Provider;
    }

    public void setOauth_Provider(String oauth_Provider) {
        Oauth_Provider = oauth_Provider;
    }

    public String getOauthUid() {
        return OauthUid;
    }

    public void setOauthUid(String oauthUid) {
        OauthUid = oauthUid;
    }

    public String getOauthLink() {
        return OauthLink;
    }

    public void setOauthLink(String oauthLink) {
        OauthLink = oauthLink;
    }

    public String getOnline() {
        return Online;
    }

    public void setOnline(String online) {
        Online = online;
    }

    public String getNotify() {
        return Notify;
    }

    public void setNotify(String notify) {
        Notify = notify;
    }

    public String getNotifyCat() {
        return NotifyCat;
    }

    public void setNotifyCat(String notifyCat) {
        NotifyCat = notifyCat;
    }

    public String getCoins() {
        return Coins;
    }

    public void setCoins(String coins) {
        Coins = coins;
    }

    public String getFcm_id() {
        return fcm_id;
    }

    public void setFcm_id(String fcm_id) {
        this.fcm_id = fcm_id;
    }
}
