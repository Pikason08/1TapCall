package com.example.keigo.defencer.SelectGetContact;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.keigo.defencer.Main.MainActivity;
import com.example.keigo.defencer.registration.Contacts;
import com.jp.keigo.dial.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by keigo on 2016/04/06.
 */
public class SelectContactActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static void start(Activity activity){
        Intent mIntent = new Intent(activity, SelectContactActivity.class);
        activity.startActivity(mIntent);
    }

    ArrayAdapter<String> mAdapter = null; /* All Data add this array */
    ArrayAdapter<String> display = null;  /* Only name add this array .And this :vsrray use listView*/
    ContentResolver mResolver = null;

    private SearchView searchView;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_select);

        initToolBar();
        initRealmObject();
        setView();
    }

    private void initRealmObject() {
        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext())
                .name("contacts.realm").build();
        realm = Realm.getInstance(config);
    }

    protected void initToolBar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getString(R.string.choose_display));
        mToolbar.inflateMenu(R.menu.menu);
//        toolbar.setOnMenuItemClickListener(goSearch);
    }

    protected void setView() {
        AddData();
        ListView mListView = (ListView) findViewById(R.id.list);
        mListView.setAdapter(display);
        mListView.setOnItemClickListener(this);
    }


    private void AddData() {
        mAdapter = new ArrayAdapter<String>(SelectContactActivity.this, android.R.layout.simple_list_item_1);
        display = new ArrayAdapter<String>(SelectContactActivity.this, android.R.layout.simple_list_item_1);

        mResolver = getContentResolver();
        Cursor cursor = mResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            String id;
            String info;

            do {
                id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                info = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                display.add(info);
                String mPhoneNumber = getPhoneNumber(id);
                if (mPhoneNumber.isEmpty()) {
                    info += ",noNum";
                } else {
                    info += getPhoneNumber(id);
                }
                mAdapter.add(info);
            } while (cursor.moveToNext());
        }
        cursor.close();


    }

    private String getPhoneNumber(String id) {
        String phones = "";

        Cursor cursor = mResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                null,
                null
        );

        assert cursor != null;
        if (cursor.moveToFirst()) {
            do {
                phones += "," + cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return phones;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String content = mAdapter.getItem(position);
        if (!content.contains("noNum")) {
            String[] contents = content.split(",", 0);
            String setName = contents[0];
            String setNumber = "tel:" + contents[1];
            writeDataForRealm(setName, setNumber);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), R.string.not_regist_callNum, Toast.LENGTH_SHORT).show();
        }
    }

    private void writeDataForRealm(String name, String num) {
        realm.beginTransaction();
        Contacts contacts = realm.createObject(Contacts.class);
        contacts.setCallName(name);
        contacts.setCallNumber(num);
        realm.commitTransaction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    //    SearchView.OnQueryTextListener toolListener = new SearchView.OnQueryTextListener() {
//        @Override
//        public boolean onQueryTextSubmit(String s) {
//            return false;
//        }
//
//        @Override
//        public boolean onQueryTextChange(String s) {
//            return false;
//        }
//    };
//
//    Toolbar.OnMenuItemClickListener goSearch = new Toolbar.OnMenuItemClickListener() {
//        @Override
//        public boolean onMenuItemClick(MenuItem item) {
//            int id = item.getItemId();
//            if (id == R.id.part_search){
//                Log.d("check","search button");
//                return true;
//            }
//
//            return false;
//        }
//    };
}
