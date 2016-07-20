package com.example.keigo.defencer.registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.keigo.defencer.Main.MainActivity;
import com.example.keigo.defencer.R;
import com.example.keigo.defencer.SelectGetContact.SelectContactActivity;

/**
 * Created by keigo on 2016/04/05.
 *
 */
public class InputActivity extends AppCompatActivity{

    EditText inputNum;
    EditText inputName;
    SharedPreferences SaveData;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        setView();
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

    View.OnClickListener go_select = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(InputActivity.this,SelectContactActivity.class);
            startActivity(intent);
        }
    };



    View.OnClickListener go_next = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SaveData = getSharedPreferences("save", Context.MODE_PRIVATE);
            editor = SaveData.edit();
            inputNum.selectAll();
            inputName.selectAll();
            String getContent = "tel:";
            String getInputData = inputNum.getText().toString();
            String getInputDataName = inputName.getText().toString();
            if (getInputData.isEmpty()) {
                setToast(R.string.not_regist_num);
            }else if (getInputDataName.isEmpty()) {
                setToast(R.string.not_regist_name);
            }else if (getInputData.isEmpty() && getInputDataName.isEmpty()){
                setToast(R.string.not_regist);
            }else {
                getContent += getInputData;
                Toast.makeText(InputActivity.this,""+getContent,Toast.LENGTH_SHORT).show();
                editor.putString("importantNum",getContent);
                editor.putString("importantName",getInputDataName);
                Intent intent = new Intent(InputActivity.this,MainActivity.class);
                editor.apply();
                startActivity(intent);
                finish();
            }
        }
    };

    private void setToast(int message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
