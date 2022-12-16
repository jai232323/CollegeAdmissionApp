package com.example.pecpec.Students.Admission;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.pecpec.R;
import com.example.pecpec.Staff_Student;
import com.example.pecpec.Staffs.Admission.AdmissionStaffData;
import com.example.pecpec.Students.StudentData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

public class FirstyearBEActivity extends AppCompatActivity {

    private String Name,FatherName,Mothername,Address,MobileNumber,AlterMobileNumber,Email,AlterEmail,
            YourReference,Plus2RegisterNo,YearofPassing,Plus2MathsMarks,Plus2PhysicsMarks,Plus2ChemistryMarks,
            Plus2RegisterNo1,YearofPassing1,Plus2Practical1Marks1,Plus2TheoryMarks,Plus2Practical1Marks,Plus2Practical2Marks,
            dob,downloadUrl="";


    private FirebaseUser firebaseUser;
    FirebaseAuth auth;

    private ImageView RegisterStudentImage;
    private EditText ApplicantName,ApplicantFatherName,ApplicantMotherName,ApplicantAddress,
            ApplicantMobileNumber,ApplicantAlternativeMobileNumber,ApplicantEmail,ApplicantAlternativeEmail,
            YourReferenceStaff,Plus2RegisterNumber,ApplicantYearofPassing,Applicant_Plus2_Maths_Marks,
            Applicant_Plus2_Physics_Marks,Applicant_Plus2_Chemistry_Marks,Plus2RegisterNumber1,
            ApplicantYearofPassing1,Applicant_Plus2_Maths_Marks1,Applicant_Plus2_Theory_Marks,
            Applicant_Plus2_Practical1_Marks,Applicant_Plus2_Practical2_Marks;
    private Spinner ApplicantGender,ApplicantCourse,ApplicantCommunity;
    private Button SPinnerDOB,uploadApplication;

    DatePickerDialog.OnDateSetListener onDateSetListener;

    String Gender1,Course1,Community1;
    private final int REQ = 1;
    private Bitmap bitmap;


    private DatabaseReference reference,dbRef;
    private StorageReference storageReference;
    private ProgressDialog pd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstyear_beactivity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("1st Year BE Admission Form");
        toolbar.setNavigationIcon(R.drawable.ic_back_btn);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pd=new ProgressDialog(this);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        auth=FirebaseAuth.getInstance();

        reference= FirebaseDatabase.getInstance().getReference("First Year BE Admission Forms");
        storageReference= FirebaseStorage.getInstance().getReference();

        RegisterStudentImage=findViewById(R.id.RegisterStudentImage);
        ApplicantName=findViewById(R.id.ApplicantName);
        ApplicantFatherName=findViewById(R.id.ApplicantFatherName);
        ApplicantMotherName=findViewById(R.id.ApplicantMotherName);
        ApplicantAddress=findViewById(R.id.ApplicantAddress);
        ApplicantMobileNumber=findViewById(R.id.ApplicantMobileNumber);
        ApplicantAlternativeMobileNumber=findViewById(R.id.ApplicantAlternativeMobileNumber);
        ApplicantEmail=findViewById(R.id.ApplicantEmail);
        ApplicantAlternativeEmail=findViewById(R.id.ApplicantAlternativeEmail);
        YourReferenceStaff=findViewById(R.id.YourReferenceStaff);
        ApplicantYearofPassing=findViewById(R.id.ApplicantYearofPassing);
        Plus2RegisterNumber=findViewById(R.id.Plus2RegisterNumber);
        Applicant_Plus2_Maths_Marks=findViewById(R.id.Applicant_Plus2_Maths_Marks);
        Applicant_Plus2_Physics_Marks=findViewById(R.id.Applicant_Plus2_Physics_Marks);
        Applicant_Plus2_Chemistry_Marks=findViewById(R.id.Applicant_Plus2_Chemistry_Marks);
        Plus2RegisterNumber1=findViewById(R.id.Plus2RegisterNumber1);
        ApplicantYearofPassing1=findViewById(R.id.ApplicantYearofPassing1);
        Applicant_Plus2_Maths_Marks1=findViewById(R.id.Applicant_Plus2_Maths_Marks1);
        Applicant_Plus2_Theory_Marks=findViewById(R.id.Applicant_Plus2_Theory_Marks);
        Applicant_Plus2_Practical1_Marks=findViewById(R.id.Applicant_Plus2_Practical1_Marks);
        Applicant_Plus2_Practical2_Marks=findViewById(R.id.Applicant_Plus2_Practical2_Marks);
        ApplicantGender=findViewById(R.id.ApplicantGender);
        ApplicantCourse=findViewById(R.id.ApplicantCourse);
        SPinnerDOB=findViewById(R.id.SPinnerDOB);
        uploadApplication=findViewById(R.id.uploadApplication);
        ApplicantCommunity=findViewById(R.id.ApplicantCommunity);




        SPinnerDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(FirstyearBEActivity.this,
                        onDateSetListener,year,month,day);
                datePickerDialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = dayOfMonth + "/" +month+"/"+year;
                SPinnerDOB.setText(date);
            }
        };

        String[] itemsCommunity = new String[]{"Select Community","OC","BC","BCM","MBC","SC","SCA","ST"};


        ApplicantCommunity.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsCommunity));

        ApplicantCommunity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Community1=ApplicantCommunity.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] itemsgender = new String[]{"Select Gender","Male","Female"};


        ApplicantGender.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsgender));

        ApplicantGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Gender1=ApplicantGender.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        String[] itemscourse = new String[]{"Select Department","Mechanical Engineering",
                "Civil Engineering", "Computer Science Engineering",
                "ECE", "EEE"};


        ApplicantCourse.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemscourse));

        ApplicantCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Course1=ApplicantCourse.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        uploadApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bitmap == null){
                    Toast.makeText(FirstyearBEActivity.this,"Please Set Image",Toast.LENGTH_SHORT).show();
                }else if (Gender1.equals("Select Gender")){
                    Toast.makeText(FirstyearBEActivity.this,"Please Select Gender",Toast.LENGTH_SHORT).show();
                }else if (Community1.equals("Select Community")){
                    Toast.makeText(FirstyearBEActivity.this,"Please Select Community",Toast.LENGTH_SHORT).show();
                }else if (Course1.equals("Select Department")){
                    Toast.makeText(FirstyearBEActivity.this,"Please Select Department",Toast.LENGTH_SHORT).show();
                }
                else {
                    checkValidation();
                }



            }
        });

        RegisterStudentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


    }

    private void checkValidation() {

        Name=ApplicantName.getText().toString();
        FatherName=ApplicantFatherName.getText().toString();
        Mothername=ApplicantMotherName.getText().toString();
        Address=ApplicantAddress.getText().toString();
        MobileNumber=ApplicantMobileNumber.getText().toString();
        AlterMobileNumber=ApplicantAlternativeMobileNumber.getText().toString();
        Email=ApplicantEmail.getText().toString();
        AlterEmail=ApplicantAlternativeEmail.getText().toString();
        YourReference=YourReferenceStaff.getText().toString();
        Plus2RegisterNo=Plus2RegisterNumber.getText().toString();
        YearofPassing=ApplicantYearofPassing.getText().toString();
        Plus2MathsMarks=Applicant_Plus2_Maths_Marks.getText().toString();
        Plus2PhysicsMarks=Applicant_Plus2_Physics_Marks.getText().toString();
        Plus2ChemistryMarks=Applicant_Plus2_Chemistry_Marks.getText().toString();
        Plus2RegisterNo1=Plus2RegisterNumber1.getText().toString();
        YearofPassing1=ApplicantYearofPassing1.getText().toString();
        Plus2Practical1Marks1=Applicant_Plus2_Maths_Marks1.getText().toString();
        Plus2TheoryMarks=Applicant_Plus2_Theory_Marks.getText().toString();
        Plus2Practical1Marks=Applicant_Plus2_Practical1_Marks.getText().toString();
        Plus2Practical2Marks=Applicant_Plus2_Practical2_Marks.getText().toString();
        dob=SPinnerDOB.getText().toString();



        if (Name.isEmpty()){
            ApplicantName.setError("Name Empty");
            ApplicantName.requestFocus();
        }else  if (FatherName.isEmpty()){
            ApplicantFatherName.setError("Father Name Empty");
            ApplicantFatherName.requestFocus();
        }else  if (Mothername.isEmpty()){
            ApplicantMotherName.setError("Mother Name Empty");
            ApplicantMotherName.requestFocus();
        }else  if (Address.isEmpty()){
            ApplicantAddress.setError("Address Empty");
            ApplicantAddress.requestFocus();
        }else  if (MobileNumber.isEmpty()){
            ApplicantMobileNumber.setError("Mobile Number Empty");
            ApplicantMobileNumber.requestFocus();
        }else if (MobileNumber.length() < 10){
            Toast.makeText(FirstyearBEActivity.this,"Phone Number Must Have 10 Numbers" , Toast.LENGTH_SHORT).show();
        }
        else  if (AlterMobileNumber.isEmpty()){
            ApplicantAlternativeMobileNumber.setError("Alter Mobile Number Empty");
            ApplicantAlternativeMobileNumber.requestFocus();
        }else if (AlterMobileNumber.length() < 10){
            Toast.makeText(FirstyearBEActivity.this,"Phone Number Must Have 10 Numbers" , Toast.LENGTH_SHORT).show();
        }
        else  if (Email.isEmpty()){
            ApplicantEmail.setError("Email Empty");
            ApplicantEmail.requestFocus();
        }
        else  if (AlterEmail.isEmpty()){
            ApplicantAlternativeEmail.setError("Alter Email Empty");
            ApplicantAlternativeEmail.requestFocus();
        }
        else  if (YourReference.isEmpty()) {
            YourReferenceStaff.setError("Your Reference Staff Empty");
            YourReferenceStaff.requestFocus();
        }
//        } else  if (Plus2RegisterNo.isEmpty()){
//            Plus2RegisterNumber.setError("+2 Register Number");
//            Plus2RegisterNumber.requestFocus();
//        }
//        else  if (YearofPassing.isEmpty()){
//            ApplicantYearofPassing.setError("year of passing Empty");
//            ApplicantYearofPassing.requestFocus();
//        }else  if (Plus2MathsMarks.isEmpty()){
//            Applicant_Plus2_Maths_Marks.setError("+2 Register Maths Marks Empty");
//            Applicant_Plus2_Maths_Marks.requestFocus();
//        }else  if (Plus2PhysicsMarks.isEmpty()){
//            Applicant_Plus2_Physics_Marks.setError("+2 Register Physics Marks Empty");
//            Applicant_Plus2_Physics_Marks.requestFocus();
//        }else  if (Plus2PhysicsMarks.isEmpty()){
//            Applicant_Plus2_Physics_Marks.setError("+2 Register Physics Marks Empty");
//            Applicant_Plus2_Physics_Marks.requestFocus();
//        }

        else {
            uploadImage();
        }
    }

    private void uploadImage() {
        pd.setMessage("Uploading\nPlease Wait...");
        pd.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();

        final StorageReference filePath;
        filePath=storageReference.child("First Year BE Admission Forms").child(finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(FirstyearBEActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                    insertDate();
                                }
                            });
                        }
                    });
                }else {
                    pd.dismiss();
                    Toast.makeText(FirstyearBEActivity.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertDate() {

       // dbRef=reference.child(Course1);

        FirebaseUser firebaseUser = auth.getCurrentUser();
        String userid = firebaseUser.getUid();

        String uniqueKey = reference.push().getKey();

        String userIDUniqueKey = userid + uniqueKey;

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueKey",uniqueKey);
        hashMap.put("Name",Name);
        hashMap.put("FatherName",FatherName);
        hashMap.put("MotherName",Mothername);
        hashMap.put("Address",Address);
        hashMap.put("MobileNumber",MobileNumber);
        hashMap.put("AlterNativeMobileNumber",AlterMobileNumber);
        hashMap.put("Email",Email);
        hashMap.put("AlterNativeEmail",AlterEmail);
        hashMap.put("YourReferenceStaff",YourReference);
        hashMap.put("Plus2RegisterNumber1",Plus2RegisterNo);
        hashMap.put("Yearofpassing1",YearofPassing);
        hashMap.put("Plus2MathsMark",Plus2MathsMarks);
        hashMap.put("Plus2PhysicsMark1",Plus2PhysicsMarks);
        hashMap.put("Plus2ChemistryMark",Plus2ChemistryMarks);
        hashMap.put("Plus2RegisterNumber2",Plus2RegisterNo1);
        hashMap.put("Yearofpassing2",YearofPassing1);
        hashMap.put("Plus2MathsMark2",Plus2Practical1Marks1);
        hashMap.put("Plus2TheroryMark",Plus2TheoryMarks);
        hashMap.put("Plus2Practical1mark",Plus2Practical1Marks);
        hashMap.put("Plus2Practical2mark",Plus2Practical2Marks);
        hashMap.put("Course",Course1);
        hashMap.put("Community",Community1);
        hashMap.put("DOB",dob);
        hashMap.put("Gender", Gender1);
        hashMap.put("StudentImage", downloadUrl);
        hashMap.put("userid", userid);
        hashMap.put("userIDUniqueKey",userIDUniqueKey);


        reference.child(userIDUniqueKey).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                pd.dismiss();
                Toast.makeText(FirstyearBEActivity.this,"Admission Form Submitted Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FirstyearBEActivity.this, Staff_Student.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
                editor.putString("userid", firebaseUser.getUid());
                editor.apply();

                SharedPreferences.Editor editor1 = getSharedPreferences("PREFS1",MODE_PRIVATE).edit();
                editor1.putString("Course", Course1);
                editor1.apply();

                SharedPreferences.Editor editor2 = getSharedPreferences("PREFS1",MODE_PRIVATE).edit();
                editor2.putString("userIDUniqueKey", userIDUniqueKey);
                editor2.apply();

                finish();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                pd.dismiss();
                Toast.makeText(FirstyearBEActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent picImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picImage,REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ && resultCode == RESULT_OK) {

            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            RegisterStudentImage.setImageBitmap(bitmap);
        }
    }
}