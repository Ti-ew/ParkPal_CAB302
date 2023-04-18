package com.example.parkpal;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void launchGeneralSettings(View view) {
        Intent i = new Intent(this, GeneralSettingsActivity.class);
        startActivity(i);
    }

    public void launchNotificationsSettings(View view) {
        Intent i = new Intent(this, NotificationSettingsActivity.class);
        startActivity(i);
    }

    public void launchPrivacySettings(View view) {
        Intent i = new Intent(this, PrivacySettingsActivity.class);
        startActivity(i);
    }
    public void launchAccountSettings(View view) {
        Intent i = new Intent(this, AccountSettingsActivity.class);
        startActivity(i);
    }
    public void launchVoiceSoundSettings(View v){
        //Launch The Settings Page
        Intent i = new Intent(this, VoiceSoundSettingsActivity.class);
        startActivity(i);
    }
}
