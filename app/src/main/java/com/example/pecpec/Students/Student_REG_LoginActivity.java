package com.example.pecpec.Students;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pecpec.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Student_REG_LoginActivity extends AppCompatActivity {


    private Button Register,Login;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reg_login);


        Register=findViewById(R.id.Register);
        Login=findViewById(R.id.Login);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Student_REG_LoginActivity.this,StudentRegisterActivity.class);
                startActivity(i);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Student_REG_LoginActivity.this,StudentLoginActivity.class);
                startActivity(i);
            }
        });

    }
}