package org.miage.placesearcher;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.miage.placesearcher.model.Place;
import org.miage.placesearcher.ui.PlaceAdapter;
import java.util.ArrayList;
import java.util.List;
import jp.wasabeef.recyclerview.adapters.*;
import jp.wasabeef.recyclerview.animators.*;

public class AnimatorItem extends AppCompatActivity {
    private static List<Place> places = new ArrayList<Place>();

    // Enumération qui comprends toute les méthodes possible pour l'animation d'ajout et de suppresion d'item
    // Elle va être ajouter à la liste déroulante.
    enum Type {
        FadeIn(new FadeInAnimator()),
        FadeInDown(new FadeInDownAnimator()),
        FadeInUp(new FadeInUpAnimator()),
        FadeInLeft(new FadeInLeftAnimator()),
        FadeInRight(new FadeInRightAnimator()),
        Landing(new LandingAnimator()),
        ScaleIn(new ScaleInAnimator()),
        ScaleInTop(new ScaleInTopAnimator()),
        ScaleInBottom(new ScaleInBottomAnimator()),
        ScaleInLeft(new ScaleInLeftAnimator()),
        ScaleInRight(new ScaleInRightAnimator()),
        FlipInTopX(new FlipInTopXAnimator()),
        FlipInBottomX(new FlipInBottomXAnimator()),
        FlipInLeftY(new FlipInLeftYAnimator()),
        FlipInRightY(new FlipInRightYAnimator()),
        SlideInLeft(new SlideInLeftAnimator()),
        SlideInRight(new SlideInRightAnimator()),
        SlideInDown(new SlideInDownAnimator()),
        SlideInUp(new SlideInUpAnimator()),
        OvershootInRight(new OvershootInRightAnimator(1.0f)),
        OvershootInLeft(new OvershootInLeftAnimator(1.0f));

        private BaseItemAnimator mAnimator;

        Type(BaseItemAnimator animator) {
            mAnimator = animator;
        }

        public BaseItemAnimator getAnimator() {
            return mAnimator;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        // Ajout d'éléments dans notre liste de Places.
        for(int i = 0; i < 50000; i ++) {
            places.add(new Place(0, 0, "Street" + i, "44000", "Nantes"));
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final PlaceAdapter adapter = new PlaceAdapter(this, places);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ajout de l'enum dans le spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (Type type : Type.values()) {
            spinnerAdapter.add(type.name());
        }
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recyclerView.setItemAnimator(Type.values()[position].getAnimator());
                recyclerView.getItemAnimator().setAddDuration(500);
                recyclerView.getItemAnimator().setRemoveDuration(500);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Position pour l'ajout et la suppression à 1
        // Si on la met à 0, on ne voit pas l'ajout ou la suppression car elle s'effectue au dessus de la première ligne donc hors de ce qui est charge dans le recyclerview.
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                adapter.add(1);
            }
        });
        findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                adapter.remove(1);
            }
        });

        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        // Do NOT forget to call super.onResume()
        super.onResume();
    }
}
