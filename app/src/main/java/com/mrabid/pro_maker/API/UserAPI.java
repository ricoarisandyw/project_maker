package com.mrabid.pro_maker.API;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrabid.pro_maker.Model.User;
import com.mrabid.pro_maker.SharedPref;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * Created by Reaper on 10/13/2017.
 */

public class UserAPI {
    static Gson gson;

    public static void SignUp(final Activity activity, final ResponseSignUp callBack, final String username, final String password, final String email, final String name, final String phone) {
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        final Gson gson = gsonBuilder.create();

        final SharedPref sharedPref = new SharedPref(activity);

        String url = "http://jca.atrama-studio.com/backend/web/auth/signup";
        StringRequest postRequest = new StringRequest(
                Request.Method.POST,
                url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("UserAPI","Response : "+response);
                        ResponseAPI responseAPI = gson.fromJson(response,ResponseAPI.class);
                        User userData = new User();
                        userData.setEmail("");
                        userData.setId(0);
                        userData.setToken("");
                        userData.setUsername("");
                        if(responseAPI.isResult()){
                            userData = gson.fromJson(response,User.class);
                            sharedPref.saveData("id_user", String.valueOf(userData.getId()));
                            sharedPref.saveData("token", userData.getToken());
                        }else {
                            Toast.makeText(activity,responseAPI.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                        callBack.onSuccess(userData);
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
                params.put("username", username);
                params.put("password", password);
                params.put("email", email);
                params.put("name", name);
                params.put("phone", phone);
                return params;
            }
        };
        requestQueue.add(postRequest);
    }

    public static void Login(final Activity activity, final ResponseLogin callBack, final String username, final String password){
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        final SharedPref sharedPref = new SharedPref(activity);

        String url = "http://jca.atrama-studio.com/backend/web/auth/login";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("UserAPI","Response : "+response);
                        ResponseAPI responseAPI = gson.fromJson(response,ResponseAPI.class);
                        User userData = new User();
                        userData.setEmail("");
                        userData.setId(0);
                        userData.setToken("");
                        userData.setUsername("");
                        if(responseAPI.isResult()){
                            userData = gson.fromJson(response,User.class);
                            sharedPref.saveData("id_user", String.valueOf(userData.getId()));
                            sharedPref.saveData("token", userData.getToken());
                            callBack.onSuccess(userData);
                        }else {
                            Toast.makeText(activity,responseAPI.getMessage(),Toast.LENGTH_SHORT).show();
                        }
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
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };
        requestQueue.add(postRequest);
        //close --JSON--
    }

    public interface ResponseSignUp {
        public void onSuccess(User user);
    }

    public interface ResponseLogin {
        public void onSuccess(User user);
    }
}
