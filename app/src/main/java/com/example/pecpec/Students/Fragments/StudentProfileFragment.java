package com.example.pecpec.Students.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pecpec.FullImagerActivity;
import com.example.pecpec.R;
import com.example.pecpec.Students.StudentAdmissionFormActivity;
import com.example.pecpec.Students.StudentAdmissionResultActivity;
import com.example.pecpec.Students.StudentData;
import com.example.pecpec.Students.StudentLoginActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentProfileFragment extends Fragment {


    TextView Student_Name,Student_Email1;
    DatabaseReference reference;
    String userid;
    ImageView Student_Image;
    MaterialCardView StudentAdmissionForm,StudentAdmissionResult;

    //Logout
    LinearLayout Logout;
    FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_profile, container, false);

        Student_Name=view.findViewById(R.id.Student_Name1);
        Student_Email1=view.findViewById(R.id.Student_Email1);
        Student_Image=view.findViewById(R.id.Student_Image1);
        StudentAdmissionForm=view.findViewById(R.id.StudentAdmissionForm);
        StudentAdmissionResult=view.findViewById(R.id.StudentAdmissionResult);


        SharedPreferences prefs1 = getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        userid=prefs1.getString("userid","none");

        firebaseAuth= FirebaseAuth.getInstance();

        Logout=view.findViewById(R.id.Logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Students")
                        .child(userid);
                reference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        firebaseAuth.signOut();
                        Toast.makeText(getContext(), "Logout Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getContext(), StudentLoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        StudentAdmissionForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), StudentAdmissionFormActivity.class));
            }
        });
        StudentAdmissionResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), StudentAdmissionResultActivity.class));
            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("Students");




        getStudentData();

        return view;


    }

    private void getStudentData() {

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Students")
                .child(userid);

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ((getContext() == null)){
                    return;
                }
                StudentData data = snapshot.getValue(StudentData.class);


//                assert data != null;
                Glide.with(getContext()).load(data.getS_Image()).into(Student_Image);

                Student_Image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), FullImagerActivity.class);
                        intent.putExtra("image",data.getS_Image());
                        getContext().startActivity(intent);
                    }
                });

                Student_Name.setText(data.getS_Name());
                Student_Email1.setText(data.getS_Email());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}