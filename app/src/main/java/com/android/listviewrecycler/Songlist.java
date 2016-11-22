package com.android.listviewrecycler;

import android.content.Context;

/**
 * Created by arunkumar on 21/11/16.
 */

public class Songlist {

    Context context;

    String idno;
    String no;
    String songnamess;
    String duration;

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSongnamess() {
        return songnamess;
    }

    public void setSongnamess(String songnamess) {
        this.songnamess = songnamess;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
