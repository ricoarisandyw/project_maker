package com.mrabid.pro_maker.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mrabid.pro_maker.Activity.AddProjectActivity;
import com.mrabid.pro_maker.Model.Corporation;
import com.mrabid.pro_maker.Project;
import com.mrabid.pro_maker.R;
import com.mrabid.pro_maker.SharedPref;

import java.util.List;

public class RecyclerViewCorporationAdapter extends RecyclerView.Adapter<RecyclerViewCorporationAdapter.ViewHolder> {
    Context context;
    List<Corporation> listCorporation;
    SharedPref sharedPref;

    public RecyclerViewCorporationAdapter(Context context, List<Corporation> listCorporation) {
        this.context = context;
        this.listCorporation = listCorporation;
        sharedPref = new SharedPref(context);

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
        ImageView imgPencil;


        public ViewHolder(View itemView) {
            super(itemView);
            listCorp = (RelativeLayout) itemView.findViewById(R.id.list_corp);
            name = (TextView) itemView.findViewById(R.id.txt_corp_title);
            progressBar = (ProgressBar) itemView.findViewById(R.id.prog_status_corp);
            imgPencil = (ImageView) itemView.findViewById(R.id.img_itemcorp_edit);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Corporation p = listCorporation.get(position);
        holder.name.setText(p.getName());
        holder.progressBar.setProgress(position*10);
        holder.listCorp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DO SOMETHING HERE
                Intent i = new Intent(v.getContext(), Project.class);
                i.putExtra("id_corp", p.getId_corporation());
                sharedPref.saveData("name_corporation", p.getName());
                sharedPref.saveData("id_corporation", p.getId_corporation());
                v.getContext().startActivity(i);
                ((Activity)context).finish();
            }
        });
        holder.imgPencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v,p.getId_corporation());
            }
        });

    }

    public void showMenu(View v, String pos) {
        final String position = pos;
        PopupMenu popup = new PopupMenu(context, v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.popup_corp_delete: Toast.makeText(context,"Delete"+position+"}",Toast.LENGTH_SHORT).show();break;
                    case R.id.popup_corp_edit: Toast.makeText(context,"Edit"+position+"}",Toast.LENGTH_SHORT).show();;break;
                }
                return false;
            }
        });
        popup.inflate(R.menu.popup_corporation_edit);
        popup.show();
    }
}