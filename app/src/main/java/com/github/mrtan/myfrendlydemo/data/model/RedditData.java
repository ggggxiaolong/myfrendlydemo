package com.github.mrtan.myfrendlydemo.data.model;

import org.immutables.value.Value;

@Value.Immutable
public abstract class RedditData {

/** "kind": "Listing",
  * "data": {...}
 */
    public abstract Data data ();
    public abstract String kind ();
}
