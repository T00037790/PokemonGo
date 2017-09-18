package com.example.alvaro.pokemongo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jugar = new Intent();
                jugar.setClass(getApplicationContext(), Play.class);
                jugar.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                jugar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                jugar.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(jugar);
            }
        };
        findViewById(R.id.imageView).setOnClickListener(listener);
    }

    }