package org.miage.placesearcher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.btn_animator_scroll).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent i = new Intent(Menu.this, AnimatorScroll.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_animator_item).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent i = new Intent(Menu.this, AnimatorItem.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btn_exemple).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent i = new Intent(Menu.this, MainExemple.class);
                startActivity(i);
            }
        });
    }
}
