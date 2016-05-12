package com.sjn.healthassistant.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sjn.healthassistant.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ZbarScanActivity extends BaseActivity implements ZBarScannerView.ResultHandler {


    @Bind(R.id.content_frame)
    FrameLayout mContentFrame;

    private ZBarScannerView mZBarScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zbar_scan);
        ButterKnife.bind(this);
        mZBarScannerView = new ZBarScannerView(this);
        mContentFrame.addView(mZBarScannerView);
        setUpToolbar("扫描条形码");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mZBarScannerView.setResultHandler(this);
        mZBarScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mZBarScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {

    }
}
