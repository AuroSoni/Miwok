package com.example.miwok;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.miwok.adapters.SimpleFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.view_pager);
        SimpleFragmentPagerAdapter simpleFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(),getLifecycle());
        viewPager.setAdapter(simpleFragmentPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0){
                tab.setText(R.string.category_numbers);
            }
            else if (position == 1){
                tab.setText(R.string.category_colors);
            }
            else if (position == 2){
                tab.setText(R.string.category_family);
            }
            else {
                tab.setText(R.string.category_phrases);
            }
        }).attach();
    }
}