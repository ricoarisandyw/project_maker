package com.mrabid.pro_maker.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrabid.pro_maker.Helper.CircleConverter;
import com.mrabid.pro_maker.Model.Personal;
import com.mrabid.pro_maker.R;

import java.util.List;

public class RecycleViewPersonalAdapter extends RecyclerView.Adapter<RecycleViewPersonalAdapter.ViewHolder> {
    Context context;
    List<Personal> listPersonal;

    public RecycleViewPersonalAdapter(Context context, List<Personal> listPersonal) {
        this.context = context;
        this.listPersonal = listPersonal;
    }

    @Override
    public RecycleViewPersonalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.personal_view,null));
    }

    @Override
    public void onBindViewHolder(RecycleViewPersonalAdapter.ViewHolder holder, int position) {
        final Personal p = listPersonal.get(position);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.cm);
        Bitmap circularBitmap = CircleConverter.getRoundedCornerBitmap(bitmap, 100);
        holder.profile.setImageBitmap(circularBitmap);

        holder.judul.setText(""+p.getNama());
        holder.status.setText(""+p.getStatus().substring(0,10)+".....");
        holder.about.setImageResource(R.drawable.ic_info_black_24dp);
    }
    @Override
    public int getItemCount() {
        return listPersonal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView profile,about;
        TextView judul,status;

        public ViewHolder(View itemView) {
            super(itemView);

            profile = (ImageView)itemView.findViewById(R.id.img_profile_personal);
            about = (ImageView)itemView.findViewById(R.id.img_about_personal);
            judul = (TextView)itemView.findViewById(R.id.txt_judul_personal);
            status = (TextView)itemView.findViewById(R.id.txt_status_personal);

        }
    }
}