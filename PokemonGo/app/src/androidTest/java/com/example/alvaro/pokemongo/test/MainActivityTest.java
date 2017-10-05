package com.example.alvaro.pokemongo.test;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.alvaro.Vistas.MainActivity;
import com.example.alvaro.pokemongo.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Predicates.not;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import static org.junit.Assert.*;

/**
 * Created by Alvaro on 5/10/2017.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    //Probar que el boton "Fight"  está habilitado luego de clickear el boton seleccion aleatoria
    @Test
    public void test_button_Fight_Is_Enable(){
        onView(withId(R.id.random)).perform(click());
        onView(withId(R.id.vs)).check(matches((isEnabled())));
    }

    //Probar que el boton "Fight" no está mostrado en pantalla al iniciar la aplicacion
    @Test
    public void test_buttom_Fight_is_not_displayed(){
        onView(withId(R.id.vs)).check(matches(not(isDisplayed())));
    }


}