package com.mrabid.pro_maker.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import com.mrabid.pro_maker.Adapter.RecyclerViewProjectAdapter;
import com.mrabid.pro_maker.Model.Projects;
import com.mrabid.pro_maker.R;

public class AddTaskActivity extends AppCompatActivity {

    private int hour, minute, day, month, year;
    TextView date,time;
    Dialog dialogListTask;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        RelativeLayout btnProject = (RelativeLayout)findViewById(R.id.rlt_project);
        RelativeLayout btnDate = (RelativeLayout)findViewById(R.id.rlt_datePicker);
        RelativeLayout btnTime = (RelativeLayout)findViewById(R.id.rlt_timePicker);
        date = (TextView)findViewById(R.id.txt_dateline);
        time = (TextView)findViewById(R.id.txt_time);

        //----------------toolbar-----------------------------//
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
//        upArrow.setColorFilter(getResources().getColor(R.color.White), PorterDuff.Mode.SRC_ATOP);
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("Create Task");
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //----------------datepicker-----------------------//
        btnDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar  c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);

                final DatePickerDialog datePickerDialog = new DatePickerDialog(AddTaskActivity.this,new DatePickerDialog.OnDateSetListener(){
                  @Override
                  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                      date.setText(dayOfMonth+"-"+(month+1)+"-"+year);

                  }
                 },day,month,year);
                 datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                 datePickerDialog.show();
            }
        });

        //---------------timepicker-------------------------//
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar d =Calendar.getInstance();
                hour = d.get(Calendar.HOUR_OF_DAY);
                minute = d.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddTaskActivity.this,new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time.setText(hourOfDay+":"+minute+":"+"00");
                    }
                },hour,minute,false);
                timePickerDialog.show();
            }
        });

        btnProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogListTask = new Dialog(AddTaskActivity.this);
                dialogListTask.setContentView(R.layout.projectview_layout_task);
                dialogListTask.setTitle("List Task");
                List<Projects> listProjects = new ArrayList<Projects>();
//                listProjects.add(new Projects("ProjectMaker","aaa","13 Spetember 2017"));
//                listProjects.add(new Projects("aaa","aaa","aaaavv"));
//                listProjects.add(new Projects("aaa","aaa","aaaavv"));
//                listProjects.add(new Projects("aaa","aaa","aaaavv"));
//                listProjects.add(new Projects("aaa","aaa","aaaavv"));
                RecyclerView rvListTask = (RecyclerView) dialogListTask.findViewById(R.id.rcyView_projectTask);
                rvListTask.setHasFixedSize(true);
                rvListTask.setLayoutManager(new LinearLayoutManager(AddTaskActivity.this));
                rvListTask.setAdapter(new RecyclerViewProjectAdapter(AddTaskActivity.this,listProjects));
                dialogListTask.show();
            }
        });
    }


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

    public String loadData(String name){
        SharedPreferences prefs = getSharedPreferences("UserData", 0);
        String data = prefs.getString(name,"");
        return  data;
    }
}
