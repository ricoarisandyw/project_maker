package com.mrabid.pro_maker.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mrabid.pro_maker.R;

public class detailProjectActivity extends AppCompatActivity {

    Toolbar mtoolbar;
    TextView mnamaProject;
    Button createTaskDetailProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_project);

        mtoolbar = (Toolbar)findViewById(R.id.toolbar);
        createTaskDetailProject = (Button)findViewById(R.id.btn_detailPro_createTask);
        mnamaProject = (TextView)findViewById(R.id.tv_nameProject);

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
        mnamaProject.setText("ProjectMaker");

        createTaskDetailProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Intent i = new Intent(detailProjectActivity.this,AddTaskActivity.class);
                //bundle.putString("namaProject",mnamaProject.getText().toString());
                //i.putExtras(bundle);
                startActivity(i);
            }
        });
    }
}
