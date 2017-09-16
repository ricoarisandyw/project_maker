package com.mrabid.pro_maker.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.mrabid.pro_maker.Model.Corporation;
import com.mrabid.pro_maker.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class myCorporationActivity extends AppCompatActivity {

    Gson gson;
    Button btnMyCorp;
    String id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_corporation);

        btnMyCorp = (Button) findViewById(R.id.btn_mycorp_name);
        id_user = loadData("id_user");
        Init();
        btnMyCorp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(myCorporationActivity.this, AddProjectActivity.class);
                startActivity(i);
            }
        });
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
                        Log.d("Response", response);
                        responseInit posts =  gson.fromJson(response, responseInit.class);
                        if(posts.getStatus()==1){
                            btnMyCorp.setText(posts.getCorporation().get(0).getName());
                            saveData("id_corporation",posts.getCorporation().get(0).getId_corporation().toString());
                            saveData("name_corporation",posts.getCorporation().get(0).getName().toString());
                        }else{
                            Toast.makeText(myCorporationActivity.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Response", error.toString());
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
