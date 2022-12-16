package com.example.pecpec.Students;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.pecpec.R;
import com.example.pecpec.Staffs.Notice.NoticeData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Notice_NoticeActivity extends AppCompatActivity {

    private RecyclerView NoticeRecyclerView_Student;
    private LinearLayout mcaNoData;
    private ProgressBar NoticeProgressBar_Student;

    private ArrayList<NoticeData> list;
    private NoticeAdapter_Student adapter;

    private DatabaseReference reference;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_notice);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Notice");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        NoticeRecyclerView_Student=findViewById(R.id.NoticeRecyclerView_Student);
        mcaNoData= findViewById(R.id.mcaNoData);
        NoticeProgressBar_Student=findViewById(R.id.NoticeProgressBar_Student);

        reference= FirebaseDatabase.getInstance().getReference("Notice");

        NoticeRecyclerView_Student.setLayoutManager(new LinearLayoutManager(Notice_NoticeActivity.this));
        NoticeRecyclerView_Student.setHasFixedSize(true);

        getNotice();
    }
    private void getNotice() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                //   list = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    mcaNoData.setVisibility(View.VISIBLE);
                    NoticeRecyclerView_Student.setVisibility(View.GONE);
                } else {
                    mcaNoData.setVisibility(View.GONE);
                    NoticeRecyclerView_Student.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        NoticeData data = snapshot.getValue(NoticeData.class);
                        list.add(0, data);
                    }
                    adapter = new NoticeAdapter_Student(Notice_NoticeActivity.this, list);
                    adapter.notifyDataSetChanged();
                    NoticeProgressBar_Student.setVisibility(View.GONE);
                    NoticeRecyclerView_Student.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                NoticeProgressBar_Student.setVisibility(View.GONE);
                Toast.makeText(Notice_NoticeActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}