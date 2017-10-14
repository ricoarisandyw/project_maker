package com.mrabid.pro_maker.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrabid.pro_maker.Activity.AddProjectActivity;
import com.mrabid.pro_maker.Adapter.RecyclerViewProjectAdapter;
import com.mrabid.pro_maker.Model.Projects;
import com.mrabid.pro_maker.R;
import com.mrabid.pro_maker.Activity.SettingActivity;
import com.mrabid.pro_maker.SharedPref;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyProjectFragment extends Fragment {

    RecyclerView recyclerView;
    Gson gson;
    SharedPref sharedPref;
    ArrayList<Projects> projList = new ArrayList<>();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        project();
        recyclerView = (RecyclerView)getActivity().findViewById(R.id.rcy_projectList);

    }


    public void project(){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
        String id = sharedPref.loadData("id");
        String token = sharedPref.loadData("token");
        String url = "http://jca.atrama-studio.com/backend/web/api-project/view?id_user="+id+"&access-token="+token;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        responseProject posts = new responseProject();
                        Log.d("Response", response);
                            try{
                                posts =  gson.fromJson(response, responseProject.class);
                            }catch(Exception e){
                                Toast.makeText(getActivity(), "Wrong Username/Password", Toast.LENGTH_SHORT).show();
                            }
                        Log.d("Response", String.valueOf(posts.getProjectData().get(1).getName()));
                        if(posts.getResult().toString().equalsIgnoreCase("true")) {
                            if (posts.getProjectData() == null) {
                                Toast.makeText(getActivity(), "Anda tidak memiliki project untuk dikerjakan", Toast.LENGTH_SHORT).show();
                            } else {
                                for (int i = 0; i < posts.getProjectData().size(); i++) {
                                    projList.add(new Projects(
                                            posts.getProjectData().get(i).getId_project(),
                                            posts.getProjectData().get(i).getId_creator(),
                                            posts.getProjectData().get(i).getName(),
                                            posts.getProjectData().get(i).getDeadline(),
                                            posts.getProjectData().get(i).getId_corporation(),
                                            posts.getProjectData().get(i).getDescription()));
                                }
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                recyclerView.setAdapter(new RecyclerViewProjectAdapter(getActivity(), projList));

                            }
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Response", error.toString());
                        Toast.makeText(getActivity(), "Tolong cek paket data anda", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                return params;
            }
        };
        requestQueue.add(postRequest);


    }


    public class responseProject{
        private String result;
        private String message;
        private ArrayList<Projects> projectData;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ArrayList<Projects> getProjectData() {
            return projectData;
        }

        public void setProjectData(ArrayList<Projects> projectData) {
            this.projectData = projectData;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_project, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_project,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                return true;

            case R.id.action_addProject:
                startActivity(new Intent(getActivity(), AddProjectActivity.class));
                return true;
        }
        return false;
    }
}
