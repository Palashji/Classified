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
import com.bluewebspark.classified.adapter.AdsAdapter;
import com.bluewebspark.classified.model.FindAdsModel;
import com.bluewebspark.classified.utils.ApiClient;
import com.bluewebspark.classified.utils.ApiInterface;
import com.bluewebspark.classified.utils.Helper;
import com.bluewebspark.classified.utils.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FeaturedFragment extends Fragment {


    ArrayList<FindAdsModel> arrayList = new ArrayList<>();

    private RecyclerView recycler_view;
    String sub_cat_id;
    String cat_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_featured, container, false);


        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
        sub_cat_id = getActivity().getIntent().getStringExtra("sub_cat_id");
        cat_id = getActivity().getIntent().getStringExtra("cat_id");
        Utility.E("sub_cat_id : " + sub_cat_id);


        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdsAdapter adsAdapter = new AdsAdapter(getActivity(), arrayList);
        recycler_view.setAdapter(adsAdapter);
        callApiforProduct();
        return view;

    }

    private void callApiforProduct() {

        new JSONParser(getActivity()).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).getProduct(
                cat_id,
                sub_cat_id
        ), new Helper() {
            @Override
            public void backResponse(String response) {
                Utility.E("product response : " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.get("status").equals("200")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);
                            FindAdsModel findAdsModel = new FindAdsModel();
                            findAdsModel.setTextsell(json.getString("product_name"));
                            findAdsModel.setProfile(json.getString("product_image"));
                            findAdsModel.setTextbikesandcars(json.getString("sub_cat_name"));
                            findAdsModel.setLocation(json.getString("country"));
                            findAdsModel.setHours(json.getString("created_at"));
                            findAdsModel.setAmount(json.getString("price"));
                            findAdsModel.setCat(json.getString("cat_name"));
                            findAdsModel.setFeature(json.getString("featured"));
                            findAdsModel.setUrjent(json.getString("urgent"));
                            findAdsModel.setHighlights(json.getString("highlight"));
                            findAdsModel.setContrycode(json.getString("countrycode"));
                            findAdsModel.setCity(json.getString("city"));
                            if (json.getString("featured").equals("1"))
                            arrayList.add(findAdsModel);
                        }

                        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                        AdsAdapter adsAdapter = new AdsAdapter(getActivity(), arrayList);
                        recycler_view.setAdapter(adsAdapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


}



