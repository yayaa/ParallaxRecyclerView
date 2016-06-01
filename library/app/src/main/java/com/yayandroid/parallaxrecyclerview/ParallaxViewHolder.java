package com.yayandroid.parallaxrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yahyabayramoglu on 15/04/15.
 */
public abstract class ParallaxViewHolder extends RecyclerView.ViewHolder implements ParallaxImageView.ParallaxImageListener {

    private ParallaxImageView backgroundImage;

    public abstract int getParallaxImageId();

    public ParallaxViewHolder(View itemView) {
        super(itemView);

        backgroundImage = (ParallaxImageView) itemView.findViewById(getParallaxImageId());
        backgroundImage.setListener(this);
    }

    @Override
    public int[] requireValuesForTranslate() {
        if (itemView.getParent() == null) {
            // Not added to parent yet!
            return null;
        } else {
            int[] itemPosition = new int[2];
            itemView.getLocationOnScreen(itemPosition);

            int[] recyclerPosition = new int[2];
            ((RecyclerView) itemView.getParent()).getLocationOnScreen(recyclerPosition);

            return new int[]{itemView.getMeasuredHeight(), itemPosition[1], ((RecyclerView) itemView.getParent()).getMeasuredHeight(), recyclerPosition[1]};
        }
    }

    public void animateImage() {
        getBackgroundImage().doTranslate();
    }

    public ParallaxImageView getBackgroundImage() {
        return backgroundImage;
    }
}
