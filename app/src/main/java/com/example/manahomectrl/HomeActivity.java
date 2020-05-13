package com.example.manahomectrl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
        Button btnwaterpump;
        Button btnwatersprinkler;
        Button btnleakage;
        Button btnLogout;
        FirebaseAuth mFirebaseAuth;
        private FirebaseAuth.AuthStateListener mAuthStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(HomeActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


        btnLogout = findViewById(R.id.logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent inToMain = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(inToMain);
            }
        });
        btnwaterpump = findViewById(R.id.button3);
        btnwaterpump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intoPump = new Intent(HomeActivity.this,WaterPump.class);
                startActivity(intoPump);
            }
        });
        btnwatersprinkler = findViewById(R.id.button4);
        btnwatersprinkler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intoSprinkle = new Intent(HomeActivity.this,WaterSprinkler.class);
                startActivity(intoSprinkle);
            }
        });
        btnleakage = findViewById(R.id.button5);
        btnleakage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intoLeakage = new Intent(HomeActivity.this,LeakageActivity.class);
                startActivity(intoLeakage);
            }
        });

    }

}
