package com.sjn.healthassistant.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;

import com.cjj.OnLoadMoreListener;
import com.cjj.RecyclerViewWithFooter;
import com.google.gson.Gson;
import com.r0adkll.slidr.Slidr;
import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.common.ItemClickSupport;
import com.sjn.healthassistant.common.SpacesItemDecoration;
import com.sjn.healthassistant.contarct.ListContract;
import com.sjn.healthassistant.pojo.BodyPart;
import com.sjn.healthassistant.pojo.Symptom;
import com.sjn.healthassistant.presenter.SymptomListPresenter;
import com.sjn.healthassistant.ui.adapter.SymptomAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SymptomListActivity extends BaseActivity implements ListContract.View<Symptom> {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.appbar)
    AppBarLayout mAppbar;
    @Bind(R.id.recyclerView)
    RecyclerViewWithFooter mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindString(R.string.transition_image)
    String transition;


    private SymptomListPresenter mPresenter;
    private SymptomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_list);
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
        mAdapter = new SymptomAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(8, this));
        mRecyclerView.setAdapter(mAdapter);
        Slidr.attach(this);
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.nextPage();
                mPresenter.pullUp();
            }
        });
        mRecyclerView.setEnd();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.pullDown();
            }
        });
        mPresenter.pullDown();
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                Intent intent = new Intent(SymptomListActivity.this, SymptomDetailActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(SymptomListActivity.this, view.findViewById(R.id.image), transition);
                intent.putExtra(Constants.EXTRA_SYMPTOM, new Gson().toJson(mAdapter.getData().get(position)));
                startActivity(intent, options.toBundle());
            }
        });
    }

    private void parseIntent() {
        BodyPart bodyPart = Realm.getDefaultInstance().where(BodyPart.class).equalTo("id", getIntent().getIntExtra(Constants.EXTRA_BODY_PART_ID, 0)).findFirst();
        setUpToolbar(bodyPart.getName());
        mPresenter = new SymptomListPresenter();
        mPresenter.bindView(this);
        mPresenter.setBodyPart(bodyPart);
    }


    public void onSetPage(List<Symptom> data) {
        mAdapter.setData(data);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onPullDown(List<Symptom> data) {
        if (data.size() < 10) {
            mRecyclerView.setEnd();
        } else {
            mRecyclerView.setLoad();
        }
        if (mAdapter.getData().size() != 0 && mAdapter.getData().get(0).getId() == data.get(0).getId()) {
            showToast("数据是最新的啦!");
        } else {
            mAdapter.getData().addAll(0, data);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPullUp(List<Symptom> data) {
        if (data.size() < 10) {
            mRecyclerView.setEnd();
        } else {
            mRecyclerView.setLoad();
        }
        if (mAdapter.getData().size() != 0 && mAdapter.getData().get(0).getId() == data.get(0).getId()) {
            showToast("没有更多数据了!");
        } else {
            mAdapter.getData().addAll(data);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void stopLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
