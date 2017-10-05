package com.example.alvaro.Pokemons;

import android.os.Handler;

import com.example.alvaro.Factory.Factory;
import com.example.alvaro.Interface.Pokemon;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alvaro on 4/10/2017.
 */
public class Info_pokTest {

    @Test
    public void name() throws Exception {

        final String name_expected= "bulbasaur";


        Factory factory = new Factory();
        Pokemon pokemon_test = factory.getPokemon_object("pokemon");

        //Reading JsonObject from a file, please CHANGE the file path when testing
        String filename = ("C:\\Users\\Alvaro\\Documents\\GitHub\\PokemonGo\\PokemonGo\\app\\src\\test\\java\\com\\example\\alvaro\\Pokemons/file.json");

        // parsing Json File
        JSONObject jsonObject = parseJSONFile(filename);

        pokemon_test.setResponse(jsonObject);

        final String name_actual = pokemon_test.name();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                assertEquals(name_expected, name_actual);
            }
        }, 4000);



    }

    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }

}