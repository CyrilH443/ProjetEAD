package org.miage.placesearcher;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.miage.placesearcher.model.Place;
import org.miage.placesearcher.ui.PlaceAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainExemple extends AppCompatActivity {
    private static List<Place> places = new ArrayList<Place>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemple);

        // Ajout d'éléments dans notre liste de Places.
        for(int i = 0; i < 50; i ++) {
            places.add(new Place(0, 0, "Street" + i, "44000", "Nantes"));
        }
        // Instanciate a PersonAdapter
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final PlaceAdapter adapter = new PlaceAdapter(this, places);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        // Do NOT forget to call super.onResume()
        super.onResume();
    }
}
