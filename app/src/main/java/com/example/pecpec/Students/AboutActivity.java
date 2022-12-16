package com.example.pecpec.Students;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.pecpec.R;

public class AboutActivity extends AppCompatActivity {

    private ImageView map;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_btn);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ImageView imageView = findViewById(R.id.colleg_image_about);
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F02.jpg?alt=media&token=05508e42-1d79-46fa-b2e1-55d8800e081d")
                .into(imageView);



        map=findViewById(R.id.pec_map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
    }

    private void openMap() {
        Uri uri =Uri.parse("geo:0, 0?q=Priyadarshini Engineering College, Priyadarshini Engineering College, Chettiyappanur, Vaniyambadi, Tamil Nadu");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
}