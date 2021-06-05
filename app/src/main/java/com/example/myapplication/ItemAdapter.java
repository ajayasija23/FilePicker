package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private List<String> data;
    private Context context;

    public ItemAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context)
                .inflate(R.layout.adapter_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ItemAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       private TextView textView;
        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
        }
    }
}
