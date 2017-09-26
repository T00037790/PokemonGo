package com.example.alvaro.Interface;

import com.example.alvaro.Pokemons.Info_pok;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alvaro on 25/09/17.
 */

public interface Pokemon {

    void setResponse(JSONObject response);
    String name();
    String weight();
    String base_experience();
    ArrayList<String> abilites();
    String front_image();
    String back_image();
    int life();
    int id();

}
