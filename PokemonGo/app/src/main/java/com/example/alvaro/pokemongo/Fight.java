package com.example.alvaro.pokemongo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import java.util.Random;

public class Fight extends AppCompatActivity {

    private ImageLoader imageLoader;
    NetworkImageView img1, img2;
    String urlimg1="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/", urlimg2="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    public final String url = "http://pokeapi.co/api/v2/pokemon/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        RequestQueue queue = Volley.newRequestQueue(this);
        int pokemon1, pokemon2;
        Random rand = new Random();
        img1 = (NetworkImageView) findViewById(R.id.img11);
        img2 = (NetworkImageView) findViewById(R.id.img22);
        loadImage(img1, );
        loadImage(img2, );

    }

    private void loadImage(NetworkImageView imageView, String urlImg) {
        imageLoader = HttpRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(urlImg, ImageLoader.getImageListener(imageView, R.drawable.white, android.R.drawable.ic_dialog_alert));
        imageView.setImageUrl(urlImg, imageLoader);
    }

}
