package com.sjn.healthassistant.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.pojo.DrugLikeRecord;
import com.sjn.healthassistant.util.StringFormatUtil;
import com.sjn.healthassistant.util.ImageLoadUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class DrugDetailActivity extends BaseActivity {


    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.drug_message)
    TextView mDrugMessage;
    @Bind(R.id.collect)
    FloatingActionButton mCollect;
    @Bind(R.id.add_alarm)
    FloatingActionButton mAddAlarm;

    private Drug mDrug;
    private Realm realm;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);
        ButterKnife.bind(this);
        initViews();
        parseIntentAndSetData();
    }

    private void initViews() {
        setUpToolbar();
    }

    private void parseIntentAndSetData() {
        realm = Realm.getDefaultInstance();
        mDrug = realm.where(Drug.class).equalTo("id", getIntent().getStringExtra(Constants.EXTRA_DRUG_ID)).findFirst();
        collapsingToolbar.setTitle(mDrug.getDrugName());
        ImageLoadUtil.loadDrug(mDrug.getImg(), image);
        String drugString = StringFormatUtil.formatDrug(mDrug);
        if (!TextUtils.isEmpty(drugString)) {
            mDrugMessage.setText(drugString);
        }
//        String drugString = StringFormatUtil.formatDrug(mDrug.getMessage());
//        if (!TextUtils.isEmpty(drugString)) {
//            mDrugMessage.setText(drugString);
//        }
        setFabIcon();
    }

    private void uncollect() {
        RealmResults<DrugLikeRecord> results = realm.where(DrugLikeRecord.class).equalTo("drug.id", mDrug.getId()).findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
        mCollect.setImageResource(R.drawable.ic_favorite_border_white);
        mCollect.setLabelText("加入收藏");
        isFavorite = false;
        Toast.makeText(DrugDetailActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
    }

    private void collect() {
        RealmQuery<DrugLikeRecord> recordRealmQuery = realm.where(DrugLikeRecord.class);
        realm.beginTransaction();
        DrugLikeRecord record = realm.createObject(DrugLikeRecord.class);
        record.setId(recordRealmQuery.findAll().size());
        record.setDrug(mDrug);
        realm.commitTransaction();
        mCollect.setImageResource(R.drawable.ic_favorite_white);
        mCollect.setLabelText("取消收藏");
        isFavorite = true;
        Toast.makeText(DrugDetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
    }


    private void setFabIcon() {
        RealmQuery<DrugLikeRecord> recordRealmQuery = realm.where(DrugLikeRecord.class);
        recordRealmQuery.equalTo("drug.id", mDrug.getId());
        isFavorite = recordRealmQuery.findAll().size() > 0;
        if (isFavorite) {
            mCollect.setImageResource(R.drawable.ic_favorite_white);
            mCollect.setLabelText("取消收藏");
        } else {
            mCollect.setLabelText("加入收藏");
            mCollect.setImageResource(R.drawable.ic_favorite_border_white);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @OnClick({R.id.collect, R.id.add_alarm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.collect:
                if (isFavorite) {
                    uncollect();
                } else {
                    collect();
                }
                break;
            case R.id.add_alarm:
                Intent intent = new Intent(this, AddAlarmActvivity.class);
                intent.putExtra(Constants.EXTRA_DRUG_ID,mDrug.getId());
                startActivity(intent);
                break;
        }
    }
}
