package com.example.keigo.defencer.Main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.keigo.defencer.registration.InputActivity;

/**
 * Created by keigo on 2016/04/08.
 */
public class DialogFragment_list extends android.app.DialogFragment {

    Activity mActivity;


    public DialogFragment_list(Activity activity){
        mActivity = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        CharSequence[] items = {"登録した番号の変更","戻る"};


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        Intent intent = new Intent(getActivity(),InputActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        dismiss();
                        break;
                }
            }
        });
        return builder.create();
    }
}
