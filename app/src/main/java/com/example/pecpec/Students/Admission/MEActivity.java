package com.example.pecpec.Students.Admission;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pecpec.R;
import com.example.pecpec.Staff_Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

public class MEActivity extends AppCompatActivity {

    private String Name,FatherName,Mothername,Address,MobileNumber,AlterMobileNumber,Email,AlterEmail,
            YourReference,nameOFtheUGDegree,YearofPassing,
            UGRegisterNo,UGPercent,institution,dob,downloadUrl="";

    private FirebaseUser firebaseUser;
    FirebaseAuth auth;


    private ImageView RegisterStudentImage;
    private EditText ApplicantName,ApplicantFatherName,
            ApplicantMotherName,ApplicantAddress,
            ApplicantMobileNumber,ApplicantAlternativeMobileNumber,
            ApplicantEmail,ApplicantAlternativeEmail,
            YourReferenceStaff,NameOFtheUGDegree,
            ApplicantYearofPassing,UGRegisterNumber,UGPercentage,Institution;

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
        setContentView(R.layout.activity_meactivity);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ME Admission Form");
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

        reference= FirebaseDatabase.getInstance().getReference("ME Admission Forms");
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
        NameOFtheUGDegree=findViewById(R.id.NameOFtheUGDegree);
        UGRegisterNumber=findViewById(R.id.UGRegisterNumber);
        UGPercentage=findViewById(R.id.UGPercentage);
        Institution=findViewById(R.id.Institution);
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(MEActivity.this,
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


        String[] itemscourse = new String[]{"Select Department","ME(Computer Science and Engineering)",
                "ME(Communication Systems)","ME(Power Systems Engineering)","ME(Engineering Design)"};


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
                    Toast.makeText(MEActivity.this,"Please Set Image",Toast.LENGTH_SHORT).show();
                }else if (Gender1.equals("Select Gender")){
                    Toast.makeText(MEActivity.this,"Please Select Gender",Toast.LENGTH_SHORT).show();
                }else if (Community1.equals("Select Community")){
                    Toast.makeText(MEActivity.this,"Please Select Community",Toast.LENGTH_SHORT).show();
                }else if (Course1.equals("Select Department")){
                    Toast.makeText(MEActivity.this,"Please Select Department",Toast.LENGTH_SHORT).show();
                }else {

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

//        private String Name,FatherName,Mothername,Address,MobileNumber,AlterMobileNumber,Email,AlterEmail,
//                YourReference,nameOFtheUGDegree,YearofPassing,
//                UGRegisterNo,institution,Gender,Course,Community,dob;
//
//        private String downloadUrl;
//
//        private ImageView RegisterStudentImage;
//        private EditText ApplicantName,ApplicantFatherName,
//                ApplicantMotherName,ApplicantAddress,
//                ApplicantMobileNumber,ApplicantAlternativeMobileNumber,
//                ApplicantEmail,ApplicantAlternativeEmail,
//                YourReferenceStaff,NameOFtheUGDegree,
//                ApplicantYearofPassing,UGRegisterNumber,Institution;
        Name=ApplicantName.getText().toString();
        FatherName=ApplicantFatherName.getText().toString();
        Mothername=ApplicantMotherName.getText().toString();
        Address=ApplicantAddress.getText().toString();
        MobileNumber=ApplicantMobileNumber.getText().toString();
        AlterMobileNumber=ApplicantAlternativeMobileNumber.getText().toString();
        Email=ApplicantEmail.getText().toString();
        AlterEmail=ApplicantAlternativeEmail.getText().toString();
        YourReference=YourReferenceStaff.getText().toString();
        nameOFtheUGDegree=NameOFtheUGDegree.getText().toString();
        YearofPassing=ApplicantYearofPassing.getText().toString();
        UGRegisterNo=UGRegisterNumber.getText().toString();
        UGPercent=UGPercentage.getText().toString();
        institution=Institution.getText().toString();
        dob=SPinnerDOB.getText().toString();



        if (MobileNumber.length() < 10){
            Toast.makeText(MEActivity.this,"Password Must Have 10 Numbers" , Toast.LENGTH_SHORT).show();
        }else  if (Name.isEmpty()){
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
        }  else if (MobileNumber.length() < 10){
            Toast.makeText(MEActivity.this,"Phone Number must have 10 Numbers" , Toast.LENGTH_SHORT).show();
        }
        else  if (AlterMobileNumber.isEmpty()){
            ApplicantAlternativeMobileNumber.setError("Alter Mobile Number Empty");
            ApplicantAlternativeMobileNumber.requestFocus();
        }  else if (AlterMobileNumber.length() < 10){
            Toast.makeText(MEActivity.this,"Phone Number must have 10 Numbers" , Toast.LENGTH_SHORT).show();
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
        filePath=storageReference.child("ME Admission Form").child(finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(MEActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
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
                    Toast.makeText(MEActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertDate() {


        FirebaseUser firebaseUser = auth.getCurrentUser();
        String userid = firebaseUser.getUid();

        String uniqueKey = reference.push().getKey();

        String userIDUniqueKey = userid + uniqueKey;

        HashMap<String, String> hashMap4 = new HashMap<>();
        hashMap4.put("uniqueKey",uniqueKey);
        hashMap4.put("Name",Name);
        hashMap4.put("FatherName",FatherName);
        hashMap4.put("MotherName",Mothername);
        hashMap4.put("Address",Address);
        hashMap4.put("MobileNumber",MobileNumber);
        hashMap4.put("AlterNativeMobileNumber",AlterMobileNumber);
        hashMap4.put("Email",Email);
        hashMap4.put("AlterNativeEmail",AlterEmail);
        hashMap4.put("YourReferenceStaff",YourReference);
        hashMap4.put("NameOFTheUGDegree",nameOFtheUGDegree);
        hashMap4.put("YearofPassingME",YearofPassing);
        hashMap4.put("UGDegreeRegisterNumberME",UGRegisterNo);
        hashMap4.put("UGDegreePercentageME",UGPercent);
        hashMap4.put("InstitutionME",institution);
        hashMap4.put("Course",Course1);
        hashMap4.put("Community",Community1);
        hashMap4.put("DOB",dob);
        hashMap4.put("Gender", Gender1);
        hashMap4.put("StudentImage", downloadUrl);
        hashMap4.put("userid", userid);
        hashMap4.put("userIDUniqueKey",userIDUniqueKey);



        reference.child(userIDUniqueKey).setValue(hashMap4).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                pd.dismiss();
                Toast.makeText(MEActivity.this,"Admission Form Submitted Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MEActivity.this, Staff_Student.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                SharedPreferences.Editor editor2 = getSharedPreferences("PREFS1",MODE_PRIVATE).edit();
                editor2.putString("userIDUniqueKey", userIDUniqueKey);
                editor2.apply();

                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                pd.dismiss();
                Toast.makeText(MEActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
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