package com.example.greshkumartharwani.getplusset;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gresh Kumar Tharwani on 12/29/2017.
 */

public class rvadapter extends RecyclerView.Adapter<rvadapter.MyViewHolder> {

    ArrayList<model> ralist;

    public rvadapter(ArrayList<model> ralist) {
        this.ralist = ralist;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerrow, parent, false);
        return new MyViewHolder(vi);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.ttl1.setText("Title : "+ralist.get(position).getTitle());
        holder.body1.setText("Body : "+ralist.get(position).getBody());
        holder.userid1.setText("User ID : "+String.valueOf(ralist.get(position).getUserId()));
        holder.postid1.setText("Post ID : "+String.valueOf(ralist.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return ralist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView ttl1,body1,userid1,postid1;

        public MyViewHolder(View view) {
            super(view);
            ttl1=view.findViewById(R.id.ttl1);
            body1=view.findViewById(R.id.body1);
            postid1=view.findViewById(R.id.postid1);
            userid1=view.findViewById(R.id.userid1);
        }
    }
}
