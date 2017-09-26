package com.example.alvaro.Vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.alvaro.Factory.Factory;
import com.example.alvaro.Interface.Pokemon;
import com.example.alvaro.Pokemons.Info_pok;
import com.example.alvaro.Singleton.HttpRequest;
import com.example.alvaro.pokemongo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity  {

    Factory factory = new Factory();
    Pokemon first_pokemon = factory.getPokemon_object("pokemon");
    Pokemon second_pokemon = factory.getPokemon_object("pokemon");


    private ImageLoader imageLoader;
    NetworkImageView imgpok1, imgpok2;
    TextView namepok1,namepok2;
    Button randompoks, versus;
    ListView listpok1;
    ListView listpok2;
    ArrayList<String> inf1;
    ArrayList<String> inf2;

    public String[] values = new String[3];
    public final String url = "https://pokeapi.co/api/v2/pokemon/";

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
        final ArrayAdapter<String> Adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, 0 , inf1);
        final ArrayAdapter<String> Adapter2= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,0,  inf2);

        versus = (Button) findViewById(R.id.vs);
        versus.setVisibility(View.INVISIBLE);
        randompoks =(Button) findViewById(R.id.random);
        randompoks.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                System.out.println("id"+first_pokemon.id());
                while (first_pokemon.id() == second_pokemon.id())
                    second_pokemon.id();
                JsonObjectRequest getRequest1 = new JsonObjectRequest(Request.Method.GET, url + String.valueOf(first_pokemon.id())+"/",null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                first_pokemon.setResponse(response);
                                System.out.println("imagen"+first_pokemon.front_image());
                                loadImage(imgpok1,first_pokemon.front_image());
                                namepok1.setText(first_pokemon.name());

                                inf1.add("nombre: "+first_pokemon.name());

                                inf1.add("peso: " + first_pokemon.weight());
                                inf1.add("experiencia: " + first_pokemon.base_experience());

                                inf1.add("habilidades:"+ first_pokemon.abilites());


                                listpok1.setAdapter(Adapter);

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });
                JsonObjectRequest getRequest2 = new JsonObjectRequest(Request.Method.GET, url + String.valueOf(second_pokemon.id())+"/", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                second_pokemon.setResponse(response);
                                loadImage(imgpok2, second_pokemon.front_image());
                                namepok2.setText(second_pokemon.name());

                                inf2.add("nombre: "+ second_pokemon.name());
                                inf2.add("peso: "+ second_pokemon.weight());
                                inf2.add("experiencia: " + second_pokemon.base_experience());
                                inf2.add("habilidades:"+ second_pokemon.abilites());

                                listpok2.setAdapter(Adapter2);


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        });
                queue.add(getRequest1);
                queue.add(getRequest2);
                versus.setVisibility(View.VISIBLE);
                randompoks.setVisibility(View.GONE);

            }

        });

        versus =(Button) findViewById(R.id.vs);
        versus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent fight = new Intent(MainActivity.this, Fight.class);
                fight.putExtra("url", first_pokemon.back_image());
                fight.putExtra("url2", second_pokemon.front_image());
                fight.putExtra("pic", first_pokemon.front_image());
                fight.putExtra("pokemon1", first_pokemon.name());
                fight.putExtra("pokemon2", second_pokemon.name());
                fight.putExtra("powerpok1", first_pokemon.base_experience());
                fight.putExtra("powerpok2", second_pokemon.base_experience());
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