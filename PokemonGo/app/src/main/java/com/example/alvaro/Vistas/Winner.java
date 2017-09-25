package com.example.alvaro.Vistas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.alvaro.Singleton.HttpRequest;
import com.example.alvaro.pokemongo.R;

public class Winner extends AppCompatActivity {
    private ImageLoader imageLoader;
    TextView name_winner;
    NetworkImageView pic_winner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        name_winner = (TextView) findViewById(R.id.name_won);
        pic_winner = (NetworkImageView) findViewById(R.id.won);
        Intent intent_won = getIntent();
        String winner_name = intent_won.getStringExtra("winner");
        String  winner_pic = intent_won.getStringExtra("url");
        loadImage(pic_winner, winner_pic);
        name_winner.setText(winner_name);

    }
    private void loadImage(NetworkImageView imageView, String urlImg) {
        imageLoader = HttpRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(urlImg, ImageLoader.getImageListener(imageView, R.drawable.white, android.R.drawable.ic_dialog_alert));
        imageView.setImageUrl(urlImg, imageLoader);
    }
}
