package org.kingtec.utils.Base;

import org.kingtec.utils.OnObjectClickListener;

/**
 * Created by Administrator on 26/03/2019.
 */

public class Tag {
    public String name = "";
    public String subName = "";
    public String desc = "";
    public int image;
    public int badge = 0;
    public int color = 1;


    OnObjectClickListener onObjectClickListener;

    public Tag() {
    }

    public OnObjectClickListener getOnObjectClickListener() {
        return onObjectClickListener;
    }

    public Tag(String name, int image, int color, int badge, OnObjectClickListener onObjectClickListener) {
        this.name = name;
        this.image = image;
        this.color = color;
        this.badge = 0;
        this.onObjectClickListener = onObjectClickListener;
    }

    public Tag(String name, String subName, int image, OnObjectClickListener onObjectClickListener) {
        this.name = name;
        this.image = image;
        this.subName = subName;
        this.badge = 0;
        this.onObjectClickListener = onObjectClickListener;
    }


    public int getColor() {
        return color;
    }

    public Tag click(OnObjectClickListener onObjectClickListener) {
        this.onObjectClickListener = onObjectClickListener;
        return this;
    }

    public Tag color(int color) {
        this.color = color;
        return this;
    }

    public Tag badge(int badge) {
        this.badge = badge;
        return this;
    }

    public Tag desc(String desc) {
        this.desc = desc;
        return this;
    }

    public Tag desc() {
        this.desc = this.subName;
        return this;
    }

    public Tag subName(String subName) {
        this.subName = subName;
        return this;
    }

    public Tag name(String name) {
        this.name = name;
        return this;
    }

    public Tag image(int image) {
        this.image = image;
        return this;
    }

    public Tag(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }


    public String getDesc() {
        return desc;
    }

    public int getImage() {
        return image;
    }


}
