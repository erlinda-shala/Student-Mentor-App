package com.example.my_application_final;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SubjectDetailActivity extends AppCompatActivity {
    // For now, we simulate you are a Year 2 student
    private int studentYear = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_detail);

        String title = getIntent().getStringExtra("subject_name");
        int requiredYear = getIntent().getIntExtra("subject_year", 1);

        TextView titleView = findViewById(R.id.detailTitle);
        TextView yearView = findViewById(R.id.detailYearReq);
        Button enrollBtn = findViewById(R.id.enrollBtn);

        titleView.setText(title);
        yearView.setText("Required Year: " + requiredYear);

        enrollBtn.setOnClickListener(v -> {
            if (studentYear == requiredYear) {
                Toast.makeText(this, "Successfully Enrolled in " + title, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Access Denied: You are Year " + studentYear, Toast.LENGTH_LONG).show();
            }
        });
    }
}
