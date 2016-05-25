package com.github.mrtan.myfrendlydemo.data.model;


import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public abstract class Data {
    //"children": [{...}]
    public abstract List<Children> children();
}
