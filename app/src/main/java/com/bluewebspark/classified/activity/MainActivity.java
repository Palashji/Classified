package com.bluewebspark.classified.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.S;
import com.bluewebspark.classified.adapter.CategpriesAdapter;
import com.bluewebspark.classified.adapter.LatestAdapter;
import com.bluewebspark.classified.adapter.PremiumAdapter;
import com.bluewebspark.classified.model.CityModel;
import com.bluewebspark.classified.model.PremiumModel;
import com.bluewebspark.classified.model.LatestModel;
import com.bluewebspark.classified.utils.ApiClient;
import com.bluewebspark.classified.utils.ApiInterface;
import com.bluewebspark.classified.utils.Helper;
import com.bluewebspark.classified.utils.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.tvCategory)
    TextView tvCategory;
    @BindView(R.id.recycleViewCategory)
    RecyclerView recycleViewCategory;
    @BindView(R.id.tvPremium)
    TextView tvPremium;
    @BindView(R.id.recycleViewPremium)
    RecyclerView recycleViewPremium;
    @BindView(R.id.tvLatest)
    TextView tvLatest;
    @BindView(R.id.recycleViewLatest)
    RecyclerView recycleViewLatest;
    @BindView(R.id.btnsell)
    Button btnsell;
    @BindView(R.id.imgfilter)
    ImageView imgfilter;

    private LinearLayoutManager layoutManager;
    private CardView itemCardView;

    ArrayList<PremiumModel> arrayLists = new ArrayList<>();
    ArrayList<LatestModel> array = new ArrayList<>();
    ArrayList<CityModel> arrayListing = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        /*call api for get categories data*/
        setCategoryData();
        /*call api for get premium ads data */
        setDataPremium();
        /*call api for get latest ads */
        setLatestData();

        imgfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.I(MainActivity.this, FiltersActivity.class, null);

            }
        });

        btnsell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                S.I(MainActivity.this, StuffActivity.class, null);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            S.I(MainActivity.this, MainActivity.class, null);
        } else if (id == R.id.nav_stuff) {
            S.I(MainActivity.this, StuffActivity.class, null);

        } else if (id == R.id.nav_transaction) {
            S.I(MainActivity.this, TransactionActivity.class, null);

        } else if (id == R.id.nav_ads) {
            S.I(MainActivity.this, MyAdsTab.class, null);

        } else if (id == R.id.notification) {
            S.I(MainActivity.this, NotificationActivity.class, null);

        } else if (id == R.id.profile) {
            S.I(MainActivity.this, ProfileActivity.class, null);

        } else if (id == R.id.password) {
            S.I(MainActivity.this, ChangePasswordActivity.class, null);

        } else if (id == R.id.information) {
            S.I(MainActivity.this, HelpActivity.class, null);


        } else if (id == R.id.logout) {
            S.I_clear(MainActivity.this, WelcomeActivity.class, null);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setDataPremium() {

        new JSONParser(this).parseRetrofitRequestWithautProgress(ApiClient.getClient().create(ApiInterface.class).getPremium(), new Helper() {
            @Override
            public void backResponse(String response) {
                S.E("premium response : " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.get("status").equals("200")) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject json = data.getJSONObject(i);
                            PremiumModel premiumModel = new PremiumModel();
                            premiumModel.setId(json.getString("id"));
                            premiumModel.setName(json.getString("product_name"));
                            premiumModel.setSubname(json.getString("category"));
                            premiumModel.setCity(json.getString("city"));
                            premiumModel.setTime(json.getString("created_at"));
                            premiumModel.setBtnamount(json.getString("price"));
                            premiumModel.setProfile(json.getString("picture"));


                            arrayLists.add(premiumModel);

                        }
                        S.E("data size : : : " + arrayListing.size());
                        recycleViewPremium.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        PremiumAdapter premiumAdapter = new PremiumAdapter(MainActivity.this, arrayLists);
                        recycleViewPremium.setAdapter(premiumAdapter);


                    } else {
                        S.T(MainActivity.this, jsonObject.getString("message"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void setLatestData() {

        new JSONParser(this).parseRetrofitRequestWithautProgress(ApiClient.getClient().create(ApiInterface.class).getLatest(), new Helper() {
            @Override
            public void backResponse(String response) {
                S.E("latest response : " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.get("status").equals("200")) {
                        JSONArray data = jsonObject.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject json = data.getJSONObject(i);
                            LatestModel latestModel = new LatestModel();
                            latestModel.setId(json.getString("id"));
                            latestModel.setName(json.getString("product_name"));
                            latestModel.setSubname(json.getString("category"));
                            latestModel.setState(json.getString("city"));
                            latestModel.setTime(json.getString("created_at"));
                            latestModel.setButton(json.getString("price"));
                            latestModel.setImage(json.getString("picture"));


                            array.add(latestModel);

                        }
                        S.E("data size : : : " + arrayListing.size());
                        recycleViewLatest.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        LatestAdapter latestAdapter = new LatestAdapter(MainActivity.this, array);
                        recycleViewLatest.setAdapter(latestAdapter);


                    } else {
                        S.T(MainActivity.this, jsonObject.getString("message"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

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
                            cityModel.setCount(json.getString("count"));
                            // cityModel.setIcon(json.getString("icon"));


                            arrayListing.add(cityModel);

                        }
                        S.E("data size : : : " + arrayListing.size());

                        recycleViewCategory.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                        CategpriesAdapter categpriesAdapter = new CategpriesAdapter(MainActivity.this, arrayListing);
                        recycleViewCategory.setAdapter(categpriesAdapter);

                    } else {
                        S.T(MainActivity.this, jsonObject.getString("message"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}




