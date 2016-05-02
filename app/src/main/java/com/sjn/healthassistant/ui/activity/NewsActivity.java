package com.sjn.healthassistant.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.common.UrlImageGetter;
import com.sjn.healthassistant.pojo.HealthInfo;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.content)
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        showNews(new Gson().fromJson(getIntent().getStringExtra(Constants.EXTRA_HEALTH_INFO), HealthInfo.class));
    }

    private void showNews(HealthInfo healthInfo) {
        title.setText(healthInfo.getTitle());
        content.setText(Html.fromHtml(healthInfo.getMessage()
                , new UrlImageGetter(content), null));
    }
}
