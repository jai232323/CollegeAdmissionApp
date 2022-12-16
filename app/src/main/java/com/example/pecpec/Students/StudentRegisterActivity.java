package com.example.pecpec.Students;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pecpec.R;
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


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class StudentRegisterActivity extends AppCompatActivity {

    private ImageView RegisterStudentImage;
    private EditText RegisterStudentName,RegisterStudentEmail,RegisterStudentPassword;
    private Button RegisterLoginBtn;
    private TextView Register_txt_signup;

    private FirebaseAuth auth;
    private StorageReference storageReference;

    private DatabaseReference reference,dbRef,authDB;
    private ProgressDialog pd;

    String category;
    private final int REQ = 1;
    private Bitmap bitmap;
    private String Name,EMail,Password,downloadUrl = "img";


    FirebaseUser firebaseUser;

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (firebaseUser != null){
//            startActivity(new Intent(StudentRegisterActivity.this,StudentMainActivity.class));
//            finish();
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register2);

        RegisterStudentImage = findViewById(R.id.RegisterStudentImage);
        RegisterStudentName = findViewById(R.id.RegisterStudentName);
        RegisterStudentEmail = findViewById(R.id.RegisterStudentEmail);
        RegisterStudentPassword = findViewById(R.id.RegisterStudentPassword);
        RegisterLoginBtn = findViewById(R.id.RegisterLoginBtn);
        Register_txt_signup = findViewById(R.id.Register_txt_signup);

        pd = new ProgressDialog(this);

        auth = FirebaseAuth.getInstance();


        storageReference = FirebaseStorage.getInstance().getReference();

        RegisterStudentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
//                if (RegisterStudentImage==null){
//                    Toast.makeText(StudentRegisterActivity.this,"please set yout image",Toast.LENGTH_SHORT).show();
//                }

            }
        });



        Register_txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(StudentRegisterActivity.this,StudentLoginActivity.class));
                finish();
            }
        });
        RegisterLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
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

    private void checkValidation() {
        Name =RegisterStudentName.getText().toString();
        EMail =RegisterStudentEmail.getText().toString();
        Password =RegisterStudentPassword.getText().toString();

        if (Name.isEmpty()){
            RegisterStudentName.setError("Name Empty");
            RegisterStudentName.requestFocus();
        }else  if (EMail.isEmpty()){
            RegisterStudentEmail.setError("Email Empty");
            RegisterStudentEmail.requestFocus();
        }

        //password
        else   if (Password.isEmpty()){
            RegisterStudentPassword.setError("Password Empty");
            RegisterStudentPassword.requestFocus();
        }  else if (Password.length() < 6 )
        {
            Toast.makeText(StudentRegisterActivity.this,"Password must have 6 Characters" , Toast.LENGTH_SHORT).show();
        }else if (bitmap==null){
        }else if (RegisterStudentImage==null){
            Toast.makeText(StudentRegisterActivity.this,"Please Set Your Image" , Toast.LENGTH_SHORT).show();
        }
        else{
            uploadImage();
        }


    }

    private void uploadImage() {
        pd.setMessage("Your Data Uploading\nPlease Wait...");
        pd.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();

        final StorageReference filePath;
        filePath=storageReference.child("Students").child(finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(StudentRegisterActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
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
                                    insertDate(Name,EMail,Password);
                                }
                            });
                        }
                    });
                }else {
                    pd.dismiss();
                    Toast.makeText(StudentRegisterActivity.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertDate(String name, String eMail, String password) {

        auth.createUserWithEmailAndPassword(eMail, password).addOnCompleteListener(StudentRegisterActivity.this , new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                FirebaseUser firebaseUser = auth.getCurrentUser();
                String userid = firebaseUser.getUid();

                reference = FirebaseDatabase.getInstance().getReference().child("Students").child(userid);
                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put("F_Id", userid);
                hashMap.put("S_Name", name);
                hashMap.put("S_Email", eMail);
                hashMap.put("S_Password", password);
                hashMap.put("S_Image", downloadUrl);

                reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            pd.dismiss();
                            Intent intent = new Intent(StudentRegisterActivity.this, StudentMainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                            SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
                            editor.putString("userid", userid);
                            editor.apply();

                            startActivity(intent);

                            finish();



                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(StudentRegisterActivity.this,"You can't register",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}