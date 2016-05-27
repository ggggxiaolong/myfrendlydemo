package com.github.mrtan.myfrendlydemo.util;

import android.graphics.Bitmap;

import com.github.mrtan.myfrendlydemo.data.model.Image;
import com.squareup.picasso.Transformation;


public final class BitmapTransform implements Transformation{

    Image mImage;
    private int mMaxWidth,mMaxHeight,mTargetWidth, mTargetHeight;

    public BitmapTransform(Image image, int maxWidth, int maxHeight) {
        mImage = image;
        mMaxWidth = maxWidth;
        mMaxHeight = maxHeight;

        double aspectRatio;//纵横比
        if (image.width() >= image.height()){
            //宽度大于高度
            mTargetWidth = maxWidth;
            aspectRatio = (double)image.height() / image.width();
            mTargetHeight = (int) (mTargetWidth * aspectRatio);
        } else {
            mTargetHeight = maxHeight;
            aspectRatio = (double)image.width() / image.height();
            mTargetWidth = (int) (mTargetHeight * aspectRatio);
        }
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap target = Bitmap.createScaledBitmap(source, mTargetWidth, mTargetHeight, false);
        if (target != source) {
            source.recycle();
        }
        return target;
    }

    @Override
    public String key() {
        return mImage.url() + "_" +mTargetWidth +":" + mTargetHeight;
    }

    public int targetWidth() {
        return mTargetWidth;
    }

    public int targetHeight() {
        return mTargetHeight;
    }
}
