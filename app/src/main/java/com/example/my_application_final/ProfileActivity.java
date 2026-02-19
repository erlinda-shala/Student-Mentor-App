package com.example.my_application_final;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        // --- UI ELEMENTS ---
        RadioGroup yearGroup = findViewById(R.id.yearRadioGroup);
        TextView enrolledText = findViewById(R.id.enrolledCoursesList);
        TextView profileNameText = findViewById(R.id.profileName); // The "User Profile" text
        Button logoutBtn = findViewById(R.id.profileLogoutBtn);

        // --- DISPLAY YOUR NAME ---
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            // This will say "Welcome, yourname@email.com"
            profileNameText.setText("Welcome, " + email);
        }

        // --- LOAD SAVED YEAR ---
        int savedYear = prefs.getInt("studentYear", 1);
        if (savedYear == 1) yearGroup.check(R.id.year1);
        else if (savedYear == 2) yearGroup.check(R.id.year2);
        else if (savedYear == 3) yearGroup.check(R.id.year3);

        // --- SAVE YEAR ON CHANGE ---
        yearGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int year = 1;
            if (checkedId == R.id.year1) year = 1;
            else if (checkedId == R.id.year2) year = 2;
            else if (checkedId == R.id.year3) year = 3;
            prefs.edit().putInt("studentYear", year).apply();
        });

        // --- READ ENROLLMENTS ---
        String list = prefs.getString("enrolledList", "No enrollments yet");
        enrolledText.setText(list);

        // --- LOGOUT LOGIC (Now fixed) ---
        logoutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            // Clear the app history so you can't go back after logout
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        // --- BOTTOM NAVIGATION ---
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_profile);
        bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                startActivity(new Intent(this, MainActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }
            return true;
        });
    }
}
