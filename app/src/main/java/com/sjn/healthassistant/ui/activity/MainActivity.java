package com.sjn.healthassistant.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

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
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }


    private void initViews() {
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        HealthFragment healthFragment = new HealthFragment();
        DrugFragment drugFragment = new DrugFragment();
        DiseaseFragment diseaseFragment = new DiseaseFragment();
        mainPagerAdapter.addFragment(healthFragment, "健康");
        mainPagerAdapter.addFragment(drugFragment, "用药");
        mainPagerAdapter.addFragment(diseaseFragment, "病状");
        viewpager.setAdapter(mainPagerAdapter);
        viewpager.setOffscreenPageLimit(2);
        tabs.setupWithViewPager(viewpager);
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.nav_drug_scan:
                        Intent scan = new Intent(MainActivity.this, ZbarScanActivity.class);
                        startActivity(scan);
                        break;
                    case R.id.nav_drug_collect:
                        Intent collect = new Intent(MainActivity.this, DrugLikeListActivity.class);
                        startActivity(collect);
                        break;
                    case R.id.nav_drug_remind:
                        Intent remind = new Intent(MainActivity.this, RemindListActifity.class);
                        startActivity(remind);
                        break;
                    case R.id.nav_drug_search:
                        Intent intent = new Intent(MainActivity.this, DrugSearchActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
