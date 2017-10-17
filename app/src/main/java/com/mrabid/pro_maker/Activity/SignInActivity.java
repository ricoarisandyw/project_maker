package com.mrabid.pro_maker.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mrabid.pro_maker.API.CorporationAPI;
import com.mrabid.pro_maker.API.UserAPI;
import com.mrabid.pro_maker.Model.Corporation;
import com.mrabid.pro_maker.Model.User;
import com.mrabid.pro_maker.R;
import com.mrabid.pro_maker.SharedPref;

public class SignInActivity extends AppCompatActivity {
    ProgressDialog progress;
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
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.White), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("SignIn");
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        etUsername = (EditText) findViewById(R.id.et_signin_email);
        etPassword = (EditText) findViewById(R.id.et_signin_password);

        username = sharedPref.loadData("username");
        password = sharedPref.loadData("password");



        UserAPI.Login(this, new UserAPI.ResponseLogin() {
            @Override
            public void onSuccess(User user) {
                sharedPref.saveData("token",user.getToken());
            }
        },username,password);

        Button signIn = (Button)findViewById(R.id.btn_signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.show();
                UserAPI.Login(SignInActivity.this, new UserAPI.ResponseLogin() {
                    @Override
                    public void onSuccess(User user) {
                        Toast.makeText(SignInActivity.this,user.getToken(),Toast.LENGTH_SHORT).show();
                    }
                },username,password);
                progress.hide();
            }
        });
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

}
