package com.example.pecpec.Students.Fragments;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pecpec.R;
import com.example.pecpec.Students.Department.CIVILActivity;
import com.example.pecpec.Students.Department.CSEActivity;
import com.example.pecpec.Students.Department.ECEActivity;
import com.example.pecpec.Students.Department.EEEActivity;
import com.example.pecpec.Students.Department.MBAActivity;
import com.example.pecpec.Students.Department.MCAActivity;
import com.example.pecpec.Students.Department.MECHActivity;
import com.example.pecpec.Students.Department.SandHActivity;
import com.example.pecpec.Students.Home.CorredpondentActivity;
import com.example.pecpec.Students.Home.OnlineAdmissionActivity;
import com.example.pecpec.Students.Home.PrincipalActiviity;
import com.google.android.material.card.MaterialCardView;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;


public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;
    private ImageView map;

    TextView mca,mba,cse,mech,civil,ece,eee,SandH;

    private TextView forOnlineAdmissionForm;

    MaterialCardView principal,corredpondent,admissionForm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        mca=view.findViewById(R.id.mca);
        mba=view.findViewById(R.id.mba);
        cse=view.findViewById(R.id.cse);
        mech=view.findViewById(R.id.mech);
        civil=view.findViewById(R.id.civil);
        ece=view.findViewById(R.id.ece);
        eee=view.findViewById(R.id.eee);
        SandH=view.findViewById(R.id.SandH);
        principal=view.findViewById(R.id.principal);
        corredpondent=view.findViewById(R.id.corredpondent);

        admissionForm=view.findViewById(R.id.admissionForm);

        admissionForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForOnlineAdmissionForm();
            }
        });

        //marquee text
        forOnlineAdmissionForm = view.findViewById(R.id.for_online_admissionform);
        forOnlineAdmissionForm.setSelected(true);

        forOnlineAdmissionForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForOnlineAdmissionForm();
            }
        });

        principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     Intent intent = new Intent(DepartmentFragment.this, CreateStaffDetails.class);
                openprincipal();
            }
        });
        corredpondent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     Intent intent = new Intent(DepartmentFragment.this, CreateStaffDetails.class);
                opencorredpondent();
            }
        });
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

        sliderLayout=view.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(2);

        setSliderView();




        map=view.findViewById(R.id.pec_map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        return view;
    }

    private void ForOnlineAdmissionForm() {
        Intent intent = new Intent(getContext(), OnlineAdmissionActivity.class);
        startActivity(intent);
    }

    private void opencorredpondent() {
        Intent intent = new Intent(getContext(), CorredpondentActivity.class);
        startActivity(intent);
    }

    private void openprincipal() {
        Intent intent = new Intent(getContext(), PrincipalActiviity.class);
        startActivity(intent);
    }


    private void openMap() {

        Uri uri =Uri.parse("geo:0, 0?q=Priyadarshini Engineering College, Priyadarshini Engineering College, Chettiyappanur, Vaniyambadi, Tamil Nadu");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }


//    1 https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F01.jpg?alt=media&token=96659100-bd9b-4e69-8e8a-373249e6c7c4
//            2 https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F02.jpg?alt=media&token=05508e42-1d79-46fa-b2e1-55d8800e081d
//            3 https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F03.jpg?alt=media&token=10deaa7b-f1b6-4764-8316-cc18708eaeb0
//            4 https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F04.jpg?alt=media&token=8064dd81-947f-4b48-a4d1-0654098cbf2c
//            5 https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F05.jpg?alt=media&token=c2b31db5-9239-46d5-977e-6113bc81dcbe
//            6 https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F06.jpg?alt=media&token=3d16738e-0ef1-4b69-90b8-9d3cf473e30f
//            7 https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F07.jpg?alt=media&token=2fa466ec-a42d-4771-9923-b71032f23e90
//            8 https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F08.jpg?alt=media&token=4da7e3d4-08b2-4e29-b09b-61d9f546250b




    private void setSliderView() {
        for (int i=0;i<8;i++){
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i){
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F02.jpg?alt=media&token=05508e42-1d79-46fa-b2e1-55d8800e081d");
                    break;
                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F01.jpg?alt=media&token=96659100-bd9b-4e69-8e8a-373249e6c7c4");
                    break;
                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F03.jpg?alt=media&token=10deaa7b-f1b6-4764-8316-cc18708eaeb0");
                    break;
                case 3:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F04.jpg?alt=media&token=8064dd81-947f-4b48-a4d1-0654098cbf2c");
                    break;
                case 4:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F05.jpg?alt=media&token=c2b31db5-9239-46d5-977e-6113bc81dcbe");
                    break;
                case 5:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F06.jpg?alt=media&token=3d16738e-0ef1-4b69-90b8-9d3cf473e30f");
                    break;
                case 6:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F07.jpg?alt=media&token=2fa466ec-a42d-4771-9923-b71032f23e90");
                    break;
                case 7:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/pec-90f60.appspot.com/o/pec%20images%2F08.jpg?alt=media&token=4da7e3d4-08b2-4e29-b09b-61d9f546250b");
                    break;

            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);

            sliderLayout.addSliderView(sliderView);
        }
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