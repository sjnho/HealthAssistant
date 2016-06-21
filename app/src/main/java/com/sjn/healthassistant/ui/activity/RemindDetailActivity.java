package com.sjn.healthassistant.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sjn.healthassistant.R;

public class RemindDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind_detail);
    }
    private void initViews(){
        setUpToolbar("编辑用药提醒");
    }
}
