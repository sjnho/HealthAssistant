package com.sjn.healthassistant.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.Constants;
import com.sjn.healthassistant.pojo.HealthLore;
import com.sjn.healthassistant.util.ImageLoadUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sjn on 16/5/4.
 */
public class HealthLoreAdapter extends RecyclerView.Adapter<HealthLoreAdapter.ViewHolder> {


    public HealthLoreAdapter() {
        mData = new ArrayList<>();
    }

    public List<HealthLore> getData() {
        return mData;
    }

    public void setData(List<HealthLore> data) {
        mData = data;
    }

    private List<HealthLore> mData;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_health_lore, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HealthLore lore = mData.get(position);
        ImageLoadUtil.loadImageCacheDisk(Constants.IMAGE + lore.getImg(), holder.loreImage);
        holder.loreTitle.setText(lore.getTitle());
        holder.loreDescription.setText(lore.getDescription());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.lore_image)
        ImageView loreImage;
        @Bind(R.id.lore_title)
        TextView loreTitle;
        @Bind(R.id.lore_description)
        TextView loreDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
