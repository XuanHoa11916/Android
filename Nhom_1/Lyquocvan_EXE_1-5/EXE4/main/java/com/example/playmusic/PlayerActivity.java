package com.example.playmusic;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    ImageButton btnnext;
    ImageButton btnprev;
    ImageButton btnplay;
    TextView tenbaihat;
    SeekBar seekBar;
    ImageView img;
    TextView timebegin;
    TextView timeend;

    String sname;

    static MediaPlayer mediaPlayer;
    int position;

    ArrayList<File> mySong;
    Thread updateseekBar;

    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        setControl();
        setEvent();
        upDateSeeBark();
    }

    private void setControl() {
        animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        btnnext = (ImageButton) findViewById(R.id.next);
        btnprev = (ImageButton) findViewById(R.id.btn_prev);
        btnplay = (ImageButton) findViewById(R.id.btn_play);
        tenbaihat = (TextView) findViewById(R.id.tenbaihat);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        img = (ImageView) findViewById(R.id.img);
        timebegin = (TextView) findViewById(R.id.time_begin);
        timeend = (TextView) findViewById(R.id.time_end);
    }

    private void setEvent() {
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setMax(mediaPlayer.getDuration());

                if (mediaPlayer.isPlaying()) {
                    btnplay.setBackgroundResource(R.drawable.play);
                    mediaPlayer.pause();
                    img.clearAnimation();
                } else {
                    btnplay.setBackgroundResource(R.drawable.pause);
                    mediaPlayer.start();
                    img.startAnimation(animation);

                }
                Time();
                Update();
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.stop();
                mediaPlayer.release();
                position = ((position + 1) % mySong.size());

                Uri uri = Uri.parse(mySong.get(position).toString());

                mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);

                sname = mySong.get(position).getName().toString();
                tenbaihat.setText(sname);

                mediaPlayer.start();

                Time();
                Update();
            }
        });
        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.stop();
                mediaPlayer.release();

                position = ((position - 1) < 0) ? (mySong.size() - 1) : (position - 1);

                Uri uri = Uri.parse(mySong.get(position).toString());

                mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);

                sname = mySong.get(position).getName().toString();
                tenbaihat.setText(sname);

                mediaPlayer.start();

                Time();
                Update();
            }
        });
    }

    private void Update() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhdanggio = new SimpleDateFormat("mm:ss");
                timebegin.setText(dinhdanggio.format(mediaPlayer.getCurrentPosition()));

                seekBar.setProgress(mediaPlayer.getCurrentPosition());

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > mySong.size() - 1) {
                            position = 0;
                        }
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                        mediaPlayer.start();
                        btnplay.setImageResource(R.drawable.pause);
                        Time();
                        Update();
                    }
                });

                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    private void Time() {
        SimpleDateFormat dinhdanggio = new SimpleDateFormat("mm:ss");
        timeend.setText(dinhdanggio.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void upDateSeeBark() {
        updateseekBar = new Thread() {
            @Override
            public void run() {

                int totalDuration = mediaPlayer.getDuration();
                int currentPosition = 0;

                while (currentPosition < totalDuration) {
                    try {
                        sleep(500);
                        currentPosition = mediaPlayer.getCurrentPosition();
                        seekBar.setProgress(currentPosition);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        mySong = (ArrayList) bundle.getParcelableArrayList("song");

        sname = mySong.get(position).getName().toString();

        String songName = i.getStringExtra("songname");

        tenbaihat.setText(songName);
        tenbaihat.setSelected(true);

        position = bundle.getInt("pos", 0);

        Uri uri = Uri.parse(mySong.get(position).toString());

        mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);

        mediaPlayer.start();
        btnplay.setBackgroundResource(R.drawable.pause);
        img.startAnimation(animation);
        Time();
        Update();

        seekBar.setMax(mediaPlayer.getDuration());

        seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorOrange), PorterDuff.Mode.MULTIPLY);
        seekBar.getThumb().setColorFilter(getResources().getColor(R.color.colorOrange), PorterDuff.Mode.SRC_IN);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }
}
