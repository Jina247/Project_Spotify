package com.example.project01_spotify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Pattern;

public class SignIn extends AppCompatActivity {
    private TextInputLayout emailAndUsernameLabel;
    private TextInputEditText emailAndUsernameEditText;
    private TextInputLayout passwordLabel;
    private TextInputEditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        emailAndUsernameLabel = findViewById(R.id.emailLabel);
        emailAndUsernameEditText = findViewById(R.id.emailEditText);
        passwordLabel = findViewById(R.id.passwordLabel);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button button = findViewById(R.id.loginClickButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                Pattern pattern = Pattern.compile(emailRegex);

                String regex = "^[A-Za-z]\\w{5,29}$";
                Pattern pattern1 = Pattern.compile(regex);
                
                String emailAndUsername = Objects.requireNonNull(emailAndUsernameEditText.getText()).toString().trim();
                if (emailAndUsername.isEmpty()) {
                    emailAndUsernameLabel.setError("Email or username are required");
                } else if (!(pattern.matcher(emailAndUsername).matches())) {
                    emailAndUsernameLabel.setError("Email isn't matched :(");
                } else if (!(pattern1.matcher(emailAndUsername).matches())) {
                    emailAndUsernameLabel.setError("Username isn't matched :(");
                } else {
                    emailAndUsernameLabel.setError(null);
                }

                String password = Objects.requireNonNull(passwordEditText.getText()).toString().trim();
                if (password.isEmpty()) {
                    passwordLabel.setError("Password is required");
                } else {
                    passwordLabel.setError(null);
                }
            }
        });
        Button signUpButton = findViewById(R.id.signUpOption);
        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignIn.this, SignUp.class);
            startActivity(intent);
        });

    }
}