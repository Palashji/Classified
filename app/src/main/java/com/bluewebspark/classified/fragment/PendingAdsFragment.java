package com.bluewebspark.classified.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.Utility;
import com.bluewebspark.classified.adapter.MyAdsAdapter;
import com.bluewebspark.classified.model.MyAdsModel;
import com.bluewebspark.classified.utils.ApiClient;
import com.bluewebspark.classified.utils.ApiInterface;
import com.bluewebspark.classified.utils.Helper;
import com.bluewebspark.classified.utils.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PendingAdsFragment extends Fragment {


    ArrayList<MyAdsModel> arrayList = new ArrayList<>();

    private RecyclerView recycler_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_myads, container, false);


        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);

        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyAdsAdapter myAdsAdapter = new MyAdsAdapter(getActivity(), arrayList);
        recycler_view.setAdapter(myAdsAdapter);
        callApiforMyAds();
        return view;

    }

    private void callApiforMyAds() {
        // Utility.E("user id : " + UserDataHelper.getInstance().getData().get(0).getUserId());

        new JSONParser(getActivity()).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).My_Ads(
             //   UserDataHelper.getInstance().getData().get(0).getUserId(),
                "70",
                "3"

        ), new Helper() {
            @Override
            public void backResponse(String response) {
                Utility.E("MyAds response : " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.get("status").equals("200")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);
                            MyAdsModel myAdsModel = new MyAdsModel();
                            myAdsModel.setProID(json.getString("proID"));
                            myAdsModel.setProduct_name(json.getString("product_name"));
                            myAdsModel.setProduct_image(json.getString("product_image"));
                            myAdsModel.setCat_name(json.getString("cat_name"));
                            myAdsModel.setCity(json.getString("city"));
                            myAdsModel.setCreated_at(json.getString("created_at"));
                            myAdsModel.setExpired_date(json.getString("expired_date"));
                            myAdsModel.setPrice(json.getString("price"));
                           // if (json.getString("myads").equals("1"))
                                arrayList.add(myAdsModel);
                        }

                        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                        MyAdsAdapter myAdsAdapter = new MyAdsAdapter(getActivity(), arrayList);
                        recycler_view.setAdapter(myAdsAdapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


}
