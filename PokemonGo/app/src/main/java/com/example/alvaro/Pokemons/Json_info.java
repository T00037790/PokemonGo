package com.example.alvaro.Pokemons;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by alvaro on 25/09/17.
 */

public class Json_info {

    private static String name, base_experience, weight, front_img, back_img;
    private static ArrayList<String> abilities;

    /// Get pokemon's name
    public static String getName(JSONObject response){
        try {
            name = response.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }

    ///Get pokemon's base experience
    public static String getBase_experience(JSONObject response){
        try {
            base_experience = response.getString("base_experience");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return base_experience;
    }

    ///Get pokemon's base experience
    public static String getWeight(JSONObject response){
        try {
            weight = response.getString("weight");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weight;
    }

    ///Get pokemon's abilities
    public static ArrayList<String> getAbilities(JSONObject response){
        abilities = new ArrayList<String>();
        JSONArray abilities_this = null;
        try {
            abilities_this = response.getJSONArray("abilities");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < abilities_this.length(); i++) {
                JSONObject temp = abilities_this.getJSONObject(i);
                JSONObject ability = temp.getJSONObject("ability");
                abilities.add(ability.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return abilities;
    }

    ///Get pokemon's front image
    public static String getFront_img(JSONObject response) {

        try {
            front_img = response.getJSONObject("sprites").getString("front_default");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return front_img;
    }

    ///Get pokemon's back image
    public static String getBack_img(JSONObject response) {

        try {
            back_img = response.getJSONObject("sprites").getString("back_default");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return back_img;
    }



}
