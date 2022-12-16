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

public class LateralEnterBEActivity1Staff extends AppCompatActivity {

    private RecyclerView mechDept, civilDept,cseDept,eceDept,eeeDept;
    private LinearLayout mechNoData,civilNoData,cseNoData,eceNoData,eeeNoData;
    private List<AdmissionStaffData> list1,list2,list3,list4,list5;

    //Intilazie
    private LateralEnterBEActivityAdapter adapter;

    private DatabaseReference reference,dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lateral_enter_beactivity1_staff);

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

        reference= FirebaseDatabase.getInstance().getReference("Lateral Entry BE Admission Forms");


        mechDepartment();
        civilDepartment();
        cseDepartment();
        eceDepartment();
        eeeDepartment();


    }private void mechDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    mechNoData.setVisibility(View.VISIBLE);
                    mechDept.setVisibility(View.GONE);
                }else {
                    mechNoData.setVisibility(View.GONE);
                    mechDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);
                        if ("Mechanical Engineering".equals(data.getCourse())){
                            list1.add(0,data);
                        }
                    }
                    mechDept.setHasFixedSize(true);
                    mechDept.setLayoutManager(new LinearLayoutManager(LateralEnterBEActivity1Staff.this));
                    adapter = new LateralEnterBEActivityAdapter(list1,LateralEnterBEActivity1Staff.this,"Mechanical Engineering");
                    mechDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LateralEnterBEActivity1Staff.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void civilDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
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
                        if ("Civil Engineering".equals(data.getCourse())){
                            list2.add(0,data);
                        }

                    }
                    civilDept.setHasFixedSize(true);
                    civilDept.setLayoutManager(new LinearLayoutManager(LateralEnterBEActivity1Staff.this));
                    adapter = new LateralEnterBEActivityAdapter(list2, LateralEnterBEActivity1Staff.this, "Civil Engineering");
                    civilDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LateralEnterBEActivity1Staff.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void cseDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
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
                        if ("Computer Science Engineering".equals(data.getCourse())){
                            list3.add(0,data);
                        }

                    }
                    cseDept.setHasFixedSize(true);
                    cseDept.setLayoutManager(new LinearLayoutManager(LateralEnterBEActivity1Staff.this));
                    adapter = new LateralEnterBEActivityAdapter(list3,LateralEnterBEActivity1Staff.this,"Computer Science Engineering");
                    cseDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LateralEnterBEActivity1Staff.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }




    private void eceDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
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
                        if ("ECE".equals(data.getCourse())){
                            list4.add(0,data);
                        }

                    }
                    eceDept.setHasFixedSize(true);
                    eceDept.setLayoutManager(new LinearLayoutManager(LateralEnterBEActivity1Staff.this));
                    adapter = new LateralEnterBEActivityAdapter(list4,LateralEnterBEActivity1Staff.this,"ECE");
                    eceDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LateralEnterBEActivity1Staff.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eeeDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
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
                        if ("EEE".equals(data.getCourse())){
                            list5.add(0,data);
                        }

                    }
                    eeeDept.setHasFixedSize(true);
                    eeeDept.setLayoutManager(new LinearLayoutManager(LateralEnterBEActivity1Staff.this));
                    adapter = new LateralEnterBEActivityAdapter(list5, LateralEnterBEActivity1Staff.this, "EEE");
                    eeeDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LateralEnterBEActivity1Staff.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}