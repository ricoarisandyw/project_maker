package com.mrabid.pro_maker.Fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mrabid.pro_maker.Activity.AddTaskActivity;
import com.mrabid.pro_maker.Adapter.RecycleViewPersonalAdapter;
import com.mrabid.pro_maker.Model.Personal;
import com.mrabid.pro_maker.R;
import com.mrabid.pro_maker.Activity.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class GroupFragment extends Fragment {
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView)getActivity().findViewById(R.id.rcyView_group);
        coba();
    }

    public void coba(){
        List<Personal> personal = new ArrayList<>();
        for(int i = 0;i<10;i++)
            personal.add(new Personal(R.drawable.cm,"abid Ganteng","saya ingin bahagia",R.id.img_about_personal));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecycleViewPersonalAdapter(getActivity(),personal));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group, container, false);
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
//        ((Projects) getActivity()).setActionBarTitle("Group");
//    }

}
