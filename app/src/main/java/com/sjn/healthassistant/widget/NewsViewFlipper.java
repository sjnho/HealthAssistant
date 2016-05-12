package com.sjn.healthassistant.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

import com.sjn.healthassistant.util.LogUtil;
import com.sjn.healthassistant.util.ScreenUtil;

/**
 * Created by sjn on 16/5/2.
 */
public class NewsViewFlipper extends ViewFlipper implements NestedScrollingChild {


    private NestedScrollingChildHelper mChildHelper;

    private float xDown;
    private float xMove;


    private onItemClickListener onItemClickListener;

    public NewsViewFlipper(Context context) {
        this(context, null);
    }

    public NewsViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getRawX();
                LogUtil.d("xdown" + xDown);
                startNestedScroll(ViewCompat.SCROLL_AXIS_HORIZONTAL);
                getParent().requestDisallowInterceptTouchEvent(true);
                stopFlipping();
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = event.getRawX();
                break;
            case MotionEvent.ACTION_UP:
                float dx = xMove - xDown;
                LogUtil.d("xMove" + xMove);
                LogUtil.d("dx" + dx);
                if (xMove == 0) {
                    LogUtil.d("item click触发");
                    onItemClickListener.onClick(getDisplayedChild());
                } else {
                    if (dx > ScreenUtil.dp2px(getContext(), 100)) {
                        showPrevious();
                    } else if (dx < -ScreenUtil.dp2px(getContext(), 100)) {
                        showNext();
                    } else if (dx == 0) {
                        LogUtil.d("item click触发");
                        onItemClickListener.onClick(getDisplayedChild());
                    }
                }
                stopNestedScroll();
                startFlipping();
                xDown = 0;
                xMove = 0;
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return mChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return mChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        mChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return mChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    public interface onItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(NewsViewFlipper.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
