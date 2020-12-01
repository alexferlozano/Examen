package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ListElement> permisos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permisos = new ArrayList<>();
        permisos.add(new ListElement("#00AEFF", "Llamadas", "call_black", "CALL_PHONE"));
        permisos.add(new ListElement("#4b5497", "Almacenamiento", "sd_storage_black", "INTERNET"));

        ListAdapter listAdapter = new ListAdapter(permisos, MainActivity.this, this, this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);



            Button btnLogin = findViewById(R.id.btn_login);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent (view.getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            Button btnRegis = findViewById(R.id.btn_registro);
            btnRegis.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent (view.getContext(), RegistroActivity.class);
                    startActivity(intent);
                }
            });
        }
    }