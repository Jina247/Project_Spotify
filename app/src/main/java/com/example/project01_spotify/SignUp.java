package com.example.project01_spotify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    private EditText emailAndPhoneField;
    private EditText passwordField;
    private EditText re_passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sign_up);

        emailAndPhoneField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        re_passwordField = findViewById(R.id.re_passwordField);

        Button signUpButton = findViewById(R.id.signUpClickAction);
        signUpButton.setOnClickListener(v -> {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            Pattern pattern = Pattern.compile(emailRegex);

            String phoneReg = "^(\\\\+\\\\d{1,3}( )?)?((\\\\(\\\\d{1,3}\\\\))|\\\\d{1,3})[- .]?\\\\d{3,4}[- .]?\\\\d{4}$";
            Pattern pattern1 = Pattern.compile(phoneReg);

            String emailAndPhone = emailAndPhoneField.getText().toString().trim();
            if (emailAndPhone.isEmpty()) {
                emailAndPhoneField.setError("Email field is required");
            } else if (!(pattern.matcher(emailAndPhone).matches())) {
                emailAndPhoneField.setError("Invalid email. Try again");
            } else if (!(pattern1.matcher(emailAndPhone).matches())) {
                emailAndPhoneField.setError("Invalid phone number. Try again");
            } else {
                emailAndPhoneField.setError(null);
            }

            String password = passwordField.getText().toString().trim();
            if (password.isEmpty()) {
                passwordField.setError("Password field is required");
            } else {
                passwordField.setError(null);
            }

            String re_password = re_passwordField.getText().toString().trim();
            if (re_password.isEmpty()) {
                re_passwordField.setError("Password is empty");
            } else if (!(re_password.equals(password))) {
                re_passwordField.setError("Wrong password");
            } else {
                re_passwordField.setError(null);
            }
            Log.d("Sign up test", "emailAndPhone or phone: $emailAndPhoneField and password: $password");
        });

        Button signInOption = findViewById(R.id.signInAction);
        signInOption.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, SignIn.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}