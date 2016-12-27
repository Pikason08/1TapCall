package com.example.keigo.defencer.registration;

import io.realm.RealmObject;

/**
 * Created by keigo on 2016/08/13.
 */
public class Contacts extends RealmObject{

    public String CallName;
    public String CallNumber;

    public String getCallName() {
        return CallName;
    }

    public void setCallName(String callName) {
        CallName = callName;
    }

    public String getCallNumber() {
        return CallNumber;
    }

    public void setCallNumber(String callNumber) {
        CallNumber = callNumber;
    }
}
