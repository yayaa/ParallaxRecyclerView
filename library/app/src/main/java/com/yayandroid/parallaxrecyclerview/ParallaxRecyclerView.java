package com.yayandroid.parallaxrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by yahyabayramoglu on 26/04/15.
 */
public class ParallaxRecyclerView extends RecyclerView {

    private OnScrollListener scrollListener;

    public ParallaxRecyclerView(Context context) {
        super(context);
        init();
    }

    public ParallaxRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ParallaxRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setOnScrollListener(defaultListener);
    }

    private OnScrollListener defaultListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (scrollListener != null)
                scrollListener.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            for (int i = 0; i < recyclerView.getChildCount(); i++) {
                ViewHolder viewHolder = recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
                if (viewHolder instanceof ParallaxViewHolder) {
                    ((ParallaxViewHolder) viewHolder).animateImage();
                }
            }

            if (scrollListener != null)
                scrollListener.onScrolled(recyclerView, dx, dy);
        }
    };

    @Override
    public void setOnScrollListener(OnScrollListener listener) {
        if (listener != defaultListener)
            this.scrollListener = listener;
        else
            super.setOnScrollListener(listener);
    }

}
