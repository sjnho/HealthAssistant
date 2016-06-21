package com.sjn.healthassistant.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liangfeizc.flowlayout.FlowLayout;
import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.common.UrlImageGetter;
import com.sjn.healthassistant.pojo.Symptom;
import com.sjn.healthassistant.util.ImageLoadUtil;
import com.sjn.healthassistant.util.RealmGson;
import com.sjn.healthassistant.util.StringFormatUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SymptomDetailActivity extends BaseActivity {

    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.symptom_message)
    TextView symptomMessage;
    @Bind(R.id.flow_layout)
    FlowLayout flowLayout;
    @Bind(R.id.scroll)
    NestedScrollView scroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_detail);
        ButterKnife.bind(this);
        initViews();
        parseIntentAndSetData();
    }

    private void initViews() {
        setUpToolbar();
    }

    private void parseIntentAndSetData() {
        Symptom symptom = new Gson().fromJson(getIntent().getStringExtra(Constants.EXTRA_SYMPTOM), Symptom.class);
        collapsingToolbar.setTitle(symptom.getName());
        ImageLoadUtil.loadDrug(Constants.IMAGE + symptom.getImg(), image);
        symptomMessage.setText(Html.fromHtml(StringFormatUtil.formatSymptom(symptom)));
        String[] drugs = symptom.getDrug().split(",");
        for (final String drug : drugs) {
            TextView textView = new TextView(this);
            textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            textView.getPaint().setAntiAlias(true);
            textView.setText(drug);
            textView.setTextSize(16);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SymptomDetailActivity.this, DrugSearchActivity.class);
                    intent.putExtra(Constants.EXTRA_DRUGNAME, drug);
                    startActivity(intent);
                }
            });
            flowLayout.addView(textView);
        }

    }

    @OnClick(R.id.fab)
    public void fabClick() {
        scroll.fullScroll(View.FOCUS_UP);
    }
}
