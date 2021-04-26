package com.rajesh.garaajwala;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    //Arrays
    public String[] slide_headings= {
            "Our Job",
            "Your Problem",
            "Solved"
    };

    public String[] slide_des= {
            "If you're visiting this page, you're likely here because you're searching for a random sentence.",
            "If you're visiting this page, you're likely here because you're searching for a random sentence.",
            "If you're visiting this page, you're likely here because you're searching for a random sentence."
    };


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);
        TextView textView1 = view.findViewById(R.id.textView1);
        TextView textView3 = view.findViewById(R.id.textView3);

        textView1.setText(slide_headings[position]);
        textView3.setText(slide_des[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((ConstraintLayout)object);
    }
}
