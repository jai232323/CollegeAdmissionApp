package com.example.pecpec.Students.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.pecpec.R;
import com.example.pecpec.Students.Gallery.StudentGalleryAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private RecyclerView convocationRecyclerView,independenceRecyclerView,pongalRecyclerView,diwaliRecyclerView,otherEventsRecyclerView;
    private RecyclerView NinetiththGraduationDayRecyclerView,
            twenty_twentyOne_GraduationDayRecyclerView,
            FirstYearInductionDay_AugDayRecyclerView,
            National_Conference_RIETMARecyclerView;

    private StudentGalleryAdapter adapter;

    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        convocationRecyclerView=view.findViewById(R.id.convocationRecyclerView);
        independenceRecyclerView=view.findViewById(R.id.independenceRecyclerView);
        pongalRecyclerView=view.findViewById(R.id.pongalRecyclerView);
        diwaliRecyclerView=view.findViewById(R.id.diwaliRecyclerView);
        otherEventsRecyclerView=view.findViewById(R.id.otherEventsRecyclerView);


        NinetiththGraduationDayRecyclerView=view.findViewById(R.id.NinetiththGraduationDayRecyclerView);
        twenty_twentyOne_GraduationDayRecyclerView=view.findViewById(R.id.twenty_twentyOne_GraduationDayRecyclerView);
        FirstYearInductionDay_AugDayRecyclerView=view.findViewById(R.id.FirstYearInductionDay_AugDayRecyclerView);
        National_Conference_RIETMARecyclerView=view.findViewById(R.id.National_Conference_RIETMARecyclerView);

        //reference= FirebaseDatabase.getInstance().getReference().child("gallery");
        reference= FirebaseDatabase.getInstance().getReference().child("gallery");

        getConvoImage();
        getIndependenceDyImage();
        getPongalImage();
        getDiwaliImage();
        getOtherImage();

        getNinetiththGraduationDayRecyclerView();
        gettwenty_twentyOne_GraduationDayRecyclerView();
        getFirstYearInductionDay_AugDayRecyclerView();
        getNational_Conference_RIETMARecyclerView();


        return view;
    }

    private void getNational_Conference_RIETMARecyclerView() {
        reference.child("National Conference - RIETMA2019").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data =(String) snapshot.getValue();
                    imageList.add(0,data);
                }

                adapter = new StudentGalleryAdapter(getContext(),imageList);
                National_Conference_RIETMARecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                National_Conference_RIETMARecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getFirstYearInductionDay_AugDayRecyclerView() {
        reference.child("First Year Induction Day - Aug'2019").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data =(String) snapshot.getValue();
                    imageList.add(0,data);
                }

                adapter = new StudentGalleryAdapter(getContext(),imageList);
                FirstYearInductionDay_AugDayRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                FirstYearInductionDay_AugDayRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void gettwenty_twentyOne_GraduationDayRecyclerView() {
        reference.child("Graduation Day Photo Gallery-2021").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data =(String) snapshot.getValue();
                    imageList.add(0,data);
                }

                adapter = new StudentGalleryAdapter(getContext(),imageList);
                twenty_twentyOne_GraduationDayRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                twenty_twentyOne_GraduationDayRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getNinetiththGraduationDayRecyclerView() {

        reference.child("19th Graduation Day").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data =(String) snapshot.getValue();
                    imageList.add(0,data);
                }

                adapter = new StudentGalleryAdapter(getContext(),imageList);
                NinetiththGraduationDayRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                NinetiththGraduationDayRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getOtherImage() {

        reference.child("Other Events").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data =(String) snapshot.getValue();
                    imageList.add(0,data);
                }

                adapter = new StudentGalleryAdapter(getContext(),imageList);
                otherEventsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                otherEventsRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getDiwaliImage() {
        reference.child("Diwali").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data =(String) snapshot.getValue();
                    imageList.add(0,data);
                }

                adapter = new StudentGalleryAdapter(getContext(),imageList);
                diwaliRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                diwaliRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getPongalImage() {
        reference.child("Pongal").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data =(String) snapshot.getValue();
                    imageList.add(0,data);
                }

                adapter = new StudentGalleryAdapter(getContext(),imageList);
                pongalRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                pongalRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getIndependenceDyImage() {
        reference.child("Independence Day").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data =(String) snapshot.getValue();
                    imageList.add(0,data);
                }

                adapter = new StudentGalleryAdapter(getContext(),imageList);
                independenceRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                independenceRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getConvoImage() {
        reference.child("Convocation").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data =(String) snapshot.getValue();
                    imageList.add(0,data);
                }

                adapter = new StudentGalleryAdapter(getContext(),imageList);
                convocationRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
                convocationRecyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}