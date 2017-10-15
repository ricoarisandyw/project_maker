package com.mrabid.pro_maker.API;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrabid.pro_maker.Activity.AddCorporationActivity;
import com.mrabid.pro_maker.Model.Corporation;
import com.mrabid.pro_maker.ResponseGlobal;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Reaper on 10/13/2017.
 */

public class CorporationAPI {

    Activity activity;

    public Respon Post(final Corporation corporation) {
        final Respon[] respon = {new Respon()};
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        final Gson gson = gsonBuilder.create();
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        String url = "https://jcaproject.000webhostapp.com/projectmaker/api/corporation_input.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        respon[0] =  gson.fromJson(response, Respon.class);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("name", corporation.);
                return params;
            }
        };
        requestQueue.add(postRequest);
        return respon[0];
    }

    public List<Corporation> Get() {
        List<Corporation> corporationList;

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();
        String url = "https://jcaproject.000webhostapp.com/projectmaker/api/corporation_input.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                    params.put("name", etName.getText().toString());
                return params;
            }
        };
        requestQueue.add(postRequest);
        //close --JSON--
        return null;
    }

    public class Respon{

    }
}
