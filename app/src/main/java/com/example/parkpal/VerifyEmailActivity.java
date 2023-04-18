package com.example.parkpal;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import org.jetbrains.annotations.NotNull;

public class VerifyEmailActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button verify_resend_btn, return_to_login_page;
    TextView verification_resent_prompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);
        auth = FirebaseAuth.getInstance();
        verification_resent_prompt = (TextView) findViewById(R.id.prompt_Verification);
        verify_resend_btn = (Button) findViewById(R.id.verify_btn);
        verify_resend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(VerifyEmailActivity.this, "Verification Email Has Been Sent", Toast.LENGTH_LONG).show();
                        verify_resend_btn.setText(R.string.key_resend_email);
                        verification_resent_prompt.setText(R.string.verification_text_2);
                    }
                });
            }
        });
        return_to_login_page = (Button) findViewById(R.id.returnToLoginBtn);
        return_to_login_page.setEnabled(true);
        return_to_login_page.setVisibility(View.VISIBLE);
        return_to_login_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }
}