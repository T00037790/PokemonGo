package com.example.alvaro.Vistas;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.alvaro.Singleton.HttpRequest;
import com.example.alvaro.pokemongo.R;

import java.util.ArrayList;
import java.util.Random;


public class Fight extends AppCompatActivity {


    private ImageLoader imageLoader;
    NetworkImageView img1, img2;
    ProgressBar progressbar;
    ProgressBar progressbar2;
    int life, life2, attacker, power1,power2;
    TextView name1, name2;
    ListView fighting;
    ArrayList<String> hits;
    int decrease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        RequestQueue queue = Volley.newRequestQueue(this);
        img1 = (NetworkImageView) findViewById(R.id.img11);
        img2 = (NetworkImageView) findViewById(R.id.img22);
        name1 = (TextView) findViewById(R.id.poke1);
        name2 = (TextView) findViewById(R.id.poke2);
        fighting = (ListView) findViewById(R.id.results);
        hits = new ArrayList<String>();
        final ArrayAdapter<String> Adapter_fight= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , hits);

        Intent intent = getIntent();

        final String url = intent.getStringExtra("url");
        final String pic_f_winner = intent.getStringExtra("pic");
        final String  url2 = intent.getStringExtra("url2");
        final String name_pokemon1 = intent.getStringExtra("pokemon1");
        final String name_pokemon2 = intent.getStringExtra("pokemon2");

        /// getting pokemon's power
        final int power1 = Integer.parseInt(intent.getStringExtra("powerpok1"));
        final int power2 = Integer.parseInt(intent.getStringExtra("powerpok2"));

        /// loading images
        loadImage(img1, url);
        loadImage(img2, url2);

        /// setting pokemon's names
        name1.setText(name_pokemon1);
        name2.setText(name_pokemon2);

        //setting pokemon's life
        life = 100;
        life2 = 100;

        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        progressbar2 = (ProgressBar) findViewById(R.id.progressBar2);
        Random who = new Random();
        while(life>=0 && life2>=0 ) {

            attacker = who.nextInt(2);
            decrease = fighting(life, life2, attacker, power1, power2);
            if (decrease > 0) {
                if (attacker == 0) {
                    hits.add(name_pokemon1 + " is attacking");
                    progressbar2.setProgress(life2 - decrease);
                    life2 = life2 - decrease;

                } else {
                    progressbar.setProgress(life - decrease);
                    life = life - decrease;
                    hits.add(name_pokemon2 + " is attacking");
                }
            }
            fighting.setAdapter(Adapter_fight);
            decrease = 0;
        }






        // validating winner pokemom becuase of the new activity to be created
        if (life<=0 || life2<=0){

            if (life>0){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent winner = new Intent(Fight.this, Winner.class);
                        winner.putExtra("winner", name_pokemon1);
                        winner.putExtra("url", pic_f_winner);
                        startActivity(winner);
                    }
                }, 3000);


            }

            if(life2>0){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent winner = new Intent(Fight.this, Winner.class);
                        winner.putExtra("winner", name_pokemon2);
                        winner.putExtra("url", url2);
                        startActivity(winner);
                    }
                }, 3000);

            }


        }



    }

    public int fighting (int vida1, int vida2, int who, int power1, int power2){
        if (who==0){
            int remove = (power1/10);
            vida2 = vida2 - remove;
            return remove;

        }
        else{
            int remove2 = (power2/10);
            vida1 = vida1 - remove2;
            return remove2;
        }

    }

    private void loadImage(NetworkImageView imageView, String urlImg) {
        imageLoader = HttpRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(urlImg, ImageLoader.getImageListener(imageView, R.drawable.white, android.R.drawable.ic_dialog_alert));
        imageView.setImageUrl(urlImg, imageLoader);
    }

}
