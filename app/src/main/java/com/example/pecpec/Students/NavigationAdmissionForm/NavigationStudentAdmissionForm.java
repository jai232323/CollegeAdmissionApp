package com.example.pecpec.Students.NavigationAdmissionForm;



import androidx.appcompat.app.AppCompatActivity;
import com.example.pecpec.R;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class NavigationStudentAdmissionForm extends AppCompatActivity {


    private MaterialCardView First_Year_BE,Lateral_Entry_BE,ME,MBA_MCA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_student_admission_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Student Admission Forms");
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

                Intent intent = new Intent(NavigationStudentAdmissionForm.this, NavigationFirstyearBEActivity.class);
                startActivity(intent);
            }
        });

        Lateral_Entry_BE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationStudentAdmissionForm.this, NavigationLateralEnterBEActivity.class);
                startActivity(intent);
            }
        });
        ME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationStudentAdmissionForm.this, NavigationMEActivity.class);
                startActivity(intent);
            }
        });
        MBA_MCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationStudentAdmissionForm.this, NavigationMCAMBAActivity.class);
                startActivity(intent);
            }
        });

    }
}