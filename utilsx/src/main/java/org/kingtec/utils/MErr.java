package org.kingtec.utils;


public class MErr {
    private String type = "";
    private String title = "";
    private String message = "";
    private String subTitle = "";

    public MErr type(String type) {
        this.type = type;
        return this;
    }

    public static MErr title(String type) {
        return new MErr().stitle(type);
    }

    private MErr stitle(String title) {
        this.title = title;
        return this;
    }

    public MErr message(String message) {
        this.message = message;
        return this;
    }

    public MErr subTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public String getMessage() {
        return message;
    }


    public String getType() {
        return type;
    }


    public MErr(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public MErr() {
    }

    public String getTitle() {
        return title;
    }


    public String getSubTitle() {
        return subTitle;
    }

}
