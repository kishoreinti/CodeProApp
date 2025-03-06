package com.softek.codepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CodeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_code, container, false);

        // Set click listeners for each programming language section
        view.findViewById(R.id.python_section).setOnClickListener(v -> openTutorial("Python"));
        view.findViewById(R.id.sql_section).setOnClickListener(v -> openTutorial("SQL"));
        view.findViewById(R.id.java_section).setOnClickListener(v -> openTutorial("Java"));

        return view;
    }

    private void openTutorial(String language) {
        // Ensuring consistency in the key used for the Intent
        Intent intent = new Intent(getActivity(), ConceptListActivity.class);
        intent.putExtra("language", language);  // Consistent key used here
        startActivity(intent);
    }
}
