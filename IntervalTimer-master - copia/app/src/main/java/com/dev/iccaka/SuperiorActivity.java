package com.dev.iccaka;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.iccaka.intervaltimer.R;

public class SuperiorActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;
    private LinearLayout dotsLayout;
    //private Button btnNext, btnBack;

    private String[]title={"Flexion", "Sentadilla", "Burpes"};
    private String[]content={"Trabaja el pecho y biceps","Trabaja Gluteos y trapesios","trabja " +
            "diferentes musculos como los son abdomen, gluteos, brazos"};
    private int[]image={R.drawable.flexion_final_pequena,R.drawable.sentadilla_final_pequena,
            R.drawable.burpes_final_resolucion_pequena};
    private int[] colorDot;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superior);
        colorDot=getResources().getIntArray(R.array.array_dot);
        viewPager=findViewById(R.id.viewPager);

        dotsLayout=findViewById(R.id.layoutDots);
        addDots(0);
        loadViewPager();
    }
    public void volver (View view){
        Intent v = new Intent (this, EjerciciosActivity.class);
        startActivity(v);
    }
    private void addDots(int currentPage){
        dots=new TextView[title.length];
        dotsLayout.removeAllViews();
        for(int i =0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            if(i==currentPage)
                dots[i].setTextColor(colorDot[currentPage]);
            else
                dots[i].setTextColor(Color.LTGRAY);

            dotsLayout.addView(dots[i]);
        }
    }
    private void loadViewPager(){
        adapter=new MyViewPagerAdapter(getSupportFragmentManager());
        for(int i=0;i<title.length;i++){
            adapter.addFragment(newInnstance(title[i],content[i], image[i]));

        }
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(pageListener);
    }
    private SliderFragmen newInnstance(String title,String content, int image){
        Bundle bundle= new Bundle();
        bundle.putString("title",title);
        bundle.putString("content",content);
        bundle.putInt("image",image);

        SliderFragmen fragmen=new SliderFragmen();
        fragmen.setArguments(bundle);
        return fragmen;
    }
    ViewPager.OnPageChangeListener pageListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}