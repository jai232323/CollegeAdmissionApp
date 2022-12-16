package com.example.pecpec.Students.NavigationAdmissionForm;

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
import com.example.pecpec.Staffs.Faculty.UpdateStaffActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NavFirstAdapter extends RecyclerView.Adapter<NavFirstAdapter.ViewHolder> {

    private List<AdmissionStaffData> list;
    private Context context;
    private String category;


    public NavFirstAdapter(List<AdmissionStaffData> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nav_first_adapter1,parent,false);

        return new NavFirstAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NavFirstAdapter.ViewHolder holder, int position) {

        AdmissionStaffData item = list.get(position);

        Glide.with(context).load(item.getStudentImage()).into(holder.ApplicantImage);

        holder.ApplicantName.setText(item.getName());
        holder.ApplicantCourse1.setText(item.getCourseSubmitted());


        holder.ApplicantImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImagerActivity.class);
                intent.putExtra("image",item.getStudentImage());
                context.startActivity(intent);
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, UpdateFirstActivity.class);
//
//                intent.putExtra("Name",item.getName());
//                intent.putExtra("FatherName",item.getFatherName());
//                intent.putExtra("MotherName",item.getMotherName());
//                intent.putExtra("Address",item.getAddress());
//                intent.putExtra("MobileNumber",item.getMotherName());
//                intent.putExtra("AlterNativeMobileNumber",item.getAlterNativeMobileNumber());
//                intent.putExtra("Email",item.getEmail());
//                intent.putExtra("AlterNativeEmail",item.getAlterNativeEmail());
//                intent.putExtra("YourReferenceStaff",item.getYourReferenceStaff());
//                intent.putExtra("Plus2RegisterNumber1",item.getPlus2RegisterNumber1());
//                intent.putExtra("Yearofpassing1",item.getYearofpassing1());
//                intent.putExtra("Plus2MathsMark",item.getPlus2MathsMark());
//                intent.putExtra("Plus2PhysicsMark1",item.getPlus2PhysicsMark1());
//                intent.putExtra("Plus2ChemistryMark",item.getPlus2ChemistryMark());
//                intent.putExtra("Plus2RegisterNumber2",item.getPlus2RegisterNumber2());
//                intent.putExtra("Yearofpassing2",item.getYearofpassing2());
//                intent.putExtra("Plus2MathsMark2",item.getPlus2MathsMark2());
//                intent.putExtra("Plus2TheroryMark",item.getPlus2TheroryMark());
//                intent.putExtra("Plus2Practical1mark",item.getPlus2Practical1mark());
//                intent.putExtra("Plus2Practical2mark",item.getPlus2Practical2mark());
//                intent.putExtra("Course",item.getCourse());
//                intent.putExtra("Community",item.getCommunity());
//                intent.putExtra("DOB",item.getDOB());
//                intent.putExtra("Gender",item.getGender());
//                intent.putExtra("StudentImage",item.getStudentImage());
//                context.startActivity(intent);
//
//
////                hashMap.put("Name",Name);
////                hashMap.put("FatherName",FatherName);
////                hashMap.put("MotherName",Mothername);
////                hashMap.put("Address",Address);
////                hashMap.put("MobileNumber",MobileNumber);
////                hashMap.put("AlterNativeMobileNumber",AlterMobileNumber);
////                hashMap.put("Email",Email);
////                hashMap.put("AlterNativeEmail",AlterEmail);
////                hashMap.put("YourReferenceStaff",YourReference);
////                hashMap.put("Plus2RegisterNumber1",Plus2RegisterNo);
////                hashMap.put("Yearofpassing1",YearofPassing);
////                hashMap.put("Plus2MathsMark",Plus2MathsMarks);
////                hashMap.put("Plus2PhysicsMark1",Plus2PhysicsMarks);
////                hashMap.put("Plus2ChemistryMark",Plus2ChemistryMarks);
////                hashMap.put("Plus2RegisterNumber2",Plus2RegisterNo1);
////                hashMap.put("Yearofpassing2",YearofPassing1);
////                hashMap.put("Plus2MathsMark2",Plus2Practical1Marks1);
////                hashMap.put("Plus2TheroryMark",Plus2TheoryMarks);
////                hashMap.put("Plus2Practical1mark",Plus2Practical1Marks);
////                hashMap.put("Plus2Practical2mark",Plus2Practical2Marks);
////                hashMap.put("Course",Course1);
////                hashMap.put("Community",Community1);
////                hashMap.put("DOB",dob);
////                hashMap.put("Gender", Gender1);
////                hashMap.put("StudentImage", downloadUrl);
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ApplicantImage;
        private TextView ApplicantName,ApplicantCourse1;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            //
         ApplicantImage=itemView.findViewById(R.id.ApplicantImage);
          ApplicantName=itemView.findViewById(R.id.ApplicantName);
            ApplicantCourse1=itemView.findViewById(R.id.ApplicantCourse1);
        }
    }
}
