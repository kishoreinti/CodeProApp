package com.softek.codepro;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Load the default fragment (News)
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new NewsFragment())
                .commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_home) {
                selectedFragment = new NewsFragment();
            } else if (item.getItemId() == R.id.nav_code) {
                selectedFragment = new CodeFragment();

            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, selectedFragment)
                        .commit();
            }
            return true;
        });
    }
}
