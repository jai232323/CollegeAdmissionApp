package com.example.pecpec.Staffs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pecpec.R;
import com.example.pecpec.Staffs.Admission.AdmissionActivity;
import com.example.pecpec.Staffs.Faculty.FacultyActivity;
import com.example.pecpec.Staffs.Notice.UploadNotice;
import com.google.android.material.card.MaterialCardView;

public class StaffMainActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialCardView admissionForm,addNotice,addGalleryImage,addEbook,faculty,deleteNotice;

    private TextView pec_addresss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_main);


        admissionForm=findViewById(R.id.admissionForm);
        addNotice=findViewById(R.id.addNotice);
        addGalleryImage=findViewById(R.id.addGalleryImage);
//        addEbook=findViewById(R.id.addEbook);
        faculty=findViewById(R.id.faculty);
        deleteNotice=findViewById(R.id.deleteNotice);

        pec_addresss=findViewById(R.id.pec_addresss);
        pec_addresss.setSelected(true);
        pec_addresss.setSelected(true);

        admissionForm.setOnClickListener(this);
        addNotice.setOnClickListener(this);
        addGalleryImage.setOnClickListener(this);
//        addEbook.setOnClickListener(this);
        faculty.setOnClickListener(this);

        deleteNotice.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){

            case R.id.admissionForm:
                intent = new Intent(StaffMainActivity.this, AdmissionActivity.class);
                startActivity(intent);
                break;
            case R.id.addNotice:
                intent = new Intent(StaffMainActivity.this, UploadNotice.class);
                startActivity(intent);
                break;
            case R.id.addGalleryImage:
                intent = new Intent(StaffMainActivity.this,UploadImage.class);
                startActivity(intent);
                break;
//            case R.id.addEbook:
//                intent = new Intent(StaffMainActivity.this,UploadPdfActivity.class);
//                startActivity(intent);
//                break;
            case R.id.faculty:
                intent = new Intent(StaffMainActivity.this, FacultyActivity.class);
                startActivity(intent);
                break;
            case R.id.deleteNotice:
                intent = new Intent(StaffMainActivity.this, DeleteNoticeActivity.class);
                startActivity(intent);
                break;
        }

    }
}