package com.bluewebspark.classified.utils;

import android.app.Dialog;
import android.content.Context;

import com.bluewebspark.classified.Utility.Utility;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class JSONParser {
    private Context cx;

    // constructor
    public JSONParser(Context cx) {
        this.cx = cx;
    }

    public void parseRetrofitRequest(Call<ResponseBody> call, final Helper h) {
        final Dialog materialDialog = Utility.initProgressDialog(cx);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                materialDialog.dismiss();

                try {
                    h.backResponse(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                materialDialog.dismiss();
                h.backResponse("error");
                Utility.E("Something went wrong.!");
            }
        });
    }

    public void parseRetrofitRequestWithautProgress(Call<ResponseBody> call, final Helper h) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                try {

                        h.backResponse(response.body().string());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                h.backResponse("error");
                Utility.E("sdcard-err2:" + t.toString());
                Utility.E("Something went wrong.!");
            }
        });
    }
}