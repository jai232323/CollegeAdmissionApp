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

public class ECEActivity extends AppCompatActivity {


    private SliderLayout sliderLayout;
    RecyclerView MCARecyclerView;
    LinearLayout MCANoData;

    private ArrayList<StaffData> list;
    private ShowStaffsDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eceactivity);


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

        sliderLayout=findViewById(R.id.eceslider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1);

        setSliderView();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ECE");
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

                        if ("ECE".equals(data.getSpinner())){
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
            DefaultSliderView sliderView = new DefaultSliderView(ECEActivity.this);

            switch (i){
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FECE_Photos%2F1a.jpg?alt=media&token=23ab3dc4-7d04-44e2-9d7b-ff3551bb8dbf");
                    break;
                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FECE_Photos%2F1b.jpg?alt=media&token=94dccbd3-06fe-425d-ac1f-3b63144f8cf2");
                    break;
                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FECE_Photos%2F1c.jpg?alt=media&token=b03769a8-1cd7-4b35-a77b-a54725aedd74");
                    break;
                case 3:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FECE_Photos%2F1d.jpg?alt=media&token=00b883fb-bd5c-48d8-8591-6872d0ce8e84");
                    break;
                case 4:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FECE_Photos%2F6.jpg?alt=media&token=9eeff710-0e0f-4fbf-919e-4f26b659bd44");
                    break;
                case 5:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FECE_Photos%2F9.jpg?alt=media&token=210bbcc9-905b-46ab-bf27-333c66cb32a5");
                    break;
                case 6:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FECE_Photos%2F10.jpg?alt=media&token=8e3c0138-9cbd-426e-8445-8cce53d53ffc");
                    break;

            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }
}