package com.mrabid.pro_maker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mrabid.pro_maker.Activity.detailProjectActivity;
import com.mrabid.pro_maker.Model.Projects;
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
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_project,null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Projects p = listProjects.get(position);
        holder.nama.setText(""+p.getName());
        holder.date.setText(""+p.getDeadline());
        int random = (int)Math.random()*100+1;
        holder.progressBar.setProgress(random);
        holder.rlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,detailProjectActivity.class);
                i.putExtra("id_project",p.getId_project());
                i.putExtra("id_creator",String.valueOf(p.getId_creator()));
                i.putExtra("name",p.getName());
                i.putExtra("deadline",p.getDeadline());
                i.putExtra("id_corporation",String.valueOf(p.getId_corporation()));
                i.putExtra("description",p.getDescription());
                Log.d("Read",p.getDescription());
                context.startActivity(i);
            }
        });

        holder.option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showMenu(v,p.getName());
            }
        });
    }

    public void showMenu(View v, String pos){
        final String position = pos;
        PopupMenu popup = new PopupMenu(context, v);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.edit:
                        //handle menu1 click
                        break;
                    case R.id.delete:
                        //handle menu2 click
                        break;
                }
                return false;
            }
        });
        popup.inflate(R.menu.menu_dot_project);
        MenuPopupHelper menuHelper = new MenuPopupHelper(context, (MenuBuilder) popup.getMenu(), v);
        menuHelper.setForceShowIcon(true);
        menuHelper.show();
    }

    @Override
    public int getItemCount() {
        return listProjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama,option;
        TextView date;
        ProgressBar progressBar;
        RelativeLayout rlt;

        public ViewHolder(View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.tv_nameProject);
            date = (TextView) itemView.findViewById(R.id.tv_dateViewProject);
            rlt = (RelativeLayout)itemView.findViewById(R.id.rlt_listProject);
            option = (TextView)itemView.findViewById(R.id.tv_detailPro_Option);
            progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar_project_item);

        }
    }
}