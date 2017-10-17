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
import com.mrabid.pro_maker.API.UserAPI;
import com.mrabid.pro_maker.Model.User;
import com.mrabid.pro_maker.R;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {


    UserAPI userAPI;
    EditText etName, etEmail, etPassword, etUsername, etPhone;
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
        getSupportActionBar().setTitle("ResponseSignUp");
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
        etPhone = (EditText)  findViewById(R.id.et_signup_phone);

        Button btnSignup = (Button) findViewById(R.id.btn_signup_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAPI.SignUp(SignUpActivity.this, new UserAPI.ResponseSignUp() {
                    @Override
                    public void onSuccess(User user) {
                        Toast.makeText(SignUpActivity.this,user.getToken(),Toast.LENGTH_SHORT).show();
                    }
                },
                    etUsername.getText().toString(),
                    etPassword.getText().toString(),
                    etEmail.getText().toString(),
                    etName.getText().toString(),
                    etPhone.getText().toString()
                );
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

}
