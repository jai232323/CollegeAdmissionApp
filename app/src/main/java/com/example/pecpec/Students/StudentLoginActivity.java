package com.example.pecpec.Students;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pecpec.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentLoginActivity extends AppCompatActivity {

     private EditText LoginStudentEmail,LoginStudentPassword;
     Button StudentLoginBtn;
     TextView Student_txt_signup;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (firebaseUser != null) {
//            startActivity(new Intent(StudentLoginActivity.this,StudentMainActivity.class));
//            finish();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);


        LoginStudentEmail=findViewById(R.id.LoginStudentEmail);
        LoginStudentPassword=findViewById(R.id.LoginStudentPassword);
        StudentLoginBtn=findViewById(R.id.StudentLoginBtn);
        Student_txt_signup=findViewById(R.id.Student_txt_signup);

        auth=FirebaseAuth.getInstance();


        Student_txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(StudentLoginActivity.this,StudentRegisterActivity.class));
                finish();
            }
        });
        StudentLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog pd = new ProgressDialog(StudentLoginActivity.this);
                pd.setMessage("Please Wait...");
                pd.show();

                String str_email=LoginStudentEmail.getText().toString();
                String str_password = LoginStudentPassword.getText().toString();

                if (TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password)){
                    Toast.makeText(StudentLoginActivity.this,"All Fields are Required",Toast.LENGTH_SHORT).show();
                }else {
                    auth.signInWithEmailAndPassword(str_email,str_password)
                            .addOnCompleteListener(StudentLoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users")
                                                .child(auth.getCurrentUser().getUid());

                                        reference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                pd.dismiss();
                                                Intent intent = new Intent(StudentLoginActivity.this,StudentMainActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                pd.dismiss();

                                            }
                                        });
                                    }else {
                                        pd.dismiss();
                                        Toast.makeText(StudentLoginActivity.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}