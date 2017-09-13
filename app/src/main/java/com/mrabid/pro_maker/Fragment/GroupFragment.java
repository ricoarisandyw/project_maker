package com.mrabid.pro_maker.Fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mrabid.pro_maker.AddTaskActivity;
import com.mrabid.pro_maker.Project;
import com.mrabid.pro_maker.R;
import com.mrabid.pro_maker.SettingActivity;

public class GroupFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.group_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_group,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                return true;

            case R.id.action_addTask:
                startActivity(new Intent(getActivity(), AddTaskActivity.class));
                return true;
        }
        return false;
    }

//    @Override
//    public void onResume(){
//        super.onResume();
//        ((Project) getActivity()).setActionBarTitle("Group");
//    }

}
