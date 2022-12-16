package com.example.pecpec.Students.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pecpec.R;
import com.example.pecpec.Students.Department.CIVILActivity;
import com.example.pecpec.Students.Department.CSEActivity;
import com.example.pecpec.Students.Department.ECEActivity;
import com.example.pecpec.Students.Department.EEEActivity;
import com.example.pecpec.Students.Department.MBAActivity;
import com.example.pecpec.Students.Department.MCAActivity;
import com.example.pecpec.Students.Department.MECHActivity;
import com.example.pecpec.Students.Department.SandHActivity;
import com.google.android.material.card.MaterialCardView;


public class DepartmentFragment extends Fragment   {


    MaterialCardView mca,mba,cse,mech,civil,ece,eee,SandH;
    Fragment selectedFragment = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_department, container, false);

        mca=view.findViewById(R.id.mca);
        mba=view.findViewById(R.id.mba);
        cse=view.findViewById(R.id.cse);
        mech=view.findViewById(R.id.mech);
        civil=view.findViewById(R.id.civil);
        ece=view.findViewById(R.id.ece);
        eee=view.findViewById(R.id.eee);
        SandH=view.findViewById(R.id.SandH);


        mca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     Intent intent = new Intent(DepartmentFragment.this, CreateStaffDetails.class);
                openMcaDept();
            }
        });

        mba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     Intent intent = new Intent(DepartmentFragment.this, CreateStaffDetails.class);
                openMbaDept();
            }
        });


        cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     Intent intent = new Intent(DepartmentFragment.this, CreateStaffDetails.class);
                openCseDept();
            }
        });


        mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     Intent intent = new Intent(DepartmentFragment.this, CreateStaffDetails.class);
                openMechDept();
            }
        });


        civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     Intent intent = new Intent(DepartmentFragment.this, CreateStaffDetails.class);
                openCivilDept();
            }
        });


        ece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     Intent intent = new Intent(DepartmentFragment.this, CreateStaffDetails.class);
                openECEDept();
            }
        });


        eee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     Intent intent = new Intent(DepartmentFragment.this, CreateStaffDetails.class);
                openEEEDept();
            }
        });

        SandH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     Intent intent = new Intent(DepartmentFragment.this, CreateStaffDetails.class);
                openSandHDept();
            }
        });




        return view;
    }

    private void openSandHDept() {
        Intent intent = new Intent(getContext(), SandHActivity.class);
        startActivity(intent);
    }

    private void openEEEDept() {
        Intent intent = new Intent(getContext(), EEEActivity.class);
        startActivity(intent);
    }

    private void openECEDept() {
        Intent intent = new Intent(getContext(), ECEActivity.class);
        startActivity(intent);
    }

    private void openCivilDept() {
        Intent intent = new Intent(getContext(), CIVILActivity.class);
        startActivity(intent);
    }

    private void openMechDept() {
        Intent intent = new Intent(getContext(), MECHActivity.class);
        startActivity(intent);
    }

    private void openCseDept() {
        Intent intent = new Intent(getContext(), CSEActivity.class);
        startActivity(intent);
    }

    private void openMbaDept() {
        Intent intent = new Intent(getContext(), MBAActivity.class);
        startActivity(intent);
    }

    private void openMcaDept() {

        Intent intent = new Intent(getContext(), MCAActivity.class);
        startActivity(intent);
    }


}