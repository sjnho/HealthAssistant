package com.sjn.healthassistant.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.common.ItemClickSupport;
import com.sjn.healthassistant.common.SpacesItemDecoration;
import com.sjn.healthassistant.pojo.DrugAlarm;
import com.sjn.healthassistant.ui.adapter.RemindListAdapter;
import com.sjn.healthassistant.util.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class RemindListActifity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.appbar)
    AppBarLayout mAppbar;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private Realm mRealm;
    private RemindListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
        mRealm = Realm.getDefaultInstance();
        setUpToolbar("用药提醒");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RemindListAdapter(mRealm.where(DrugAlarm.class).findAll());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(5));

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                Intent intent = new Intent(RemindListActifity.this, RemindDetailActivity.class);
                intent.putExtra(Constants.REMIND_ID, mAdapter.getData().get(position).getId());
                startActivity(intent);
            }
        });
        ItemClickSupport.addTo(mRecyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(RecyclerView parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RemindListActifity.this);
                builder.setTitle("删除该项");
                builder.setMessage("是否删除该条提醒信息");
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton(R.string.ensure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogUtil.d("删除成功");
                        RealmResults<DrugAlarm> results = mRealm.where(DrugAlarm.class).equalTo("id", mAdapter.getData().get(position).getId()).findAll();
                        mRealm.beginTransaction();
                        results.deleteAllFromRealm();
                        mRealm.commitTransaction();
                        mAdapter.getData().remove(position);
                        mAdapter.notifyItemRemoved(position);
                    }
                });
                builder.show();
                return false;
            }
        });
    }
}
