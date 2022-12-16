package com.example.pecpec.Students.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.pecpec.R;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {

    //    private ViewPager viewPager;
//    private AboutBranchAdapter adapter;
//    private List<AboutBranchModel> list;

    private ImageView map;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);




//
//        list=new ArrayList<>();
//
//        list.add(new AboutBranchModel(R.drawable.ic_mcacse,"MCA1","The Department of Master of Computer Applications was started with a sanctioned intake of 30 in the year 1998. In 2000 the intake increased to 60. The department of Computer Applications established in the year 1998 was affiliated to University of Madras, Chennai. From 2002 batch onwards the course is affiliated to Anna University, Chennai"));
//        list.add(new AboutBranchModel(R.drawable.ic_mba,"MBA","The Department of Management studies (DoMS) has been functioning since 2000 and at present the Department intake is 120 students under the permanent affiliation of Anna University, Approved by AICTE and the government of Tamilnadu"));
//        list.add(new AboutBranchModel(R.drawable.ic_mcacse,"Computer Science Engineering","The Department was established with the mission to educate students from rural areas, so that they become enlightened individuals, improving the living standards of their Families, Industry and Society. The Post Graduate Programme viz M.E – Computer Science and engineering was introduced in the year 2014. The department is equipped with well qualified and experienced faculty members to improve and enhance the knowledge of the student community."));
//        list.add(new AboutBranchModel(R.drawable.ic_mech1,"Mechanical Engineering","The first batch of Mechanical Engineers graduated in the year 2003. The department was first started with the intake strength of 40 seats in 1999. After, the intake was increased to 60 seats in 2004. At present the department offers 120 seats from 2014. The department has both undergraduate and postgraduate courses in Mechanical Engineering and Design. In the year 2013 Post Graduate Programme in Engineering Design was launched."));
//        list.add(new AboutBranchModel(R.drawable.ic_civil,"Civil Engineering","The department aims to enrich the knowledge of students in both theory and practice with a team of highly qualified faculty with rich experience in the diversified streams of Civil Engineering. The department of Civil Engineering is equipped with state-of-the-art laboratory facilities having advanced equipments and machineries operated by experienced technicians."));
//        list.add(new AboutBranchModel(R.drawable.ic_ece,"ECE","with the intake of 60 students with the objective of imparting quality education in the field of Electronics and Communication and the intake was increased to 120 in the year 2013. The department started M.E.Communication System in the year 2014 with an intake of 24 students. At present, the department is offering an undergraduate course in Electronics and Communication Engineering and one post graduate course in Communication Systems."));
//        list.add(new AboutBranchModel(R.drawable.ic_ece,"EEE","It is the power for Green India, bringing out greenery worldwide. There is efficient staff in our department with specialization in various areas. Our lab is well equipped and modernized with the latest technology. EEE paves a platform for the young talented minds to enrich the knowledge. We guide the students in displaying their ability, talents & stamina for putting forth their sustained hardwork. EEE department is equipped with AC Machines, DC Machines, Power Electronics, Integrated Circuits, Electrical Workshops, Microprocessor and Micro controller..."));
//        list.add(new AboutBranchModel(R.drawable.ic_s_h,"S and H","This department, the backbone of the entire engineering education process, is efficiently supporting the engineering departments by providing high quality of training in basic sciences. Headed by the most experienced and highly qualified professor, this department has Mathematics, Physics, Chemistry and English under its canopy."));
//

//        adapter = new AboutBranchAdapter(getContext(),list);
//
//        viewPager=view.findViewById(R.id.viewpager_about);
//        viewPager.setAdapter(adapter);

        ImageView imageView = view.findViewById(R.id.colleg_image_about);
        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F02.jpg?alt=media&token=05508e42-1d79-46fa-b2e1-55d8800e081d")
                .into(imageView);



        map=view.findViewById(R.id.pec_map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        return view;
    }

    private void openMap() {

        Uri uri =Uri.parse("geo:0, 0?q=Priyadarshini Engineering College, Priyadarshini Engineering College, Chettiyappanur, Vaniyambadi, Tamil Nadu");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

}