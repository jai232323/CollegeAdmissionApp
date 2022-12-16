package com.example.pecpec.Staffs.Admission;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pecpec.FullImagerActivity;
import com.example.pecpec.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;


import java.util.HashMap;
import java.util.List;

public class LateralEnterBEActivityAdapter extends RecyclerView.Adapter<LateralEnterBEActivityAdapter.ViewHolder> {

    private List<AdmissionStaffData> list;
    private Context context;
    private String category;

    private String accrej;
    private DatabaseReference reference , dbRef;

    private ProgressDialog pd;


    public LateralEnterBEActivityAdapter(List<AdmissionStaffData> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lateral_entry_be_adapter,parent,false);

        return new LateralEnterBEActivityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LateralEnterBEActivityAdapter.ViewHolder holder, int position) {

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
        holder.VerifiedAdmissionForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkvalidation(holder,position);
                // Toast.makeText(context,"Admission Form Okay ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkvalidation(ViewHolder holder, int position) {



        accrej = holder.AcceptedOrRejected.getText().toString();


        if (accrej.isEmpty()){
            holder.AcceptedOrRejected.setError("Empty");
            holder.AcceptedOrRejected.requestFocus();
            // Toast.makeText(context,"Course With \nAccepted  or Rejected Empty\nPlease Fill it...",Toast.LENGTH_LONG).show();
        }else {

            //checkvalidation(holder, position);
            pushData(holder, position);
        }

    }

    private void pushData(LateralEnterBEActivityAdapter.ViewHolder holder, int position) {
        pd=new ProgressDialog(context);
        AdmissionStaffData item = list.get(position);

//        final String uniqueKey = dbRef.push().getKey();

        DatabaseReference reference= FirebaseDatabase.getInstance().
                getReference("Lateral Entry BE Admission Forms Staff Verified").child(item.getUserIDUniqueKey());

        String userIDUniqueKey=item.getUserIDUniqueKey();
        String Course1 = item.getCourse();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Name",item.getName());
        hashMap.put("CourseSubmitted",item.getCourse()+" "+accrej);
        hashMap.put("StudentImage",item.getStudentImage());
        hashMap.put("userid",item.getUserid());

       reference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                pd.dismiss();
                Toast.makeText(context,item.getCourse()+"\n"+accrej,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, AdmissionActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                SharedPreferences.Editor editor2 = context.getSharedPreferences("PREFS1",Context.MODE_PRIVATE).edit();
                editor2.putString("userIDUniqueKey", userIDUniqueKey);
                editor2.apply();

                SharedPreferences.Editor editor1 = context.getSharedPreferences("PREFS1",context.MODE_PRIVATE).edit();
                editor1.putString("Course", Course1);
                editor1.apply();
                context.startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                pd.dismiss();
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show();
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

        public EditText AcceptedOrRejected;
        public Button VerifiedAdmissionForm;


        public ViewHolder(@NonNull @NotNull View itemView) {
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

            AcceptedOrRejected= itemView.findViewById(R.id.AcceptedOrRejected);

            VerifiedAdmissionForm= itemView.findViewById(R.id.VerifiedAdmissionForm);
        }
    }
}
