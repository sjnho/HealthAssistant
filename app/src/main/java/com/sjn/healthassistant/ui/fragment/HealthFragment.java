package com.sjn.healthassistant.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.contarct.ListContract;
import com.sjn.healthassistant.pojo.HealthClassify;
import com.sjn.healthassistant.pojo.HealthInfo;
import com.sjn.healthassistant.presenter.HealthPresenter;
import com.sjn.healthassistant.ui.activity.NewsActivity;
import com.sjn.healthassistant.util.ImageLoadUtil;
import com.sjn.healthassistant.util.LogUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by sjn on 16/4/21.
 */
public class HealthFragment extends Fragment implements ListContract.View<HealthInfo> {
    @Bind(R.id.flipper)
    ViewFlipper mFlipper;
    @Bind(R.id.container)
    LinearLayout mContainer;
    private ListContract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_health, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<HealthClassify> query = realm.where(HealthClassify.class);
        for (final HealthClassify healthClassify : query.findAll()) {
            View tabView = View.inflate(getContext(), R.layout.tv_tab, null);
            TextView textView = (TextView) tabView.findViewById(R.id.classify_name);
            textView.setText(healthClassify.getTitle());
            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.d(healthClassify.getTitle());
                }
            });
            mContainer.addView(tabView);
        }
        mPresenter = new HealthPresenter(this);
        mPresenter.pullDown();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setPresenter(ListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onPullDown(List<HealthInfo> data) {
        for (final HealthInfo hi : data) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoadUtil.loadImageCacheDisk(Constants.IMAGE + hi.getImg(), imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), NewsActivity.class);
                    intent.putExtra(Constants.EXTRA_HEALTH_INFO, new Gson().toJson(hi));
                    startActivity(intent);
                }
            });
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            mFlipper.addView(imageView);
        }

    }

    @Override
    public void onPullUp(List<HealthInfo> data) {

    }

    @Override
    public void stopLoading() {

    }


}
