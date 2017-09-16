package com.mrabid.pro_maker.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.mrabid.pro_maker.R;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword, etUsername;
    RequestQueue requestQueue;
    ProgressDialog progress;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.White), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("SignUp");
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        progress=new ProgressDialog(SignUpActivity.this);
        progress.setMessage("Please Wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.setCanceledOnTouchOutside(false);

        etName = (EditText) findViewById(R.id.et_signup_nama);
        etEmail = (EditText) findViewById(R.id.et_signup_email);
        etUsername = (EditText) findViewById(R.id.et_signup_username);
        etPassword = (EditText) findViewById(R.id.et_signup_password);
        Button btnSignup = (Button) findViewById(R.id.btn_signup_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
                progress.show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void SignUp(){
        requestQueue = Volley.newRequestQueue(SignUpActivity.this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String url = "https://jcaproject.000webhostapp.com/projectmaker/api/signup.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        progress.hide();
                        Log.d("Response", response);
                        responseLogin posts =  gson.fromJson(response, responseLogin.class);
                        Log.d("Response", posts.getMsg());
                        if(posts.getStatus()==1){
                            Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(SignUpActivity.this, response, Toast.LENGTH_SHORT).show();
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
                params.put("name", etName.getText().toString());
                params.put("username", etUsername.getText().toString());
                params.put("email", etEmail.getText().toString());
                params.put("password", etPassword.getText().toString());
                return params;
            }
        };
        requestQueue.add(postRequest);
        //close --JSON--
    }

    public class responseLogin{
        private int status;
        private String msg;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setResult(String msg) {
            this.msg= msg;
        }
    }

}
