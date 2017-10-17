package com.mrabid.pro_maker.API;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrabid.pro_maker.Model.Task;
import com.mrabid.pro_maker.SharedPref;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Reaper on 10/13/2017.
 */

public class TaskAPI {
    static Activity activity;
    static Gson gson;

    public static void load(Activity activity, final ResponseRead callBack) {
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        final Gson gson = gsonBuilder.create();

        SharedPref sharedPref = new SharedPref(activity);
        String id_user = sharedPref.loadData("id_user");
        String token = sharedPref.loadData("token");

        String url = "http://jca.atrama-studio.com/backend/web/api-corporation/view?id_user="+id_user+"&access-token="+token;
        StringRequest postRequest = new StringRequest(
                Request.Method.POST,
                url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ResponseAPI responseAPI = gson.fromJson(response,ResponseAPI.class);
                        Task[] corporations = null;
                        if(responseAPI.isResult()){
                            corporations = gson.fromJson(response,Task[].class);
                        }
                        callBack.onSuccess(corporations);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Read UserAPI", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        requestQueue.add(postRequest);
    }

    public static void save(Activity activity, final ResponseCreate callBack, final Task task){
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String url = "https://jcaproject.000webhostapp.com/projectmaker/api/corporation_filter.php?id_owner=";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        ResponseAPI responseAPI = gson.fromJson(response,ResponseAPI.class);
                        Task taskData = null;
                        if(responseAPI.isResult()){
                            taskData = gson.fromJson(response,Task.class);
                        }
                        callBack.onSuccess(taskData);
                    }
                },
                new com.android.volley.Response.ErrorListener()
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

    public static void update(Activity activity, final ResponseUpdate callBack, final Task task){
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String url = "https://jcaproject.000webhostapp.com/projectmaker/api/corporation_filter.php?id_owner=";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        ResponseAPI responseAPI = gson.fromJson(response,ResponseAPI.class);
                        Task corporationData = null;
                        if(responseAPI.isResult()){
                            corporationData = gson.fromJson(response,Task.class);
                        }
                        callBack.onSuccess(corporationData);
                    }
                },
                new com.android.volley.Response.ErrorListener()
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

    public static void delete(Activity activity, final ResponseDelete callBack, final int id_corporation){
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String url = "https://jcaproject.000webhostapp.com/projectmaker/api/corporation_filter.php?id_owner=";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        ResponseAPI responseAPI = gson.fromJson(response,ResponseAPI.class);
                        callBack.onSuccess(responseAPI);
                    }
                },
                new com.android.volley.Response.ErrorListener()
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

    public interface ResponseRead {
        public void onSuccess(Task[] corporations);
    }

    public interface ResponseCreate{
        public void onSuccess(Task corporations);
    }

    public interface ResponseDelete{
        public void onSuccess(ResponseAPI response);
    }

    public interface ResponseUpdate{
        public void onSuccess(Task corporations);
    }

}
