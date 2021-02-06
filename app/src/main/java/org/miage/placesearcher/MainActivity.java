package org.miage.placesearcher;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.miage.placesearcher.model.Place;
import org.miage.placesearcher.ui.PlaceAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.*;
import jp.wasabeef.recyclerview.animators.*;

public class MainActivity<i> extends AppCompatActivity {
    private static List<Place> places = new ArrayList<Place>();

    // Enumération qui comprends toute les méthodes possible pour le scroll d'une recylcer View.
    // Elle va être ajouter à la première liste déroulante.
    enum Type {
        AlphaIn {
            @Override public AnimationAdapter get(Context context) {
                PlaceAdapter adapter = new PlaceAdapter(context, places);
                return new AlphaInAnimationAdapter(adapter);
            }
        },
        ScaleIn {
            @Override public AnimationAdapter get(Context context) {
                PlaceAdapter adapter = new PlaceAdapter(context, places);
                return new ScaleInAnimationAdapter(adapter);
            }
        },
        SlideInBottom {
            @Override public AnimationAdapter get(Context context) {
                PlaceAdapter adapter = new PlaceAdapter(context, places);
                return new SlideInBottomAnimationAdapter(adapter);
            }
        },
        SlideInLeft {
            @Override public AnimationAdapter get(Context context) {
                PlaceAdapter adapter = new PlaceAdapter(context, places);
                return new SlideInLeftAnimationAdapter(adapter);
            }
        },
        SlideInRight {
            @Override public AnimationAdapter get(Context context) {
                PlaceAdapter adapter = new PlaceAdapter(context, places);
                return new SlideInRightAnimationAdapter(adapter);
            }
        };

        public abstract AnimationAdapter get(Context context);
    }


    enum Type2 {
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

        Type2(BaseItemAnimator animator) {
            mAnimator = animator;
        }

        public BaseItemAnimator getAnimator() {
            return mAnimator;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0; i < 50000; i ++) {
            places.add(new Place(0, 0, "Street" + i, "44000", "Nantes"));
        }
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        if (getIntent().getBooleanExtra("GRID", true)) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        recyclerView.setItemAnimator(new SlideInLeftAnimator());

        final PlaceAdapter adapter = new PlaceAdapter(this, places);
        recyclerView.setAdapter(adapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> spinnerAdapter2 =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (Type2 type : Type2.values()) {
            spinnerAdapter2.add(type.name());
        }
        spinner2.setAdapter(spinnerAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recyclerView.setItemAnimator(Type2.values()[position].getAnimator());
                recyclerView.getItemAnimator().setAddDuration(500);
                recyclerView.getItemAnimator().setRemoveDuration(500);
            }

            @Override public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        // Premier Spinner permettant l'animation du Scroll
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
                AnimationAdapter adapter = Type.values()[position].get(MainActivity.this);
                adapter.setFirstOnly(true);
                adapter.setDuration(500);
                adapter.setInterpolator(new OvershootInterpolator(.5f));
                recyclerView.setAdapter(adapter);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {
            }
        });



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



        recyclerView.setItemAnimator(new FadeInAnimator());
        SlideInLeftAnimationAdapter alphaAdapter = new SlideInLeftAnimationAdapter(adapter);
        alphaAdapter.setFirstOnly(true);
        alphaAdapter.setDuration(500);
        alphaAdapter.setInterpolator(new OvershootInterpolator(.5f));
        recyclerView.setAdapter(alphaAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        // Do NOT forget to call super.onResume()
        super.onResume();
    }
}
