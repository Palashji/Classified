package com.bluewebspark.classified.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bluewebspark.classified.data.model.UserDataModel;


public class DataManager extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Classified";

    public DataManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        UserDataModel.creteTable(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        UserDataModel.dropTable(db);
        onCreate(db);
    }


}