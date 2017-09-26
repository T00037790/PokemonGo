package com.example.alvaro.Pokemons;

import com.example.alvaro.Interface.Pokemon;
import com.example.alvaro.Vistas.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alvaro on 25/09/17.
 */

public class Info_pok implements Pokemon {

    JSONObject response;

    @Override
    public void setResponse(JSONObject response) {
        this.response = response;
    }



    @Override
    public String name() {
        return Json_info.getName(response);
    }

    @Override
    public String base_experience() {
        return Json_info.getBase_experience(response);
    }

    @Override
    public String weight() {
        return Json_info.getWeight(response);
    }

    @Override
    public ArrayList<String> abilites(){
        return Json_info.getAbilities(response);
    }

    @Override
    public String front_image() {
        return Json_info.getFront_img(response);
    }

    @Override
    public String back_image(){
        return Json_info.getBack_img(response);
    }

    @Override
    public int life() {
        return 100;
    }

    @Override
    public int id() {
        Random rand = new Random();
        int id = rand.nextInt(721) + 1;
        return id;
    }

}
