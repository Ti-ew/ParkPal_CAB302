package com.example.parkpal;

import android.app.Dialog;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;

public class HomePageActivity extends AppCompatActivity {

    private static final String tag = "HomePageActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        logout = (Button)findViewById(R.id.et_Logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomePageActivity.this, LoginActivity.class));
                finish();
            }
        });
        if(isServicesOk()){
            init();
        }
    }

    public void init(){
        Button mapBtn = (Button)findViewById(R.id.map_button);
        mapBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(HomePageActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isServicesOk(){
        Log.d(tag, "isServicesOk: checking google services version.");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(HomePageActivity.this);

        if(available == ConnectionResult.SUCCESS){
            /*Everything Is Fine And The User Can Make Map Requests*/
            Log.d(tag, "isServicesOk: Google Play Services is working.");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            /*An Error Occurred But We Can Resolve It*/
            Log.d(tag, "isServicesOk: an error occurred but we can fix it.");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(HomePageActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else{
            Toast.makeText(this, "We can't make map requests",Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    public void disable(View v){
        v.setEnabled(false);
        Button button = (Button)v;
        button.setText("Disabled");
    }
    public void launchGoogleMaps(View v){
        //Launch The Settings Page
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }
    public void launchSettings(View v){
        //Launch The Settings Page
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }
    public void launchProfilePage(View v){
        //Launch The Settings Page
        Intent i = new Intent(this, AccountProfileActivity.class);
        startActivity(i);
    }
    public void launchWalletPage(View v){
        //Launch The Settings Page
        Intent i = new Intent(this, PaymentsActivity.class);
        startActivity(i);
    }
}