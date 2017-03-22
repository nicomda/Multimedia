package org.danico.whoru;

/**
 * Created by nicomda on 22/3/17.
 */

public class TeacherData {
    public enum infoType {
        TWITTER,
        WEBSITE,
        MAIL,
        ADDITIONALINFO,
        OFFICE,
        DEPARTMENT
    }

    public String info;
    infoType type;


    TeacherData(String info, infoType type) {
        this.info = info;
        this.type = type;
    }

    public infoType getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setType(infoType type) {
        this.type = type;
    }
}
