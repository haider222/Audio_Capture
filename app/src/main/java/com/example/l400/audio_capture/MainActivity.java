package com.example.l400.audio_capture;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button start , pause , play;
    MediaRecorder myAudioRecorder ;
    String outputFile = Environment.getExternalStorageDirectory(). getAbsolutePath() + "/myrecording.3gp";;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.pause);
        play = (Button) findViewById(R.id.play);
        start.setEnabled(true);
        pause.setEnabled(true);

        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);
    }
        public void start(View view)  {
        try {
            {
                myAudioRecorder.prepare();
                myAudioRecorder.start();
                start.setEnabled(false);
                pause.setEnabled(true);
                Toast.makeText(MainActivity.this, "Recording is starting now", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    public void pause(View view )
    {
        myAudioRecorder.stop();
        myAudioRecorder.release();
        myAudioRecorder = null;
        start.setEnabled(true);
        pause.setEnabled(false);
        Toast.makeText(MainActivity.this, "Audio recorded succesfully", Toast.LENGTH_SHORT).show();
    }
    public void play(View view) throws IOException {
        MediaPlayer mediaplayer = new MediaPlayer();
        mediaplayer.setDataSource(outputFile);
        mediaplayer.prepare();
        mediaplayer.start();
        Toast.makeText(MainActivity.this, "Playing Audio", Toast.LENGTH_SHORT).show();
    }

    }

