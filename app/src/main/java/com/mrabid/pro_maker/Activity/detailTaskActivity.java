package com.mrabid.pro_maker.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.mrabid.pro_maker.Adapter.RecyclerViewCorporationAdapter;
import com.mrabid.pro_maker.Adapter.RecyclerViewTaskAdapter;
import com.mrabid.pro_maker.Model.Corporation;
import com.mrabid.pro_maker.Model.Task;
import com.mrabid.pro_maker.R;

import java.util.ArrayList;
import java.util.List;

public class detailTaskActivity extends AppCompatActivity {

    Gson gson;
    Button mBtnDivideTask;
    String id_user;
    RecyclerView mRecyclerView;
    RecyclerViewTaskAdapter adapter;
    List<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);

        coba();

        adapter = new RecyclerViewTaskAdapter(detailTaskActivity.this, taskList);
        mRecyclerView = (RecyclerView) findViewById(R.id.rcyView_detailTask);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(detailTaskActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

        Button btnDivideTask = (Button) findViewById(R.id.btn_detailTask_divideTask);
        btnDivideTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(detailTaskActivity.this,AddTaskActivity.class));
            }
        });
    }

    public void coba(){
        taskList = new ArrayList<>();
        for(int i = 0;i<10;i++)
            taskList.add(new Task("Design Pensi","lalalala","Abid Ganteng","On Progress","Design Pensi"));

    }
}
