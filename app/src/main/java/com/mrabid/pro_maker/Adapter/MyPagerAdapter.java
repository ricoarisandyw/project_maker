package com.mrabid.pro_maker.Adapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.mrabid.pro_maker.Fragment.GroupFragment;
import com.mrabid.pro_maker.Fragment.MyProjectFragment;
import com.mrabid.pro_maker.Fragment.PersonalFragment;
import com.mrabid.pro_maker.Fragment.ProfilFragment;
import com.mrabid.pro_maker.Fragment.TaskFragment;


public class MyPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public MyPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TaskFragment tab1 = new TaskFragment();
                return tab1;
            case 1:
                GroupFragment tab2 = new GroupFragment();
                return tab2;
            case 2:
                PersonalFragment tab3 = new PersonalFragment();
                return tab3;
            case 3:
                MyProjectFragment tab4 = new MyProjectFragment();
                return tab4;
            case 4:
                ProfilFragment tab5 = new ProfilFragment();
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}