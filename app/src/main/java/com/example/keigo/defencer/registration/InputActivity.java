package com.example.keigo.defencer.registration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.keigo.defencer.Main.MainActivity;
import com.example.keigo.defencer.SelectGetContact.SelectContactActivity;
import com.jp.keigo.dial.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by keigo on 2016/04/05.
 *
 */
public class InputActivity extends AppCompatActivity{

    public static void start(Activity  activity){
        Intent intent = new Intent(activity, InputActivity.class);
        activity.startActivity(intent);
    }

    private EditText inputNum;
    private EditText inputName;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        initToolbar();
        initRealmObject();
        setView();
    }

    private void initRealmObject(){
        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext())
                .name("contacts.realm").build();
        realm = Realm.getInstance(config);
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_input);
        toolbar.setTitle(getString(R.string.input_display));
    }

    private void setView(){
        Button select = (Button)findViewById(R.id.selectPhoneData);
        Button next = (Button)findViewById(R.id.next);
        inputNum = (EditText)findViewById(R.id.importantNum);
        inputName = (EditText)findViewById(R.id.importantName);

//        inputText.setInputType();

        next.setOnClickListener(go_next);
        select.setOnClickListener(go_select);

    }

    private void writeDataForRealm(String name, String num){
        realm.beginTransaction();
        Contacts contacts= realm.createObject(Contacts.class);
        contacts.setCallName(name);
        contacts.setCallNumber(num);
        realm.commitTransaction();
    }

    View.OnClickListener go_select = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setToast(R.string.loading_now);
            Intent intent = new Intent(InputActivity.this,SelectContactActivity.class);
            startActivity(intent);
        }
    };



    View.OnClickListener go_next = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String number_to_register = inputNum.getText().toString();
            String name_to_register = inputName.getText().toString();
            if (number_to_register.isEmpty()) {
                setToast(R.string.not_regist_num);
            }else if (name_to_register.isEmpty()) {
                setToast(R.string.not_regist_name);
            }else if (number_to_register.isEmpty() && name_to_register.isEmpty()) {
                setToast(R.string.not_regist);
            }else if (3 > number_to_register.length()){
                setToast(R.string.few_length);
            }else {
                number_to_register = getResources().getString(R.string.add_tel) + number_to_register;
                writeDataForRealm(name_to_register, number_to_register);
                setToast(R.string.registrationed);
                Intent intent = new Intent(InputActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    private void setToast(int message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
