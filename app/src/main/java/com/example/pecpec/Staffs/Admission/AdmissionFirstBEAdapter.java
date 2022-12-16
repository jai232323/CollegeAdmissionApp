package com.example.pecpec.Staffs.Admission;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pecpec.FullImagerActivity;
import com.example.pecpec.R;

import com.example.pecpec.Staffs.Faculty.StaffData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;


import java.util.HashMap;
import java.util.List;

public class AdmissionFirstBEAdapter extends RecyclerView.Adapter<AdmissionFirstBEAdapter.ViewHolder> {

    private List<AdmissionStaffData> list;
    private List<StaffData> list1;
    private Context context;
    private String category;
    private String accrej;
    private DatabaseReference reference , dbRef;



    FirebaseAuth auth;


    public AdmissionFirstBEAdapter(List<AdmissionStaffData> list, List<StaffData> list1, Context context, String category, FirebaseAuth auth) {
        this.list = list;
        this.list1 = list1;
        this.context = context;
        this.category = category;
        this.auth = auth;
    }

    public AdmissionFirstBEAdapter(List<AdmissionStaffData> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.first_be_adapter,parent,false);

        return new AdmissionFirstBEAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdmissionFirstBEAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {



        auth=FirebaseAuth.getInstance();

        AdmissionStaffData item = list.get(position);


        holder.StaffNameVerification.setText(item.getStaffNameVerification());

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
        holder.VerifiedAdmissionForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        });

    }


    private void pushData(ViewHolder holder,int position) {

        ProgressDialog pd;
        pd=new ProgressDialog(context);
        AdmissionStaffData item = list.get(position);

      //  StaffData item1 = list1.get(position);




//        FirebaseUser firebaseUser = auth.getCurrentUser();
//        String userid = firebaseUser.getUid();

        String userIDUniqueKey=item.getUserIDUniqueKey();
        String Course1 = item.getCourse();

        DatabaseReference reference= FirebaseDatabase.getInstance().
                getReference("First Year BE Admission Forms Staff Verified").child(item.getUserIDUniqueKey());



        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Name",item.getName());
        hashMap.put("CourseSubmitted",item.getCourse()+" "+accrej);
      //  hashMap.put("StaffNameVerification",item1.getName());
        hashMap.put("StudentImage",item.getStudentImage());
        hashMap.put("userid",item.getUserid());

        reference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                pd.dismiss();
                Toast.makeText(context,item.getCourse()+"\n"+accrej,Toast.LENGTH_SHORT).show();
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
                ApplicantPlus2RegisterNumber1,  ApplicantYearofpassing1, ApplicantPlus2MathsMark,
                ApplicantPlus2PhysicsMark1,  ApplicantPlus2ChemistryMark, ApplicantPlus2RegisterNumber2,
                ApplicantYearofpassing2, ApplicantPlus2MathsMark2,
                ApplicantPlus2TheroryMark, ApplicantPlus2Practical1mark, ApplicantPlus2Practical2mark;
        public EditText AcceptedOrRejected;
        public Button VerifiedAdmissionForm;
        public TextView StaffNameVerification;

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

            AcceptedOrRejected= itemView.findViewById(R.id.AcceptedOrRejected);

            VerifiedAdmissionForm= itemView.findViewById(R.id.VerifiedAdmissionForm);

            StaffNameVerification=itemView.findViewById(R.id.StaffNameVerification);
        }
    }
}
