package com.example.alvaro.pokemongo;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity  {

    private ImageLoader imageLoader;
    NetworkImageView imgpok1, imgpok2;
    TextView namepok1,namepok2,vd1,vd2;
    Button randompoks, versus;
    ArrayList<String> inf1;
    ArrayList<String> inf2;
    ListView listpok1;
    ListView listpok2;
    Boolean rd1=false,rd2=false;
    int vida1=100,vida2=100, pokemon1, pokemon2;
    String id;
    public String[] values = new String[2];
    Random rand = new Random();

    public final String url = "http://pokeapi.co/api/v2/pokemon/";
    String urlimg1="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/", urlimg2="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RequestQueue queue = Volley.newRequestQueue(this);
        imgpok1 = (NetworkImageView) findViewById(R.id.img1);
        imgpok2 = (NetworkImageView) findViewById(R.id.img2);
        namepok1 = (TextView) findViewById(R.id.pk1);
        namepok2 = (TextView) findViewById(R.id.pk2);
        listpok1= (ListView) findViewById(R.id.listView);
        listpok2= (ListView) findViewById(R.id.listView2);
        inf1=new ArrayList<String>();
        inf2=new ArrayList<String>();
        final ArrayAdapter<String> Adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, 0 ,inf1);
        final ArrayAdapter<String> Adapter2= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,0,  inf2);

        pokemon1 = rand.nextInt(721) + 1;
        pokemon2 = rand.nextInt(721) + 1;

        versus = (Button) findViewById(R.id.vs);
        versus.setVisibility(0);
        randompoks =(Button) findViewById(R.id.random);
        randompoks.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                while (pokemon1 == pokemon2)
                    pokemon2 = rand.nextInt(721) + 1;
                JsonObjectRequest getRequest1 = new JsonObjectRequest(Request.Method.GET, url + String.valueOf(pokemon1)+"/",null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    loadImage(imgpok1,response.getJSONObject("sprites").getString("back_default"));
                                    namepok1.setText(response.getString("name"));
                                    inf1.add("nombre: "+response.getString("name"));
                                    inf1.add("peso: "+response.getString("weight"));
                                    inf1.add("experiencia: "+response.getString("base_experience"));
                                    values[0] = response.getJSONObject("sprites").getString("back_default");
                                    JSONArray abilities = null;
                                    abilities = response.getJSONArray("abilities");
                                    try {
                                        for (int i = 0; i < abilities.length(); i++) {
                                            JSONObject c = abilities.getJSONObject(i);
                                            // Ability node is JSON Object
                                            JSONObject ability = c.getJSONObject("ability");
                                            inf1.add("habilidad:"+ability.getString("name"));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    listpok1.setAdapter(Adapter);
                                    ///vd1.setText("Vida: "+String.valueOf(vida1));
                                    ///rd1=true;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });
                JsonObjectRequest getRequest2 = new JsonObjectRequest(Request.Method.GET, url + String.valueOf(pokemon2)+"/", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    loadImage(imgpok2,response.getJSONObject("sprites").getString("front_default"));
                                    namepok2.setText(response.getString("name"));
                                    inf2.add("nombre: "+response.getString("name"));
                                    inf2.add("peso: "+response.getString("weight"));
                                    inf2.add("experiencia: "+response.getString("base_experience"));
                                    values[1]= response.getJSONObject("sprites").getString("back_default");
                                    JSONArray abilities = null;
                                    abilities = response.getJSONArray("abilities");
                                    try {
                                        for (int i = 0; i < abilities.length(); i++) {
                                            JSONObject c = abilities.getJSONObject(i);
                                            // Ability node is JSON Object
                                            JSONObject ability = c.getJSONObject("ability");
                                            inf2.add("habilidad:"+ability.getString("name"));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    listpok2.setAdapter(Adapter2);
                                    /// vd2.setText("Vida: "+String.valueOf(vida2));
                                    /// rd2=true;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });
                queue.add(getRequest1);
                queue.add(getRequest2);
            }

        });

        versus =(Button) findViewById(R.id.vs);
        versus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent fight = new Intent(MainActivity.this, Fight.class);
                startActivity(fight);

            }
        });

    }


    private void loadImage(NetworkImageView imageView, String urlImg) {
        imageLoader = HttpRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(urlImg, ImageLoader.getImageListener(imageView, R.drawable.white, android.R.drawable.ic_dialog_alert));
        imageView.setImageUrl(urlImg, imageLoader);
    }


}