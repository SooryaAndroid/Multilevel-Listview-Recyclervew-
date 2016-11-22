package com.android.listviewrecycler;

import android.content.Context;

/**
 * Created by arunkumar on 21/11/16.
 */

public class Song {
    Context  context;
    String id;
    String songnames;
    String songcounts;

    public String getIds() {
        return id;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSongnames() {
        return songnames;
    }

    public void setSongnames(String songnames) {
        this.songnames = songnames;
    }

    public String getSongcounts() {
        return songcounts;
    }

    public void setSongcounts(String songcounts) {
        this.songcounts = songcounts;
    }
}
