package com.example.miwok;

import android.os.Bundle;

import com.example.miwok.adapters.WordAdapter;
import com.example.miwok.models.TemplateFragment;
import com.example.miwok.models.Word;

import java.util.ArrayList;

public class PhrasesFragment extends TemplateFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWords = new ArrayList<>();
        mWords.add(new Word("Where are you going?","minto wuksus", R.raw.phrase_where_are_you_going));
        mWords.add(new Word("What is your name?","tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        mWords.add(new Word("My name is...","oyaaset...", R.raw.phrase_my_name_is));
        mWords.add(new Word("How are you feeling?","michәksәs?", R.raw.phrase_how_are_you_feeling));
        mWords.add(new Word("I’m feeling good.","kuchi achit", R.raw.phrase_im_feeling_good));
        mWords.add(new Word("Are you coming?","әәnәs'aa?", R.raw.phrase_are_you_coming));
        mWords.add(new Word("Yes, I’m coming.","hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        mWords.add(new Word("I’m coming.","әәnәm", R.raw.phrase_im_coming));
        mWords.add(new Word("Let’s go.","yoowutis", R.raw. phrase_lets_go));
        mWords.add(new Word("Come here.","әnni'nem", R.raw.phrase_come_here));

        wordAdapter = new WordAdapter(getContext(), mWords, R.color.category_phrases);
    }

}