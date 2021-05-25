package com.example.miwok;

import android.os.Bundle;
import android.widget.ListView;

import com.example.miwok.adapters.WordAdapter;
import com.example.miwok.models.TemplateActivity;
import com.example.miwok.models.Word;

import java.util.ArrayList;

public class ColorsActivity extends TemplateActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);


        mWords = new ArrayList<>();
        mWords.add(new Word("red","weṭeṭṭi", R.raw.color_red, R.drawable.color_red));
        mWords.add(new Word("green","chokokki", R.raw.color_green, R.drawable.color_green));
        mWords.add(new Word("brown","ṭakaakki", R.raw.color_brown, R.drawable.color_brown));
        mWords.add(new Word("gray","ṭopoppi", R.raw.color_gray, R.drawable.color_gray));
        mWords.add(new Word("black","kululli", R.raw.color_black, R.drawable.color_black));
        mWords.add(new Word("white","kelelli", R.raw.color_white, R.drawable.color_white));
        mWords.add(new Word("dusty yellow","ṭopiisә", R.raw.color_dusty_yellow, R.drawable.color_dusty_yellow));
        mWords.add(new Word("mustard yellow","chiwiiṭә", R.raw.color_mustard_yellow, R.drawable.color_mustard_yellow));

        WordAdapter wordAdapter = new WordAdapter(this, mWords,R.color.category_colors);

        ListView listView = findViewById(R.id.wordView);

        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(getWordClickListener());
    }
}