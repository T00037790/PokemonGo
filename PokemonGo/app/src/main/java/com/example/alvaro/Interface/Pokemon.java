package com.example.alvaro.Interface;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by alvaro on 25/09/17.
 */

public interface Pokemon {

    ///String getInfo(JSONObject info);
    String name();
    String base_experience();
    ArrayList<String> abilites();
    String front_image();
    String back_image();


}
