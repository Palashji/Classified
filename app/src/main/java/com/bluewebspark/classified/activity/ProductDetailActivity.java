package com.bluewebspark.classified.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.adapter.AdditionalDetailAdapter;
import com.bluewebspark.classified.adapter.ProductDetailAdapter;
import com.bluewebspark.classified.adapter.ProductsTagsAdapter;
import com.bluewebspark.classified.model.AdditionalDetailModel;
import com.bluewebspark.classified.model.ProductDetailModel;
import com.bluewebspark.classified.model.ProductsTagModel;
import com.bluewebspark.classified.utils.ApiClient;
import com.bluewebspark.classified.utils.ApiInterface;
import com.bluewebspark.classified.utils.Helper;
import com.bluewebspark.classified.utils.JSONParser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, OnMapReadyCallback {

    String user_id;
    double UserLat;
    double UserLong;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.btncontact)
    Button btncontact;
    @BindView(R.id.tvsale)
    TextView tvsale;
    @BindView(R.id.tvdays)
    TextView tvdays;
    @BindView(R.id.tvlocation)
    TextView tvlocation;
    @BindView(R.id.tvview)
    TextView tvview;
    @BindView(R.id.recycleViewPremium)
    RecyclerView electronicrecyclerView;
    @BindView(R.id.tvdetail)
    TextView tvdetail;
    @BindView(R.id.additionaldetail)
    TextView additionaldetail;
    @BindView(R.id.myview)
    View myview;
    @BindView(R.id.additional_recyclerview)
    RecyclerView additionalRecyclerview;
    @BindView(R.id.tvdescription)
    TextView tvdescription;
    @BindView(R.id.myviewing)
    View myviewing;
    @BindView(R.id.tvgood)
    TextView tvgood;
    @BindView(R.id.tvproducttags)
    TextView tvproducttags;
    @BindView(R.id.viewproduct)
    View viewproduct;
    @BindView(R.id.product_recyclerview)
    RecyclerView productRecyclerview;
    @BindView(R.id.tvloctaion)
    TextView tvloctaion;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.tvaveragerating)
    TextView tvaveragerating;
    @BindView(R.id.averagerating)
    TextView averagerating;
    @BindView(R.id.rtbHighScore)
    RatingBar rtbHighScore;
    private String pro_id;
    private String id;
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private Marker mCurrLocationMarker;
    private SupportMapFragment mapFrag;

    GoogleMap mGoogleMap;

    ArrayList<AdditionalDetailModel> arrayListAdditionalDetails = new ArrayList<>();
    ArrayList<ProductDetailModel> arrayList = new ArrayList<>();
    ArrayList<ProductsTagModel> arraylistproduct = new ArrayList<>();

    @Override
    protected int getContentResId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setToolbarWithBackButton("Product Detail");
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

        pro_id = getIntent().getStringExtra("proID");
        id = getIntent().getStringExtra("id");
        callApiForProductDetail();

        btncontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("user_id", user_id);

                //   S.E("Palash ");
                S.I(ProductDetailActivity.this, ContactActivity.class, bundle);
            }
        });

    }

    private void callApiForProductDetail() {
        new JSONParser(this).parseRetrofitRequest(ApiClient.getClient().create(ApiInterface.class).productaddetail(
                pro_id

        ), new Helper() {
            @Override
            public void backResponse(String response) {
                S.E("product details response : " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equals("200")) {
                        S.T(ProductDetailActivity.this, jsonObject.getString("message"));

                        JSONObject jsonData = jsonObject.getJSONObject("data");
                        user_id = jsonData.getString("user_id");
                        String proID = jsonData.getString("proID");
                        String product_name = jsonData.getString("product_name");
                        String cat_name = jsonData.getString("cat_name");
                        String sub_cat_name = jsonData.getString("sub_cat_name");
                        String countrycode = jsonData.getString("countrycode");
                        String created_at = jsonData.getString("created_at");
                        String price = jsonData.getString("price");
                        String featured = jsonData.getString("featured");
                        String urgent = jsonData.getString("urgent");
                        String highlight = jsonData.getString("highlight");
                        String country = jsonData.getString("country");
                        String city = jsonData.getString("city");
                        String lat = jsonData.getString("lat");
                        String lng = jsonData.getString("long");
                        String product_review = jsonData.getString("product_review");
                        UserLat = Double.parseDouble(lat);
                        UserLong = Double.parseDouble(lng);

                        String description = jsonData.getString("description");

                        JSONArray product_image = jsonData.getJSONArray("product_image");
                        for (int i = 0; i < product_image.length(); i++) {
                            JSONObject json = product_image.getJSONObject(i);
                            ProductDetailModel productDetailModel = new ProductDetailModel();
                            productDetailModel.setImage(json.getString("image"));
                            arrayList.add(productDetailModel);
                        }

                        S.E("arraylistimagesize :" + arrayList.size());
                        if (arrayList.size() > 0) {
                            electronicrecyclerView.setVisibility(View.VISIBLE);
                            electronicrecyclerView.setLayoutManager(new LinearLayoutManager(ProductDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
                            ProductDetailAdapter productDetailAdapter = new ProductDetailAdapter(ProductDetailActivity.this, arrayList);
                            electronicrecyclerView.setAdapter(productDetailAdapter);
                        } else {
                            electronicrecyclerView.setVisibility(View.GONE);
                        }

                        JSONArray product_tag = jsonData.getJSONArray("product_tag");
                        for (int i = 0; i < product_tag.length(); i++) {
                            JSONObject json = product_tag.getJSONObject(i);

                            ProductsTagModel productsTagModel = new ProductsTagModel();

                            productsTagModel.setProduct(json.getString("tag"));

                            arraylistproduct.add(productsTagModel);
                        }
                        //     S.E("arraylistimagesize :" + arrayList.size());
                        productRecyclerview.setLayoutManager(new LinearLayoutManager(ProductDetailActivity.this));
                        ProductsTagsAdapter productsTagsAdapter = new ProductsTagsAdapter(ProductDetailActivity.this, arraylistproduct);
                        productRecyclerview.setAdapter(productsTagsAdapter);


                        JSONArray product_additional_detail = jsonData.getJSONArray("product_additional_detail");

                        for (int i = 0; i < product_additional_detail.length(); i++) {
                            JSONObject json = product_additional_detail.getJSONObject(i);

                            AdditionalDetailModel additionalDetailModel = new AdditionalDetailModel();

                            additionalDetailModel.setTitle(json.getString("title"));
                            additionalDetailModel.setValue(json.getString("value"));

                            arrayListAdditionalDetails.add(additionalDetailModel);
                        }

                        additionalRecyclerview.setLayoutManager(new LinearLayoutManager(ProductDetailActivity.this));
                        AdditionalDetailAdapter additionalDetailAdapter = new AdditionalDetailAdapter(ProductDetailActivity.this, arrayListAdditionalDetails);
                        additionalRecyclerview.setAdapter(additionalDetailAdapter);

                        tvsale.setText(product_name);
                        tvlocation.setText(city);
                        tvgood.setText(description);
                        averagerating.setText(product_review);
                        Bundle bundle = new Bundle();
                        onConnected(bundle);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
            } else {
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            // ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            // public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);
        mGoogleMap.getUiSettings().setCompassEnabled(false);
        mGoogleMap.getUiSettings().setIndoorLevelPickerEnabled(false);
        mGoogleMap.getUiSettings().setMapToolbarEnabled(false);
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        LatLng latLng = new LatLng(UserLat, UserLong);

        S.E("latLng  : : " + latLng);

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

        mGoogleMap.addMarker(new MarkerOptions()
                .position(latLng)
//                .title(returnAddress(UserLat, UserLong))
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(ProductDetailActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private String returnAddress(double lat, double lng) {
        Geocoder myLocation = new Geocoder(ProductDetailActivity.this, Locale.getDefault());
        List<Address> myList = new ArrayList<>();
        try {
            myList = myLocation.getFromLocation(lat, lng, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myList.get(0).getAddressLine(0);
    }

    public LatLng getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }


}


