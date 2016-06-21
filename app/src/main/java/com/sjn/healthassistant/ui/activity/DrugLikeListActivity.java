package com.sjn.healthassistant.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.common.ItemClickSupport;
import com.sjn.healthassistant.pojo.DrugLikeRecord;
import com.sjn.healthassistant.ui.adapter.DrugListAdapter;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class DrugLikeListActivity extends BaseActivity {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindString(R.string.transition_image)
    String transition;

    private ProgressDialog mProgressDialog;

    private RealmResults<DrugLikeRecord> mResults;
    private DrugListAdapter mDrugListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
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
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                Intent intent = new Intent(DrugLikeListActivity.this, DrugDetailActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(DrugLikeListActivity.this, view.findViewById(R.id.drug_image), transition);
                intent.putExtra(Constants.EXTRA_DRUG_ID, mDrugListAdapter.getDrugs().get(position).getId());
                startActivity(intent, options.toBundle());
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        mResults = Realm.getDefaultInstance().where(DrugLikeRecord.class).findAllAsync();
        mResults.addChangeListener(new RealmChangeListener<RealmResults<DrugLikeRecord>>() {
            @Override
            public void onChange(RealmResults<DrugLikeRecord> element) {
                if (mResults.isLoaded()) {
                    if (mDrugListAdapter.getDrugs() != null && mDrugListAdapter.getDrugs().size() == 0) {
                        for (DrugLikeRecord likeRecord : mResults) {
                            mDrugListAdapter.getDrugs().add(0, likeRecord.getDrug());
                            mDrugListAdapter.notifyItemChanged(0);
                        }
                    }
                }
            }
        });

        mProgressDialog.dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mResults.removeChangeListeners();
    }
}
