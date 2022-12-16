package com.example.pecpec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.pecpec.R;
import com.github.chrisbanes.photoview.PhotoView;

public class FullImagerActivity extends AppCompatActivity {

    private PhotoView FullImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_imager);

        FullImageView=findViewById(R.id.FullImageView);

        String image = getIntent().getStringExtra("image");

        Glide.with(this).load(image).into(FullImageView);

    }
}