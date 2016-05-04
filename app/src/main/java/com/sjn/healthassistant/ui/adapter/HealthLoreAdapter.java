package com.sjn.healthassistant.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sjn.healthassistant.R;
import com.sjn.healthassistant.pojo.HealthLore;

import java.util.List;

/**
 * Created by sjn on 16/5/4.
 */
public class HealthLoreAdapter extends RecyclerView.Adapter<HealthLoreAdapter.ViewHolder> {

    public List<HealthLore> getData() {
        return mData;
    }

    private List<HealthLore> mData;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_health_lore, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
