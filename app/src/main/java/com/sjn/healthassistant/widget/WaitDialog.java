package com.sjn.healthassistant.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sjn.healthassistant.R;

/**
 * Created by Sjn on 16/5/3.
 * Email:sjn@bombvote.com
 */
public class WaitDialog extends DialogFragment {


    @Override
    public void onStart() {
        super.onStart();
        if(getDialog() != null){
            getDialog().getWindow().setBackgroundDrawable(null);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_wait,container,false);
    }
}
