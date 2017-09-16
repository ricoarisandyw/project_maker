package com.mrabid.pro_maker;

import android.app.LauncherActivity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.style.IconMarginSpan;
import android.view.Menu;
import android.view.MenuItem;

import com.mrabid.pro_maker.Adapter.MyPagerAdapter;

public class Project extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout   tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle("MyTask");


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        final TabLayout.Tab zeroTab = tabLayout.newTab();
        //zeroTab.setText("Task");
        zeroTab.setIcon(R.drawable.ic_content_task_black_48dp);
        tabLayout.addTab(zeroTab);

        final TabLayout.Tab firstTab = tabLayout.newTab();
        //firstTab.setText("Group");
        firstTab.setIcon(R.drawable.ic_group_black_120dp);
        tabLayout.addTab(firstTab);

        TabLayout.Tab secondTab = tabLayout.newTab();
        //secondTab.setText("Personal");
        secondTab.setIcon(R.drawable.ic_chat_black_120dp);
        tabLayout.addTab(secondTab);

        TabLayout.Tab thirdTab = tabLayout.newTab();
        //thirdTab.setText("Projects");
        thirdTab.setIcon(R.drawable.ic_today_black_72dp);
        tabLayout.addTab(thirdTab);

        TabLayout.Tab fourthTab = tabLayout.newTab();
        //fourthTab.setText("Profil");
        fourthTab.setIcon(R.drawable.ic_account_circle_black_120dp);
        tabLayout.addTab(fourthTab);


        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    toolbar.setTitle("MyTask");
                }else if(tab.getPosition()==1){
                    toolbar.setTitle("Group");
                }else if(tab.getPosition()==2){
                    toolbar.setTitle("Message");
                }else if(tab.getPosition()==3){
                    toolbar.setTitle("MyProject");
                }else {
                    toolbar.setTitle("MyProfile");
                }
               viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });


    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_addTask:
                return false;
        }
        return false;
    }

}