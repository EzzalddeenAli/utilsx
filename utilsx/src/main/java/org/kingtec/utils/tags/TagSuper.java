package org.kingtec.utils.tags;

import android.app.Activity;
import android.view.View;

import org.kingtec.utils.EzzGuideView;
import org.kingtec.utils.OnObjectClickListener;
import org.kingtec.utils.Sh;
import org.kingtec.utils.showcaseezz.config.DismissType;
import org.kingtec.utils.showcaseezz.config.Gravity;
import org.kingtec.utils.showcaseezz.listener.GuideListener;


/**
 * Created by Administrator on 26/03/2019.
 */

public class TagSuper {
    public String name = "";
    public String subName = "";
    public String desc = "";
    public int image;
    public int badge = 0;
    public int color = 1;

    public static void showGuideView(Activity axm, Sh sh) {
        new EzzGuideView.Builder(axm)
                .setTitle(sh.getText())
                .setContentText(sh.getTitle())
                .hideNext()
                .setGravity(Gravity.auto)
                .setTargetView((sh.stype == 2) ? sh.getV() : axm.findViewById(sh.getView()))
                .setDismissType(DismissType.anywhere) //optional - default dismissible by TargetView
                .setGuideListener(new GuideListener() {
                    @Override
                    public void onDismiss(View view) {
                        //showToast("onDismiss");
                    }

                    @Override
                    public void onClose(View view) {
                    }
                })
                .build()
                .show();
    }

    OnObjectClickListener onObjectClickListener;

    public OnObjectClickListener getOnObjectClickListener() {
        return onObjectClickListener;
    }

    public TagSuper() {
    }

    public TagSuper(String name, int image, int color, int badge, OnObjectClickListener onObjectClickListener) {
        this.name = name;
        this.image = image;
        this.color = color;
        this.badge = 0;
        this.onObjectClickListener = onObjectClickListener;
    }

    public TagSuper(String name, String subName, int image, OnObjectClickListener onObjectClickListener) {
        this.name = name;
        this.image = image;
        this.subName = subName;
        this.badge = 0;
        this.onObjectClickListener = onObjectClickListener;
    }


    public int getColor() {
        return color;
    }

    TagSuper click(OnObjectClickListener onObjectClickListener) {
        this.onObjectClickListener = onObjectClickListener;
        return this;
    }


    TagSuper color(int color) {
        this.color = color;
        return this;
    }

    TagSuper badge(int badge) {
        this.badge = badge;
        return this;
    }

    public TagSuper desc(String desc) {
        this.desc = desc;
        return this;
    }

    TagSuper desc() {
        this.desc = this.subName;
        return this;
    }

    TagSuper subName(String subName) {
        this.subName = subName;
        return this;
    }

    String getSubName() {
        return subName;
    }

    public static TagSuperBulder.SubNameBulder name(String name) {
        return new TagSuperBulder().name(name);
    }

    TagSuper sname(String name) {
        this.name = name;
        return this;
    }

    TagSuper image(int image) {
        this.image = image;
        return this;
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