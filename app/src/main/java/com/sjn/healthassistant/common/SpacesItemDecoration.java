package com.sjn.healthassistant.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sjn.healthassistant.util.ScreenUtil;

/**
 * Created by sjn on 16/5/21.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    public SpacesItemDecoration(int dp, Context context) {
        space = ScreenUtil.dp2px(context, dp);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space;
        }
    }

}
