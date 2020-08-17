package org.kingtec.utils;

import android.view.View;


public class Sh {
    String title;
    String text;
    int view;
    View v;
    int type = 0;
    public int stype = 1;
    public int postion = 0;


    public String getText() {
        return text;
    }

    public Sh text(String text) {
        this.title = text;
        return this;
    }
    public Sh d(String text) {
        this.title = text;
        return this;
    }

    public int getView() {
        return view;
    }

    public View getV() {
        return v;
    }

    public static Sh view(View view) {
        return new Sh().setView(view);
    }

    public static Sh v(View view) {
        return new Sh().setView(view);
    }

    public static Sh view(int view) {
        return new Sh().setView(view);
    }
    public static Sh v(int view) {
        return new Sh().setView(view);
    }

    private Sh setView(int view) {
        this.stype = 1;
        this.view = view;
        return this;
    }

    public Sh withView(View view) {
        this.stype = 2;
        this.v = view;
        return this;
    }

    private Sh setView(View view) {
        this.stype = 2;
        this.v = view;
        return this;
    }

    private Sh postion(int postion) {
        this.postion = postion;
        return this;
    }

    public int getType() {
        return type;
    }

    public Sh as_nonStyle() {
        this.type = 0;
        return this;
    }

    public Sh as_materialStyle() {
        this.type = 1;
        return this;
    }

    public Sh as_newStyle() {
        this.type = 2;
        return this;
    }

    public Sh as_holoStyle() {
        this.type = 3;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Sh title(String title) {
        this.text = title;
        return this;
    }
    public Sh t(String title) {
        this.text = title;
        return this;
    }
}
