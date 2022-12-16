package com.example.pecpec.Students;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pecpec.R;
import com.example.pecpec.Staffs.Admission.AdmissionFirstBEAdapter;
import com.example.pecpec.Staffs.Admission.AdmissionStaffData;
import com.example.pecpec.Staffs.Admission.FirstyearBEActivityStaff;
import com.example.pecpec.Students.Admission.LateralEnterBEActivity;
import com.example.pecpec.Students.AdmissionForms.FirstYearAdmissionFormsAdapter;
import com.example.pecpec.Students.AdmissionForms.LateralEnterBEAdmissionFormAdapter;
import com.example.pecpec.Students.AdmissionForms.MCAMBAAdmissionFormAdapter;
import com.example.pecpec.Students.AdmissionForms.MEAdmissionFormAdaapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentAdmissionFormActivity extends AppCompatActivity {

    private RecyclerView firstyearRecyclerView,Lateral_Entry_BERecyclerView,MERecyclerView,MBAMCARecyclerView;
    private LinearLayout firstyearData;
    private List<AdmissionStaffData> list1,list2,list3,list4;

    private FirstYearAdmissionFormsAdapter adapter;
    private LateralEnterBEAdmissionFormAdapter adapter1;
    private MEAdmissionFormAdaapter adapter2;
    private MCAMBAAdmissionFormAdapter adapter3;

    DatabaseReference reference , dbRef;

    String userid,Course,userIDUniqueKey;
    FirebaseUser firebaseUser;

    private TextView firstyear1,lateral2,me1,mbamca1;
    View firstView,lateralView,meView,mbamcaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_admission_form);

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

        firstyearRecyclerView=findViewById(R.id.firstyearRecyclerView);
        Lateral_Entry_BERecyclerView=findViewById(R.id.Lateral_Entry_BERecyclerView);
        MBAMCARecyclerView=findViewById(R.id.MBAMCARecyclerView);
        MERecyclerView=findViewById(R.id.MERecyclerView);
        firstyearData=findViewById(R.id.firstyearData);

        firstyear1=findViewById(R.id.firstyear1);
        lateral2=findViewById(R.id.lateral2);
        me1=findViewById(R.id.me1);
        mbamca1=findViewById(R.id.mbamca1);

        firstView=findViewById(R.id.firstView);
        lateralView=findViewById(R.id.lateralView);
        meView=findViewById(R.id.meView);
        mbamcaView=findViewById(R.id.mbamcaView);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


      //  reference= FirebaseDatabase.getInstance().getReference("First Year BE Admission Forms");

        SharedPreferences prefs1 = this.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        userid=prefs1.getString("userid","none");

        SharedPreferences prefs2 = this.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        Course=prefs2.getString("Course","none");

        SharedPreferences prefs3 = this.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        userIDUniqueKey=prefs3.getString("userIDUniqueKey","none");

        firstYearAdmissionForm();
        lateralEntryAdmissionForm();
        meAdmissionForm();
        mcambaAdmissionForm();
    }

    private void mcambaAdmissionForm() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("MCA MBA Admission Forms");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();

                if (!dataSnapshot.exists()) {
                    firstyearData.setVisibility(View.VISIBLE);
                    MBAMCARecyclerView.setVisibility(View.GONE);
                } else
                {
                    mbamca1.setVisibility(View.VISIBLE);
                    mbamcaView.setVisibility(View.VISIBLE);
                    firstyearData.setVisibility(View.GONE);
                    MBAMCARecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);

                        assert data != null;
                        if (firebaseUser.getUid().equals(data.getUserid())){
                            list4.add(0,data);
                        }
                    }
                    MBAMCARecyclerView.setHasFixedSize(true);
                    MBAMCARecyclerView.setLayoutManager(new LinearLayoutManager(StudentAdmissionFormActivity.this));
                    adapter3 = new MCAMBAAdmissionFormAdapter(list4, StudentAdmissionFormActivity.this, Course);
                    MBAMCARecyclerView.setAdapter(adapter3);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentAdmissionFormActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void meAdmissionForm() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("ME Admission Forms");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();

                if (!dataSnapshot.exists()) {
                    firstyearData.setVisibility(View.VISIBLE);
                    MERecyclerView.setVisibility(View.GONE);
                } else
                {
                    meView.setVisibility(View.VISIBLE);
                    me1.setVisibility(View.VISIBLE);
                    firstyearData.setVisibility(View.GONE);
                    MERecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);

                        assert data != null;
                        if (firebaseUser.getUid().equals(data.getUserid())){
                            list3.add(0,data);
                        }
                    }
                    MERecyclerView.setHasFixedSize(true);
                    MERecyclerView.setLayoutManager(new LinearLayoutManager(StudentAdmissionFormActivity.this));
                    adapter2 = new MEAdmissionFormAdaapter(list3, StudentAdmissionFormActivity.this, Course);
                    MERecyclerView.setAdapter(adapter2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentAdmissionFormActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void lateralEntryAdmissionForm() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Lateral Entry BE Admission Forms");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();

                if (!dataSnapshot.exists()) {
                    lateralView.setVisibility(View.GONE);
                    lateral2.setVisibility(View.GONE);
                    firstyearData.setVisibility(View.VISIBLE);
                    Lateral_Entry_BERecyclerView.setVisibility(View.GONE);
                } else
                {
                    lateralView.setVisibility(View.VISIBLE);
                    lateral2.setVisibility(View.VISIBLE);
                    firstyearData.setVisibility(View.GONE);
                    Lateral_Entry_BERecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);

                        assert data != null;
                        if (firebaseUser.getUid().equals(data.getUserid())){
                            list2.add(0,data);
                        }
                    }
                    Lateral_Entry_BERecyclerView.setHasFixedSize(true);
                    Lateral_Entry_BERecyclerView.setLayoutManager(new LinearLayoutManager(StudentAdmissionFormActivity.this));
                    adapter1 = new LateralEnterBEAdmissionFormAdapter(list2, StudentAdmissionFormActivity.this, Course);
                    Lateral_Entry_BERecyclerView.setAdapter(adapter1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentAdmissionFormActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void firstYearAdmissionForm() {

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("First Year BE Admission Forms");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();

                if (!dataSnapshot.exists()) {
                    firstyearData.setVisibility(View.VISIBLE);
                    firstyearRecyclerView.setVisibility(View.GONE);
                } else
                    {
                        firstView.setVisibility(View.VISIBLE);
                        firstyear1.setVisibility(View.VISIBLE);
                    firstyearData.setVisibility(View.GONE);
                    firstyearRecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        AdmissionStaffData data = snapshot.getValue(AdmissionStaffData.class);

                        assert data != null;
                        if (firebaseUser.getUid().equals(data.getUserid())){
                            list1.add(0,data);
                        }
                    }
                    firstyearRecyclerView.setHasFixedSize(true);
                    firstyearRecyclerView.setLayoutManager(new LinearLayoutManager(StudentAdmissionFormActivity.this));
                    adapter = new FirstYearAdmissionFormsAdapter(list1, StudentAdmissionFormActivity.this, Course);
                    firstyearRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentAdmissionFormActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}