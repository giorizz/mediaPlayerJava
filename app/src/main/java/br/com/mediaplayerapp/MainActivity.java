package br.com.mediaplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekVolume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.teste);

        initializeSeek();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        if ( mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    private void initializeSeek(){
        seekVolume = findViewById(R.id.seekBar);

        //configura o volume do usuário
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //recuperar valor máximo e atual
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int actualVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //configura o volume máximo
        seekVolume.setMax(maxVolume);

        //configura o progresso da seekbar
        seekVolume.setProgress(actualVolume);

        seekVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void playSound(View view){
        if (mediaPlayer != null){
            mediaPlayer.start();
        }
    }

    public void pauseSound(View view){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    public void stopSound(View view){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.teste);
        }
    }

}