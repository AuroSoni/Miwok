package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.miwok.models.Word;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        TextView numbersTextView = findViewById(R.id.numbers);
        TextView colorsTextView = findViewById(R.id.colors);
        TextView familyTextView = findViewById(R.id.family);
        TextView phrasesTextView = findViewById(R.id.phrases);

        numbersTextView.setOnClickListener(v -> startActivity(new Intent(this, NumbersActivity.class)));
        colorsTextView.setOnClickListener(v -> startActivity(new Intent(this, ColorsActivity.class)));
        phrasesTextView.setOnClickListener(v -> startActivity(new Intent(this, PhrasesActivity.class)));
        familyTextView.setOnClickListener(v -> startActivity(new Intent(this, FamilyActivity.class)));

    }
}