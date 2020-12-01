package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class PermissionsActivity extends AppCompatActivity{
    List<ListElement> permisos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        permisos = new ArrayList<>();
        permisos.add(new ListElement("#00AEFF", "Llamadas", "call_black", Manifest.permission.CALL_PHONE));
        permisos.add(new ListElement("#4b5497", "Cámara", "sd_storage_black", Manifest.permission.CAMERA));

        ListAdapter listAdapter = new ListAdapter(permisos, this.getApplicationContext(), PermissionsActivity.this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
}
