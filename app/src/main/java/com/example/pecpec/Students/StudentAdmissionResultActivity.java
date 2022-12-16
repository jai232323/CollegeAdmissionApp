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
import com.example.pecpec.Staffs.Admission.AdmissionStaffData;
import com.example.pecpec.Students.AdmissionForms.FirstYearAdmissionFormsAdapter;
import com.example.pecpec.Students.AdmissionForms.MCAMBAAdmissionFormAdapter;
import com.example.pecpec.Students.AdmissionResults.FirstYearResultAdapter;
import com.example.pecpec.Students.AdmissionResults.LateralEntryResultAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentAdmissionResultActivity extends AppCompatActivity {


    private RecyclerView firstyearRecyclerView,Lateral_Entry_BERecyclerView,MERecyclerView,MBAMCARecyclerView;
    private LinearLayout firstyearData;
    private List<AdmissionStaffData> list1,list2,list3,list4;

    private FirstYearResultAdapter adapter;
    private FirstYearResultAdapter adapter1;
    private FirstYearResultAdapter adapter2;
    private FirstYearResultAdapter adapter3;

    TextView firstData;

    String Course , userIDUniqueKey;

    private TextView firstyear1,lateral2,me1,mbamca1;
    View firstView,lateralView,meView,mbamcaView;

    FirebaseUser firebaseUser;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_admission_result);

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

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        firstyearRecyclerView=findViewById(R.id.firstyearRecyclerView);
        Lateral_Entry_BERecyclerView=findViewById(R.id.Lateral_Entry_BERecyclerView);
        MBAMCARecyclerView=findViewById(R.id.MBAMCARecyclerView);
        MERecyclerView=findViewById(R.id.MERecyclerView);
        firstyearData=findViewById(R.id.firstyearData1);
        firstData=findViewById(R.id.firstData);

        firstyear1=findViewById(R.id.firstyear1);
        lateral2=findViewById(R.id.lateral2);
        me1=findViewById(R.id.me1);
        mbamca1=findViewById(R.id.mbamca1);

        firstView=findViewById(R.id.firstView);
        lateralView=findViewById(R.id.lateralView);
        meView=findViewById(R.id.meView);
        mbamcaView=findViewById(R.id.mbamcaView);


        SharedPreferences prefs1 = this.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        userid=prefs1.getString("userid","none");


        SharedPreferences prefs2 = this.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        Course=prefs2.getString("Course","none");

        SharedPreferences prefs3 = this.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        userIDUniqueKey=prefs3.getString("userIDUniqueKey","none");

        firstyearResult();
        lateralbeResult();
        meResult();
        McaMbaResult();
    }

    private void firstyearResult() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance()
                .getReference("First Year BE Admission Forms Staff Verified");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();

                if (!dataSnapshot.exists()) {
                    firstyearData.setVisibility(View.VISIBLE);
                    firstyearRecyclerView.setVisibility(View.GONE);
                } else
                {
                    mbamca1.setVisibility(View.VISIBLE);
                    mbamcaView.setVisibility(View.VISIBLE);
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
                    firstyearRecyclerView.setLayoutManager(new LinearLayoutManager(StudentAdmissionResultActivity.this));
                    adapter = new FirstYearResultAdapter(list1, StudentAdmissionResultActivity.this, Course);
                    firstyearRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentAdmissionResultActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void lateralbeResult() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance()
                .getReference("Lateral Entry BE Admission Forms Staff Verified");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();

                if (!dataSnapshot.exists()) {
                    firstyearData.setVisibility(View.VISIBLE);
                    Lateral_Entry_BERecyclerView.setVisibility(View.GONE);
                } else
                {
                    mbamca1.setVisibility(View.VISIBLE);
                    mbamcaView.setVisibility(View.VISIBLE);
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
                    Lateral_Entry_BERecyclerView.setLayoutManager(new LinearLayoutManager(StudentAdmissionResultActivity.this));
                    adapter1 = new FirstYearResultAdapter(list2, StudentAdmissionResultActivity.this, Course);
                    Lateral_Entry_BERecyclerView.setAdapter(adapter1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentAdmissionResultActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void meResult() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance()
                .getReference("ME Admission Forms Staff Verified");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();

                if (!dataSnapshot.exists()) {
                    firstyearData.setVisibility(View.VISIBLE);
                    MERecyclerView.setVisibility(View.GONE);
                } else
                {
                    mbamca1.setVisibility(View.VISIBLE);
                    mbamcaView.setVisibility(View.VISIBLE);
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
                    MERecyclerView.setLayoutManager(new LinearLayoutManager(StudentAdmissionResultActivity.this));
                    adapter2 = new FirstYearResultAdapter(list3, StudentAdmissionResultActivity.this, Course);
                    MERecyclerView.setAdapter(adapter2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentAdmissionResultActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void McaMbaResult() {
        DatabaseReference reference1 = FirebaseDatabase.getInstance()
                .getReference("MCA MBA Admission Forms Admission Forms Staff Verified");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();

                if (!dataSnapshot.exists()) {
                 //   firstyearData.setVisibility(View.VISIBLE);
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
                    MBAMCARecyclerView.setLayoutManager(new LinearLayoutManager(StudentAdmissionResultActivity.this));
                    adapter3 = new FirstYearResultAdapter(list4, StudentAdmissionResultActivity.this, Course);
                    MBAMCARecyclerView.setAdapter(adapter3);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentAdmissionResultActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}