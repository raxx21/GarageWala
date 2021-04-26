package com.rajesh.garaajwala;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rajesh.garaajwala.models.MostPopularHelper;

import java.util.ArrayList;

public class MostPopularAdpater extends RecyclerView.Adapter<MostPopularAdpater.MostPopularViewHolder> {

    ArrayList<MostPopularHelper> mostPopularLocations;

    public MostPopularAdpater(ArrayList<MostPopularHelper> mostPopularLocations) {
        this.mostPopularLocations = mostPopularLocations;
    }

    @NonNull
    @Override
    public MostPopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_popular_garage,parent,false);
        MostPopularViewHolder mostPopularViewHolder = new MostPopularViewHolder(view);
        return mostPopularViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostPopularViewHolder holder, int position) {

        MostPopularHelper mostPopularHelper = mostPopularLocations.get(position);
        holder.image.setImageResource(mostPopularHelper.getImage());
        holder.title.setText(mostPopularHelper.getTitle());
        holder.des.setText(mostPopularHelper.getDes());

    }

    @Override
    public int getItemCount() {
        return mostPopularLocations.size();
    }

    public static class MostPopularViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, des;
        public MostPopularViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.mp_image);
            title = itemView.findViewById(R.id.mp_title);
            des = itemView.findViewById(R.id.mp_des);

        }
    }

}
