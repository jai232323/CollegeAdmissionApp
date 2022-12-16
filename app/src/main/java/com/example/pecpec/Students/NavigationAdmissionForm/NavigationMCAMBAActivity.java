package com.example.pecpec.Students.NavigationAdmissionForm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pecpec.R;
import com.example.pecpec.Staffs.Admission.AdmissionStaffData;
import com.example.pecpec.Staffs.Admission.MCAMBAActivityAdapteer;
import com.example.pecpec.Staffs.Admission.MCAMBAActivityStaff;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NavigationMCAMBAActivity extends AppCompatActivity {

    private RecyclerView mcaDept, mbaDept;
    private LinearLayout mcaNoData,mbaNoData;
    private List<AdmissionStaffData> list1,list2;

    //Intilazie
    private NavMCAMBAAdapteer adapter;

    private DatabaseReference reference,dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_mcambaactivity);


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



        mcaDept=findViewById(R.id.mcaDept);
        mbaDept=findViewById(R.id.mbaDept);

        mcaNoData= findViewById(R.id.mcaNoData);
        mbaNoData= findViewById(R.id.mbaNoData);



        reference= FirebaseDatabase.getInstance().getReference("MCA MBA Admission Forms Admission Forms Staff Verified");

        mcaDepartment();
        mbaDepartment();
    }

    private void mbaDepartment() {
        dbRef=reference.child("MBA");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    mbaNoData.setVisibility(View.VISIBLE);
                    mbaDept.setVisibility(View.GONE);
                }else {
                    mbaNoData.setVisibility(View.GONE);
                    mbaDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);
                        list2.add(0,data);
                    }
                    mbaDept.setHasFixedSize(true);
                    mbaDept.setLayoutManager(new LinearLayoutManager(NavigationMCAMBAActivity.this));
                    adapter = new NavMCAMBAAdapteer(list2,NavigationMCAMBAActivity.this,"MBA");
                    mbaDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NavigationMCAMBAActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mcaDepartment() {
        dbRef=reference.child("MCA");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    mcaNoData.setVisibility(View.VISIBLE);
                    mcaDept.setVisibility(View.GONE);
                }else {
                    mcaNoData.setVisibility(View.GONE);
                    mcaDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);
                        list1.add(0,data);
                    }
                    mcaDept.setHasFixedSize(true);
                    mcaDept.setLayoutManager(new LinearLayoutManager(NavigationMCAMBAActivity.this));
                    adapter = new NavMCAMBAAdapteer(list1,NavigationMCAMBAActivity.this,"MCA");
                    mcaDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NavigationMCAMBAActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}