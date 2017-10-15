package com.mrabid.pro_maker.API;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Excluder;
import com.mrabid.pro_maker.Model.User;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static java.lang.Thread.sleep;

/**
 * Created by Reaper on 10/13/2017.
 */

public class UserAPI {
    Activity activity;

    public UserAPI(Activity activity) {
        this.activity = activity;
    }


    public Login Login(final String username, final String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(activity);

        final Login[] respon = {new Login()};
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        final Gson gson = gsonBuilder.create();
        RequestFuture<String> future = RequestFuture.newFuture();
        String url = "http://jca.atrama-studio.com/backend/web/auth/login";
        StringRequest postRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ResponseAPI responseAPI = gson.fromJson(response, ResponseAPI.class);
                        if (responseAPI.isResult()) {
                            respon[0] = gson.fromJson(response, Login.class);
                        } else {
                            respon[0].setResult(false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Response UserAPI", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        requestQueue.add(postRequest);
        //Waiting Reponse
        while(respon[0].getMessage()==null){
            Log.d("Response", "UserAPI respon=null");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //close --JSON--
        return respon[0];
    }

    public SignUp SignUp(final String username, final String password, final String email, final String name, final String phone) {
        final SignUp[] respon = new SignUp[1];
        respon[0].setMessage(null);
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        final Gson gson = gsonBuilder.create();
        String url = "https://jca.atrama-studio.com/backend/web/auth/signup";
        StringRequest postRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ResponseAPI responseAPI = gson.fromJson(response, ResponseAPI.class);
                        if (responseAPI.isResult()) {
                            respon[0] = gson.fromJson(response, SignUp.class);
                        } else {
                            respon[0].setResult(false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Response UserAPI", error.toString());
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
        //Waiting Reponse
        while(respon[0].getMessage()==null){
            Log.d("Response", "UserAPI respon=null");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //close --JSON--
        return respon[0];
    }

    public class Login {
        private boolean result;
        private String message;
        private User userData;

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public User getUserData() {
            return userData;
        }

        public void setUserData(User userData) {
            this.userData = userData;
        }
    }

    public class SignUp {
        private boolean result;
        private String message;
        private User userData;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public User getUserData() {
            return userData;
        }

        public void setUserData(User userData) {
            this.userData = userData;
        }

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }
    }
}
