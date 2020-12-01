package com.example.examen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    TextView v_name,v_email;
    String name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        v_name=(TextView)findViewById(R.id.v_name);
        v_email=(TextView)findViewById(R.id.v_correo);
        name=getIntent().getExtras().getString("name");
        email=getIntent().getExtras().getString("email");

        v_name.setText(name);
        v_email.setText(email);
    }
}
