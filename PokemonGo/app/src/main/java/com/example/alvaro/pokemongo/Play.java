package com.example.alvaro.pokemongo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;


import static com.example.alvaro.pokemongo.R.id.imageView;

/**
 * Created by alvaro on 18/09/17.
 */

public class Play extends AppCompatActivity{
    private ImageLoader imageLoader;
    NetworkImageView img1, img2;
    TextView tx1,tx2,vd1,vd2;
    Button attack;
    Boolean rd1=false,rd2=false;
    int vida1=100,vida2=100;
    Random rand = new Random();
    public final String url = "http://pokeapi.co/api/v2/pokemon/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int pokemon1, pokemon2;
        super.onCreate(savedInstanceState);
        attack=(Button) findViewById(R.id.ran);
        img1 = (NetworkImageView) findViewById(imageView);
        img2 = (NetworkImageView) findViewById(R.id.imageView2);
        /** tx1 = (TextView) findViewById(R.id.textView1);
        * tx2 = (TextView) findViewById(R.id.textView2);
        * vd1 = (TextView) findViewById(R.id.textView3);
        * vd2 = (TextView) findViewById(R.id.textView4);
         */



    JsonObjectRequest getRequest1 = new JsonObjectRequest(Request.Method.GET, url + String.valueOf(pokemon1)+"/",null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        loadImage(img1,response.getJSONObject("sprites").getString("back_default"));
                        tx1.setText(response.getString("name"));
                        vd1.setText("Vida: "+String.valueOf(vida1));
                        rd1=true;
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

    private void loadImage(NetworkImageView imageView, String urlImg){
        imageLoader = Volley.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(urlImg, ImageLoader.getImageListener(imageView,
                R.drawable.white, android.R.drawable
                        .ic_dialog_alert));
        imageView.setImageUrl(urlImg, imageLoader);
    }
}

}
