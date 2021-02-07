package org.miage.placesearcher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.miage.placesearcher.ui.PlaceAdapter;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInRightAnimationAdapter;
import jp.wasabeef.recyclerview.animators.BaseItemAnimator;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;
import jp.wasabeef.recyclerview.animators.FadeInRightAnimator;
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator;
import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator;
import jp.wasabeef.recyclerview.animators.FlipInLeftYAnimator;
import jp.wasabeef.recyclerview.animators.FlipInRightYAnimator;
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.OvershootInLeftAnimator;
import jp.wasabeef.recyclerview.animators.OvershootInRightAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInLeftAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInRightAnimator;
import jp.wasabeef.recyclerview.animators.ScaleInTopAnimator;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainExempleChoix extends AppCompatActivity {

    // Enumération qui comprends toute les méthodes possible pour le scroll d'une recylcer View.
    // Elle va être ajouter à la première liste déroulante.
    enum Type {
        AlphaInAnimationAdapter,
        ScaleInAnimationAdapter,
        SlideInBottomAnimationAdapter,
        SlideInLeftAnimationAdapter,
        SlideInRightAnimationAdapter
    }

    enum Type2 {
        FadeInAnimator,
        FadeInDownAnimator,
        FadeInUpAnimator,
        FadeInLeftAnimator,
        FadeInRightAnimator,
        LandingAnimator,
        ScaleInAnimator,
        ScaleInTopAnimator,
        ScaleInBottomAnimator,
        ScaleInLeftAnimator,
        ScaleInRightAnimator,
        FlipInTopXAnimator,
        FlipInBottomXAnimator,
        FlipInLeftYAnimator,
        FlipInRightYAnimator,
        SlideInLeftAnimator,
        SlideInRightAnimator,
        SlideInDownAnimator,
        SlideInUpAnimator,
        OvershootInRightAnimator,
        OvershootInLeftAnimator
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplechoix);


        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> spinnerAdapter2 =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (MainActivity.Type2 type : MainActivity.Type2.values()) {
            spinnerAdapter2.add(type.name());
        }
        spinner2.setAdapter(spinnerAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Premier Spinner permettant l'animation du Scroll
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (MainActivity.Type type : MainActivity.Type.values()) {
            spinnerAdapter.add(type.name());
        }
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        findViewById(R.id.btn_animator_scroll).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent i = new Intent(MainExempleChoix.this, MainExemple.class);
                startActivity(i);
            }
        });
    }

}
