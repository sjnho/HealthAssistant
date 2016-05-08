package com.sjn.healthassistant.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sjn.healthassistant.R;
import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.util.ImageLoadUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sjn on 16/5/8.
 */
public class DrugListAdapter extends RecyclerView.Adapter<DrugListAdapter.ViewHolder> {


    private List<Drug> mDrugs;

    public DrugListAdapter() {
        mDrugs = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drug, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Drug drug = mDrugs.get(position);
        ImageLoadUtil.loadImageCacheDisk(drug.getImg(), holder.mDrugImage);
        holder.mDrugDescription.setText(drug.getDescription());
        holder.mDrugName.setText(drug.getName());
    }

    @Override
    public int getItemCount() {
        return mDrugs.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.drug_image)
        ImageView mDrugImage;
        @Bind(R.id.drug_name)
        TextView mDrugName;
        @Bind(R.id.drug_description)
        TextView mDrugDescription;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public List<Drug> getDrugs() {
        return mDrugs;
    }
}