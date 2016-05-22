package com.sjn.healthassistant.ui.activity;

import android.app.ProgressDialog;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.cjj.RecyclerViewWithFooter;
import com.sjn.healthassistant.R;
import com.sjn.healthassistant.contarct.ListContract;
import com.sjn.healthassistant.pojo.Symptom;
import com.sjn.healthassistant.presenter.SyptomListPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SymptomListActivity extends BaseActivity implements ListContract.View<Symptom>{


    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.appbar)
    AppBarLayout mAppbar;
    @Bind(R.id.recyclerView)
    RecyclerViewWithFooter mRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;


    private SyptomListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        initViews();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pagemenu, menu);
        return true;
    }

    private void initViews(){

    }

    public void onSetPage(List<Symptom> data){
        
    }


    @Override
    public void onPullDown(List<Symptom> data) {

    }

    @Override
    public void onPullUp(List<Symptom> data) {

    }

    @Override
    public void stopLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
