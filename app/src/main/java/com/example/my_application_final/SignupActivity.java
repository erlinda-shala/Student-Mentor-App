package com.example.my_application_final;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth; // The Firebase "Connection"

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth; // Create a variable for Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance(); // Initialize Firebase

        EditText emailField = findViewById(R.id.signupEmail);
        EditText passwordField = findViewById(R.id.signupPassword);
        Button registerBtn = findViewById(R.id.signupBtn);

        registerBtn.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            if (email.isEmpty() || password.length() < 6) {
                Toast.makeText(this, "Check email or password (min 6 chars)", Toast.LENGTH_SHORT).show();
                return;
            }

            // THIS IS THE MAGIC LINE: Sends data to Firebase
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Account Created!", Toast.LENGTH_LONG).show();
                            finish(); // Takes them back to Login screen
                        } else {
                            Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });

        // Your existing back button logic
        findViewById(R.id.backToLogin).setOnClickListener(v -> finish());
    }
}
