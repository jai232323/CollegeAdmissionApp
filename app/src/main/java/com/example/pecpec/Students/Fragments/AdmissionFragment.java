package com.example.pecpec.Students.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pecpec.R;
import com.example.pecpec.Students.Admission.FirstyearBEActivity;
import com.example.pecpec.Students.Admission.LateralEnterBEActivity;
import com.example.pecpec.Students.Admission.MCAMBAActivity;
import com.example.pecpec.Students.Admission.MEActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.List;


public class AdmissionFragment extends Fragment {
//
//    private RecyclerView admissionstudentRecyclerview;
//    private List<AdmissionData1> list;
//    private Context mContext;
//    private AdmissionInfoAdapter admissionInfoAdapter;

    private DatabaseReference reference;

    private MaterialCardView First_Year_BE,Lateral_Entry_BE,ME,MBA_MCA;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admission, container, false);


        //  admissionstudentRecyclerview=view.findViewById(R.id.admissionstudentRecyclerview);
        // reference= FirebaseDatabase.getInstance().getReference("StudentDetails");

        //getInfo();

        First_Year_BE=view.findViewById(R.id.First_Year_BE);
        Lateral_Entry_BE=view.findViewById(R.id.Lateral_Entry_BE);
        ME=view.findViewById(R.id.ME);
        MBA_MCA=view.findViewById(R.id.MBA_MCA);


        First_Year_BE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                First_Year_BEActivity();
            }
        });

        Lateral_Entry_BE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lateral_Entry_BEActivity();
            }
        });
        ME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MEActivity();
            }
        });
        MBA_MCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MBA_MCAActivity();
            }
        });

        return  view;
    }

//    private void getInfo() {
//
//        String userid = "";
//        reference.child(userid).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    AdmissionData1 data = snapshot.getValue(AdmissionData1.class);
//                    list.add(data);
//                }
//                admissionstudentRecyclerview.setHasFixedSize(true);
//                admissionstudentRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
//                admissionInfoAdapter = new AdmissionInfoAdapter(list,mContext);
//                admissionstudentRecyclerview.setAdapter(admissionInfoAdapter);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//                Toast.makeText(mContext,error.getMessage() , Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }

    private void MBA_MCAActivity() {
        Intent intent = new Intent(getContext(), MCAMBAActivity.class);
        startActivity(intent);
    }

    private void MEActivity() {
        Intent intent = new Intent(getContext(), MEActivity.class);
        startActivity(intent);
    }

    private void Lateral_Entry_BEActivity() {
        Intent intent = new Intent(getContext(), LateralEnterBEActivity.class);
        startActivity(intent);
    }

    private void First_Year_BEActivity() {

        Intent intent = new Intent(getContext(), FirstyearBEActivity.class);
        startActivity(intent);

    }
}