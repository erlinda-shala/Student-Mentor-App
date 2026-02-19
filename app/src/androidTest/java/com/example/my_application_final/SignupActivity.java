package com.example.my_application_final; // Keep your actual package name here

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    // 1. Declare variables
    private EditText emailField, passwordField;
    private Button signupBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // 2. Initialize Firebase and UI elements
        mAuth = FirebaseAuth.getInstance();
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        signupBtn = findViewById(R.id.signupBtn);

        // 3. Set the button click action
        signupBtn.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            String pass = passwordField.getText().toString().trim();

            if (email.isEmpty() || pass.length() < 6) {
                Toast.makeText(this, "Enter email and 6+ char password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create user in Firebase
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Signup Success!", Toast.LENGTH_SHORT).show();
                            // This moves the user to the Login screen after signing up
                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(SignupActivity.this, "Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}