package com.sjn.healthassistant.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.cjj.OnLoadMoreListener;
import com.cjj.RecyclerViewWithFooter;
import com.google.gson.Gson;
import com.r0adkll.slidr.Slidr;
import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.common.ItemClickSupport;
import com.sjn.healthassistant.contarct.ListContract;
import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.presenter.DrugSearchPresenter;
import com.sjn.healthassistant.ui.adapter.DrugListAdapter;
import com.sjn.healthassistant.util.RealmGson;
import com.sjn.healthassistant.widget.WaitDialog;


import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class DrugSearchActivity extends AppCompatActivity implements ListContract.View<Drug> {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recyclerView)
    RecyclerViewWithFooter mRecyclerView;
    @Bind(R.id.edit_text)
    EditText editText;
    @BindString(R.string.transition_image)
    String transition;
    private WaitDialog mWaitDialog;
    private DrugListAdapter mAdapter;
    private DrugSearchPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_search);
        ButterKnife.bind(this);
        initViews();
    }

    @OnTextChanged(value = R.id.edit_text, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onTextChange(CharSequence s) {
        if (TextUtils.isEmpty(s.toString())) {
            mAdapter.getDrugs().clear();
            mAdapter.notifyDataSetChanged();
        }
    }


    private void initViews() {
        Slidr.attach(this);
        mWaitDialog = new WaitDialog();
        mPresenter = new DrugSearchPresenter();
        mPresenter.bindView(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitle("");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mWaitDialog.show(getSupportFragmentManager(), "wait");
                    mPresenter.setKeyWord(editText.getText().toString());
                    mPresenter.pullDown();
                }
                return true;
            }
        });
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.nextPage();
                mPresenter.pullUp();
            }
        });
        mAdapter = new DrugListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setEnd();
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                Intent intent = new Intent(DrugSearchActivity.this, DrugDetailActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(DrugSearchActivity.this, view.findViewById(R.id.drug_image), transition);
                intent.putExtra(Constants.EXTRA_DRUG_ID, mAdapter.getDrugs().get(position).getId());
                startActivity(intent, options.toBundle());
            }
        });

    }

    @Override
    public void onPullDown(List<Drug> data) {

        mAdapter.getDrugs().addAll(0, data);
        mAdapter.notifyDataSetChanged();
        if (data.size() < 1) {
            mRecyclerView.setEmpty();
        } else if (data.size() < 20) {
            mRecyclerView.setEnd();
        } else {
            mRecyclerView.setLoad();
        }
    }

    @Override
    public void onPullUp(List<Drug> data) {
        mAdapter.getDrugs().addAll(data);
        mAdapter.notifyDataSetChanged();
        if (data.size() < 20) {
            mRecyclerView.setEnd();
        } else {
            mRecyclerView.setLoad();
        }
    }

    @Override
    public void stopLoading() {
        mWaitDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }
}
