package com.example.keigo.defencer.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.jp.keigo.dial.R;

import java.util.ArrayList;

/**
 * Created by keigo on 2016/08/10
 * .
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ItemViewHolder> {

    private ArrayList<Registrant> mDataSet = new ArrayList<>();

    public ContactListAdapter(ArrayList<Registrant> mDataSet){
        this.mDataSet = mDataSet;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()) .inflate(R.layout.recycler_item, parent, false);
        return new ItemViewHolder(mView);

    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position){
        holder.mTextView.setText(mDataSet.get(position).getName());
    }

    @Override
    public int getItemCount(){
        return mDataSet.size();
    }

   public void removeFromDataset(int position){
        for(int i=0; i<mDataSet.size(); i++){
            if(i == position){
                mDataSet.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
   }

    static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public View view;

        ItemViewHolder(View view){
            super(view);
            this.view = view;
            mTextView = (TextView)view.findViewById(R.id.name);
        }
    }
}
