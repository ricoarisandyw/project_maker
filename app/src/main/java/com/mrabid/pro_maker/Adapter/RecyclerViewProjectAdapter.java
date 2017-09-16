package com.mrabid.pro_maker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mrabid.pro_maker.Model.Projects;
import com.mrabid.pro_maker.Project;
import com.mrabid.pro_maker.R;
import java.util.List;

/**
 * Created by Mr Abid on 9/15/2017.
 */

public class RecyclerViewProjectAdapter extends RecyclerView.Adapter<RecyclerViewProjectAdapter.ViewHolder> {
    Context context;
    List<Projects> listProjects;

    public RecyclerViewProjectAdapter(Context context, List<Projects> listProject) {
        this.context = context;
        this.listProjects = listProject;
    }

    @Override
    public RecyclerViewProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.project_view,null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Projects p = listProjects.get(position);
        holder.nama.setText(""+p.getNama());
        holder.date.setText(""+p.getDateLine());
        holder.rlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = context.getSharedPreferences("UserData",0);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("nama",p.getNama());
                editor.commit();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listProjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama;
        TextView date;
        RelativeLayout rlt;

        public ViewHolder(View itemView) {
            super(itemView);

            nama = (TextView) itemView.findViewById(R.id.txt_nameViewProject);
            date = (TextView) itemView.findViewById(R.id.txt_ViewDateLine);
            rlt = (RelativeLayout)itemView.findViewById(R.id.rlt_projectView);

        }
    }
}