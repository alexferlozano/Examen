package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity{
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        queue= Volley.newRequestQueue(this);
        Button btnRegre = (Button) findViewById(R.id.reg_regresar);
        btnRegre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        Button btn = (Button) findViewById(R.id.reg_registro);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrarse();
            }
        });
    }
       private void Registrarse()
    {
        String url = "http://192.168.0.15:8000/api/registro";
        TextView name,email,password;
        name= findViewById(R.id.reg_name);
        email=findViewById(R.id.reg_correo);
        password=findViewById(R.id.reg_pass);
        JSONObject persona=new JSONObject();
        try {
            persona.put("name", name.getText().toString());
            persona.put("email",email.getText().toString());
            persona.put("password",password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.POST, url, persona, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(RegistroActivity.this,"Has enviado los datos correctamente",Toast.LENGTH_SHORT).show();
                name.setText("");
                email.setText("");
                password.setText("");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistroActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

}
