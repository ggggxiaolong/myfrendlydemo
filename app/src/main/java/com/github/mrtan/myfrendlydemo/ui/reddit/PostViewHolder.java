package com.github.mrtan.myfrendlydemo.ui.reddit;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import com.github.mrtan.myfrendlydemo.R;
import com.github.mrtan.myfrendlydemo.data.model.Image;
import com.github.mrtan.myfrendlydemo.data.model.ImmutableImage;
import com.github.mrtan.myfrendlydemo.data.model.Post;
import com.github.mrtan.myfrendlydemo.ui.base.BaseActivity;
import com.github.mrtan.myfrendlydemo.util.BitmapTransform;
import com.github.mrtan.myfrendlydemo.util.DeviceUtils;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class PostViewHolder extends RecyclerView.ViewHolder {

    private int mMaxHeight;
    private int mMaxWidth;
    private TextView mTitle;
    private ImageView mThumbnail;
    private Space mTopSpace;

    @Inject
    DeviceUtils mDecideUtils;

    @Inject
    Picasso mPicasso;

    public PostViewHolder(View itemView) {
        super(itemView);
        ((BaseActivity) itemView.getContext()).getActivityComponent().inject(this);
        performInjection(itemView);
        findViews(itemView);
        setMaxDimensions(itemView);
    }

    public void onBind(Post article) {
        mTitle.setText(article.title());
        showImage(article);
    }

    private void showImage(Post article) {
        Image image = ImmutableImage.
                builder().
                height(article.height()).
                width(article.width()).
                url(article.url()).
                build();
        BitmapTransform transform = new BitmapTransform(image, mMaxWidth, mMaxHeight);

        setSpacer(transform.targetHeight < transform.targetWidth);

        setThumbnail(transform.targetWidth, transform.targetHeight);
        mPicasso.load(article.url())
                .transform(transform)
                .resize(transform.targetWidth, transform.targetHeight)
                .centerInside()
                .placeholder(R.color.gray80)
                .into(mThumbnail);
    }


    private void setThumbnail(int targetWidth, int targetHeight) {
        mThumbnail.setMaxHeight(targetHeight);
        mThumbnail.setMaxWidth(targetWidth);
        mThumbnail.setMinimumHeight(targetHeight);
        mThumbnail.setMinimumWidth(targetWidth);
        mThumbnail.requestLayout();
    }

    private void setSpacer(boolean b){
        //如果宽小于高就补一个空白
        if (b){
            mTopSpace.setVisibility(View.GONE);
        } else {
            mTopSpace.setVisibility(View.VISIBLE);
        }
    }

    private void setMaxDimensions(View view) {
        int screenWidth = mDecideUtils.getScreenWidth();
        int screenHeight = mDecideUtils.getScreenHeight();

        if (screenWidth > screenHeight) {
            screenHeight = mDecideUtils.getScreenWidth();
            screenWidth = mDecideUtils.getScreenHeight();
        }

        mMaxHeight = (int) (screenHeight * .55f);
        int margin = view.getContext().getResources().getDimensionPixelSize(R.dimen.post_horizontal_margin);
        mMaxWidth = screenWidth - 2 * margin;
    }

    private void findViews(View view) {
        mTitle = ButterKnife.findById(view, R.id.title);
        mThumbnail = ButterKnife.findById(view, R.id.thumbnail);
        mTopSpace = ButterKnife.findById(view, R.id.topSpacer);
    }

    private void performInjection(View view) {
        ((BaseActivity) view.getContext())
                .getActivityComponent()
                .inject(this);
    }
}
