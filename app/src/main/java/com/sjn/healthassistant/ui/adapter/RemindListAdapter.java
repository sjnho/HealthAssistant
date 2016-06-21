package com.sjn.healthassistant.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjn.healthassistant.R;
import com.sjn.healthassistant.pojo.DrugAlarm;
import com.sjn.healthassistant.util.DateUtil;
import com.sjn.healthassistant.util.ImageLoadUtil;

import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.labelview.LabelView;

/**
 * Created by sjn on 16/6/15.
 */
public class RemindListAdapter extends RecyclerView.Adapter<RemindListAdapter.RemindViewHolder> {


    public RemindListAdapter(List<DrugAlarm> data) {
        mData = data;
    }

    public List<DrugAlarm> getData() {
        return mData;
    }

    private List<DrugAlarm> mData;


    @Override
    public RemindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_remind, parent, false);
        return new RemindViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(RemindViewHolder holder, int position) {
        DrugAlarm drugAlarm = mData.get(position);
        holder.mDrugName.setText(drugAlarm.getDrug().getDrugName());
        ImageLoadUtil.loadDrug(drugAlarm.getDrug().getImg(), holder.mDrugImage);
        holder.mType.setText(drugAlarm.getType() == 0 ? "单次" : "重复");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(drugAlarm.getTime());
        String text = DateUtil.getDateText(calendar.getTime());
        if (drugAlarm.getType() == 0) {
            text = text.substring(0, 10);
        }
        holder.mTime.setText(text);
    }

    static class RemindViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.drug_image)
        ImageView mDrugImage;
        @Bind(R.id.drug_name)
        TextView mDrugName;
        @Bind(R.id.time)
        TextView mTime;
        @Bind(R.id.type)
        LabelView mType;

        public RemindViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
