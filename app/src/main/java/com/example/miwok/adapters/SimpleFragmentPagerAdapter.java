package com.example.miwok.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.miwok.ColorsFragment;
import com.example.miwok.FamilyFragment;
import com.example.miwok.NumbersFragment;
import com.example.miwok.PhrasesFragment;

public class SimpleFragmentPagerAdapter extends FragmentStateAdapter {

    private static final int NO_OF_FRAGMENTS = 4;

    public SimpleFragmentPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 0){
            return new NumbersFragment();
        }
        else if (position == 1){
            return new ColorsFragment();
        }
        else if (position == 2){
            return new FamilyFragment();
        }
        else {
            return new PhrasesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return NO_OF_FRAGMENTS;
    }
}
