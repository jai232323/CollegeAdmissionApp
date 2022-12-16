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

public class MCAMBAActivityStaff extends AppCompatActivity {

    private RecyclerView mcaDept, mbaDept;
    private LinearLayout mcaNoData,mbaNoData;
    private List<AdmissionStaffData> list1,list2;

    //Intilazie
    private MCAMBAActivityAdapteer adapter;

    private DatabaseReference reference,dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcambastaff);


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



        reference= FirebaseDatabase.getInstance().getReference("MCA MBA Admission Forms");

        mcaDepartment();
        mbaDepartment();
    }

    private void mbaDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
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
                        if ("MBA".equals(data.getCourse())){
                            list2.add(0,data);
                        }
                    }
                    mbaDept.setHasFixedSize(true);
                    mbaDept.setLayoutManager(new LinearLayoutManager(MCAMBAActivityStaff.this));
                    adapter = new MCAMBAActivityAdapteer(list2,MCAMBAActivityStaff.this,"MBA");
                    mbaDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MCAMBAActivityStaff.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mcaDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
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
                        if ("MCA".equals(data.getCourse())){
                            list1.add(0,data);
                        }
                    }
                    mcaDept.setHasFixedSize(true);
                    mcaDept.setLayoutManager(new LinearLayoutManager(MCAMBAActivityStaff.this));
                    adapter = new MCAMBAActivityAdapteer(list1,MCAMBAActivityStaff.this,"MCA");
                    mcaDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MCAMBAActivityStaff.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

}