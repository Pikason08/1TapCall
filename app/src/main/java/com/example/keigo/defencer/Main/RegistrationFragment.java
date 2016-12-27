package com.example.keigo.defencer.Main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.keigo.defencer.registration.Contacts;
import com.jp.keigo.dial.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by keigo on 2016/08/13.
 * <p>
 * this is ViewPager's item
 * User can registration item 6 data
 */
public class RegistrationFragment extends Fragment {

    private ArrayList<Registrant> mDataset = new ArrayList<Registrant>();
    private Realm realm;

    @Override
    public void onCreate(Bundle savedInstate) {
        super.onCreate(savedInstate);

        initRealmObject();
    }

    private void initRealmObject() {
        RealmConfiguration config = new RealmConfiguration.Builder(getContext()).name("contacts.realm").build();
        realm = Realm.getInstance(config);
    }

    private void setView(View view) {
        RecyclerView.LayoutManager mLayoutManager;
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_registration);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        getData_Realm();
        if (mDataset != null) {
            RecyclerView.Adapter mAdapter = new ContactListAdapter(mDataset);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    callFunc(mDataset.get(position).getNumber());
                }

                @Override
                public void onItemLongClick(View view1, int position) {
                    String name = mDataset.get(position).getName();
                    String num = mDataset.get(position).getNumber();
                    EditDialogFragment dialog = new EditDialogFragment();
                    dialog.setData(mDataset, position, name, num);
                    dialog.show(getFragmentManager(), getTag());
                    removeItem(position);
                }
            }));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_page_item2, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setView(view);
    }

    protected void deleteItem(int position) {
        mDataset.remove(position);
    }

    public final void removeItem(int position){
        mDataset.remove(position);
    }

    private void getData_Realm() {
        RealmQuery<Contacts> query = realm.where(Contacts.class);
        RealmResults<Contacts> results = query.findAll();
        for (int i = 0; i < results.size(); i++) {
            Contacts contacts = results.get(i);
            Registrant content = new Registrant();
            content.setName(contacts.getCallName());
            content.setNumber(contacts.getCallNumber());
            mDataset.add(content);
        }
    }

    private void callFunc(String number) {
        Uri mUri = Uri.parse(number);
        Intent mIntent = new Intent(Intent.ACTION_DIAL, mUri);
        startActivity(mIntent);
    }
}
