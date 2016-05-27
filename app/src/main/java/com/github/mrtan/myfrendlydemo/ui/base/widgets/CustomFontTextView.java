package com.github.mrtan.myfrendlydemo.ui.base.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.github.mrtan.myfrendlydemo.R;
import com.github.mrtan.myfrendlydemo.ui.utils.TypefaceCache;

import timber.log.Timber;

public class CustomFontTextView extends TextView{

    public CustomFontTextView(Context context) {
        super(context);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        this(context, attrs, R.style.TextView);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        setPaintFlags(getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);

        String fontName = null;
        TypedArray appearance = context.obtainStyledAttributes(attrs, R.styleable.CustomFontTextView);

        if (appearance != null) {
            int count = appearance.getIndexCount();
            for (int i = 0; i< count; i++) {
                int attr = appearance.getIndex(i);
                if (attr == R.styleable.CustomFontTextView_font){
                    fontName = appearance.getString(attr);
                }
            }
            appearance.recycle();
        }
        setTypeFace(fontName);
    }

    @Nullable
    public void setTypeFace(@Nullable String typeFaceName){
        if (typeFaceName == null) return;

        Context context = getContext();
        Typeface font = TypefaceCache.get(typeFaceName);
        if (font == null) {
            try {
                font = Typeface.createFromAsset(context.getAssets(), typeFaceName);
            } catch (Exception e) {
                Timber.e(CustomFontTextView.class.getSimpleName(),
                        String.format("Error loading font: %s. Reverting to system default.", typeFaceName));
                return;
            }
            TypefaceCache.put(typeFaceName,font);
            setTypeface(font);
        }
    }
}
