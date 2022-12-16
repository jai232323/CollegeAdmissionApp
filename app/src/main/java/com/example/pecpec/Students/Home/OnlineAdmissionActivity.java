package com.example.pecpec.Students.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pecpec.R;
import com.example.pecpec.Staffs.Admission.AdmissionActivity;
import com.example.pecpec.Staffs.Admission.FirstyearBEActivityStaff;
import com.example.pecpec.Staffs.Admission.LateralEnterBEActivity1Staff;
import com.example.pecpec.Staffs.Admission.MCAMBAActivityStaff;
import com.example.pecpec.Staffs.Admission.MEActivityStaff;
import com.example.pecpec.Students.Admission.FirstyearBEActivity;
import com.example.pecpec.Students.Admission.LateralEnterBEActivity;
import com.example.pecpec.Students.Admission.MCAMBAActivity;
import com.example.pecpec.Students.Admission.MEActivity;
import com.google.android.material.card.MaterialCardView;

public class OnlineAdmissionActivity extends AppCompatActivity {


    private MaterialCardView First_Year_BE,Lateral_Entry_BE,ME,MBA_MCA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_admission);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Admission Forms");
        toolbar.setNavigationIcon(R.drawable.ic_back_btn);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        First_Year_BE=findViewById(R.id.First_Year_BE);
        Lateral_Entry_BE=findViewById(R.id.Lateral_Entry_BE);
        ME=findViewById(R.id.ME);
        MBA_MCA=findViewById(R.id.MBA_MCA);


        First_Year_BE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OnlineAdmissionActivity.this, FirstyearBEActivity.class);
                startActivity(intent);
            }
        });

        Lateral_Entry_BE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineAdmissionActivity.this, LateralEnterBEActivity.class);
                startActivity(intent);
            }
        });
        ME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineAdmissionActivity.this, MEActivity.class);
                startActivity(intent);
            }
        });
        MBA_MCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnlineAdmissionActivity.this, MCAMBAActivity.class);
                startActivity(intent);
            }
        });
    }
}