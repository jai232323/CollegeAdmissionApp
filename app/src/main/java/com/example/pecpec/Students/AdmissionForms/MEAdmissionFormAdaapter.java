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

public class MEAdmissionFormAdaapter extends RecyclerView.Adapter<MEAdmissionFormAdaapter.ViewHolder> {


    private List<AdmissionStaffData> list;
    private Context context;
    private String course;

    public MEAdmissionFormAdaapter(List<AdmissionStaffData> list, Context context, String course) {
        this.list = list;
        this.context = context;
        this.course = course;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.me_admissionforms_adapter,parent,false);

        return new MEAdmissionFormAdaapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
        holder.ApplicantNameofTheUGDegreeME.setText(item.getNameOFTheUGDegree());
        holder.ApplicantUGRegisterNumber.setText(item.getUGDegreeRegisterNumberME());
        holder.ApplicantYearOFPassing.setText(item.getYearofPassingME());
        holder.ApplicantPercentage.setText(item.getUGDegreePercentageME());
        holder.ApplicantInstitution.setText(item.getInstitutionME());

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
                ApplicantMobileNumber, ApplicantAlterMobileNumber , ApplicantEmail,
                ApplicantAlterEmail, ApplicantGender, ApplicantCommunity, ApplicantDob,
                ApplicantReferenceStaff, ApplicantCourse, ApplicantNameofTheUGDegreeME,ApplicantUGRegisterNumber,
                ApplicantYearOFPassing,ApplicantPercentage,ApplicantInstitution;
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
            ApplicantNameofTheUGDegreeME = itemView.findViewById(R.id.ApplicantNameofTheUGDegreeME);
            ApplicantUGRegisterNumber = itemView.findViewById(R.id.ApplicantUGRegisterNumberME);
            ApplicantYearOFPassing = itemView.findViewById(R.id.ApplicantyearofpassingME);
            ApplicantPercentage = itemView.findViewById(R.id.ApplicantUGPercentageME);
            ApplicantInstitution = itemView.findViewById(R.id.ApplicantInstitutionME);


        }
    }
}
