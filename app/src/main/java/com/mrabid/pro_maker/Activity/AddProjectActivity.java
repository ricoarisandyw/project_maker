package com.mrabid.pro_maker.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
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
import com.mrabid.pro_maker.ResponseGlobal;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddProjectActivity extends AppCompatActivity {

    private int hour, minute, day, month, year;
    EditText etTitle, etDescription;
    TextView txtDate, txtTime;
    TextView txtProjectId;
    Gson gson;
    String gIdUser, gIdCorp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        RelativeLayout btnDate = (RelativeLayout)findViewById(R.id.rlt_datePickerP);
        RelativeLayout btnTime = (RelativeLayout)findViewById(R.id.rlt_timePickerP);
        txtDate = (TextView)findViewById(R.id.txt_datelineP);
        txtTime = (TextView)findViewById(R.id.txt_timeP);
        txtProjectId = (TextView) findViewById(R.id.txt_addprojcet_idproject);

        Button btnSubmit = (Button) findViewById(R.id.btn_addproject_submit);

        etTitle = (EditText) findViewById(R.id.edt_title_task);
        etDescription = (EditText) findViewById(R.id.edt_title_task);

        gIdUser = loadData("id_user");
        gIdCorp = loadData("id_corporation");
        String gNameCorp = loadData("name_corporation");
        txtProjectId.setText(gNameCorp);

        //----------------toolbar-----------------------------//
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.White), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("Create New Projects");
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //----------------datepicker-----------------------//
        btnDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);

                final DatePickerDialog datePickerDialog = new DatePickerDialog(AddProjectActivity.this,new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDate.setText(dayOfMonth+"-"+(month+1)+"-"+year);

                    }
                },day,month,year);
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();
                
            }
        });

        //----------------datepicker-----------------------//
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Submit();
            }
        });

        //---------------timepicker-------------------------//
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar d =Calendar.getInstance();
                hour = d.get(Calendar.HOUR_OF_DAY);
                minute = d.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddProjectActivity.this,new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        txtTime.setText(hourOfDay+":"+minute+":"+"00");
                    }
                },hour,minute,false);
                timePickerDialog.show();
            }
        });
    }



    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                //overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void Submit(){
        RequestQueue requestQueue = Volley.newRequestQueue(AddProjectActivity.this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String url = "https://jcaproject.000webhostapp.com/projectmaker/api/project_input.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        ResponseGlobal posts =  gson.fromJson(response, ResponseGlobal.class);
                        if(posts.getStatus()==1){
                            finish();
                        }else{
                            Toast.makeText(AddProjectActivity.this, response, Toast.LENGTH_SHORT).show();
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
                params.put("name", etTitle.getText().toString());
                params.put("id_creator", gIdUser);
                params.put("deadline", etDescription.getText().toString());
                params.put("description", etDescription.getText().toString());
                params.put("id_corporation", gIdCorp);
                return params;
            }
        };
        requestQueue.add(postRequest);
        //close --JSON--
    }

    public String loadData(String name){
        SharedPreferences prefs = getSharedPreferences("UserData", 0);
        String data = prefs.getString(name,"");
        Log.d(name + " keluar:", data);
        return data;
    }
}
