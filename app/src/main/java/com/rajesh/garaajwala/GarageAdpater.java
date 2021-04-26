package com.rajesh.garaajwala;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rajesh.garaajwala.models.GarageHelper;

import java.util.ArrayList;

public class GarageAdpater extends RecyclerView.Adapter<GarageAdpater.FeaturedViewHolder> {

    ArrayList<GarageHelper> featuredLocations;

    public GarageAdpater(ArrayList<GarageHelper> featuredLocations) {
        this.featuredLocations = featuredLocations;
    }

    @NonNull
    @Override
    public GarageAdpater.FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.garage_card,parent,false);
        GarageAdpater.FeaturedViewHolder featuredViewHolder = new GarageAdpater.FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GarageAdpater.FeaturedViewHolder holder, int position) {

        GarageHelper featuredHelper = featuredLocations.get(position);

        holder.title.setText(featuredHelper.getTitle());
        holder.des.setText(featuredHelper.getDes());
        holder.phone.setText(featuredHelper.getPhone());

    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        TextView title, des,phone;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            title = itemView.findViewById(R.id.mp_title1);
            des = itemView.findViewById(R.id.mp_des1);
            phone = itemView.findViewById(R.id.mp_phone1);

        }
    }

}
