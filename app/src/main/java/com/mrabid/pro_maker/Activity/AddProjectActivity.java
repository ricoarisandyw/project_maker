package com.mrabid.pro_maker.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.mrabid.pro_maker.Model.Corporation;
import com.mrabid.pro_maker.Model.Projects;
import com.mrabid.pro_maker.R;
import com.mrabid.pro_maker.ResponseGlobal;
import com.mrabid.pro_maker.SharedPref;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddProjectActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private int hour, minute, day, month, year;
    EditText etTitle, etDescription;
    TextView txtDate, txtTime;
    TextView txtProjectId;
    ArrayAdapter<String> adapter;
    Spinner spnListCorp;
    ArrayList<String> ListCorporations = new ArrayList<String>();
    ArrayList<Corporation> corporations = new ArrayList<>();
    Gson gson;
    String gIdUser, gIdCorp, gNameCorp;
    SharedPref sharedPref;

    String corporation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        sharedPref = new SharedPref(this);

        RelativeLayout btnDate = (RelativeLayout)findViewById(R.id.rlt_datePickerP);
        RelativeLayout btnTime = (RelativeLayout)findViewById(R.id.rlt_timePickerP);
        txtDate = (TextView)findViewById(R.id.txt_datelineP);
        txtTime = (TextView)findViewById(R.id.txt_timeP);
        Button btnSubmit = (Button) findViewById(R.id.btn_addproject_submit);
        etTitle = (EditText) findViewById(R.id.edt_title_task);
        etDescription = (EditText) findViewById(R.id.edt_desc_task);

        Init();



        spnListCorp = (Spinner) findViewById(R.id.spn_addprojcet_namecorp);
        spnListCorp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                corporation = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        /*adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ListCorporations);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnListCorp.setAdapter(adapter);
        spnListCorp.setOnItemSelectedListener(this);*/


        gIdUser = sharedPref.loadData("id");
        gIdCorp = sharedPref.loadData("id_corporation");
        gNameCorp = sharedPref.loadData("name_corporation");;

        //----------------toolbar-----------------------------//
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        //upArrow.setColorFilter(getResources().getColor(R.color.White), PorterDuff.Mode.SRC_ATOP);
        //getSupportActionBar().setHomeAsUpIndicator(upArrow);
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
        String token_user = sharedPref.loadData("token");


        String url = "http://jca.atrama-studio.com/backend/web/api-project?access-token="+token_user;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Read", response);
                        responseProject posts =  gson.fromJson(response, responseProject.class);
                        if(posts.getResult().toString().equalsIgnoreCase("true")){
                            Toast.makeText(AddProjectActivity.this, "Create ProjectActivity berhasil", Toast.LENGTH_SHORT).show();
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
                        Log.d("Read", error.toString());
                        if(error.toString().contains("Timeout")){
                            Submit();
                        }
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("id_creator", gIdUser);
                params.put("name", etTitle.getText().toString());
                params.put("deadline", txtDate.toString()+"-"+txtTime.toString());
                params.put("id_corporation", "2");
                params.put("description", etDescription.getText().toString());

                return params;
            }
        };
        requestQueue.add(postRequest);
    }

    public void Init(){
        final int[] id_corp = new int[1];
        RequestQueue requestQueue = Volley.newRequestQueue(AddProjectActivity.this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
        String token_user = sharedPref.loadData("token");
        String url = "https://jca.atrama-studio.com/backend/web/api-corporation?access-token="+token_user;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Read", response);
                        AddProjectActivity.responseInit posts =  gson.fromJson(response, AddProjectActivity.responseInit.class);
                        if(posts.getStatus()==1){
                            Log.d("Read", response);
                            int i = 0;
                            for (Corporation corp : posts.getCorporation()){
                                corporations.add(new Corporation(
                                        corp.getId_corporation(),
                                        corp.getName(),
                                        corp.getDescription(),
                                        corp.getAddress(),
                                        corp.getId_owner(),
                                        corp.getId_parent()
                                ));
                                ListCorporations.add(corp.getName());
                                if(corp.getName().equalsIgnoreCase(gNameCorp)){
                                    id_corp[0] = i;
                                }
                                i++;

                            }
                            adapter.notifyDataSetChanged();
                            spnListCorp.setAdapter(adapter);
                            spnListCorp.setSelection(id_corp[0]);
                            startActivity(new Intent(AddProjectActivity.this,MainActivity.class));

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

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String id_corp = corporations.get(pos).getId_corporation();
        Toast.makeText(AddProjectActivity.this, id_corp, Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    public class responseProject{
        private String result;
        private String message;
        private ArrayList<Projects> data;

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

        public ArrayList<Projects> getData() {
            return data;
        }

        public void setData(ArrayList<Projects> data) {
            this.data = data;
        }
    }

}
