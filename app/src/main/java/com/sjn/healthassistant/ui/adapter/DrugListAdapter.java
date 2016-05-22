package com.sjn.healthassistant.ui.adapter;

import android.content.Context;
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

    private Context mContext;

    public DrugListAdapter() {
        mDrugs = new ArrayList<>();
    }

    public void appendData(List<Drug> data) {
        if (mDrugs.isEmpty() && !data.isEmpty() && mDrugs.get(mDrugs.size() - 1).getId() == data.get(data.size() - 1).getId()) {
            return;
        }
        mDrugs.addAll(data);
    }

    public void addData(List<Drug> data) {
        if (mDrugs.isEmpty() && !data.isEmpty() && mDrugs.get(0).getId() == data.get(0).getId()) {
            return;
        }
        mDrugs.addAll(0, data);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drug, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Drug drug = mDrugs.get(position);
        ImageLoadUtil.loadDrug(drug.getImg(), holder.mDrugImage);
        String description = "适应症:"+drug.getSyz()+"主治疾病"+drug.getZzjb();
        holder.mDrugDescription.setText(description);
        holder.mDrugName.setText(drug.getDrugName());
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
