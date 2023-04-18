package com.example.parkpal;

import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;;
import com.google.firebase.auth.FirebaseUser;
import org.jetbrains.annotations.NotNull;


public class LoginActivity extends AppCompatActivity {

    private TextView register;
    private EditText editTextEmail, editTextPassword;
    private Button logIn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView)findViewById(R.id.et_Register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                finish();
            }
        });

        logIn = (Button)findViewById(R.id.et_Login);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        editTextEmail = (EditText) findViewById(R.id.et_Email);
        editTextPassword = (EditText) findViewById(R.id.et_Password);

        progressBar = (ProgressBar) findViewById(R.id.et_ProgressBar);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this,HomePageActivity.class));
        }
    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email Is Required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please Enter A Valid Email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextEmail.setError("Password Is Required");
            editTextEmail.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextEmail.setError("Password Must Contain At Least 6 Characters");
            editTextEmail.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        startActivity(new Intent(LoginActivity.this,HomePageActivity.class));
                        finish();
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Please Check Your Email Inbox To Verify Your Account", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Failed To Login, Please Check Your Credentials", Toast.LENGTH_LONG).show();
                }
            }
        }) ;
    }
}
