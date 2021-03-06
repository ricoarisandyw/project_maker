package com.mrabid.pro_maker.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrabid.pro_maker.Adapter.RecyclerViewCorporationAdapter;
import com.mrabid.pro_maker.Model.Corporation;
import com.mrabid.pro_maker.R;
import com.mrabid.pro_maker.Rak.Rak;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class myCorporationActivity extends AppCompatActivity {

    Gson gson;
    Button btnMyCorp;
    String id_user;
    RecyclerView recyclerView;
    RecyclerViewCorporationAdapter adapter;
    List<Corporation> corporations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_corporation);

        Rak CorpDB = new Rak(this,"Corp",new Corporation());

        id_user = loadData("id_user");
        Log.d("Reponse", id_user);

        adapter = new RecyclerViewCorporationAdapter(myCorporationActivity.this, corporations);
        recyclerView = (RecyclerView) findViewById(R.id.rcyView_Corporation);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(myCorporationActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        int i=0;
        while(i<10){
            corporations.add(new Corporation(
                    "1",
                    "Name Corp",
                    "Corp Desc",
                    "Corp Address",
                    "1",
                    "2"
            ));
            i++;
        }
        adapter.notifyDataSetChanged();
    }

    public void Init(){
        RequestQueue requestQueue = Volley.newRequestQueue(myCorporationActivity.this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String url = "https://jcaproject.000webhostapp.com/projectmaker/api/corporation_filter.php?id_owner="+id_user;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Read", response);
                        responseInit posts =  gson.fromJson(response, responseInit.class);
                        if(posts.getStatus()==1){
                            Log.d("Read", response);
                            for (Corporation corp : posts.getCorporation()){
                                corporations.add(new Corporation(
                                        corp.getId_corporation(),
                                        corp.getName(),
                                        corp.getDescription(),
                                        corp.getAddress(),
                                        corp.getId_owner(),
                                        corp.getId_parent()
                                ));
                                Log.d("Read Name", corp.getName());
                            }
                        }else{
                            Toast.makeText(myCorporationActivity.this, response, Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        if(error.toString().contains("Timeout")){
                            Log.d("Read E", error.toString());
                            Log.d("Read E", "Time Out");
                        }
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
        //close --JSON--
    }

    public class responseInit{
        private int status;
        private ArrayList<Corporation> corporation;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public ArrayList<Corporation> getCorporation() {
            return corporation;
        }

        public void setCorporation(ArrayList<Corporation> corporation) {
            this.corporation = corporation;
        }
    }

    public String loadData(String name){
        SharedPreferences prefs = getSharedPreferences("UserData", 0);
        String data = prefs.getString(name,"");
        Log.d(name + " keluar:", data);
        return data;
    }
    public void saveData(String name, String value){
        SharedPreferences prefs = getSharedPreferences("UserData", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(name, value);
        Log.d(name + " masuk :", value);
        editor.commit();
    }
}
