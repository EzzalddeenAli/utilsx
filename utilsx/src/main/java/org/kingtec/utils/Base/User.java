package org.kingtec.utils.Base;

/**
 * Created by Administrator on 23/03/2019.
 */


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

public class User {
    @StringDef({ADMIN, PARENT, SCHOOL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface UserType {
    }

    public static final String ADMIN = "admin";
    public static final String PARENT = "parent";
    public static final String TEACHER = "teacher";
    public static final String STUDENT = "student";
    public static final String SCHOOL = "admin";

    private String id, name, pass, type, email;

    public static final String UNV_USER = "UNV_USER";

    boolean active = true;

    public boolean isActive() {
        return active;
    }

    public User setActive(boolean active) {
        this.active = active;
        return this;
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public User setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public String getType() {
        return type;
    }

    public User setType(@UserType String type) {
        this.type = type;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

}
