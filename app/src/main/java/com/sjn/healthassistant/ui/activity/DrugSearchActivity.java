package com.sjn.healthassistant.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.cjj.OnLoadMoreListener;
import com.cjj.RecyclerViewWithFooter;
import com.r0adkll.slidr.Slidr;
import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.common.ItemClickSupport;
import com.sjn.healthassistant.contarct.ListContract;
import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.presenter.DrugSearchPresenter;
import com.sjn.healthassistant.ui.adapter.DrugListAdapter;


import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class DrugSearchActivity extends BaseActivity implements ListContract.View<Drug> {

    @Bind(R.id.recyclerView)
    RecyclerViewWithFooter mRecyclerView;
    @Bind(R.id.edit_text)
    EditText editText;
    @BindString(R.string.transition_image)
    String transition;
    private DrugListAdapter mAdapter;
    private DrugSearchPresenter mPresenter;
    private ProgressDialog mProgressDialog;

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
        mPresenter = new DrugSearchPresenter();
        mPresenter.bindView(this);
        setUpToolbar();
        mProgressDialog = new ProgressDialog(this);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mProgressDialog.show();
                    mPresenter.setKeyWord(editText.getText().toString());
                    mPresenter.pullDown();
                }
                return true;
            }
        });
        mRecyclerView.setEnd();
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
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                Intent intent = new Intent(DrugSearchActivity.this, DrugDetailActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(DrugSearchActivity.this, view.findViewById(R.id.drug_image), transition);
                intent.putExtra(Constants.EXTRA_DRUG_ID, mAdapter.getDrugs().get(position).getId());
                startActivity(intent, options.toBundle());
            }
        });
        String key = getIntent().getStringExtra(Constants.EXTRA_DRUGNAME);
        if(!TextUtils.isEmpty(key)){
            editText.setText(key);
            mProgressDialog.show();
            mPresenter.setKeyWord(editText.getText().toString());
            mPresenter.pullDown();
        }
    }

    @Override
    public void onPullDown(List<Drug> data) {
        if (mAdapter.getDrugs().size() < 1) {
            mRecyclerView.setEmpty();
        }
        if (data.size() < 10) {
            mRecyclerView.setEnd();
        } else {
            mRecyclerView.setLoad();
        }
        mAdapter.getDrugs().addAll(0, data);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onPullUp(List<Drug> data) {
        if (data.size() < 10) {
            mRecyclerView.setEnd();
        } else {
            mRecyclerView.setLoad();
        }
        mAdapter.getDrugs().addAll(data);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void stopLoading() {
        mProgressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }
}
