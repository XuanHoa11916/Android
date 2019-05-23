package com.example.maytinh;

import android.app.Activity;
import android.content.Context;
import android.nfc.NfcAdapter;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Myviewholder> {

    Context context;
    ArrayList<Maytinh> data;
    int LayoutResource;

    public Adapter(Context context, ArrayList<Maytinh> data, int layoutResource) {
        this.context = context;
        this.data = data;
        LayoutResource = layoutResource;
    }

    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
        View row = layoutInflater.inflate(LayoutResource, viewGroup, false);
        return new Myviewholder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull final Myviewholder myviewholder, final int i) {
        myviewholder.tvchuoi.setText(data.get(i).getChuoi());
        myviewholder.tvkq.setText(data.get(i).getKq());
        myviewholder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(i);
                Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
    //ham remove
    public void remove(int i)
    {
            data.remove(i);
            notifyItemRemoved(i);
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class Myviewholder extends RecyclerView.ViewHolder {
        TextView tvchuoi;
        TextView tvkq;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            tvchuoi = itemView.findViewById(R.id.chuoioo);
            tvkq = itemView.findViewById(R.id.ketqua);
        }
    }
}
