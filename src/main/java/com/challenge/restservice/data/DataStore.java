package com.challenge.restservice.data;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public class DataStore {

    static DataStore store = new DataStore();

    ConcurrentMap<String, Receipt> data;

    private DataStore() {

    }

    Receipt find(String s) {
        if (s == null) {
            return null;
        }

        return data.get(s);
    }

    boolean store(Receipt r) {
        if (r == null) {
            return false;
        }

        if (r.id == null) {
            String newId = UUID.randomUUID().toString();
            r.id = newId;
        }

        data.put(r.id, r);
        return false;
    }

}
