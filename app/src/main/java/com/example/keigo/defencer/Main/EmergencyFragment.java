package com.example.keigo.defencer.Main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.jp.keigo.dial.R;

import java.util.ArrayList;

/**
 * Created by keigo on 2016/08/13.
 *
 */
public class EmergencyFragment extends Fragment {


    private ArrayList<Registrant> mDataset;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstate){
        super.onCreate(savedInstate);

    }

    private void setView(View view){
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager mLayoutManager;
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView_emergency);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mDataset = new ArrayList<>();
        String[] emergency_dial = getResources().getStringArray(R.array.emergency_contact_name); //name
        String[] emergency_address = getResources().getStringArray(R.array.emergency_contact_address); // num
        for (int i = 0;i < emergency_dial.length;i++){
            Registrant mRegistrant = new Registrant();
            mRegistrant.setName(emergency_dial[i]);
            mRegistrant.setNumber(emergency_address[i]);
            mDataset.add(mRegistrant);
        }
        mAdapter = new ContactListAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                callFunc(mDataset.get(position).getNumber());
            }

            @Override
            public void onItemLongClick(View view1, int position){
                //no action
            }
        }));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View mView = inflater.inflate(R.layout.view_page_item1, null);
        setView(mView);

        return mView;
    }

    private void callFunc(String number){
        Uri mUri = Uri.parse(number);
        Intent mIntent = new Intent(Intent.ACTION_DIAL, mUri);
        startActivity(mIntent);
    }
}
