package com.softek.codepro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TutorialActivity extends AppCompatActivity {
    private TextView tutorialText;
    private TextView tutorialhead;
    private String language, concept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        tutorialhead=findViewById(R.id.tutorialhead);
        tutorialText = findViewById(R.id.tutorialText);
        language = getIntent().getStringExtra("language");
        concept = getIntent().getStringExtra("concept");
        tutorialhead.setText(concept);

        if (language == null || concept == null) {
            Log.e("TutorialActivity", "Error: Language or Concept is NULL! [Language: " + language + ", Concept: " + concept + "]");
            tutorialText.setText("Error loading tutorial.");
            return;
        }

        Log.d("TutorialActivity", "Received Language: " + language + ", Concept: " + concept);
        loadTutorialContent();
    }

    private void loadTutorialContent() {
        try {
            InputStream is = getAssets().open("tutorials.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, Charset.forName("UTF-8"));

            Log.d("TutorialActivity", "JSON Loaded: " + json);

            JSONObject jsonObject = new JSONObject(json);

            if (!jsonObject.has(language)) {
                Log.e("TutorialActivity", "Language not found: " + language);
                tutorialText.setText("Error: Language not found.");
                return;
            }

            JSONObject languageObject = jsonObject.getJSONObject(language);

            if (!languageObject.has(concept)) {
                Log.e("TutorialActivity", "Concept not found: " + concept);
                tutorialText.setText("Error: Concept not found.");
                return;
            }

            String content = languageObject.getString(concept);
            tutorialText.setText(content);

        } catch (IOException | JSONException e) {
            Log.e("TutorialActivity", "Error loading tutorial", e);
            tutorialText.setText("Error loading tutorial.");
        }
    }
}
