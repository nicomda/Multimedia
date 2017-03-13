package org.danico.whoru;

import java.util.UUID;

import io.realm.RealmObject;

/**
 * Created by dcc00030 on 09/03/2017.
 */

public class Teacher extends RealmObject {
    private String id;
    private String name;
    private String department;
    private String office;
    private String twitter;
    private String web;
    private String email;
    private String additional_info;
    private boolean fav;

    public Teacher(String _name, String _department, String _office, String _email, String _twitter, String _web, String _additional_info, boolean _fav) {
        id = "T-" + UUID.randomUUID().toString();
        name = _name;
        department = _department;
        office = _office;
        email = _email;
        twitter = _twitter;
        web = _web;
        additional_info = _additional_info;
        fav = false;
    }

    public Teacher(Teacher _teacher) {
        id = _teacher.getId();
        name = _teacher.getName();
        department = _teacher.getDepartment();
        office = _teacher.getOffice();
        twitter = _teacher.getTwitter();
        web = _teacher.getWeb();
        email = _teacher.getEmail();
        additional_info = _teacher.getAdditional_info();
        fav = _teacher.isFav();

    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdditional_info() {
        return additional_info;
    }

    public void setAdditional_info(String additional_info) {
        this.additional_info = additional_info;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}
