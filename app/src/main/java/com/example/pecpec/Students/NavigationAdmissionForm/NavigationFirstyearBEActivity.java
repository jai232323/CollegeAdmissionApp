package com.example.pecpec.Students.NavigationAdmissionForm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pecpec.R;
import com.example.pecpec.Staffs.Admission.AdmissionFirstBEAdapter;
import com.example.pecpec.Staffs.Admission.AdmissionStaffData;
import com.example.pecpec.Staffs.Admission.FirstyearBEActivityStaff;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NavigationFirstyearBEActivity extends AppCompatActivity {


    private FirebaseUser firebaseUser;


    private RecyclerView mechDept, civilDept,cseDept,eceDept,eeeDept;
    private LinearLayout mechNoData,civilNoData,cseNoData,eceNoData,eeeNoData;
    private List<AdmissionStaffData> list1,list2,list3,list4,list5;

    //Intilazie
    private NavFirstAdapter adapter;

    private DatabaseReference reference,dbRef;

    String uniqueKey;
    String position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_firstyear_beactivity);

        SharedPreferences preferences = this.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        uniqueKey=preferences.getString("uniqueKey","none");

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

        mechDept = findViewById(R.id.mechDept);
        civilDept = findViewById(R.id.civilDept);
        cseDept = findViewById(R.id.cseDept);
        eceDept = findViewById(R.id.eceDept);
        eeeDept = findViewById(R.id.eeeDept);

        //Linearlayout Decalartion

        mechNoData=findViewById(R.id.mechNoData);
        civilNoData=findViewById(R.id.civilNoData);
        cseNoData=findViewById(R.id.cseNoData);
        eceNoData=findViewById(R.id.eceNoData);
        eeeNoData=findViewById(R.id.eeeNoData);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("First Year BE Admission Forms Staff Verified");



        mechDepartment();
        civilDepartment();
        cseDepartment();
        eceDepartment();
        eeeDepartment();


    }
    private void mechDepartment() {

        dbRef = reference.child("Mechanical Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    mechNoData.setVisibility(View.VISIBLE);
                    mechDept.setVisibility(View.GONE);
                } else {
                    mechNoData.setVisibility(View.GONE);
                    mechDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);
                        list1.add(0,data);
                    }
                    mechDept.setHasFixedSize(true);
                    mechDept.setLayoutManager(new LinearLayoutManager(NavigationFirstyearBEActivity.this));
                    adapter = new NavFirstAdapter(list1, NavigationFirstyearBEActivity.this, "Civil Engineering");
                    mechDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NavigationFirstyearBEActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void civilDepartment() {
        dbRef = reference.child("Civil Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    civilNoData.setVisibility(View.VISIBLE);
                    civilDept.setVisibility(View.GONE);
                } else {
                    civilNoData.setVisibility(View.GONE);
                    civilDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);
                        list2.add(0,data);
                    }
                    civilDept.setHasFixedSize(true);
                    civilDept.setLayoutManager(new LinearLayoutManager(NavigationFirstyearBEActivity.this));
                    adapter = new NavFirstAdapter(list2, NavigationFirstyearBEActivity.this, "Civil Engineering");
                    civilDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NavigationFirstyearBEActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void cseDepartment() {
        dbRef=reference.child("Computer Science Engineering");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    cseNoData.setVisibility(View.VISIBLE);
                    cseDept.setVisibility(View.GONE);
                }else {
                    cseNoData.setVisibility(View.GONE);
                    cseDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);
                        list3.add(0,data);
                    }
                    cseDept.setHasFixedSize(true);
                    cseDept.setLayoutManager(new LinearLayoutManager(NavigationFirstyearBEActivity.this));
                    adapter = new NavFirstAdapter(list3,NavigationFirstyearBEActivity.this,"Computer Science Engineering");
                    cseDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NavigationFirstyearBEActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void eceDepartment() {
        dbRef=reference.child("ECE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    eceNoData.setVisibility(View.VISIBLE);
                    eceDept.setVisibility(View.GONE);
                }else {
                    eceNoData.setVisibility(View.GONE);
                    eceDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);
                        list4.add(0,data);
                    }
                    eceDept.setHasFixedSize(true);
                    eceDept.setLayoutManager(new LinearLayoutManager(NavigationFirstyearBEActivity.this));
                    adapter = new NavFirstAdapter(list4,NavigationFirstyearBEActivity.this,"ECE");
                    eceDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NavigationFirstyearBEActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eeeDepartment() {
        dbRef = reference.child("EEE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list5 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    eeeNoData.setVisibility(View.VISIBLE);
                    eeeDept.setVisibility(View.GONE);
                } else {
                    eeeNoData.setVisibility(View.GONE);
                    eeeDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);
                        list5.add(0,data);
                    }
                    eeeDept.setHasFixedSize(true);
                    eeeDept.setLayoutManager(new LinearLayoutManager(NavigationFirstyearBEActivity.this));
                    adapter = new NavFirstAdapter(list5, NavigationFirstyearBEActivity.this, "EEE");
                    eeeDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NavigationFirstyearBEActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
















































//
//  ApplicantImage=findViewById(R.id.ApplicantImage);
//          ApplicantName=findViewById(R.id.ApplicantName);
//          ApplicantCourse=findViewById(R.id.ApplicantCourse);
//
//          firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//          DatabaseReference reference = FirebaseDatabase.getInstance().getReference("First Year BE Admission Forms")
//          .child("Mechanical Engineering").child(firebaseUser.getUid());
//
//          reference.addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//        AdmissionStaffData admissionStaffData =snapshot.getValue(AdmissionStaffData.class);
////
//
//        Glide.with(getApplicationContext()).load(admissionStaffData.getStudentImage()).into(ApplicantImage);
//        ApplicantName.setText(admissionStaffData.getName());
//        ApplicantCourse.setText(admissionStaffData.getCourseSubmitted());
//        }
//
//@Override
//public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//        }
//        });
