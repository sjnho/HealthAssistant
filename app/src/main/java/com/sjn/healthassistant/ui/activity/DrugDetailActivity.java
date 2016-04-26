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
    ImageView image;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);
        ButterKnife.bind(this);
        collapsingToolbar.setTitle("");

    }
}
