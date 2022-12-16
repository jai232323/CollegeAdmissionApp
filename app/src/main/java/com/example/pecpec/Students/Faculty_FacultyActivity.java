package com.example.pecpec.Students;


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
import com.example.pecpec.Staffs.Faculty.StaffData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Faculty_FacultyActivity extends AppCompatActivity {

    private RecyclerView mcaDept,mbaDept,cseDept,mechDept,civilDept,eceDept,eeeDept,SandHDept;
    private LinearLayout mcaNoData,mbaNoData,cseNoData,mechNoData,civilNoData,eceNoData,eeeNoData,SandHNoData;
    private List<StaffData> list1,list2,list3,list4,list5,list6,list7,list8;

    //Intilazie
    private StudentFacultyAdapter adapter;

    private DatabaseReference reference,dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_faculty);



        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Staff Faculty");
        toolbar.setNavigationIcon(R.drawable.ic_back_btn);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //RecyclerView Decalartion

        mcaDept = findViewById(R.id.mcaDept);
        mbaDept =  findViewById(R.id.mbaDept);
        cseDept =  findViewById(R.id.cseDept);
        mechDept =  findViewById(R.id.mechDept);
        civilDept =  findViewById(R.id.civilDept);
        eceDept =  findViewById(R.id.eceDept);
        eeeDept =  findViewById(R.id.eeeDept);
        SandHDept =  findViewById(R.id.SandHDept);

        //Linearlayout Decalartion

        mcaNoData= findViewById(R.id.mcaNoData);
        mbaNoData= findViewById(R.id.mbaNoData);
        cseNoData= findViewById(R.id.cseNoData);
        mechNoData= findViewById(R.id.mechNoData);
        civilNoData= findViewById(R.id.civilNoData);
        eceNoData= findViewById(R.id.eceNoData);
        eeeNoData= findViewById(R.id.eeeNoData);
        SandHNoData= findViewById(R.id.SandHNoData);

        reference= FirebaseDatabase.getInstance().getReference("Staffs");

        mcaDepartment();
        mbaDepartment();
        cseDepartment();
        mechDepartment();
        civilDepartment();
        eceDepartment();
        eeeDepartment();
        sandhDepartment();
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
                        StaffData data = snapshot.getValue(StaffData.class);
                        if ("MCA".equals(data.getSpinner())){
                            list1.add(data);
                        }
                    }
                    mcaDept.setHasFixedSize(true);
                    mcaDept.setLayoutManager(new LinearLayoutManager(Faculty_FacultyActivity.this));
                    adapter = new StudentFacultyAdapter(list1,Faculty_FacultyActivity.this,"MCA");
                    mcaDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Faculty_FacultyActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void mbaDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    mbaNoData.setVisibility(View.VISIBLE);
                    mbaDept.setVisibility(View.GONE);
                } else {
                    mbaNoData.setVisibility(View.GONE);
                    mbaDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        StaffData data = snapshot.getValue(StaffData.class);
                        if ("MBA".equals(data.getSpinner())){
                            list2.add(data);
                        }
                    }
                    mbaDept.setHasFixedSize(true);
                    mbaDept.setLayoutManager(new LinearLayoutManager(Faculty_FacultyActivity.this));
                    adapter = new StudentFacultyAdapter(list2, Faculty_FacultyActivity.this, "MBA");
                    mbaDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Faculty_FacultyActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
                        StaffData data = snapshot.getValue(StaffData.class);
                        if ("Computer Science Engineering".equals(data.getSpinner())){
                            list3.add(data);
                        }
                    }
                    cseDept.setHasFixedSize(true);
                    cseDept.setLayoutManager(new LinearLayoutManager(Faculty_FacultyActivity.this));
                    adapter = new StudentFacultyAdapter(list3,Faculty_FacultyActivity.this,"Computer Science Engineering");
                    cseDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Faculty_FacultyActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mechDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    mechNoData.setVisibility(View.VISIBLE);
                    mechDept.setVisibility(View.GONE);
                }else {
                    mechNoData.setVisibility(View.GONE);
                    mechDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        StaffData data = snapshot.getValue(StaffData.class);
                        if ("Mechanical Engineering".equals(data.getSpinner())){
                            list4.add(data);
                        }

                    }
                    mechDept.setHasFixedSize(true);
                    mechDept.setLayoutManager(new LinearLayoutManager(Faculty_FacultyActivity.this));
                    adapter = new StudentFacultyAdapter(list4,Faculty_FacultyActivity.this,"Mechanical Engineering");
                    mechDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Faculty_FacultyActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void civilDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list5 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    civilNoData.setVisibility(View.VISIBLE);
                    civilDept.setVisibility(View.GONE);
                }else {
                    civilNoData.setVisibility(View.GONE);
                    civilDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        StaffData data = snapshot.getValue(StaffData.class);
                        if ("Civil Engineering".equals(data.getSpinner())){
                            list5.add(data);
                        }
                    }
                    civilDept.setHasFixedSize(true);
                    civilDept.setLayoutManager(new LinearLayoutManager(Faculty_FacultyActivity.this));
                    adapter = new StudentFacultyAdapter(list5,Faculty_FacultyActivity.this,"Civil Engineering");
                    civilDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Faculty_FacultyActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eceDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list6 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    eceNoData.setVisibility(View.VISIBLE);
                    eceDept.setVisibility(View.GONE);
                }else {
                    eceNoData.setVisibility(View.GONE);
                    eceDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        StaffData data = snapshot.getValue(StaffData.class);
                        if ("ECE".equals(data.getSpinner())){
                            list6.add(data);
                        }
                    }
                    eceDept.setHasFixedSize(true);
                    eceDept.setLayoutManager(new LinearLayoutManager(Faculty_FacultyActivity.this));
                    adapter = new StudentFacultyAdapter(list6,Faculty_FacultyActivity.this,"ECE");
                    eceDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Faculty_FacultyActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eeeDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list7 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    eeeNoData.setVisibility(View.VISIBLE);
                    eeeDept.setVisibility(View.GONE);
                }else {
                    eeeNoData.setVisibility(View.GONE);
                    eeeDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        StaffData data = snapshot.getValue(StaffData.class);
                        if ("EEE".equals(data.getSpinner())){
                            list7.add(data);
                        }
                    }
                    eeeDept.setHasFixedSize(true);
                    eeeDept.setLayoutManager(new LinearLayoutManager(Faculty_FacultyActivity.this));
                    adapter = new StudentFacultyAdapter(list7,Faculty_FacultyActivity.this,"EEE");
                    eeeDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Faculty_FacultyActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sandhDepartment() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list8 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    SandHNoData.setVisibility(View.VISIBLE);
                    SandHDept.setVisibility(View.GONE);
                }else {
                    SandHNoData.setVisibility(View.GONE);
                    SandHDept.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        StaffData data = snapshot.getValue(StaffData.class);
                        if ("S and H".equals(data.getSpinner())){
                            list8.add(data);
                        }
                    }
                    SandHDept.setHasFixedSize(true);
                    SandHDept.setLayoutManager(new LinearLayoutManager(Faculty_FacultyActivity.this));
                    adapter = new StudentFacultyAdapter(list8,Faculty_FacultyActivity.this,"S and H");
                    SandHDept.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Faculty_FacultyActivity.this,error.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}