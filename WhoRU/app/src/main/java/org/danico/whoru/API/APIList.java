package org.danico.whoru.API;

import java.util.ArrayList;

/**
 * Created by nicomda on 21/3/17.
 */

public class APIList {
    private ArrayList<String> subject_ids = new ArrayList<>();

    ArrayList<String> getSubject_ids() {
        return subject_ids;
    }

    public void setSubject_ids(ArrayList<String> names) {
        this.subject_ids = names;
    }

}
