package com.sjn.healthassistant.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;
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
import com.sjn.healthassistant.ui.adapter.HealthLoreAdapter;
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
    private HealthLoreAdapter mHealthLoreAdapter;

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

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final NumberPicker numPick = new NumberPicker(this);
            numPick.setMinValue(2);
            numPick.setMaxValue(mPresenter.getPageCount());
            builder.setTitle(R.string.select_page);
            builder.setView(numPick);
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    mPresenter.setPageData(numPick.getValue());
                }
            });
            builder.show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        mHealthLoreAdapter = new HealthLoreAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mHealthLoreAdapter);
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
        mRecyclerView.setEnd();
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.pullUp();
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.pullDown();
            }
        });
        mPresenter.pullDown();
    }

    private void parseIntent() {
        HealthClassify healthClassify = RealmGson.getGson().fromJson(getIntent().getStringExtra(Constants.EXTRA_HEALTH_CLASSIFY), HealthClassify.class);
        mToolbar.setTitle(healthClassify.getTitle());
        mPresenter = new HealthClassifyPresenter();
        mPresenter.bindView(this);
        mPresenter.setHealthClassify(healthClassify);
    }

    public void onSetPageData(List<HealthLore> data) {
        mHealthLoreAdapter.setData(data);
        mHealthLoreAdapter.notifyDataSetChanged();
    }


    @Override
    public void onPullDown(List<HealthLore> data) {
        if (mHealthLoreAdapter.getData().size() != 0 && mHealthLoreAdapter.getData().get(0).getId() == data.get(0).getId()) {
            Toast.makeText(HealthClassifyListActivity.this, "数据是最新的啦!", Toast.LENGTH_SHORT).show();
        } else {
            mHealthLoreAdapter.getData().addAll(0, data);
            mHealthLoreAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onPullUp(List<HealthLore> data) {
        if (mHealthLoreAdapter.getData().size() != 0 && mHealthLoreAdapter.getData().get(0).getId() == data.get(0).getId()) {
            mRecyclerView.setEnd();
            Toast.makeText(HealthClassifyListActivity.this, "没有更多数据了!", Toast.LENGTH_SHORT).show();
        } else {
            mHealthLoreAdapter.getData().addAll(data);
            mHealthLoreAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void stopLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
