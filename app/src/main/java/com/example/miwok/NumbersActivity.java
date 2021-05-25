package com.example.miwok;

import android.os.Bundle;
import android.widget.ListView;

import com.example.miwok.adapters.WordAdapter;
import com.example.miwok.models.TemplateActivity;
import com.example.miwok.models.Word;

import java.util.ArrayList;

public class NumbersActivity extends TemplateActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        mWords = new ArrayList<>();
        mWords.add(new Word("one","lutti", R.raw.number_one,R.drawable.number_one));
        mWords.add(new Word("two","otiiko", R.raw.number_two, R.drawable.number_two));
        mWords.add(new Word("three","tolookosu", R.raw.number_three, R.drawable.number_three));
        mWords.add(new Word("four","oyyisa", R.raw.number_four, R.drawable.number_four));
        mWords.add(new Word("five","massokka", R.raw.number_five, R.drawable.number_five));
        mWords.add(new Word("six","temmokka", R.raw.number_six, R.drawable.number_six));
        mWords.add(new Word("seven","kenekaku", R.raw.number_seven, R.drawable. number_seven));
        mWords.add(new Word("eight","kawinta", R.raw.number_eight, R.drawable.number_eight));
        mWords.add(new Word("nine","wo'e", R.raw.number_nine, R.drawable.number_nine));
        mWords.add(new Word("ten","na'aacha", R.raw.number_ten,R.drawable.number_ten));

        WordAdapter wordAdapter = new WordAdapter(this, mWords, R.color.category_numbers);

        ListView listView = findViewById(R.id.wordView);

        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(getWordClickListener());
    }
}