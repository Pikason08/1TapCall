package com.example.keigo.defencer.SelectGetContact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by keigo on 2016/04/07.
 */
public class SelectListAdapter /*extends BaseAdapter*/ {

    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<String> list;

    public SelectListAdapter(Context context){
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(ArrayList<String> list_item){
        this.list = list_item;
    }

//    @Override
//    public int getCount(){
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int position){
//        return list.get(position);
//    }

//    @Override
//    public long getItemId(int position){
//        return list.get(position);
//    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent){
//        convertView = layoutInflater.inflate(R.layout.list_item,parent,false);
//
//        ((TextView)convertView.findViewById(R.id.name)).setText(list.get(position));
//        ((TextView)convertView.findViewById(R.id.PhoneNumber)).setText(list.get(position));
//    }
}
