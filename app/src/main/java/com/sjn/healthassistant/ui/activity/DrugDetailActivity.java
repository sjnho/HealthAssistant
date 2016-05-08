package com.sjn.healthassistant.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.util.DrugStringFormat;
import com.sjn.healthassistant.util.ImageLoadUtil;
import com.sjn.healthassistant.util.RealmGson;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DrugDetailActivity extends AppCompatActivity {


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

    private Drug mDrug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);
        ButterKnife.bind(this);
        initViews();
        parseIntentAndSetData();
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void parseIntentAndSetData() {
        mDrug = RealmGson.getGson().fromJson(getIntent().getStringExtra(Constants.EXTRA_DRUG), Drug.class);
        collapsingToolbar.setTitle(mDrug.getName());
        ImageLoadUtil.loadImageCacheDisk(mDrug.getImg(), image);
        String drugString = DrugStringFormat.formatDrug(mDrug.getMessage());
        if (!TextUtils.isEmpty(drugString)) {
            mDrugMessage.setText(drugString);
        }
    }
}
