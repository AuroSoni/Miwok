package com.example.miwok.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.miwok.R;
import com.example.miwok.models.Word;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private final int mColorResourceId;

    public WordAdapter(@NonNull Context context, @NonNull ArrayList<Word> objects, int colorResourceId) {
        super(context, 0, objects);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Word word = getItem(position);
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        //Set the background color
        ConstraintLayout textContainer = listItem.findViewById(R.id.itemView);
        textContainer.setBackgroundColor(getContext().getResources().getColor(mColorResourceId,null));

        //Set the text views.
        TextView englishView = listItem.findViewById(R.id.defaultView);
        TextView miwokView = listItem.findViewById(R.id.miwokView);
        ImageView imageView = listItem.findViewById(R.id.imageView);

        miwokView.setText(word.getMiwokTranslation());
        englishView.setText(word.getDefaultTranslation());
        int resourceId = word.getImageResourceId();
        if(resourceId != -1 ){
            imageView.setImageResource(resourceId);
        }
        else{
            imageView.setVisibility(View.GONE);
        }


        return listItem;
    }

}
