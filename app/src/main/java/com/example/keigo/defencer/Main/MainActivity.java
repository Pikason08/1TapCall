package com.example.keigo.defencer.Main;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import com.example.keigo.defencer.registration.InputActivity;
import com.jp.keigo.dial.R;

public class MainActivity extends AppCompatActivity  {

    private FragmentPagerAdapter adapter;
    private FloatingActionButton floatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);
        setViews();
    }

    private void setViews(){
        floatButton = (FloatingActionButton)findViewById(R.id.floating_button);
        floatButton.setImageResource(R.drawable.ic_add_white_24dp);
        floatButton.setVisibility(View.INVISIBLE);
        floatButton.setOnClickListener(clickFav);
        FragmentManager manager = getSupportFragmentManager();
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPage);
        adapter = new FragmentPagerAdapter(manager);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout_main);
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(clickTab);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(MainActivity.this, R.color.underLine));
    }

    TabLayout.OnTabSelectedListener clickTab = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            if (tab.getPosition() == 0){
                floatButton.setVisibility(View.INVISIBLE);
            }else {
                floatButton.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    View.OnClickListener clickFav = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            InputActivity.start(MainActivity.this);
        }
    };
}
