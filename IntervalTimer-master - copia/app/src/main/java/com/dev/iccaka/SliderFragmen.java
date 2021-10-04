package com.dev.iccaka;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.iccaka.intervaltimer.R;

public class SliderFragmen extends Fragment {
    View view;
    ImageView image;
    TextView title, content;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragmen_slider, container,false);
        image=view.findViewById(R.id.image);
        title=view.findViewById(R.id.txtTitle);
        content=view.findViewById(R.id.txtContent);
        RelativeLayout background=view.findViewById(R.id.background);

        if(getArguments()!=null){
            title.setText(getArguments().getString("title"));
            content.setText(getArguments().getString("content"));
            image.setImageResource(getArguments().getInt("image"));
        }

        return view;
    }
}
