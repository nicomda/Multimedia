package org.danico.whoru;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by dcc00030 on 09/03/2017.
 */

public class Teacher extends RealmObject {
    public static final String ID = "id", NAME = "name", FAV = "fav";
    @PrimaryKey
    private String id;
    private String name;
    private String department;
    private String office;
    private String twitter;
    private String web;
    private String email;
    private String additional_info;
    private Boolean fav;
    private byte[] image;

    public Teacher(String name, String department, String office, String email, String twitter, String web, String additional_info, Bitmap bm) {
        id = "T-" + UUID.randomUUID().toString();
        this.name = name;
        this.department = department;
        this.office = office;
        this.email = email;
        this.twitter = twitter;
        this.web = web;
        this.additional_info = additional_info;
        this.fav = false;
        if (bm != null) {
            setImage(bitmapToByteArray(bm));
        } else image = null;
    }

    public Teacher(Teacher teacher) {
        id = teacher.getId();
        name = teacher.getName();
        department = teacher.getDepartment();
        office = teacher.getOffice();
        twitter = teacher.getTwitter();
        web = teacher.getWeb();
        email = teacher.getEmail();
        additional_info = teacher.getAdditional_info();
        fav = teacher.isFav();
        image = teacher.getImage();
    }

    public Teacher() {

    }

    private byte[] bitmapToByteArray(Bitmap bm) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
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

    public Boolean isFav() {
        return fav;
    }

    public void setFav(Boolean fav) {
        this.fav = fav;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
