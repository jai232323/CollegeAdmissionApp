package com.example.pecpec.Students.AdmissionForms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pecpec.FullImagerActivity;
import com.example.pecpec.R;
import com.example.pecpec.Staffs.Admission.AdmissionStaffData;

import java.util.List;

public class LateralEnterBEAdmissionFormAdapter extends RecyclerView.Adapter<LateralEnterBEAdmissionFormAdapter.ViewHolder> {


    private List<AdmissionStaffData> list;
    private Context context;
    private String course;

    public LateralEnterBEAdmissionFormAdapter(List<AdmissionStaffData> list, Context context, String course) {
        this.list = list;
        this.context = context;
        this.course = course;
    }

    @NonNull
    @Override
    public LateralEnterBEAdmissionFormAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lateral_entry_be_admissionform_adapter,parent,false);

        return new LateralEnterBEAdmissionFormAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LateralEnterBEAdmissionFormAdapter.ViewHolder holder, int position) {
        AdmissionStaffData item = list.get(position);

        Glide.with(context).load(item.getStudentImage()).into(holder.applicantImage);

        holder.ApplicantName.setText(item.getName());
        holder.ApplicantFatherName.setText(item.getFatherName());
        holder.ApplicantMotherName.setText(item.getMotherName());
        holder.ApplicantAddress.setText(item.getAddress());
        holder.ApplicantMobileNumber.setText(item.getMobileNumber());
        holder.ApplicantAlterMobileNumber.setText(item.getAlterNativeMobileNumber());
        holder.ApplicantEmail.setText(item.getEmail());
        holder.ApplicantAlterEmail.setText(item.getAlterNativeEmail());
        holder.ApplicantGender.setText(item.getGender());
        holder.ApplicantCommunity.setText(item.getCommunity());
        holder.ApplicantDob.setText(item.getDOB());
        holder.ApplicantReferenceStaff.setText(item.getYourReferenceStaff());
        holder.ApplicantCourse.setText(item.getCourse());
        holder.ApplicantDiplomoDegreeRegNo.setText(item.getDiplomaDegreeRegisterNumberLateral());
        holder.ApplicantYearOfPassing.setText(item.getYearofPassingLateral());
        holder.ApplicatnFirstSemMark.setText(item.getFirstSemesterMark());
        holder.ApplicatnSecondSemMark.setText(item.getSecondSemesterMark());
        holder.ApplicatnThiredSemMark.setText(item.getThiredSemesterMark());
        holder.ApplicatnFourthSemMark.setText(item.getFourthSemesterMark());
        holder.ApplicatnFifthSemMark.setText(item.getFifthSemesterMark());
        holder.ApplicatnSixthSemMark.setText(item.getSixthSemesterMark());


        holder.applicantImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImagerActivity.class);
                intent.putExtra("image",item.getStudentImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView applicantImage;
        public TextView ApplicantName,  ApplicantFatherName, ApplicantMotherName, ApplicantAddress,
                ApplicantMobileNumber, ApplicantAlterMobileNumber , ApplicantEmail, ApplicantAlterEmail,
                ApplicantGender, ApplicantCommunity, ApplicantDob, ApplicantReferenceStaff, ApplicantCourse,
                ApplicantDiplomoDegreeRegNo,ApplicantYearOfPassing,ApplicatnFirstSemMark,ApplicatnSecondSemMark,ApplicatnThiredSemMark,
                ApplicatnFourthSemMark,ApplicatnFifthSemMark,ApplicatnSixthSemMark;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            applicantImage = itemView.findViewById(R.id.applicantImage);

            ApplicantName = itemView.findViewById(R.id.ApplicantName);
            ApplicantFatherName = itemView.findViewById(R.id.ApplicantFatherName);
            ApplicantMotherName = itemView.findViewById(R.id.ApplicantMotherName);
            ApplicantAddress = itemView.findViewById(R.id.ApplicantAddress);
            ApplicantMobileNumber = itemView.findViewById(R.id.ApplicantMobileNumber);
            ApplicantEmail = itemView.findViewById(R.id.ApplicantEmail);
            ApplicantAlterMobileNumber = itemView.findViewById(R.id.ApplicantAlterMobileNumber);
            ApplicantAlterEmail = itemView.findViewById(R.id.ApplicantALterEmail);
            ApplicantGender = itemView.findViewById(R.id.ApplicantGender);
            ApplicantCommunity = itemView.findViewById(R.id.ApplicantCommunity);
            ApplicantDob = itemView.findViewById(R.id.ApplicantDob);
            ApplicantReferenceStaff = itemView.findViewById(R.id.ApplicantReferenceStaff);
            ApplicantCourse = itemView.findViewById(R.id.ApplicantCourse);
            ApplicantDiplomoDegreeRegNo = itemView.findViewById(R.id.ApplicantDiplomoDegreeRegisterNumber);
            ApplicantYearOfPassing = itemView.findViewById(R.id.Applicantyearofpassing);
            ApplicatnFirstSemMark = itemView.findViewById(R.id.Applicant1stSemMark);
            ApplicatnSecondSemMark = itemView.findViewById(R.id.Applicant2ndSemMark);
            ApplicatnThiredSemMark = itemView.findViewById(R.id.Applicant3rdSemMark);
            ApplicatnFourthSemMark = itemView.findViewById(R.id.Applicant4thSemMark);
            ApplicatnFifthSemMark = itemView.findViewById(R.id.Applicant5thSemMark);
            ApplicatnSixthSemMark = itemView.findViewById(R.id.Applicant6thSemMark);

        }
    }
}
