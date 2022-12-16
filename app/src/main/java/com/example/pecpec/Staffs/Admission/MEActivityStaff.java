package com.example.pecpec.Staffs.Admission;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pecpec.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MEActivityStaff extends AppCompatActivity {

    private RecyclerView mecseDept, mecsDept,mepseDept,meedDept;
    private LinearLayout mecseNoData,mecsNoData,mepseNoData,meedNoData;
    private List<AdmissionStaffData> list1,list2,list3,list4;

    //Intilazie
    private MEAdapter adapter;

    private DatabaseReference reference,dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mestaff);

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

        mecseDept = findViewById(R.id.mecse);
        mecsDept = findViewById(R.id.mecs);
        mepseDept = findViewById(R.id.mepse);
        meedDept = findViewById(R.id.meed);

        //Linearlayout Decalartion

        mecseNoData=findViewById(R.id.mecseNoData);
        mecsNoData=findViewById(R.id.mecsNoData);
        mepseNoData=findViewById(R.id.mepseNoData);
        meedNoData=findViewById(R.id.meedNoData);

        reference= FirebaseDatabase.getInstance().getReference("ME Admission Forms");

        mecseDepartment();
        mecsDepartment();
        mepseDepartment();
        meedDepartment();
    }

    private void meedDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    meedNoData.setVisibility(View.VISIBLE);
                    meedDept.setVisibility(View.GONE);
                }else {
                    meedNoData.setVisibility(View.GONE);
                    meedDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);
                        if ("ME(Engineering Design)".equals(data.getCourse())){
                            list4.add(0,data);
                        }

                    }
                    meedDept.setHasFixedSize(true);
                    meedDept.setLayoutManager(new LinearLayoutManager(MEActivityStaff.this));
                    adapter = new MEAdapter(list4,MEActivityStaff.this,"ME(Engineering Design)");
                    meedDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MEActivityStaff.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mepseDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    mepseNoData.setVisibility(View.VISIBLE);
                    mepseDept.setVisibility(View.GONE);
                }else {
                    mepseNoData.setVisibility(View.GONE);
                    mepseDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);
                        if ("ME(Power Systems Engineering)".equals(data.getCourse())){
                            list3.add(0,data);
                        }

                    }
                    mepseDept.setHasFixedSize(true);
                    mepseDept.setLayoutManager(new LinearLayoutManager(MEActivityStaff.this));
                    adapter = new MEAdapter(list3,MEActivityStaff.this,"ME(Power Systems Engineering)");
                    mepseDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MEActivityStaff.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mecsDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    mecsNoData.setVisibility(View.VISIBLE);
                    mecsDept.setVisibility(View.GONE);
                }else {
                    mecsNoData.setVisibility(View.GONE);
                    mecsDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);
                        if ("ME(Communication Systems)".equals(data.getCourse())){
                            list2.add(0,data);
                        }

                    }
                    mecsDept.setHasFixedSize(true);
                    mecsDept.setLayoutManager(new LinearLayoutManager(MEActivityStaff.this));
                    adapter = new MEAdapter(list2,MEActivityStaff.this,"ME(Communication Systems)");
                    mecsDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MEActivityStaff.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mecseDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    mecseNoData.setVisibility(View.VISIBLE);
                    mecseDept.setVisibility(View.GONE);
                }else {
                    mecseNoData.setVisibility(View.GONE);
                    mecseDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);
                        if ("ME(Computer Science and Engineering)".equals(data.getCourse())){
                            list1.add(0,data);
                        }

                    }
                    mecseDept.setHasFixedSize(true);
                    mecseDept.setLayoutManager(new LinearLayoutManager(MEActivityStaff.this));
                    adapter = new MEAdapter(list1,MEActivityStaff.this,"ME(Computer Science and Engineering)");
                    mecseDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MEActivityStaff.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}