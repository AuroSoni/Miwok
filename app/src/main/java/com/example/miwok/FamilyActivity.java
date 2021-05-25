package com.example.miwok;

import android.os.Bundle;
import android.widget.ListView;

import com.example.miwok.adapters.WordAdapter;
import com.example.miwok.models.TemplateActivity;
import com.example.miwok.models.Word;

import java.util.ArrayList;

public class FamilyActivity extends TemplateActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        mWords = new ArrayList<>();
        mWords.add(new Word("father","әpә", R.raw.family_father, R.drawable.family_father));
        mWords.add(new Word("mother","әṭa", R.raw.family_mother, R.drawable.family_mother));
        mWords.add(new Word("son","angsi", R.raw.family_son, R.drawable.family_son));
        mWords.add(new Word("daughter","tune", R.raw.family_daughter, R.drawable.family_daughter));
        mWords.add(new Word("older brother","taachi", R.raw.family_older_brother, R.drawable.family_older_brother));
        mWords.add(new Word("younger brother","chalitti", R.raw.family_younger_brother, R.drawable.family_younger_brother));
        mWords.add(new Word("older sister","teṭe", R.raw.family_older_sister, R.drawable.family_older_sister));
        mWords.add(new Word("younger sister","kolliti", R.raw.family_younger_sister, R.drawable.family_younger_sister));
        mWords.add(new Word("grandmother","ama", R.raw.family_grandmother, R.drawable.family_grandmother));
        mWords.add(new Word("grandfather","paapa", R.raw.family_grandfather, R.drawable.family_grandfather));

        WordAdapter wordAdapter = new WordAdapter(this, mWords,R.color.category_family);

        ListView listView = findViewById(R.id.wordView);

        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(getWordClickListener());
    }
}