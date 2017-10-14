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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mrabid.pro_maker.Adapter.RecyclerViewTaskAdapter;
import com.mrabid.pro_maker.Model.Task;
import com.mrabid.pro_maker.R;

import java.util.ArrayList;

public class detailProjectActivity extends AppCompatActivity {

    Toolbar mtoolbar;
    TextView mnamaProject,mtanggal,mcreated,mdescrib;
    Button createTaskDetailProject;
    RecyclerView detailTaskProject;
    RelativeLayout descRelative;
    ImageView up,down;
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
        mtanggal = (TextView)findViewById(R.id.tv_detailPro_deadLine);
        mcreated = (TextView)findViewById(R.id.tv_detailPro_CreatedBy);
        mdescrib= (TextView)findViewById(R.id.tv_detailPro_desc);
        detailTaskProject = (RecyclerView)findViewById(R.id.rcyView_detailProjectTask);
        scroll = (ScrollView)findViewById(R.id.scrlv_detailPro);
        up = (ImageView) findViewById(R.id.imgV_detailPro_descrip_up);
        down = (ImageView) findViewById(R.id.imgV_detailPro_descrip_down);
        descRelative = (RelativeLayout)findViewById(R.id.rlt_detailPro_descrip);

        //set scrollview always top
        scroll.smoothScrollTo(0, 0);
        scroll.fullScroll(ScrollView.FOCUS_UP);


        //=================================toolbar=========================================//
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
//        upArrow.setColorFilter(getResources().getColor(R.color.White), PorterDuff.Mode.SRC_ATOP);
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle(getIntent().getExtras().getString("name"));
        mtoolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //isi
        mnamaProject.setText(getIntent().getExtras().getString("name"));
        mtanggal.setText(getIntent().getExtras().getString("deadline"));
        mcreated.setText(getIntent().getExtras().getString("id_creator"));
        mdescrib.setText(getIntent().getExtras().getString("description"));


        //description
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descRelative.setVisibility(View.VISIBLE);
                up.setVisibility(View.INVISIBLE);
                down.setVisibility(View.VISIBLE);
                Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down_desc_project);
                descRelative.startAnimation(slideDown);
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descRelative.setVisibility(View.GONE);
                up.setVisibility(View.VISIBLE);
                down.setVisibility(View.INVISIBLE);
            }
        });

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
