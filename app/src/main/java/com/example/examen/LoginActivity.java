package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    RequestQueue queue;
    TextView name,email,password;
    String correo,name2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        queue= Volley.newRequestQueue(this);
        email=findViewById(R.id.log_email);
        password=findViewById(R.id.log_correo);

        Button btnRegre = (Button) findViewById(R.id.log_regresar);
        btnRegre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        Button login = (Button) findViewById(R.id.log_iniciar);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
                email.setText("");
                password.setText("");
            }
        });

    }
    private void Login()
    {
        String url = "http://192.168.0.15:8000/api/login";
        JSONObject persona=new JSONObject();
        try {
            persona.put("email",email.getText().toString());
            persona.put("password",password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.POST, url, persona, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(LoginActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                try {
                    correo=response.getString("email");
                    name2=response.getString("name");
                    Intent intent=new Intent(LoginActivity.this,UserActivity.class);
                    intent.putExtra("name",name2);
                    intent.putExtra("email",correo);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}