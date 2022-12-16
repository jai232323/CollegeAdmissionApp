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

public class SandHActivity extends AppCompatActivity {

    private SliderLayout sliderLayout;

    RecyclerView MCARecyclerView;
    LinearLayout MCANoData;

    private ArrayList<StaffData> list;
    private ShowStaffsDataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sand_hactivity);


        sliderLayout=findViewById(R.id.SandHslider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1);

        setSliderView();

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


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("S and H");
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

                        if ("S and H".equals(data.getSpinner())){
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
        for (int i=0;i<7;i++){
            DefaultSliderView sliderView = new DefaultSliderView(SandHActivity.this);

            switch (i){
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FS_and_H_Photos%2F1.jpg?alt=media&token=723c1590-ddf7-4581-84ac-27a66c130661");
                    break;
                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FS_and_H_Photos%2F2.jpg?alt=media&token=ca09fdf7-26c0-467d-93dd-14961914b4fe");
                    break;
                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FS_and_H_Photos%2F3.jpg?alt=media&token=65ec2f06-6ba1-42e6-870a-f9e18695b37f");
                    break;
                case 3:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FS_and_H_Photos%2F4.jpg?alt=media&token=1d38c766-42b8-4d9d-8c45-cd1bd9bed7e8");
                    break;
                case 4:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FS_and_H_Photos%2F5.jpg?alt=media&token=f33a694b-d769-4553-922a-b00d9798d70b");
                    break;
                case 5:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FS_and_H_Photos%2F6.jpg?alt=media&token=f4e946be-5be5-440f-a2d6-8e3141afdfc5");
                    break;
                case 6:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FS_and_H_Photos%2F7.jpg?alt=media&token=ca15b4b4-f219-4f01-a961-30d54b184cdd");
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }
}