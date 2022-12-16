package com.example.pecpec.Students.Department;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class MCAActivity extends AppCompatActivity {


    private SliderLayout sliderLayout;

    RecyclerView MCARecyclerView;
    LinearLayout MCANoData;

    private ArrayList<StaffData> list;
    private ShowStaffsDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcaactivity);



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

        sliderLayout=findViewById(R.id.MCAslider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1);

        setSliderView();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("MCA");
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

                        if ("MCA".equals(data.getSpinner())){
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


        //3.https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F3.jpg?alt=media&token=ec978731-bd58-4706-9a35-99a6f1479521
        //4.https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F4.jpg?alt=media&token=2bf24fd5-0bfd-45e2-9f3d-450e672d6ebd
        //5.https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F5.jpg?alt=media&token=eb48e6bf-f804-4605-b444-af748e55d61c
        //6. https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F6.jpg?alt=media&token=f1f64a5a-5512-41e9-b663-f3ef4f9ba0eb
        //7.https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F7.jpg?alt=media&token=3fe89755-e13e-4609-adeb-9be8cc82d748
        //8.https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F8.jpg?alt=media&token=abafbbe8-e92d-4d83-a352-61c61976359a
        //9.https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F9.jpg?alt=media&token=af5f7fc0-7769-48ed-bb04-3a2a29eee6e6
        //10.https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F10.jpg?alt=media&token=4bd7a608-da37-4acb-873c-ae412470b6b8
        //11.https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F10.jpg?alt=media&token=4bd7a608-da37-4acb-873c-ae412470b6b8
        //12.https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F12.jpg?alt=media&token=7622c761-a16a-4904-b24b-055641859ff8
        //13.https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F13.jpg?alt=media&token=f5835235-25ff-4521-a772-794fc706e229
        //14.https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F14.jpg?alt=media&token=c196ed7a-3d6e-4739-b5b7-979ae6256b4c
        //15.https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F15.jpg?alt=media&token=a2f91321-6e0e-499e-81da-1e3ae2184f9f
        //16.https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F16.jpg?alt=media&token=1efcb66a-04d7-4019-8d1b-f9ea5817a3e4
        //pooja https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2Fpooja%20copy.jpg?alt=media&token=f24cd5fb-efdb-4a40-875e-ec24c8ac740d


        for (int i=0;i<15;i++){
            DefaultSliderView sliderView = new DefaultSliderView(MCAActivity.this);

            switch (i){
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F3.jpg?alt=media&token=ec978731-bd58-4706-9a35-99a6f1479521");
                    break;
                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F4.jpg?alt=media&token=2bf24fd5-0bfd-45e2-9f3d-450e672d6ebd");
                    break;
                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F5.jpg?alt=media&token=eb48e6bf-f804-4605-b444-af748e55d61c");
                    break;
                case 3:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F6.jpg?alt=media&token=f1f64a5a-5512-41e9-b663-f3ef4f9ba0eb");
                    break;
                case 4:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F4.jpg?alt=media&token=2bf24fd5-0bfd-45e2-9f3d-450e672d6ebd");
                    break;
                case 5:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F5.jpg?alt=media&token=eb48e6bf-f804-4605-b444-af748e55d61c");
                    break;
                case 6:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F6.jpg?alt=media&token=f1f64a5a-5512-41e9-b663-f3ef4f9ba0eb");
                    break;
                case 7:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F7.jpg?alt=media&token=3fe89755-e13e-4609-adeb-9be8cc82d748");
                    break;
                case 8:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F8.jpg?alt=media&token=abafbbe8-e92d-4d83-a352-61c61976359a");
                    break;
                case 9:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F9.jpg?alt=media&token=af5f7fc0-7769-48ed-bb04-3a2a29eee6e6");
                    break;
                case 10:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F10.jpg?alt=media&token=4bd7a608-da37-4acb-873c-ae412470b6b8");
                    break;
                case 11:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F11.jpg?alt=media&token=f9220293-4206-488d-b3c8-ecb9f4595777");
                    break;
                case 12:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F12.jpg?alt=media&token=7622c761-a16a-4904-b24b-055641859ff8");
                    break;
                case 13:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F13.jpg?alt=media&token=f5835235-25ff-4521-a772-794fc706e229");
                    break;
                case 14:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2F14.jpg?alt=media&token=c196ed7a-3d6e-4739-b5b7-979ae6256b4c");
                    break;
                case 15:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/Department%20Photos%2FMCA_Photos%2Fpooja%20copy.jpg?alt=media&token=f24cd5fb-efdb-4a40-875e-ec24c8ac740d");
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }

}