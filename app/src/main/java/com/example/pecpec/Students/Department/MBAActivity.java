package com.example.pecpec.Students.Department;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.pecpec.R;
import com.example.pecpec.Staffs.Faculty.StaffData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;

public class MBAActivity extends AppCompatActivity {

    private SliderLayout sliderLayout;

    RecyclerView MCARecyclerView;
    LinearLayout MCANoData;

    private ArrayList<StaffData> list;
    private ShowStaffsDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mbaactivity);

        MCARecyclerView = findViewById(R.id.MCARecyclerView);
        MCANoData = findViewById(R.id.MCANoData);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);


        MCARecyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        adapter = new ShowStaffsDataAdapter(this,list);
        MCARecyclerView.setAdapter(adapter);

        getData();

        sliderLayout=findViewById(R.id.MBAslider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1);

        setSliderView();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("MBA");
        toolbar.setNavigationIcon(R.drawable.ic_back_btn);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Staffs");

        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                if (!snapshot.exists()) {
                    MCANoData.setVisibility(View.VISIBLE);
                    MCARecyclerView.setVisibility(View.INVISIBLE);

                } else {
                    MCANoData.setVisibility(View.INVISIBLE);
                    MCARecyclerView.setVisibility(View.VISIBLE);

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {


                        StaffData data = dataSnapshot.getValue(StaffData.class);

                        String value = dataSnapshot.child(data.getUserdbpushKey()).child("MCA").getValue(String.class);

                        if ("MBA".equals(data.getSpinner())){
                            list.add(0, data);
                        }else {
                            MCANoData.setVisibility(View.VISIBLE);
                        }


                        adapter.notifyDataSetChanged();
                    }
                    adapter.notifyDataSetChanged();



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void setSliderView() {
        for (int i=0;i<4;i++){
            DefaultSliderView sliderView = new DefaultSliderView(MBAActivity.this);

            switch (i){
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMBA_Photos%2F1.jpg?alt=media&token=1add3bb5-fe7c-49fd-b0f0-cd828b22e9f6");
                    break;
                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMBA_Photos%2F2.jpg?alt=media&token=f3d4ba18-44da-4680-968a-534af9cfc4b5");
                    break;
                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMBA_Photos%2F3.jpg?alt=media&token=71ae5c98-cadd-4ea1-92a7-0c6947a55775");
                    break;
                case 3:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMBA_Photos%2F4.jpg?alt=media&token=4123e5dc-ef03-492a-b9db-eb9094ccba18");
                    break;


            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }
}