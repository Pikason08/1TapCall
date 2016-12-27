package com.example.keigo.defencer.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.jp.keigo.dial.R;


/**
 * Created by keigo on 2016/08/13.
 *
 */
public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    public FragmentPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                return new EmergencyFragment();
            case 1:
                return new RegistrationFragment();
        }
        return null;
    }

    @Override
    public int getCount(){
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position){
        String title = String.valueOf(R.string.app_name);
        switch (position){
            case 0:
                title = "緊急発信";
                break;
            case 1:
                title = "登録連絡先";
                break;
        }
        return title;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);

        if (position <= getCount()) {
            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commit();
        }
    }
}
