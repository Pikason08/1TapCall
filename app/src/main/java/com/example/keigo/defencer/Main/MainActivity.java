package com.example.keigo.defencer.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.keigo.defencer.CallNumber;
import com.example.keigo.defencer.registration.InputActivity;
import com.example.keigo.defencer.R;

public class MainActivity extends AppCompatActivity  {

    private String CallNum;
    private String CallName;
//    private LocationManager locationManager ;

    CallNumber callNumber;
//    GestureDetector detector = new GestureDetector(getApplicationContext(),);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);

        getAllData();
        setViews();
    }

    private void getAllData(){
        SharedPreferences SaveData = getSharedPreferences("save",Context.MODE_PRIVATE);
        CallNum = SaveData.getString("importantNum","none");
        CallName = SaveData.getString("importantName",getString(R.string.first_regist));
    }

    protected void setViews(){
        Button zero = (Button)findViewById(R.id.zero);
        Button one = (Button)findViewById(R.id.one);
        Button two = (Button)findViewById(R.id.two);
        Button three = (Button)findViewById(R.id.three);
        Button four = (Button)findViewById(R.id.four);
        Button five = (Button)findViewById(R.id.five);

        zero.setOnClickListener(go_zero);
        one.setOnClickListener(go_one);
        two.setOnClickListener(go_two);
        three.setOnClickListener(go_three);
        four.setOnClickListener(go_four);
        five.setOnClickListener(go_five);
        zero.setOnLongClickListener(change_data);
        zero.setText(CallName);
    }

    private void callFunc(String CallNum){
        Uri uri = Uri.parse(CallNum);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }



    View.OnLongClickListener change_data = new View.OnLongClickListener(){
        @Override
        public boolean onLongClick(View v){

            DialogFragment_list dialog = new DialogFragment_list();
            dialog.show(getFragmentManager(),"dialog");

            return false;
        }
    };


    View.OnClickListener go_zero = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (CallNum.equals("none")){
                Intent intent = new Intent(MainActivity.this,InputActivity.class);
                startActivity(intent);
            }else {
                callFunc(CallNum);
            }
        }
    };

    View.OnClickListener go_one = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            callFunc(getString(R.string.fire_num));
        }
    };

    View.OnClickListener go_two = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            callFunc(getString(R.string.police_num));
        }
    };

    View.OnClickListener go_three = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            callFunc(getString(R.string.coast_guard_num));
        }
    };

    View.OnClickListener go_four = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            callFunc(getString(R.string.help_children_num));
        }
    };

    View.OnClickListener go_five = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            callFunc(getString(R.string.test_num));
        }
    };
}
