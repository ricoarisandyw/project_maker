package com.mrabid.pro_maker.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mrabid.pro_maker.Adapter.RecyclerViewTaskAdapter;
import com.mrabid.pro_maker.Model.Task;
import com.mrabid.pro_maker.R;

import java.util.ArrayList;

public class detailProjectActivity extends AppCompatActivity {

    Toolbar mtoolbar;
    TextView mnamaProject;
    Button createTaskDetailProject;
    RecyclerView detailTaskProject;
    ScrollView scroll;
    ArrayList<Task> mtaskProjectList;
    RecyclerViewTaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_project);

        mtoolbar = (Toolbar)findViewById(R.id.toolbar);
        createTaskDetailProject = (Button)findViewById(R.id.btn_detailPro_createTask);
        mnamaProject = (TextView)findViewById(R.id.tv_detailPro_nameProject);
        detailTaskProject = (RecyclerView)findViewById(R.id.rcyView_detailProjectTask);
        scroll = (ScrollView)findViewById(R.id.scrlv_detailPro);

        //set scrollview always top
        scroll.smoothScrollTo(0, 0);
        scroll.fullScroll(ScrollView.FOCUS_UP);


        //=================================toolbar=========================================//
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.White), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("NamaProject");
        mtoolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //isi dengan nama project nanti(user.getNamaProject);
        mnamaProject.setText("Nama Project");

        //Ketika penekanan tombol mengirim nama project
        createTaskDetailProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent i = new Intent(detailProjectActivity.this,AddTaskActivity.class);
                bundle.putString("namaProject",mnamaProject.getText().toString());
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        coba();
        adapter = new RecyclerViewTaskAdapter(detailProjectActivity.this, mtaskProjectList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(detailProjectActivity.this);
        detailTaskProject.setLayoutManager(mLayoutManager);
        detailTaskProject.setItemAnimator(new DefaultItemAnimator());
        detailTaskProject.setAdapter(adapter);
    }

    public void coba(){
        mtaskProjectList = new ArrayList<>();
        for(int i = 0;i<10;i++)
            mtaskProjectList.add(new Task("Task ProjectMaker","lalalala","Abid Ganteng","On Progress","Task ProjectMaker"));
        }


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
}
