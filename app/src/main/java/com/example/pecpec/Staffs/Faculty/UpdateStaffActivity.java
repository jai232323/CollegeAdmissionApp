package com.example.pecpec.Staffs.Faculty;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.pecpec.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class UpdateStaffActivity extends AppCompatActivity {

    private ImageView updateStaffImage;
    private EditText updateStaffName,updateStaffEmail,updateStaffPost;
    private Button updateStaffBtn,deleteStaffBtn;

    private String name,email,post,image,uniqueKey,category,userdbpushKey;

    private final int REQ = 1;
    private Bitmap bitmap = null;

    private StorageReference storageReference;
    private DatabaseReference reference;
    ProgressDialog pd;
    private String downloadUrl;

    AdmissionData admissionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_staff);


        pd = new ProgressDialog(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Update Faculty");
        toolbar.setNavigationIcon(R.drawable.ic_back_btn);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        name=getIntent().getStringExtra("name");
        email=getIntent().getStringExtra("email");
        post=getIntent().getStringExtra("post");
        image=getIntent().getStringExtra("image");
        uniqueKey=getIntent().getStringExtra("key");
        category=getIntent().getStringExtra("category");
        userdbpushKey = getIntent().getStringExtra("userdbpushKey");

        updateStaffImage=findViewById(R.id.updateStaffImage);
        updateStaffName=findViewById(R.id.updateStaffName);
        updateStaffEmail=findViewById(R.id.updateStaffEmail);
        updateStaffPost=findViewById(R.id.updateStaffPost);
        updateStaffBtn=findViewById(R.id.updateStaffBtn);
        deleteStaffBtn=findViewById(R.id.deleteStaffBtn);




        storageReference= FirebaseStorage.getInstance().getReference();
        reference= FirebaseDatabase.getInstance().getReference("Staffs");

        Picasso.get().load(image).into(updateStaffImage);



        updateStaffName.setText(name);
        updateStaffEmail.setText(email);
        updateStaffPost.setText(post);

        updateStaffImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        updateStaffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = updateStaffName.getText().toString();
                email=updateStaffEmail.getText().toString();
                post=updateStaffPost.getText().toString();

                //Method Under
                checkValidation();
            }
        });
        deleteStaffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });

    }

    private void deleteData() {
        reference.child(userdbpushKey).removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(UpdateStaffActivity.this,"Deleted Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateStaffActivity.this,FacultyActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateStaffActivity.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkValidation() {


        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(post))
        {
            Toast.makeText(UpdateStaffActivity.this,"All fields are required" , Toast.LENGTH_SHORT).show();
        }
        else if (name.isEmpty()){
            updateStaffName.setError("Empty");
            updateStaffName.requestFocus();
        }else  if (email.isEmpty()){
            updateStaffEmail.setError("Empty");
            updateStaffEmail.requestFocus();
        }
        else  if (post.isEmpty()){
            updateStaffPost.setError("Empty");
            updateStaffPost.requestFocus();
        }else if (bitmap == null){
            updateData(image);
        }else {
            uploadImage();
        }


    }
    private void updateData(String s) {

        HashMap hashMap = new HashMap();
        hashMap.put("name",name);
        hashMap.put("email",email);
        hashMap.put("post",post);
        hashMap.put("image",image);

        reference.child(userdbpushKey).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {

                Toast.makeText(UpdateStaffActivity.this,"Updated Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateStaffActivity.this,FacultyActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateStaffActivity.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void uploadImage() {
//
        pd.setMessage("Uploading");
        pd.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();

        final StorageReference filePath;
        filePath=storageReference.child("Staffs").child(finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(UpdateStaffActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
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

                                    HashMap<String,Object> hashMap = new HashMap<>();
                                    hashMap.put("image",downloadUrl);
                                    hashMap.put("name",name);
                                    hashMap.put("email",email);
                                    hashMap.put("post",post);

                                    reference.child(userdbpushKey).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            pd.dismiss();
                                            Toast.makeText(UpdateStaffActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(UpdateStaffActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    //updateData(downloadUrl);
                                }
                            });
                        }
                    });
                }else {
                    //pd.dismiss();
                    Toast.makeText(UpdateStaffActivity.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
                }
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
            updateStaffImage.setImageBitmap(bitmap);
        }
    }
}