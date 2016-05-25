package com.github.mrtan.myfrendlydemo.data.model;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Children {
    //"data": {...}
    public abstract Post data();
}
