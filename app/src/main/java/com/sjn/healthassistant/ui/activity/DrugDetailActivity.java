package com.sjn.healthassistant.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.sjn.healthassistant.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DrugDetailActivity extends AppCompatActivity {

    @Bind(R.id.image)
    ImageView sImage;
    @Bind(R.id.toolbar)
    Toolbar sToolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout sCollapsingToolbar;
    @Bind(R.id.appbar)
    AppBarLayout sAppbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);
        ButterKnife.bind(this);
        sCollapsingToolbar.setTitle("");
    }
}
