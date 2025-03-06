package com.softek.codepro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConceptListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ConceptAdapter adapter;
    private List<String> conceptList;
    private String selectedLanguage;
    private JSONObject tutorials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concept_list);

        // Get selected language from intent
        selectedLanguage = getIntent().getStringExtra("language");
        if (selectedLanguage == null) {
            Log.e("ConceptListActivity", "No language selected!");
            finish();
            return;
        }

        Log.d("ConceptListActivity", "Selected Language: " + selectedLanguage);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        conceptList = new ArrayList<>();

        // Load JSON and populate concepts list
        String jsonString = loadJSONFromAsset(this, "tutorials.json");
        if (jsonString == null) {
            Log.e("ConceptListActivity", "Failed to load JSON file!");
            return;
        }

        try {
            tutorials = new JSONObject(jsonString);

            if (tutorials.has(selectedLanguage)) {
                JSONObject concepts = tutorials.getJSONObject(selectedLanguage);
                Iterator<String> keys = concepts.keys();
                while (keys.hasNext()) {
                    conceptList.add(keys.next());
                }
                Log.d("ConceptListActivity", "Concepts loaded: " + conceptList);
            } else {
                Log.e("ConceptListActivity", "Language not found in JSON: " + selectedLanguage);
                return;
            }

        } catch (JSONException e) {
            Log.e("ConceptListActivity", "Error parsing JSON: " + e.getMessage());
            return;
        }

        // Check if concepts are available before setting the adapter
        if (conceptList.isEmpty()) {
            Log.e("ConceptListActivity", "No concepts found for language: " + selectedLanguage);
            return;
        }

        // Set up adapter with click listener
        // Set up adapter with click listener
        adapter = new ConceptAdapter(conceptList, concept -> {
            Intent intent = new Intent(ConceptListActivity.this, TutorialActivity.class);
            intent.putExtra("language", selectedLanguage); // Ensure language is passed
            intent.putExtra("concept", concept);

            // Log to ensure correct data is being passed
            Log.d("ConceptListActivity", "Passing Language: " + selectedLanguage + ", Concept: " + concept);

            startActivity(intent);
        });


        recyclerView.setAdapter(adapter);
    }

    // Method to load JSON file from assets folder
    private String loadJSONFromAsset(Context context, String filename) {
        String json = "";
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8"); // No need for StandardCharsets.UTF_8
            Log.d("ConceptListActivity", "Loaded JSON: " + json);  // Debugging
        } catch (IOException e) {
            Log.e("ConceptListActivity", "Error loading JSON: " + e.getMessage());
        }
        return json.trim();  // Trim extra spaces
    }

}
