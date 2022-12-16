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
import com.example.pecpec.Staffs.Admission.AdmissionFirstBEAdapter;
import com.example.pecpec.Staffs.Admission.AdmissionStaffData;

import java.util.List;

public class FirstYearAdmissionFormsAdapter extends RecyclerView.Adapter<FirstYearAdmissionFormsAdapter.ViewHolder> {


    private List<AdmissionStaffData> list;
    private Context context;
    private String course;

    public FirstYearAdmissionFormsAdapter(List<AdmissionStaffData> list, Context context, String course) {
        this.list = list;
        this.context = context;
        this.course = course;
    }
    @NonNull
    @Override
    public FirstYearAdmissionFormsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.firstyear_admissionforms_adapter,parent,false);

        return new FirstYearAdmissionFormsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirstYearAdmissionFormsAdapter.ViewHolder holder, int position) {
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
        holder.ApplicantPlus2RegisterNumber1.setText(item.getPlus2RegisterNumber1());
        holder.ApplicantYearofpassing1.setText(item.getYearofpassing1());
        holder.ApplicantPlus2MathsMark.setText(item.getPlus2MathsMark());
        holder.ApplicantPlus2PhysicsMark1.setText(item.getPlus2PhysicsMark1());
        holder.ApplicantPlus2ChemistryMark.setText(item.getPlus2ChemistryMark());
        holder.ApplicantPlus2RegisterNumber2.setText(item.getPlus2RegisterNumber2());
        holder.ApplicantYearofpassing2.setText(item.getYearofpassing2());
        holder.ApplicantPlus2MathsMark2.setText(item.getPlus2MathsMark2());
        holder.ApplicantPlus2TheroryMark.setText(item.getPlus2TheroryMark());
        holder.ApplicantPlus2Practical1mark.setText(item.getPlus2Practical1mark());
        holder.ApplicantPlus2Practical2mark.setText(item.getPlus2Practical2mark());

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
                ApplicantPlus2RegisterNumber1,  ApplicantYearofpassing1, ApplicantPlus2MathsMark,
                ApplicantPlus2PhysicsMark1,  ApplicantPlus2ChemistryMark, ApplicantPlus2RegisterNumber2,
                ApplicantYearofpassing2, ApplicantPlus2MathsMark2,
                ApplicantPlus2TheroryMark, ApplicantPlus2Practical1mark, ApplicantPlus2Practical2mark;

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
            ApplicantPlus2RegisterNumber1 = itemView.findViewById(R.id.Applicantplus2_regNo);
            ApplicantYearofpassing1 = itemView.findViewById(R.id.Applicantyearofpassing);
            ApplicantPlus2MathsMark= itemView.findViewById(R.id.Applicantplus2mathsmark);
            ApplicantPlus2PhysicsMark1= itemView.findViewById(R.id.Applicantplus2Pysicssmark);
            ApplicantPlus2ChemistryMark= itemView.findViewById(R.id.Applicantplus2Chemistrymark);
            ApplicantPlus2RegisterNumber2= itemView.findViewById(R.id.Applicantplus2_regNo1);
            ApplicantYearofpassing2= itemView.findViewById(R.id.Applicantyearofpassing1);
            ApplicantPlus2MathsMark2= itemView.findViewById(R.id.Applicantplus2mathsmark1);
            ApplicantPlus2TheroryMark= itemView.findViewById(R.id.Applicantplus2Theorymark);
            ApplicantPlus2Practical1mark= itemView.findViewById(R.id.Applicantplus2Practical1mark);
            ApplicantPlus2Practical2mark= itemView.findViewById(R.id.Applicantplus2Practical12mark);
        }
    }
}
