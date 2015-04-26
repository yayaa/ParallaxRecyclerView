package com.yayandroid.parallaxrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by yahyabayramoglu on 15/04/15.
 */
public class ParallaxImageView extends ImageView {

    private final float DEFAULT_PARALLAX_RATIO = 1.2f;
    private float parallaxRatio = DEFAULT_PARALLAX_RATIO;
    private boolean needToTranslate = true;
    private ParallaxImageListener listener;

    private int rowHeight = -1;
    private int rowYPos = -1;
    private int recyclerViewHeight = -1;
    private int recyclerViewYPos = -1;

    public interface ParallaxImageListener {
        public int[] requireValuesForTranslate();
    }

    public ParallaxImageView(Context context) {
        super(context);
        init(context, null);
    }

    public ParallaxImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ParallaxImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setScaleType(ImageView.ScaleType.MATRIX);

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ParallaxImageView, 0, 0);
            this.parallaxRatio = ta.getFloat(R.styleable.ParallaxImageView_parallax_ratio, DEFAULT_PARALLAX_RATIO);
            ta.recycle();
        }
    }

    /**
     * This trick was needed because there is no way to detect when image is displayed,
     * we need to translate image for very first time as well. This will be needed only
     * if you are using async image loading...
     *
     * # If only there was another way to get notified when image has displayed.
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (needToTranslate)
            needToTranslate = !doTranslate();
    }

    /**
     * Notify this view when it is back on recyclerView, so we can reset.
     */
    public void reuse() {
        needToTranslate = true;
    }

    public synchronized boolean doTranslate() {
        if (getDrawable() == null) {
            return false;
        }

        if (getListener() != null) {
            getValues();
            calculateAndMove();
        } else {
            return false;
        }

        return true;
    }

    public void setParallaxRatio(float parallaxRatio) {
        this.parallaxRatio = parallaxRatio;
    }

    public ParallaxImageListener getListener() {
        return listener;
    }

    public void setListener(ParallaxImageListener listener) {
        this.listener = listener;
    }

    private void getValues() {
        int[] values = getListener().requireValuesForTranslate();
        this.rowHeight = values[0];
        this.rowYPos = values[1];
        this.recyclerViewHeight = values[2];
        this.recyclerViewYPos = values[3];
    }

    private void calculateAndMove() {
        float distanceFromCenter = (recyclerViewYPos + recyclerViewHeight) / 2 - rowYPos;
        int imageHeight = getDrawable().getIntrinsicHeight();
        float difference = imageHeight - rowHeight;
        float move = (distanceFromCenter / recyclerViewHeight) * difference * parallaxRatio;

        moveTo((move / 2) - (difference / 2));
    }

    private void moveTo(float move) {
        Matrix imageMatrix = getImageMatrix();
        float[] matrixValues = new float[9];
        imageMatrix.getValues(matrixValues);
        float current = matrixValues[Matrix.MTRANS_Y];
        imageMatrix.postTranslate(0, move - current);
        setImageMatrix(imageMatrix);
        invalidate();
    }

}
