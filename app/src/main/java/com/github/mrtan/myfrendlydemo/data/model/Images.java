package com.github.mrtan.myfrendlydemo.data.model;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Images {

    //"source": {...}
    public abstract Image source();
}
