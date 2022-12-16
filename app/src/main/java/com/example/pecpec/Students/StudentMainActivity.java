package com.example.pecpec.Students;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import com.example.pecpec.BuildConfig;
import com.example.pecpec.R;
import com.example.pecpec.Students.Fragments.StudentProfileFragment;
import com.example.pecpec.Students.NavigationAdmissionForm.NavigationStudentAdmissionForm;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class StudentMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //    private TextView textView;
//    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    //Theme

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int checkedItem;
    private String selected;

    private final String CHECKEDITEM = "checked_ite";

    androidx.fragment.app.Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        sharedPreferences = this.getSharedPreferences("themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        switch (getCheckedItem()) {
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("PEC");
        toolbar.setNavigationIcon(R.drawable.ic_nav_drawer_icon);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        textView=findViewById(R.id.textview_framelayout);
//        frameLayout=findViewById(R.id.frame_layout);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.frame_layout);


//        textView.setText("Home");
//        bottomNavigationView.setOnNavigationItemSelectedListener(nav);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view_student);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(this);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        Intent intent;

        switch (item.getItemId()) {
            case R.id.navigation_faculty:
                intent = new Intent(StudentMainActivity.this, Faculty_FacultyActivity.class);
                startActivity(intent);
                break;
//            case R.id.navigation_student_admissionForm:
//                intent = new Intent(StudentMainActivity.this, NavigationStudentAdmissionForm.class);
//                startActivity(intent);
//                break;
            case R.id.navigation_notice:
                intent = new Intent(StudentMainActivity.this, Notice_NoticeActivity.class);
                startActivity(intent);
                break;

            case R.id.navigation_Genuineness:
                intent = new Intent(StudentMainActivity.this, GenuninenessActivity.class);
                startActivity(intent);
                break;

            case R.id.navigation_contact_us:
                intent = new Intent(StudentMainActivity.this, ContactUsActivity.class);
                startActivity(intent);
                break;

            case R.id.navigation_developer:
                intent = new Intent(StudentMainActivity.this, DevloperActivity.class);
                startActivity(intent);
                break;
            case R.id.navigation_about:
                intent = new Intent(StudentMainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;

            case R.id.navigation_color:
                showDialog();
                break;

            case R.id.navigation_share:

                try {
                    Intent intent1 = new Intent(Intent.ACTION_SEND);
                    intent1.setType("text/plain");
                    intent1.putExtra(Intent.EXTRA_SUBJECT, "Share");
                    String shareMessage = "https://play.google.com/store/apps/details?id=in.superdream.datastructure&hl=en_IN&gl=US"
                            + BuildConfig.APPLICATION_ID + "\n\n";
                    intent1.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(intent1, "share by PEC"));
                } catch (Exception e) {
                    Toast.makeText(this, "Something went wrong!!!", Toast.LENGTH_SHORT).show();
                }


                break;


//            case R.id.navigation_studentProfile:
//
//                SharedPreferences.Editor editor = getSharedPreferences("PREFS",MODE_PRIVATE).edit();
//                editor.putString("userid", FirebaseAuth.getInstance().getCurrentUser().getUid());
//                editor.apply();
//
//                selectedFragment = new StudentProfileFragment();
//
//                break;

//            case R.id.navigation_ebook:
//                intent = new Intent(StudentMainActivity1.this, EbookActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.navigation_website:
//                Toast.makeText(this,"Website",Toast.LENGTH_SHORT).show();
//                break;

//            case R.id.navigation_themes:
//                Toast.makeText(this,"Themes",Toast.LENGTH_SHORT).show();
//                break;

        }

        return true;
    }

    private void showDialog() {
        String[] themes = this.getResources().getStringArray(R.array.theme);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Select Theme");
        builder.setSingleChoiceItems(R.array.theme, getCheckedItem(),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selected = themes[which];
                        checkedItem = which;
                    }
                });
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (selected == null) {
                            selected = themes[which];
                            checkedItem = which;
                        }
                        switch (selected) {
                            case "System Default":
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                                break;
                            case "Dark":
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                                break;
                            case "Light":
                                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                                break;
                        }
                        setCheckedItem(which);
                    }
                });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public int getCheckedItem() {
        return sharedPreferences.getInt(CHECKEDITEM, 0);
    }

    public void setCheckedItem(int checkedItem) {
        editor.putInt(CHECKEDITEM, checkedItem);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }
//    private BottomNavigationView.OnNavigationItemSelectedListener nav = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
//            switch (item.getItemId()){
//                case R.id.navigation_home:
//                    textView.setText("Home");
//                    break;
//                case R.id.navigation_admission:
//                    textView.setText("Admission");
//                    break;
//                case R.id.navigation_department:
//                    textView.setText("Department");
//                    break;
//                case R.id.navigation_gallery:
//                    textView.setText("Gallery");
//                    break;
//                case R.id.navigation_about:
//                    textView.setText("About");
//                    break;
//            }
//            return  true;
//        }
//    };

}