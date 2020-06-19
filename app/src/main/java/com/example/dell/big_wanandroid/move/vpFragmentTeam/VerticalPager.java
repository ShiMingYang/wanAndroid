package com.example.dell.big_wanandroid.move.vpFragmentTeam;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Dell on 2019/5/3.
 */

public  class VerticalPager extends ViewPager {
    private VerticalPager.OnItemClickListener mOnItemClickListener;

    public VerticalPager(Context context) {
        super(context);
        init();
    }

    public VerticalPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setup();
    }

    private void init() {
        // The majority of the magic happens here
        setPageTransformer(true, new VerticalPageTransformer());
        // The easiest way to get rid of the overscroll drawing that happens on the left and right
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    private MotionEvent swapXY(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);

        return ev;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
        swapXY(ev); // return touch coordinates to original reference frame for any child views
        return intercepted;
    }

    private float scaleY;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        System.out.println("----------getY" + ev.getY());
        System.out.println("----------scaleY" + scaleY);
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {

        }
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            scaleY = ev.getY();
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (scaleY == ev.getY()) {
                System.out.println("------------------======");
                scaleY = 0;
                return false;
            }
        }
        try {
            return super.onTouchEvent(swapXY(ev));
        } catch (Exception e) {
        }
        return true;
    }
    //    点击事件
    public void setOnItemClickListener(VerticalPager.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = (OnItemClickListener) onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private void setup() {
        final GestureDetector tapGestureDetector = new GestureDetector(getContext(), new VerticalPager.TapGestureListener());

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tapGestureDetector.onTouchEvent(event);
                return false;
            }
        });
    }

    private class TapGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getCurrentItem());
            }
            return true;
        }
    }
}
