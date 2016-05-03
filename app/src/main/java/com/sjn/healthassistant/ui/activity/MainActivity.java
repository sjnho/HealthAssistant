package com.sjn.healthassistant.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.sjn.healthassistant.R;
import com.sjn.healthassistant.ui.adapter.MainPagerAdapter;
import com.sjn.healthassistant.ui.fragment.DiseaseFragment;
import com.sjn.healthassistant.ui.fragment.DrugFragment;
import com.sjn.healthassistant.ui.fragment.HealthFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        HealthFragment healthFragment= new HealthFragment();
        DrugFragment drugFragment = new DrugFragment();
        DiseaseFragment diseaseFragment = new DiseaseFragment();
        mainPagerAdapter.addFragment(healthFragment,"健康");
        mainPagerAdapter.addFragment(drugFragment,"用药");
        mainPagerAdapter.addFragment(diseaseFragment,"病状");
        viewpager.setAdapter(mainPagerAdapter);
        tabs.setupWithViewPager(viewpager);
    }
}
