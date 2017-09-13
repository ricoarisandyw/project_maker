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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mrabid.pro_maker.Adapter.ListViewAdapter;
import com.mrabid.pro_maker.AddTaskActivity;
import com.mrabid.pro_maker.Model.Personal;
import com.mrabid.pro_maker.Project;
import com.mrabid.pro_maker.R;
import com.mrabid.pro_maker.SettingActivity;

import java.util.ArrayList;
import java.util.List;

public class PersonalFragment extends Fragment {

    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private List<Personal> personalList;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView)getActivity().findViewById(R.id.listViewPersonal);
        getPersonalList();
        setAdapters();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.personal_fragment, container, false);


    }

    private List<Personal> getPersonalList() {
            personalList = new ArrayList<>();
            personalList.add(new Personal(R.drawable.cm,"Abid Ganteng","Aku bahagia",R.drawable.ic_info_black_24dp));
            personalList.add(new Personal(R.drawable.cm,"Abid Ganteng","Aku bahagia",R.drawable.ic_info_black_24dp));
            personalList.add(new Personal(R.drawable.cm,"Abid Ganteng","Aku bahagia",R.drawable.ic_info_black_24dp));
            personalList.add(new Personal(R.drawable.cm,"Abid Ganteng","Aku bahagia",R.drawable.ic_info_black_24dp));
            personalList.add(new Personal(R.drawable.cm,"Abid Ganteng","Aku bahagia",R.drawable.ic_info_black_24dp));
            personalList.add(new Personal(R.drawable.cm,"Abid Ganteng","Aku bahagia",R.drawable.ic_info_black_24dp));
            personalList.add(new Personal(R.drawable.cm,"Abid Ganteng","Aku bahagia",R.drawable.ic_info_black_24dp));
            return personalList;
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getActivity(), "Succes", Toast.LENGTH_SHORT).show();
        }
    };

    private void setAdapters(){
        listViewAdapter = new ListViewAdapter(getActivity(),R.layout.personal_fragment,personalList);
        listView.setAdapter(listViewAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_message,menu);
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
    
}
