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
import com.bluewebspark.classified.fragment.UrgentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abc on 07-Mar-18.
 */

public class SubCategoryTab extends BaseActivity {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    String sub_cat_id;

    @Override
    protected int getContentResId() {
        return R.layout.activity_subcategorytab;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        setToolbarWithBackButton(getIntent().getStringExtra("sub_cat_name"));
        sub_cat_id = getIntent().getStringExtra("sub_cat_id");
        Utility.E("sub_cat_id : " + sub_cat_id);

        viewpager.setOffscreenPageLimit(3);
        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new FindAdsFragment(), "Find ADS");
        adapter.addFrag(new FeaturedFragment(), "FEATURED");
        adapter.addFrag(new UrgentFragment(), "URGENT");
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
