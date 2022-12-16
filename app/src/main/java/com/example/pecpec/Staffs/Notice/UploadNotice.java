package com.example.pecpec.Staffs.Notice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pecpec.FullImagerActivity;
import com.example.pecpec.R;

import com.example.pecpec.Staffs.StaffMainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UploadNotice extends AppCompatActivity {

    private CardView addImage;
    private ImageView noticeImageView;
    private EditText noticeTitle;
    private Button uploadNoticeBtn;

    FirebaseAuth auth;

    //Firebase Initialize
    private DatabaseReference reference,dbRef;
    private StorageReference storageReference;

    private ProgressDialog pd;

    private final int REQ = 0;
    private Bitmap bitmap;
    String downlodUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notice);

        reference= FirebaseDatabase.getInstance().getReference();
        storageReference=FirebaseStorage.getInstance().getReference();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Upload Notice");
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

        addImage=findViewById(R.id.addImage);
        noticeImageView=findViewById(R.id.noticeImagView);
        noticeTitle=findViewById(R.id.noticeTitle);
        uploadNoticeBtn=findViewById(R.id.uploadNoticeBtn);

        auth= FirebaseAuth.getInstance();

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        noticeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadNotice.this, FullImagerActivity.class);
                NoticeData currentItem = null;
                intent.putExtra("image",currentItem.getImage());
                startActivity(intent);
            }
        });


        uploadNoticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (noticeTitle.getText().toString().isEmpty()){
                    noticeTitle.setError("Empty");
                    noticeTitle.requestFocus();
                }else if (bitmap == null){
                    uploadData();
                }else {
                    uploadImage();
                }

            }
        });
    }

    private void uploadImage() {

        pd.setMessage("Uploading");
        pd.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();

        final StorageReference filePath;

        filePath=storageReference.child("Notice").child(finalimg+".jpg");

        final UploadTask uploadTask = filePath.putBytes(finalimg);

        uploadTask.addOnCompleteListener(UploadNotice.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downlodUrl = String.valueOf(uri);
                                    uploadData();
                                }
                            });
                        }
                    });
                }else {
                    pd.dismiss();
                    Toast.makeText(UploadNotice.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploadData() {

        dbRef=reference.child("Notice");
        final String uniqueKey = dbRef.push().getKey();

        FirebaseUser firebaseUser = auth.getCurrentUser();
        String userid = firebaseUser.getUid();

        String title = noticeTitle.getText().toString();

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
        String date=currentDate.format(calForDate.getTime());


        Calendar calForTIme = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time=currentTime.format(calForTIme.getTime());

        NoticeData noticeData = new NoticeData(title,downlodUrl,date,time,uniqueKey,userid);

        dbRef.child(uniqueKey).setValue(noticeData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                Toast.makeText(UploadNotice.this,"Notice Upload Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UploadNotice.this, StaffMainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadNotice.this,"Something went wrong",Toast.LENGTH_SHORT).show();
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
            noticeImageView.setImageBitmap(bitmap);
        }
    }
}