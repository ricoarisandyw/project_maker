package com.mrabid.pro_maker.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrabid.pro_maker.Model.Personal;
import com.mrabid.pro_maker.R;

import java.util.List;

/**
 * Created by Mr Abid on 9/13/2017.
 */

public class ListViewAdapter extends ArrayAdapter<Personal>{


    public ListViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Personal> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(null==v){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.listview_personal,null);
        }
        Personal personal = getItem(position);
        ImageView profil = (ImageView) v.findViewById(R.id.img_profile_personal);
        TextView nama = (TextView) v.findViewById(R.id.txt_judul_personal);
        TextView status = (TextView)v.findViewById(R.id.txt_status_personal);
        ImageView about = (ImageView)v.findViewById(R.id.img_about_personal);

        profil.setImageResource(personal.getImage());
        nama.setText(personal.getNama());
        status.setText(personal.getStatus());
        about.setImageResource(personal.getImage_about());

        return v;


    }
}
