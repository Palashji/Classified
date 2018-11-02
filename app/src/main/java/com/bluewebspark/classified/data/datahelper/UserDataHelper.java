package com.bluewebspark.classified.data.datahelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bluewebspark.classified.data.DataManager;
import com.bluewebspark.classified.data.model.UserDataModel;

import java.util.ArrayList;

import static com.bluewebspark.classified.data.model.UserDataModel.KEY_UserEmail;
import static com.bluewebspark.classified.data.model.UserDataModel.KEY_UserID;


public class UserDataHelper {
    private static UserDataHelper instance;
    private SQLiteDatabase db;
    private DataManager dm;
    Context cx;

    public UserDataHelper(Context cx) {
        instance = this;
        this.cx = cx;
        dm = new DataManager(cx, DataManager.DATABASE_NAME, null, DataManager.DATABASE_VERSION);
    }

    private boolean isExist(UserDataModel dataModel) {
        read();
        Cursor cur = db.rawQuery("select * from " + UserDataModel.TABLE_NAME + " where " + UserDataModel.KEY_UserID + "='" + dataModel.getUserId() + "'", null);
        if (cur.moveToFirst()) {
            return true;
        }
        return false;
    }

    public static UserDataHelper getInstance() {
        return instance;
    }

    public void open() {
        db = dm.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public void read() {
        db = dm.getReadableDatabase();
    }

    public void insertDataModel(UserDataModel userDataModel) {
        open();
        ContentValues values = new ContentValues();
        values.put(UserDataModel.KEY_UserID, userDataModel.getUserId());
        values.put(UserDataModel.KEY_USERNAME, userDataModel.getUserName());
        values.put(KEY_UserEmail, userDataModel.getUserEmail());
        values.put(UserDataModel.KEY_UserProfile, userDataModel.getUserProfile());
        values.put(UserDataModel.KEY_Name, userDataModel.getName());
        values.put(UserDataModel.KEY_UserGroupID, userDataModel.getUserGroupID());
        values.put(UserDataModel.KEY_UserType, userDataModel.getUser_type());
        values.put(UserDataModel.KEY_Password, userDataModel.getPassword());
        values.put(UserDataModel.KEY_Forgot, userDataModel.getForgot());
        values.put(UserDataModel.KEY_Confirm, userDataModel.getConfirm());
        values.put(UserDataModel.KEY_Status, userDataModel.getStatus());
        values.put(UserDataModel.KEY_View, userDataModel.getViews());
        values.put(UserDataModel.KEY_CreatedAt, userDataModel.getCreatedAt());
        values.put(UserDataModel.KEY_UpdatedAt, userDataModel.getUpdatedAt());
        values.put(UserDataModel.KEY_TagLine, userDataModel.getTagLine());
        values.put(UserDataModel.KEY_Description, userDataModel.getDescription());
        values.put(UserDataModel.KEY_Sex, userDataModel.getSex());
        values.put(UserDataModel.KEY_Phone, userDataModel.getPhone());
        values.put(UserDataModel.KEY_PostCode, userDataModel.getPostCode());
        values.put(UserDataModel.KEY_Address, userDataModel.getAddress());
        values.put(UserDataModel.KEY_Country, userDataModel.getCountry());
        values.put(UserDataModel.KEY_City, userDataModel.getCity());
        values.put(UserDataModel.KEY_LastActive, userDataModel.getLastActive());
        values.put(UserDataModel.KEY_Facebook, userDataModel.getFacebook());
        values.put(UserDataModel.KEY_Twitter, userDataModel.getTwitter());
        values.put(UserDataModel.KEY_GooglePlus, userDataModel.getGooglePlus());
        values.put(UserDataModel.KEY_Instagram, userDataModel.getInstagram());
        values.put(UserDataModel.KEY_Linkedin, userDataModel.getLinkedin());
        values.put(UserDataModel.KEY_Youtube, userDataModel.getYoutube());
        values.put(UserDataModel.KEY_OauthProvider, userDataModel.getOauth_Provider());
        values.put(UserDataModel.KEY_OauthUid, userDataModel.getOauthUid());
        values.put(UserDataModel.KEY_OauthLink, userDataModel.getOauthLink());
        values.put(UserDataModel.KEY_Online, userDataModel.getOnline());
        values.put(UserDataModel.KEY_Notify, userDataModel.getNotify());
        values.put(UserDataModel.KEY_NotifyCat, userDataModel.getNotifyCat());
        values.put(UserDataModel.KEY_Coins, userDataModel.getCoins());
        values.put(UserDataModel.KEY_UserfirbaseID, userDataModel.getFcm_id());


        if (!isExist(userDataModel)) {
            db.insert(UserDataModel.TABLE_NAME, null, values);
            Log.e("sohel", "insert successfully");
        } else {
            db.update(UserDataModel.TABLE_NAME, values, UserDataModel.KEY_UserID + " = '" + userDataModel.getUserId() + "'", null);
            Log.e("sohel", "update successfully : " + userDataModel.getUserId());

        }
        close();
    }

    public ArrayList<UserDataModel> getData() {
        ArrayList<UserDataModel> userItem = new ArrayList<>();
        read();
        Cursor clientCur = db.rawQuery("select * from " + UserDataModel.TABLE_NAME, null);

        if (clientCur != null && clientCur.getCount() > 0) {
            clientCur.moveToFirst();
            do {
                UserDataModel userDataModel = new UserDataModel();
                userDataModel.setUserId(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_UserID)));
                userDataModel.setUserName(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_USERNAME)));
                userDataModel.setUserEmail(clientCur.getString(clientCur.getColumnIndex(KEY_UserEmail)));
                userDataModel.setName(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Name)));
                userDataModel.setUserProfile(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_UserProfile)));
                userDataModel.setUserGroupID(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_UserGroupID)));
                userDataModel.setUser_type(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_UserType)));
                userDataModel.setPassword(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Password)));
                userDataModel.setForgot(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Forgot)));
                userDataModel.setConfirm(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Confirm)));
                userDataModel.setStatus(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Status)));
                userDataModel.setViews(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_View)));
                userDataModel.setCreatedAt(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_CreatedAt)));
                userDataModel.setUpdatedAt(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_UpdatedAt)));
                userDataModel.setTagLine(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_TagLine)));
                userDataModel.setDescription(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Description)));
                userDataModel.setSex(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Sex)));
                userDataModel.setPhone(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Phone)));
                userDataModel.setPostCode(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_PostCode)));
                userDataModel.setAddress(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Address)));
                userDataModel.setCountry(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Country)));
                userDataModel.setCity(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_City)));
                userDataModel.setLastActive(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_LastActive)));
                userDataModel.setFacebook(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Facebook)));
                userDataModel.setTwitter(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Twitter)));
                userDataModel.setGooglePlus(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_GooglePlus)));
                userDataModel.setInstagram(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Instagram)));
                userDataModel.setLinkedin(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Linkedin)));
                userDataModel.setYoutube(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Youtube)));
                userDataModel.setOauth_Provider(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_OauthProvider)));
                userDataModel.setOauthUid(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_OauthUid)));
                userDataModel.setOauthLink(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_OauthLink)));
                userDataModel.setOnline(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Online)));
                userDataModel.setNotify(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Notify)));
                userDataModel.setNotifyCat(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_NotifyCat)));
                userDataModel.setCoins(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_Coins)));

                userDataModel.setFcm_id(clientCur.getString(clientCur.getColumnIndex(UserDataModel.KEY_UserfirbaseID)));

                userItem.add(userDataModel);
            } while ((clientCur.moveToNext()));
            clientCur.close();
        }
        close();
        return userItem;
    }

    public void delete() {
        open();
        db.delete(UserDataModel.TABLE_NAME, null, null);
        close();
    }

    public void delete(String userId) {   // delete query of SQL they pass to the
        open();
        db.delete(UserDataModel.TABLE_NAME, KEY_UserID + "='" + userId + "'", null);
        close();


    }

}