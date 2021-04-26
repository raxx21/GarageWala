package com.rajesh.garaajwala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Intro1 extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private SliderAdapter sliderAdapter;
    private TextView bprev;
    private TextView bnext;
    private int currentPage;
    private TextView[] mDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        viewPager = findViewById(R.id.viewPager);
        linearLayout = findViewById(R.id.dots);

        bprev = findViewById(R.id.prev);
        bnext = findViewById(R.id.next);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(onPageChangeListener);

        //OnCLickedLister
            bnext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(currentPage < 2){
                        viewPager.setCurrentItem(currentPage + 1 );

                    }
                    else{
                        startActivity(new Intent(getApplicationContext(), InterfacePage.class));
                        finish();
                    }
                }
            });
        bprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPage - 1);
            }
        });
    }

    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        linearLayout.removeAllViews();
        for(int i=0; i<mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.transparent));
            linearLayout.addView(mDots[i]);
        }
        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            currentPage= i;

            if(i == 0){
                bnext.setEnabled(true);
                bprev.setEnabled(false);
                bprev.setVisibility(View.INVISIBLE);

                bnext.setText("Next");
                bprev.setText("");
            }else if(i == mDots.length -1){
                bnext.setEnabled(true);
                bprev.setEnabled(true);
                bprev.setVisibility(View.VISIBLE);

                bnext.setText("Finish");
                bprev.setText("Back");
            }else {
                bnext.setEnabled(true);
                bprev.setEnabled(true);
                bprev.setVisibility(View.VISIBLE);

                bnext.setText("Next");
                bprev.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}