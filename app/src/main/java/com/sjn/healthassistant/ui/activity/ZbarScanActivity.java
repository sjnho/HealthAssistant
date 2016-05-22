package com.sjn.healthassistant.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.contarct.DetailContract;
import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.presenter.DrugCodePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.dm7.barcodescanner.zbar.BarcodeFormat;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ZbarScanActivity extends BaseActivity implements ZBarScannerView.ResultHandler, DetailContract.View<Drug> {


    @Bind(R.id.content_frame)
    FrameLayout mContentFrame;

    private ZBarScannerView mZBarScannerView;

    private DrugCodePresenter mDrugCodePresenter;

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zbar_scan);
        ButterKnife.bind(this);
        setUpToolbar("扫描条形码");
        mZBarScannerView = new ZBarScannerView(this);
        mZBarScannerView.setFormats(BarcodeFormat.ALL_FORMATS);
        mContentFrame.addView(mZBarScannerView);
        mDrugCodePresenter = new DrugCodePresenter();
        mDrugCodePresenter.bindView(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading_data));

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
        mProgressDialog.show();
        mDrugCodePresenter.setCode(result.getContents());
        mDrugCodePresenter.getDeatil();
    }

    @Override
    public void onStopLoading() {
        mProgressDialog.dismiss();
    }

    @Override
    public void onGetDetail(Drug detail) {
        mProgressDialog.dismiss();
        Intent intent = new Intent(this, DrugDetailActivity.class);
        intent.putExtra(Constants.EXTRA_DRUG_ID, detail.getId());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDrugCodePresenter.destroy();
    }
}
