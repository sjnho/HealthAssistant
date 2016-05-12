package com.sjn.healthassistant.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;

import com.google.gson.Gson;
import com.r0adkll.slidr.Slidr;
import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.common.UrlImageGetter;
import com.sjn.healthassistant.pojo.HealthNews;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsActivity extends BaseActivity {

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
        Slidr.attach(this);
        setUpToolbar();
        parseIntent();
    }

    private void parseIntent() {
        HealthNews healthInfo = new Gson().fromJson(getIntent().getStringExtra(Constants.EXTRA_HEALTH_INFO), HealthNews.class);
        title.setText(healthInfo.getTitle());
        content.setText(Html.fromHtml(healthInfo.getMessage(), new UrlImageGetter(content), null));
    }

}
