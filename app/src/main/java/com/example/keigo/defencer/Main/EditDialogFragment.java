package com.example.keigo.defencer.Main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Toast;
import com.example.keigo.defencer.registration.Contacts;
import com.jp.keigo.dial.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

/**
 * Created by keigo on 2016/04/08.
 *
 */
public class EditDialogFragment extends DialogFragment {

    private int position;
    private String name;
    private String num;
    private ArrayList<Registrant> mDataSet;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        CharSequence[] items = {"削除", "戻る"};


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        deleteRealmData();
                        break;
                    case 1:
                        dismiss();
                        break;
                }
            }
        });
        return builder.create();
    }

    //choose item all data
    public void setData(ArrayList<Registrant> mDataSet, int position, String name, String num) {
        this.position = position;
        this.name = name;
        this.num = num;
        this.mDataSet = mDataSet;
    }

    private void deleteRealmData() {
        try {
            RealmConfiguration config = new RealmConfiguration.Builder(getContext()).name("contacts.realm").build();
            Realm realm = Realm.getInstance(config);
            RealmQuery<Contacts> query = realm.where(Contacts.class);
            RealmResults<Contacts> results = query.findAll();

            realm.beginTransaction();

            for (int i = 0; i < results.size(); i++) {
                Contacts mContacts = results.get(i);
                if (name.equals(mContacts.getCallName()) && num.equals(mContacts.getCallNumber())) {
                    results.deleteFromRealm(i);
                    break;
                }
            }
            realm.commitTransaction();
            Log.d("check", "remove success is " + name);
        } catch (RealmException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), R.string.error_delete, Toast.LENGTH_SHORT).show();
        }
    }

}
