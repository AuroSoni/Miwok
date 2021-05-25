package com.example.miwok.models;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.miwok.R;

import java.util.ArrayList;

public class TemplateActivity extends AppCompatActivity {


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
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), mWord.getAudioResourceId());
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        mAudioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
