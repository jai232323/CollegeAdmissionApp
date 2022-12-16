package com.example.pecpec.Staffs;

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
import com.example.pecpec.Staffs.Notice.NoticeAdapter;
import com.example.pecpec.Staffs.Notice.NoticeData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class DeleteNoticeActivity extends AppCompatActivity {

    private RecyclerView deleteNoticeRecyclerView;
    private LinearLayout mcaNoData;
    private ProgressBar progressBar;

    private ArrayList<NoticeData> list;
    private NoticeAdapter adapter;

    private DatabaseReference reference;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_notice);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Delete Notice");
        toolbar.setNavigationIcon(R.drawable.ic_back_btn);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteNoticeRecyclerView=findViewById(R.id.deleteNoticeRecyclerView);
        mcaNoData= findViewById(R.id.mcaNoData);
        progressBar=findViewById(R.id.progressBar);

        reference= FirebaseDatabase.getInstance().getReference("Notice");

        deleteNoticeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        deleteNoticeRecyclerView.setHasFixedSize(true);

        getNotice();

    }

    private void getNotice() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    mcaNoData.setVisibility(View.VISIBLE);
                    deleteNoticeRecyclerView.setVisibility(View.GONE);
                } else {
                    mcaNoData.setVisibility(View.GONE);
                    deleteNoticeRecyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        NoticeData data = snapshot.getValue(NoticeData.class);
                        list.add(0, data);
                    }
                    adapter = new NoticeAdapter(DeleteNoticeActivity.this, list);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    deleteNoticeRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(DeleteNoticeActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}