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

public class AnimatorScroll extends AppCompatActivity {
    private static List<Place> places = new ArrayList<Place>();

    // Enumération qui comprends toute les méthodes possible pour l'animation du scroll d'une recylcer View.
    // Elle va être ajouter liste déroulante.
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        // Ajout d'éléments dans notre liste de Places.
        for(int i = 0; i < 50000; i ++) {
            places.add(new Place(0, 0, "Street" + i, "44000", "Nantes"));
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final PlaceAdapter adapter = new PlaceAdapter(this, places);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // On charge les différentes méthodes de scroll dans le spinner.
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
                AnimationAdapter adapter = Type.values()[position].get(AnimatorScroll.this);
                adapter.setFirstOnly(true);
                adapter.setDuration(500);
                adapter.setInterpolator(new OvershootInterpolator(.5f));
                recyclerView.setAdapter(adapter);
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {
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
