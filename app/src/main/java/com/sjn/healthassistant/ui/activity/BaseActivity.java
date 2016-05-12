package com.sjn.healthassistant.ui.activity;

import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;

import com.sjn.healthassistant.R;

/**
 * Created by sjn on 16/5/12.
 */
public class BaseActivity extends AppCompatActivity {
    public void setUpToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        if (!TextUtils.isEmpty(title) && toolbar != null) {
            toolbar.setTitle(title);
        }
    }

    public void setUpToolbar() {
        setUpToolbar("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
