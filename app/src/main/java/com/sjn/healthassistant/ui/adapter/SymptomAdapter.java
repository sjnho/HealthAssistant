package com.sjn.healthassistant.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.pojo.Symptom;
import com.sjn.healthassistant.util.ImageLoadUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sjn on 16/5/24.
 */
public class SymptomAdapter extends RecyclerView.Adapter<SymptomAdapter.ViewHolder> {


    public List<Symptom> getData() {
        return mData;
    }

    public void setData(List<Symptom> data) {
        mData = data;
    }

    private List<Symptom> mData;

    public SymptomAdapter() {
        mData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_symptom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Symptom symptom = mData.get(position);
        ImageLoadUtil.load16x9Image(Constants.IMAGE + symptom.getImg(), holder.mImage);
        holder.mDescription.setText(symptom.getMessage());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image)
        ImageView mImage;
        @Bind(R.id.description)
        TextView mDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
