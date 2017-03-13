package org.danico.whoru;

import android.content.res.Resources;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by dcc00030 on 13/03/2017.
 */

public class Database {
    private Realm realm;

    public Database() {
        realm = Realm.getDefaultInstance();
    }

    public void insertTeacher(Teacher teacher) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(teacher);
        realm.commitTransaction();
    }

    public void deleteTeacher(Teacher teacher) {
        realm.beginTransaction();
        //Make the query.
        RealmResults<Teacher> realmResults = realm.where(Teacher.class).equalTo(Teacher.ID, teacher.getId()).findAll();


        //Remove elements.
        realmResults.deleteAllFromRealm();

        //Commit changes.
        realm.commitTransaction();
    }

    public Teacher getTeacher(String id) {
        realm.beginTransaction();
        RealmResults<Teacher> realmResults = realm.where(Teacher.class).equalTo(Teacher.ID, id).findAll();
        realm.commitTransaction();
        return realmResults.get(1);

    }

    public ArrayList<Teacher> getAllTeacher() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

            }
        });
        RealmResults<Teacher> realmResults = realm.where(Teacher.class).findAll();
        teachers.addAll(realm.copyFromRealm(realmResults));
        return teachers;
    }


}
