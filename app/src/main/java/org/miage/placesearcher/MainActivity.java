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
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import jp.wasabeef.recyclerview.adapters.*;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.spinner)
    Spinner mSpinner;

   /* enum Type {
        AlphaIn {
            @Override public AnimationAdapter get(Context context) {
                MainAdapter adapter = new MainAdapter(context, new ArrayList<>(Arrays.asList(data)));
                return new AlphaInAnimationAdapter(adapter);
            }
        },
        ScaleIn {
            @Override public AnimationAdapter get(Context context) {
                MainAdapter adapter = new MainAdapter(context, new ArrayList<>(Arrays.asList(data)));
                return new ScaleInAnimationAdapter(adapter);
            }
        },
        SlideInBottom {
            @Override public AnimationAdapter get(Context context) {
                MainAdapter adapter = new MainAdapter(context, new ArrayList<>(Arrays.asList(data)));
                return new SlideInBottomAnimationAdapter(adapter);
            }
        },
        SlideInLeft {
            @Override public AnimationAdapter get(Context context) {
                MainAdapter adapter = new MainAdapter(context, new ArrayList<>(Arrays.asList(data)));
                return new SlideInLeftAnimationAdapter(adapter);
            }
        },
        SlideInRight {
            @Override public AnimationAdapter get(Context context) {
                MainAdapter adapter = new MainAdapter(context, new ArrayList<>(Arrays.asList(data)));
                return new SlideInRightAnimationAdapter(adapter);
            }
        };

        public abstract AnimationAdapter get(Context context);
    }

    private static String[] data = new String[] {
            "Apple", "Ball", "Camera", "Day", "Egg", "Foo", "Google", "Hello", "Iron", "Japan", "Coke",
            "Dog", "Cat", "Yahoo", "Sony", "Canon", "Fujitsu", "USA", "Nexus", "LINE", "Haskell", "C++",
            "Java", "Go", "Swift", "Objective-c", "Ruby", "PHP", "Bash", "ksh", "C", "Groovy", "Kotlin",
            "Chip", "Japan", "U.S.A", "San Francisco", "Paris", "Tokyo", "Silicon Valley", "London",
            "Spain", "China", "Taiwan", "Asia", "New York", "France", "Kyoto", "Android", "Google",
            "iPhone", "iPad", "iPod", "Wasabeef"
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (getIntent().getBooleanExtra("GRID", true)) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }


        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (Type type : Type.values()) {
            spinnerAdapter.add(type.name());
        }
        mSpinner.setAdapter(spinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AnimationAdapter adapter = Type.values()[position].get(MainActivity.this);
                adapter.setFirstOnly(true);
                adapter.setDuration(500);
                adapter.setInterpolator(new OvershootInterpolator(.5f));
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/

      /*  mRecyclerView.setItemAnimator(new FadeInAnimator());
        MainAdapter adapter = new MainAdapter(this, new ArrayList<>(Arrays.asList(data)));
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(adapter);
        alphaAdapter.setFirstOnly(true);
        alphaAdapter.setDuration(500);
        alphaAdapter.setInterpolator(new OvershootInterpolator(.5f));
        mRecyclerView.setAdapter(alphaAdapter);
*/

        //Binding ButterKnife annotations now that content view has been set
        ButterKnife.bind(this);


        // Define list of persons
        List<Place> places = new ArrayList<Place>();
        for (int i = 0; i < 50000; i ++) {
            places.add(new Place(0, 0, "Street" + i, "44000", "Nantes"));
        }
        // Instanciate a PersonAdapter
        PlaceAdapter adapter = new PlaceAdapter(this, places);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        // Do NOT forget to call super.onResume()
        super.onResume();
    }
}
