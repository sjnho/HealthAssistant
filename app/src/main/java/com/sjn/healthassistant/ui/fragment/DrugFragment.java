package com.sjn.healthassistant.ui.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sjn.healthassistant.R;
import com.sjn.healthassistant.ui.activity.DrugSearchActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sjn on 16/4/8.
 */
public class DrugFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_drug, container, false);
        ButterKnife.bind(this, fragment);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        Intent intent = new Intent(getActivity(), DrugSearchActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(0, 0);
    }
}
