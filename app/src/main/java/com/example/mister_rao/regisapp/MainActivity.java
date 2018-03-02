package com.example.mister_rao.regisapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView uName, uEmail, uUname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uName = findViewById(R.id.txv_name);
        uEmail = findViewById(R.id.txv_Email);
        uUname = findViewById(R.id.txv_Username);

        String sName = getIntent().getStringExtra("intent_Name");
        String sEmail = getIntent().getStringExtra("intent_Email");
        String sUname = getIntent().getStringExtra("intent_Username");

        uName.setText(sName);
        uEmail.setText(sEmail);
        uUname.setText(sUname);
    }
}
