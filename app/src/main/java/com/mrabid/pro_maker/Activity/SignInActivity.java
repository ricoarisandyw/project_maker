package com.mrabid.pro_maker.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrabid.pro_maker.Model.User;
import com.mrabid.pro_maker.ProjectActivity;
import com.mrabid.pro_maker.R;
import com.mrabid.pro_maker.SharedPref;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {
    ProgressDialog progress;
    Gson gson;
    RequestQueue requestQueue;
    EditText etUsername, etPassword;
    String username, password;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sharedPref = new SharedPref(this);

        progress=new ProgressDialog(SignInActivity.this);
        progress.setMessage("Please Wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.setCanceledOnTouchOutside(false);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
//        upArrow.setColorFilter(getResources().getColor(R.color.White), PorterDuff.Mode.SRC_ATOP);
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("SignIn");
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        etUsername = (EditText) findViewById(R.id.et_signin_email);
        etPassword = (EditText) findViewById(R.id.et_signin_password);


        Button signIn = (Button)findViewById(R.id.btn_signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.show();
                Validate(etUsername.getText().toString(),etPassword.getText().toString());
            }
        });


        //Urgent Only While Connection error
        //startActivity(new Intent(SignInActivity.this, ProjectActivity.class));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(SignInActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void Validate(final String username, final String  password){
        requestQueue = Volley.newRequestQueue(SignInActivity.this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String url = "http://jca.atrama-studio.com/backend/web/auth/login";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        progress.hide();
                        responseLogin posts = new responseLogin();
                        try{
                            posts =  gson.fromJson(response, responseLogin.class);
                        }catch(Exception e){
                            Toast.makeText(SignInActivity.this, "Wrong Username/Password", Toast.LENGTH_SHORT).show();
                        }
                        if(posts.getResult().toString().equalsIgnoreCase("true")){
                            Intent i = new Intent(SignInActivity.this, ProjectActivity.class);
                            Log.d("Response", posts.getResult()+" "+posts.getMessage()+" "+posts.getUserData().getId());
                            String id = String.valueOf(posts.getUserData().getId());
                            String token = posts.getUserData().getToken();
                            sharedPref.saveData("username", username);
                            sharedPref.saveData("password", password);
                            sharedPref.saveData("id", id);
                            sharedPref.saveData("token", token);
                            startActivity(i);
                        }else{
                            Toast.makeText(SignInActivity.this, "Wrong Username/Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Response", error.toString());
                        Toast.makeText(SignInActivity.this, "Tolong cek paket data anda", Toast.LENGTH_SHORT).show();
                        progress.hide();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        requestQueue.add(postRequest);
        //close --JSON--
    }

    public class responseLogin{
        private String result;
        private String message;
        private User userData;

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

        public User getUserData() {
            return userData;
        }

        public void setUserData(User userData) {
            this.userData = userData;
        }
    }
    public void saveData(String name, String value){
        SharedPreferences prefs = getSharedPreferences("UserData", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(name, value);
        Log.d(name + " masuk :", value);
        editor.commit();
    }
}
