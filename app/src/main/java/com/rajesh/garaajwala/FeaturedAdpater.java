package com.rajesh.garaajwala;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rajesh.garaajwala.models.FeaturedHelper;

import java.util.ArrayList;

public class FeaturedAdpater extends RecyclerView.Adapter<FeaturedAdpater.FeaturedViewHolder> {

    ArrayList<FeaturedHelper> featuredLocations;

    public FeaturedAdpater(ArrayList<FeaturedHelper> featuredLocations) {
        this.featuredLocations = featuredLocations;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_layout,parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        FeaturedHelper featuredHelper = featuredLocations.get(position);

        holder.image.setImageResource(featuredHelper.getImage());
        holder.title.setText(featuredHelper.getTitle());
        holder.des.setText(featuredHelper.getDes());
        holder.email.setText(featuredHelper.getEmail());

    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, des, checkout,email;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.featured_card_image);
            title = itemView.findViewById(R.id.featured_card_title);
            des = itemView.findViewById(R.id.featured_card_des);
            email = itemView.findViewById(R.id.featured_card_email);
            checkout = itemView.findViewById(R.id.checkout);

            checkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), Book.class);
                    intent.putExtra("garage_email",email.getText().toString());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
