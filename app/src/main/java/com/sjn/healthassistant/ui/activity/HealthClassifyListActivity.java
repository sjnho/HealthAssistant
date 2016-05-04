package com.sjn.healthassistant.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cjj.OnLoadMoreListener;
import com.cjj.RecyclerViewWithFooter;
import com.google.gson.Gson;
import com.r0adkll.slidr.Slidr;
import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.contarct.ListContract;
import com.sjn.healthassistant.pojo.HealthClassify;
import com.sjn.healthassistant.pojo.HealthLore;
import com.sjn.healthassistant.presenter.HealthClassifyPresenter;
import com.sjn.healthassistant.util.RealmGson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HealthClassifyListActivity extends AppCompatActivity implements ListContract.View<HealthLore> {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.appbar)
    AppBarLayout mAppbar;
    @Bind(R.id.recyclerView)
    RecyclerViewWithFooter mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private HealthClassifyPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_classify_list);
        ButterKnife.bind(this);
        parseIntent();
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pagemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.page) {
            Toast.makeText(HealthClassifyListActivity.this, "选择分页", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        Slidr.attach(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mSwipeRefreshLayout.setRefreshing(true);
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.pullUp();
            }
        });
    }

    private void parseIntent() {
        HealthClassify healthClassify = RealmGson.getGson().fromJson(getIntent().getStringExtra(Constants.EXTRA_HEALTH_CLASSIFY), HealthClassify.class);
        mToolbar.setTitle(healthClassify.getTitle());
        mPresenter.setHealthClassify(healthClassify);
    }

    @Override
    public void onPullDown(List<HealthLore> data) {

    }

    @Override
    public void onPullUp(List<HealthLore> data) {

    }

    @Override
    public void stopLoading() {

    }
}
