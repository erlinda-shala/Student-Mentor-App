package com.example.my_application_final;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Access Local Memory
        SharedPreferences prefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        int studentYear = prefs.getInt("studentYear", 1);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSubjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ALL 12 SUBJECTS RESTORED
        List<Subject> subjects = new ArrayList<>();
        // Year 1
        subjects.add(new Subject("Programming 1 & 2", "15 Mentors", 1));
        subjects.add(new Subject("Intro to Informatics", "10 Mentors", 1));
        subjects.add(new Subject("English 1", "10 Mentors", 1));
        subjects.add(new Subject("Probability and Statistics", "5 Mentors", 1));
        // Year 2
        subjects.add(new Subject("Android Development", "8 Mentors", 2));
        subjects.add(new Subject("Operating Systems", "7 Mentors", 2));
        subjects.add(new Subject("Graphic Design", "10 Mentors", 2));
        subjects.add(new Subject("Intro to Cyber Security", "6 Mentors", 2));
        // Year 3
        subjects.add(new Subject("Cloud Computing", "5 Mentors", 3));
        subjects.add(new Subject("Intro to AI", "4 Mentors", 3));
        subjects.add(new Subject("Big Data", "2 Mentors", 3));
        subjects.add(new Subject("IoT (Internet of Things)", "3 Mentors", 3));

        SubjectAdapter adapter = new SubjectAdapter(subjects, subject -> {
            if (studentYear == subject.getRequiredYear()) {
                // SAVE ENROLLMENT LOCALLY
                String currentList = prefs.getString("enrolledList", "");
                if (!currentList.contains(subject.getName())) {
                    String newList = currentList + "\n• " + subject.getName();
                    prefs.edit().putString("enrolledList", newList).apply();
                }
                Toast.makeText(this, "Enrolled in " + subject.getName(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "You are Year " + studentYear + ". This is for Year " + subject.getRequiredYear(), Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_home);
        bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_profile) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                overridePendingTransition(0, 0);
            }
            return true;
        });
    }
}
