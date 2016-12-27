package com.example.keigo.defencer.Main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by keigo on 2016/08/16.
 * 
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector mGestureDetector;
    private OnItemClickListener mListener;

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, final OnItemClickListener listener){
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e){
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && listener!= null){
                    listener.onItemLongClick(childView, recyclerView.getChildPosition(childView));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent event) {
        View childView = view.findChildViewUnder(event.getX(), event.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(event)) {
            childView.setPressed(true);
            mListener.onItemClick(childView, view.getChildPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        // Do nothing
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}
