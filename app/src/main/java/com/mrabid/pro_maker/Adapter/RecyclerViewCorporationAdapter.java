package com.mrabid.pro_maker.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mrabid.pro_maker.Activity.AddProjectActivity;
import com.mrabid.pro_maker.Model.Corporation;
import com.mrabid.pro_maker.R;
import com.mrabid.pro_maker.SharedPref;

import java.util.List;

public class RecyclerViewCorporationAdapter extends RecyclerView.Adapter<RecyclerViewCorporationAdapter.ViewHolder> {
    Context context;
    List<Corporation> listCorporation;

    public RecyclerViewCorporationAdapter(Context context, List<Corporation> listCorporation) {
        this.context = context;
        this.listCorporation = listCorporation;
    }

    @Override
    public RecyclerViewCorporationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_corporation,null));
    }



    @Override
    public int getItemCount() {
        return listCorporation.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView  name,address,id_corp, id_owner, description, id_parent;
        ProgressBar progressBar;
        RelativeLayout listCorp;


        public ViewHolder(View itemView) {
            super(itemView);
            listCorp = (RelativeLayout) itemView.findViewById(R.id.list_corp);
            name = (TextView) itemView.findViewById(R.id.txt_corp_title);
            progressBar = (ProgressBar) itemView.findViewById(R.id.prog_status_corp);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Corporation p = listCorporation.get(position);
        holder.name.setText(p.getName());
        holder.progressBar.setProgress(position*10);
        holder.listCorp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DO SOMETHING HERE
                Intent i = new Intent(v.getContext(), AddProjectActivity.class);
                i.putExtra("id_corp", p.getId_corporation());
                SharedPref.saveData("name_corporation", p.getName(), context);
                SharedPref.saveData("id_corporation", p.getId_corporation(), context);
                v.getContext().startActivity(i);
                ((Activity)context).finish();
            }
        });

    }
}