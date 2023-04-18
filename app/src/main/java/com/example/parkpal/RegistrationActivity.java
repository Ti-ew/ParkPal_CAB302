package com.example.parkpal;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import org.jetbrains.annotations.NotNull;

public class RegistrationActivity extends AppCompatActivity{

    EditText regFullName, regEmailAddress, regPassword, regConfirmPassword;
    Button  registerUserButton, goBackToLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        regFullName = findViewById(R.id.et_registerName);
        regEmailAddress = findViewById(R.id.et_registerEmail);
        regPassword = findViewById(R.id.et_registerPassword);
        regConfirmPassword = findViewById(R.id.et_confirmPassword);
        registerUserButton = findViewById(R.id.et_registerNewAccount);
        registerUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewAccount();
                finish();
            }
        });
        goBackToLogin = findViewById(R.id.et_backToLogin);
        goBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }
        });

        fAuth = FirebaseAuth.getInstance();
    }


    private void registerNewAccount(){
        String fullName = regFullName.getText().toString();
        String emailAddress = regEmailAddress.getText().toString();
        String password = regPassword.getText().toString();
        String passwordConfirmation = regConfirmPassword.getText().toString();

        if(fullName.isEmpty()){
            regFullName.setError("Your Full Name Is Required");
            return;
        }
        if(emailAddress.isEmpty()){
            regEmailAddress.setError("A Valid Email Address Is Required");
            return;
        }
        if(password.isEmpty()){
            regPassword.setError("A Valid Password Is Required");
            return;
        }
        if(passwordConfirmation.isEmpty()){
            regConfirmPassword.setError("You Need To Confirm Your Password");
            return;
        }
        if(!password.equals(passwordConfirmation)){
            regConfirmPassword.setError("Passwords Do Not Match");
            return;
        }

        Toast.makeText(RegistrationActivity.this, "Data Was Validated", Toast.LENGTH_SHORT).show();

        fAuth.createUserWithEmailAndPassword(emailAddress, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                startActivity(new Intent(getApplicationContext(), VerifyEmailActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}