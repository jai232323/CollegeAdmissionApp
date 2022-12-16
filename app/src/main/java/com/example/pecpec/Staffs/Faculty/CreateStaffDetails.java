package com.example.pecpec.Staffs.Faculty;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pecpec.R;
import com.example.pecpec.Staffs.StaffMainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
//import com.theartofdev.edmodo.cropper.CropImage;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class CreateStaffDetails extends AppCompatActivity {

    private ImageView addStaffImage;
    private EditText StaffName,StaffEmail,StaffPassword,StaffPhoneNo,StaffPost;
    private Spinner SpinnerDeptCategory;
    private Button CreateStaffLogin,gotoStaffMainActivity;
    //private TextView alreaystafflogin;

    String category;
    private final int REQ = 1;
    private Bitmap bitmap;

    FirebaseAuth auth;
    private StorageReference storageReference;
    private DatabaseReference reference,dbRef,authDB;
    ProgressDialog pd;


    private String name,email,phoneNo,post,
            password,spinnerdeptCategory,downloadUrl = "";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_staff_details);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_btn);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addStaffImage = findViewById(R.id.addStaffImage);

        StaffName = findViewById(R.id.StaffName);
        StaffEmail = findViewById(R.id.StaffEmail);
        StaffPassword = findViewById(R.id.StaffPassword);
        StaffPhoneNo = findViewById(R.id.StaffPhoneNo);
        StaffPost = findViewById(R.id.StaffPost);
        //   alreaystafflogin=findViewById(R.id.alreaystafflogin);

        SpinnerDeptCategory = findViewById(R.id.SpinnerDeptCategory);
        CreateStaffLogin = findViewById(R.id.CreateStaffLogin);


        auth=FirebaseAuth.getInstance();

        storageReference= FirebaseStorage.getInstance().getReference();
        reference= FirebaseDatabase.getInstance().getReference("Staffs");

        pd=new ProgressDialog(this);


        String[] items = new String[]{"Select Department",
                "MCA", "MBA", "Computer Science Engineering", "Mechanical Engineering",
                "Civil Engineering", "ECE", "EEE", "S and H","Other Department"};

        SpinnerDeptCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items));

        SpinnerDeptCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category=SpinnerDeptCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        addStaffImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();

            }
        });


        CreateStaffLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap == null){
                    Toast.makeText(CreateStaffDetails.this,"Please Set Image",Toast.LENGTH_SHORT).show();
                }else {
                    checkValidation();
                }
            }
        });


    }

    private void checkValidation() {


        name = StaffName.getText().toString();
        email=StaffEmail.getText().toString();
        password=StaffPassword.getText().toString();
        phoneNo=StaffPhoneNo.getText().toString();
        post=StaffPost.getText().toString();
        spinnerdeptCategory=SpinnerDeptCategory.toString();



        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(phoneNo) || TextUtils.isEmpty(post) || TextUtils.isEmpty(spinnerdeptCategory))
        {
            Toast.makeText(CreateStaffDetails.this,"All fields are required" , Toast.LENGTH_SHORT).show();
        }
        else if (name.isEmpty()){
            StaffName.setError("Empty");
            StaffName.requestFocus();
        }else  if (email.isEmpty()){
            StaffEmail.setError("Empty");
            StaffEmail.requestFocus();
        }

        //password
        else   if (password.isEmpty()){
            StaffPassword.setError("Empty");
            StaffPassword.requestFocus();
        }  else if (password.length() < 6 )
        {
            Toast.makeText(CreateStaffDetails.this,"password must have 6 characters" , Toast.LENGTH_SHORT).show();
        }

        //Phone NO
        else   if (phoneNo.isEmpty()){
            StaffPhoneNo.setError("Empty");
            StaffPhoneNo.requestFocus();
        }  else if (phoneNo.length() < 10 )
        {
            Toast.makeText(CreateStaffDetails.this,"phone Number must have 10 Numbers" , Toast.LENGTH_SHORT).show();
        }


        else if (post.isEmpty()){
            StaffPost.setError("Empty");
            StaffPost.requestFocus();
        }else if (category.equals("Select Department")){
            Toast.makeText(CreateStaffDetails.this,"please provide Department Category" , Toast.LENGTH_SHORT).show();

        }else if (bitmap==null){
            // insertDate();
        }else{
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
        filePath=storageReference.child("Staffs").child(finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(CreateStaffDetails.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
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
                                    //   insertDate();
                                    insertDate(name,email,password);
                                }
                            });
                        }
                    });
                }else {
                    pd.dismiss();
                    Toast.makeText(CreateStaffDetails.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertDate(String name, String email, String password) {

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                FirebaseUser firebaseUser = auth.getCurrentUser();

              //  dbRef=reference.child(category);
                String dbkey = reference.push().getKey();
                //final String uniqueKey = dbRef.push().getKey();

                String userid = firebaseUser.getUid();
                String userdbpushKey = dbkey + userid;

                StaffData staffData = new StaffData(name,email,password,phoneNo,post,category,downloadUrl,userid,dbkey,userdbpushKey);
                reference.child(userdbpushKey).setValue(staffData).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        pd.dismiss();
                        Toast.makeText(CreateStaffDetails.this,"Staff added Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreateStaffDetails.this, StaffMainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);


                        SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
                        editor.putString("category", category);
                        editor.apply();

                        SharedPreferences.Editor editor1 = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
                        editor1.putString("userid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                        editor1.apply();

                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(CreateStaffDetails.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                    }
                });
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
            addStaffImage.setImageBitmap(bitmap);
        }
    }
}