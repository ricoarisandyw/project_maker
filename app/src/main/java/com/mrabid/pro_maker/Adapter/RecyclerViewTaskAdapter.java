package com.mrabid.pro_maker.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mrabid.pro_maker.Model.Task;
import com.mrabid.pro_maker.R;
import java.util.List;

public class RecyclerViewTaskAdapter extends RecyclerView.Adapter<RecyclerViewTaskAdapter.ViewHolder> {
    Context context;
    List<Task> listTask;

    public RecyclerViewTaskAdapter(Context context, List<Task> listTask) {
        this.context = context;
        this.listTask = listTask;
    }

    @Override
    public RecyclerViewTaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_task,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Task p = listTask.get(position);
        holder.pemberi.setText(""+p.getid_doer());
        holder.nama.setText(""+p.getName());
        holder.status.setText(""+p.getStatus());
    }

    @Override
    public int getItemCount() {
        return listTask.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView  nama,pemberi,status;

        public ViewHolder(View itemView) {
            super(itemView);

            pemberi = (TextView) itemView.findViewById(R.id.txt_pemberiViewTask);
            nama = (TextView)itemView.findViewById(R.id.txt_nameViewTask);
            status = (TextView)itemView.findViewById(R.id.txt_statusViewTask);

        }
    }
}