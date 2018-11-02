package com.bluewebspark.classified.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.Utility.Utility;
import com.bluewebspark.classified.adapter.UploadedAdapter;
import com.bluewebspark.classified.data.datahelper.UserDataHelper;
import com.bluewebspark.classified.model.CitiesModel;
import com.bluewebspark.classified.model.CityModel;
import com.bluewebspark.classified.model.SubCategoryModel;
import com.bluewebspark.classified.model.UploadedModel;
import com.bluewebspark.classified.utils.ApiClient;
import com.bluewebspark.classified.utils.ApiInterface;
import com.bluewebspark.classified.utils.Helper;
import com.bluewebspark.classified.utils.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

public class StuffActivity extends BaseActivity {

    @BindView(R.id.imgyak)
    ImageView imgyak;
    @BindView(R.id.tvadvertise)
    TextView tvadvertise;
    @BindView(R.id.tvquotes)
    TextView tvquotes;
    @BindView(R.id.btnchoosecategory)
    Button btnchoosecategory;
    @BindView(R.id.tvtitleadvertise)
    TextView tvtitleadvertise;
    @BindView(R.id.ettitleadvertise)
    EditText ettitleadvertise;
    @BindView(R.id.tvdescription)
    TextView tvdescription;
    @BindView(R.id.ettellusmore)
    EditText ettellusmore;
    @BindView(R.id.imgupload)
    ImageView imgupload;
    @BindView(R.id.reecycle)
    RecyclerView reecycle;
    @BindView(R.id.tvimages)
    TextView tvimages;
    @BindView(R.id.tvadditional)
    TextView tvadditional;
    @BindView(R.id.tvprice)
    TextView tvprice;
    @BindView(R.id.tvQ)
    TextView tvQ;
    @BindView(R.id.etamount)
    EditText etamount;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.tvmobileno)
    TextView tvmobileno;
    @BindView(R.id.tvQS)
    TextView tvQS;
    @BindView(R.id.etnumber)
    EditText etnumber;
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.tvtags)
    TextView tvtags;
    @BindView(R.id.ettagss)
    EditText ettagss;
    @BindView(R.id.tvcity)
    TextView tvcity;
    @BindView(R.id.spinnerss)
    Spinner spinnerss;
    @BindView(R.id.tvseller)
    TextView tvseller;
    @BindView(R.id.etsellername)
    EditText etsellername;
    @BindView(R.id.etselllersnames)
    EditText etselllersnames;
    @BindView(R.id.tvpremiumoptional)
    TextView tvpremiumoptional;
    @BindView(R.id.textadd)
    TextView textadd;
    @BindView(R.id.tvfree)
    TextView tvfree;
    @BindView(R.id.tvreviewver)
    TextView tvreviewver;
    @BindView(R.id.tvPremiums)
    TextView tvPremiums;
    @BindView(R.id.tvrecomended)
    TextView tvrecomended;
    @BindView(R.id.tvupgrades)
    TextView tvupgrades;
    @BindView(R.id.myview)
    View myview;
    @BindView(R.id.checking)
    CheckBox checking;
    @BindView(R.id.tvfeatureddd)
    TextView tvfeatureddd;
    @BindView(R.id.tvdisplayed)
    TextView tvdisplayed;
    @BindView(R.id.tvqq)
    TextView tvqq;
    @BindView(R.id.viewing)
    View viewing;
    @BindView(R.id.checkking)
    CheckBox checkking;
    @BindView(R.id.tvfeatureess)
    TextView tvfeatureess;
    @BindView(R.id.tvhighquality)
    TextView tvhighquality;
    @BindView(R.id.tvquetion)
    TextView tvquetion;
    @BindView(R.id.viewsee)
    View viewsee;
    @BindView(R.id.checks)
    CheckBox checks;
    @BindView(R.id.tvfeatu)
    TextView tvfeatu;
    @BindView(R.id.tvattract)
    TextView tvattract;
    @BindView(R.id.tvqamount)
    TextView tvqamount;
    @BindView(R.id.viewvision)
    View viewvision;
    @BindView(R.id.btnpostadds)
    Button btnpostadds;
    @BindView(R.id.tvtottal)
    TextView tvtottal;
    @BindView(R.id.viewww)
    View viewww;
    @BindView(R.id.tvclickingcreate)
    TextView tvclickingcreate;
    @BindView(R.id.tvyear)
    TextView tvyear;
    @BindView(R.id.tvterm)
    TextView tvterm;
    @BindView(R.id.fulllayout)
    LinearLayout fulllayout;
    private int RESULT_LOAD_IMAGE = 102;
    Dialog dialog;
    ArrayList<CityModel> arrayListing = new ArrayList<>();
    ArrayList<SubCategoryModel> arraylistsubcategory = new ArrayList<>();
    ArrayList<String> arrayListingstr = new ArrayList<>();
    ArrayList<String> arrayListingdrop = new ArrayList<>();
    ArrayList<String> cityarrayListingdrop = new ArrayList<>();
    ArrayList<UploadedModel> arrayList = new ArrayList<>();
    ArrayList<CitiesModel> citiesarrayList = new ArrayList<>();
    String cat_id;
    String sub_cat_id;


    @Override
    protected int getContentResId() {
        return R.layout.activity_stuff;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ButterKnife.bind(this);

        btnchoosecategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCategoryData();
/*                final Dialog dialog = new Dialog(StuffActivity.this); // Context, this, etc.
                dialog.setContentView(R.layout.dialog_stuff);
                dialog.show();*/
            }
        });
        btnpostadds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApiforcity();
                callApiForAdPost();
            }
        });
/*
        autocomplete.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable) {
                // TODO Auto-generated method stub

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newText = s.toString();
*/
/*
                new JSONParser(StuffActivity.this).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).getcity_list(

                ), new Helper() {
                    @Override
                    public void backResponse(String response) {
                        Utility.E("city response : " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (jsonObject.get("status").equals("200")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject json = jsonArray.getJSONObject(i);
                                    CitiesModel citiesModel = new CitiesModel();
                                    citiesModel.setId(json.getString("id"));
                                    citiesModel.setName(json.getString("name"));
                                    citiesModel.setLatlong(json.getString("latlong"));
                                    citiesModel.setCountry_code(json.getString("country_code"));
                                    citiesModel.setState(json.getString("state"));
                                    citiesarrayList.add(citiesModel);
                                }


                            } else {
                                S.T(StuffActivity.this, jsonObject.getString("message"));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
*//*



            }

        });
*/


        imgupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrayList.size() < 3) {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                } else {
                    S.T(StuffActivity.this, "Your can not upload more than 3 images");
                }
            }
        });


    }


    private void setData(Bitmap bmp) {
        arrayList.add(new UploadedModel(bmp));

        if (arrayList.size() > 0) {
            tvimages.setVisibility(View.GONE);
            reecycle.setVisibility(View.VISIBLE);
            reecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            UploadedAdapter uploadedAdapter = new UploadedAdapter(this, arrayList);
            reecycle.setAdapter(uploadedAdapter);
        } else {
            reecycle.setVisibility(View.GONE);
            tvimages.setVisibility(View.VISIBLE);
        }
    }


    private void setCategoryData() {
        new JSONParser(this).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).getCategories(), new Helper() {
            @Override
            public void backResponse(String response) {
                S.E("categories response : " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.get("status").equals("200")) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject json = data.getJSONObject(i);
                            CityModel cityModel = new CityModel();
                            cityModel.setCatID(json.getString("cat_id"));
                            cityModel.setCat_order(json.getString("cat_order"));
                            cityModel.setCat_name(json.getString("cat_name"));
                            cityModel.setSlug(json.getString("slug"));
                            // cityModel.setIcon(json.getString("icon"));

                            arrayListing.add(cityModel);
                            arrayListingstr.add(json.getString("cat_name"));
                        }

                        dialog = new Dialog(StuffActivity.this);
                        dialog.setContentView(R.layout.dialog_stuff);

                        Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);
                        Button btnok = (Button) dialog.findViewById(R.id.btnok);
                        //    recycle = (RecyclerView) dialog.findViewById(R.id.recycle);


                        S.E("data size : : : " + arrayListing.size());

                        //  recycle.setLayoutManager(new LinearLayoutManager(StuffActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        //    CategpriesAdapter cityAdapter = new CategpriesAdapter(StuffActivity.this, arrayListing);
                        //                    recycle.setAdapter(cityAdapter);
                        ArrayAdapter<String> spiincategory = new ArrayAdapter<String>(StuffActivity.this, android.R.layout.simple_spinner_item, arrayListingstr);
                        spiincategory.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        spinner.setAdapter(spiincategory);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                cat_id = arrayListing.get(i).getCatID();
                                callApiForSubCategories(arrayListing.get(i).getCatID());

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                        dialog.show();

                    } else {
                        S.T(StuffActivity.this, jsonObject.getString("message"));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void callApiForSubCategories(String cat_id) {
        new JSONParser(StuffActivity.this).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).getSubCategories(
                cat_id
        ), new Helper() {
            @Override
            public void backResponse(String response) {
                Utility.E("sub category response : " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.get("status").equals("200")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        arraylistsubcategory.clear();
                        arrayListingdrop.clear();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);
                            SubCategoryModel subCategoryModel = new SubCategoryModel();
                            subCategoryModel.setSub_cat_id(json.getString("sub_cat_id"));
                            subCategoryModel.setMain_cat_id(json.getString("main_cat_id"));
                            subCategoryModel.setSub_cat_name(json.getString("sub_cat_name"));
                            subCategoryModel.setSlug(json.getString("slug"));
                            subCategoryModel.setCat_order(json.getString("cat_order"));
                            subCategoryModel.setPhoto_show(json.getString("photo_show"));
                            subCategoryModel.setPrice_show(json.getString("price_show"));
                            arraylistsubcategory.add(subCategoryModel);
                            arrayListingdrop.add(json.getString("sub_cat_name"));

                        }
                        Spinner dropspinner = (Spinner) dialog.findViewById(R.id.dropspinner);
                        ArrayAdapter<String> spiincategory = new ArrayAdapter<String>(StuffActivity.this, android.R.layout.simple_spinner_item, arrayListingdrop);
                        spiincategory.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        dropspinner.setAdapter(spiincategory);
                        dropspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                sub_cat_id = arraylistsubcategory.get(i).getSub_cat_id();
                                callApiForSubCategories(arraylistsubcategory.get(i).getSub_cat_id());

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        //    recyclerView.setLayoutManager(new LinearLayoutManager(SubCategoryActivity.this));
                        //    SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(SubCategoryActivity.this, arrayList);
                        //  recyclerView.setAdapter(subCategoryAdapter);
                    } else {
                        S.T(StuffActivity.this, jsonObject.getString("message"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void callApiforcity() {
        new JSONParser(StuffActivity.this).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).getcity_list(

        ), new Helper() {
            @Override
            public void backResponse(String response) {
                Utility.E("cities response : " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.get("status").equals("200")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);
                            CitiesModel citiesModel = new CitiesModel();
                            citiesModel.setId(json.getString("id"));
                            citiesModel.setName(json.getString("name"));
                            citiesModel.setLatlong(json.getString("latlong"));
                            citiesModel.setCountry_code(json.getString("country_code"));
                            citiesModel.setState(json.getString("state"));
                            citiesarrayList.add(citiesModel);

                        }
                        Spinner spinnerss = (Spinner) findViewById(R.id.spinnerss);

                 //       ArrayAdapter<String> spiincategory = new ArrayAdapter<String>(StuffActivity.this, android.R.layout.simple_spinner_item, citiesarrayList);
                  //      spiincategory.setDropDownViewResource(android.R.layout.simple_spinner_item);
                //        spinnerss.setAdapter(spiincategory);
                        spinnerss.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                    } else {
                        S.T(StuffActivity.this, jsonObject.getString("message"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void callApiForAdPost() {

        new JSONParser(StuffActivity.this).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).adPost(
                cat_id,
                sub_cat_id,
                ettitleadvertise.getText().toString(),
                ettellusmore.getText().toString(),
                UserDataHelper.getInstance().getData().get(0).getName(),
                UserDataHelper.getInstance().getData().get(0).getUserEmail(),
                UserDataHelper.getInstance().getData().get(0).getCity(),
                "",
                UserDataHelper.getInstance().getData().get(0).getCountry(),
                "",
                UserDataHelper.getInstance().getData().get(0).getPassword(),
                "",
                "",
                ""
        ), new Helper() {
            @Override
            public void backResponse(String response) {
                Utility.E("update Profile :" + response);
                Utility.E(" Profile response : " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults.length > 0) {
                    boolean ACCESSSTORAGE = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean ACCESSCAMERA = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (ACCESSSTORAGE && ACCESSCAMERA) {
                        //    openChooseImagePopup();
                    } else {
                        Toast.makeText(StuffActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void requestPermission() {
        ActivityCompat.requestPermissions(StuffActivity.this, new String[]
                {
                        WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                }, 101);
    }

    public boolean checkPermission() {
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(StuffActivity.this, WRITE_EXTERNAL_STORAGE);
        int FourthPermissionResult = ContextCompat.checkSelfPermission(StuffActivity.this, CAMERA);
        return ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FourthPermissionResult == PackageManager.PERMISSION_GRANTED;
    }


    private String getBase64String(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] imageBytes = baos.toByteArray();

        String base64String = Base64.encodeToString(imageBytes, Base64.NO_WRAP);

        return base64String;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RESULT_LOAD_IMAGE && data != null) {
            Uri imgUri = data.getData();
            try {
                setData(MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
