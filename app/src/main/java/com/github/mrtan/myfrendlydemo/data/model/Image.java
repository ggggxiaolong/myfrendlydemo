package com.github.mrtan.myfrendlydemo.data.model;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Image {

   /** "url": "https://...",
     * "width": 540,
     * "height": 960*/
    public abstract String url();
    public abstract int height();
    public abstract int width();
}
