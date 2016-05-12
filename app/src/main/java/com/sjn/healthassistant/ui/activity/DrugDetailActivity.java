package com.sjn.healthassistant.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.pojo.DrugLikeRecord;
import com.sjn.healthassistant.util.DrugStringFormat;
import com.sjn.healthassistant.util.ImageLoadUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
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
    @Bind(R.id.fab)
    FloatingActionButton mFab;

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
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavorite) {
                    RealmResults<DrugLikeRecord> results = realm.where(DrugLikeRecord.class).equalTo("drug.id", mDrug.getId()).findAll();
                    realm.beginTransaction();
                    results.deleteAllFromRealm();
                    realm.commitTransaction();
                    mFab.setImageResource(R.drawable.ic_favorite_border);
                    isFavorite = true;
                    Toast.makeText(DrugDetailActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();

                } else {
                    RealmQuery<DrugLikeRecord> recordRealmQuery = realm.where(DrugLikeRecord.class);
                    realm.beginTransaction();
                    DrugLikeRecord record = realm.createObject(DrugLikeRecord.class);
                    record.setId(recordRealmQuery.findAll().size());
                    record.setDrug(mDrug);
                    realm.commitTransaction();
                    mFab.setImageResource(R.drawable.ic_favorite);
                    isFavorite = false;
                    Toast.makeText(DrugDetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void parseIntentAndSetData() {
        realm = Realm.getDefaultInstance();
        mDrug = realm.where(Drug.class).equalTo("id", getIntent().getIntExtra(Constants.EXTRA_DRUG_ID, 0)).findFirst();
        collapsingToolbar.setTitle(mDrug.getName());
        ImageLoadUtil.loadImageCacheDisk(mDrug.getImg(), image);
        String drugString = DrugStringFormat.formatDrug(mDrug.getMessage());
        if (!TextUtils.isEmpty(drugString)) {
            mDrugMessage.setText(drugString);
        }
        setFabIcon();
    }

    private void setFabIcon() {
        RealmQuery<DrugLikeRecord> recordRealmQuery = realm.where(DrugLikeRecord.class);
        recordRealmQuery.equalTo("drug.id", mDrug.getId());
        isFavorite = recordRealmQuery.findAll().size() > 0;
        if (isFavorite) {
            mFab.setImageResource(R.drawable.ic_favorite);
        } else {
            mFab.setImageResource(R.drawable.ic_favorite_border);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
