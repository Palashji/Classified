package com.bluewebspark.classified.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.bluewebspark.classified.R;
import com.bluewebspark.classified.Utility.Utility;
import com.bluewebspark.classified.fragment.FeaturedFragment;
import com.bluewebspark.classified.fragment.FindAdsFragment;
import com.bluewebspark.classified.fragment.HidddenAdsFragment;
import com.bluewebspark.classified.fragment.MyAdsFragment;
import com.bluewebspark.classified.fragment.PendingAdsFragment;
import com.bluewebspark.classified.fragment.UrgentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abc on 07-Mar-18.
 */

public class MyAdsTab extends BaseActivity {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    String sub_cat_id;

    @Override
    protected int getContentResId() {
        return R.layout.activity_myadstab;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        setToolbarWithBackButton("MyADS");

        viewpager.setOffscreenPageLimit(3);
        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new MyAdsFragment(), "MY ADS");
        adapter.addFrag(new PendingAdsFragment(), "PENDING ADS");
        adapter.addFrag(new HidddenAdsFragment(), "HIDDEN ADS");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
