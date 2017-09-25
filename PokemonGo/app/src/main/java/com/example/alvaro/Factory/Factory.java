package com.example.alvaro.Factory;

import com.example.alvaro.Interface.Pokemon;
import com.example.alvaro.Pokemons.Info_pok;

/**
 * Created by alvaro on 25/09/17.
 */

public class Factory {

    public static Pokemon getPokemon_object(String object)
    {
        if (object=="pokemon"){
            return new Info_pok();
        }
        return null;
    }
}
