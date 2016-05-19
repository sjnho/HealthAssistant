package com.sjn.healthassistant.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sjn.healthassistant.R;
import com.sjn.healthassistant.pojo.DrugLikeRecord;
import com.sjn.healthassistant.ui.adapter.DrugListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class DrugLikeListActivity extends BaseActivity {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private ProgressDialog mProgressDialog;

    private RealmResults<DrugLikeRecord> mResults;
    private DrugListAdapter mDrugListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_like_list);
        ButterKnife.bind(this);
        initViews();
        setUpToolbar("药品收藏");
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading_data));
        mProgressDialog.show();
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDrugListAdapter = new DrugListAdapter();
        mRecyclerView.setAdapter(mDrugListAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    protected void onStart() {
        super.onStart();
        mResults = Realm.getDefaultInstance().where(DrugLikeRecord.class).findAllAsync();
        mResults.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange() {
                if (mResults.isLoaded()) {
                    for (DrugLikeRecord likeRecord : mResults) {
                        mDrugListAdapter.getDrugs().add(0, likeRecord.getDrug());
                        mDrugListAdapter.notifyItemChanged(0);
                    }
                }
                mProgressDialog.dismiss();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mResults.removeChangeListeners();
    }
}
