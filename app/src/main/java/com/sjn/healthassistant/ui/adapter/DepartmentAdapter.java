package com.sjn.healthassistant.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sjn.healthassistant.R;
import com.sjn.healthassistant.pojo.Department;

import java.util.List;

/**
 * Created by sjn on 16/5/19.
 */
public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {


    public List<Department> getDatas() {
        return mDatas;
    }

    private List<Department> mDatas;

    public DepartmentAdapter(List<Department> datas) {
        mDatas = datas;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deapartment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Department department = mDatas.get(position);
        holder.mButton.setText(department.getName());

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        Button mButton;
        public ViewHolder(View itemView) {
            super(itemView);
            mButton = (Button) itemView.findViewById(R.id.department);
        }
    }

}
