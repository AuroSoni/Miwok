package com.example.miwok.models;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.miwok.R;
import com.example.miwok.adapters.WordAdapter;

import java.util.ArrayList;

public class TemplateFragment extends Fragment {


    public ArrayList<Word> mWords = null;
    private ArrayList<Word> getWords(){
        return mWords;
    }
    private WordClickListener mWordClickListener;
    private AudioManager mAudioManager;
    private AudioFocusRequest mFocusRequest;
    private Word mWord = null;
    private MediaPlayer mMediaPlayer = null;
    final Object focusLock = new Object();
    boolean playbackDelayed = false;
    boolean playbackNowAuthorized = false;
    boolean resumeOnFocusGain = false;
    public WordAdapter wordAdapter;

    public class WordClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            releaseMediaPlayer();

            mWord = getWords().get(position);

            // requesting audio focus and processing the response
            int requestResult = mAudioManager.requestAudioFocus(mFocusRequest);
            synchronized(focusLock) {
                if (requestResult == AudioManager.AUDIOFOCUS_REQUEST_FAILED) {
                    playbackNowAuthorized = false;
                } else if (requestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    playbackNowAuthorized = true;
                    playbackNow();
                } else if (requestResult == AudioManager.AUDIOFOCUS_REQUEST_DELAYED) {
                    playbackDelayed = true;
                    playbackNowAuthorized = false;
                }
            }
        }
    }

    public void releaseMediaPlayer() {

        if(mMediaPlayer!=null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void playbackNow(){
        try {
            mMediaPlayer = MediaPlayer.create(getContext(), mWord.getAudioResourceId());
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(mp -> releaseMediaPlayer());
        } catch (Exception e){
            Log.i("E",e.toString());
        }
    }

    public WordClickListener getWordClickListener(){
        return mWordClickListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWordClickListener = new WordClickListener();

        // only resume if playback is being interrupted
        AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = focusChange -> {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    if (playbackDelayed || resumeOnFocusGain) {
                        synchronized (focusLock) {
                            playbackDelayed = false;
                            resumeOnFocusGain = false;
                        }
                        playbackNow();
                    }
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    synchronized (focusLock) {
                        resumeOnFocusGain = false;
                        playbackDelayed = false;
                    }
                    releaseMediaPlayer();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    synchronized (focusLock) {
                        // only resume if playback is being interrupted
                        if (mMediaPlayer != null)
                            resumeOnFocusGain = mMediaPlayer.isPlaying();
                        else resumeOnFocusGain = false;
                        playbackDelayed = false;
                    }
                    releaseMediaPlayer();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    releaseMediaPlayer();
                    break;
            }
        };

        mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        AudioAttributes playbackAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        mFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE)
                .setAudioAttributes(playbackAttributes)
                .setAcceptsDelayedFocusGain(true)
                .setOnAudioFocusChangeListener(mOnAudioFocusChangeListener)
                .build();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_template,container,false);
        ListView listView = rootView.findViewById(R.id.wordView);

        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(getWordClickListener());

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
