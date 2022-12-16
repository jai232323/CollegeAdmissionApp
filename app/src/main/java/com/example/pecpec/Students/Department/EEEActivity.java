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

public class EEEActivity extends AppCompatActivity {


    private SliderLayout sliderLayout;

    RecyclerView MCARecyclerView;
    LinearLayout MCANoData;

    private ArrayList<StaffData> list;
    private ShowStaffsDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eeeactivity);

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

        sliderLayout=findViewById(R.id.EEEslider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1);

        setSliderView();



        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("EEE");
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

                        if ("EEE".equals(data.getSpinner())){
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
        for (int i=0;i<13;i++){
            DefaultSliderView sliderView = new DefaultSliderView(EEEActivity.this);

            switch (i){
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FEEE_Photos%2F1.jpg?alt=media&token=b5b938d7-465e-4459-bbd2-40825e19b427");
                    break;
                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FEEE_Photos%2F2%20(1).jpg?alt=media&token=fa511be8-d77a-49a3-b8c1-1a16e22f7fae");
                    break;
                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FEEE_Photos%2F2.jpg?alt=media&token=2f853595-a028-4b76-9145-a8642665a280");
                    break;
                case 3:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FEEE_Photos%2F3%20(1).jpg?alt=media&token=7e522453-4733-4145-8b98-c6551ac20399");
                    break;
                case 4:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FEEE_Photos%2F3.jpg?alt=media&token=c3b75fe0-b496-49bc-80ed-e857f69cc702");
                    break;
                case 5:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FEEE_Photos%2F4%20(1).jpg?alt=media&token=4f071a9a-8816-492f-bd25-e146fb288927");
                    break;
                case 6:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FEEE_Photos%2F4.jpg?alt=media&token=3108a483-cab4-43ce-baa8-e2c10dcc7dec");
                    break;
                case 7:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FEEE_Photos%2F5%20(1).jpg?alt=media&token=e316bde2-ca92-4a1a-b493-909a4a395877");
                    break;
                case 8:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FEEE_Photos%2F5.jpg?alt=media&token=e9dc90a0-ccba-4377-8a48-b9b742a829b8");
                    break;
                case 9:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FEEE_Photos%2F6.jpg?alt=media&token=e499e278-01c0-4b23-8a20-94b22afad47b");
                    break;
                case 10:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FEEE_Photos%2F7.jpg?alt=media&token=6b1e309f-696a-4b23-a0e0-24b9000742af");
                    break;
                case 11:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FEEE_Photos%2F8.jpg?alt=media&token=50f213ba-bcef-4336-84a6-79182c1f3f16");
                    break;
                case 12:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FEEE_Photos%2Fstudent_logo.jpg?alt=media&token=a4f7cf02-9abf-491a-bb55-d0d994575178");
                    break;

            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }

}