package com.example.pecpec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pecpec.Staffs.AdminEmailActivity;
import com.example.pecpec.Staffs.Admission.AdmissionActivity;
import com.example.pecpec.Staffs.StaffMainActivity;
import com.example.pecpec.Students.StudentLoginActivity;
import com.example.pecpec.Students.StudentRegisterActivity;
import com.example.pecpec.Students.Student_REG_LoginActivity;

public class Staff_Student extends AppCompatActivity {

    CardView student,staff;
    TextView for_online_admissionform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff__student);

        student=findViewById(R.id.student);
        staff=findViewById(R.id.staff);

        for_online_admissionform=findViewById(R.id.for_online_admissionform);
        for_online_admissionform.setSelected(true);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Staff_Student.this, StudentLoginActivity.class));
            }
        });
        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Staff_Student.this, AdminEmailActivity.class));

            }
        });

    }
}