package vu.pham.musicappcuavu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlaySongActivity extends AppCompatActivity {

    ImageView imghinh;
    TextView txttenbaihat, txttencasi, txtstart, txtend;
    SeekBar skbplay;
    ImageButton imgbtnprev, imgbtnplaystop, imgbtnnext;
    MediaPlayer mediaPlayNhac;
    Music musicPlaySong;
    Handler threadHandle=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        anhxa();
        GetBaiHat();
        KhoiTaoMediaPlayer();
        ToTalTimeBaiHat();
        Events();
        mediaPlayNhac.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                doComplete();
            }
        });
    }
    class UpdateSeekBarThread implements Runnable{
        @Override
        public void run() {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
            txtstart.setText(simpleDateFormat.format(mediaPlayNhac.getCurrentPosition()));
            skbplay.setProgress(mediaPlayNhac.getCurrentPosition());
            threadHandle.postDelayed(this, 50);
        }
    }

    private void doStart(){
        if(mediaPlayNhac.isPlaying()){
            return;
        }
        mediaPlayNhac.start();
        imgbtnplaystop.setImageResource(R.drawable.pause);
        UpdateSeekBarThread updateSeekBarThread=new UpdateSeekBarThread();
        threadHandle.postDelayed(updateSeekBarThread, 50);
    }
    private void doPause(){
        mediaPlayNhac.pause();
        imgbtnplaystop.setImageResource(R.drawable.play);
    }
    private void doComplete(){
        imgbtnplaystop.setImageResource(R.drawable.play);
    }

    private void ToTalTimeBaiHat(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
        txtend.setText(simpleDateFormat.format(mediaPlayNhac.getDuration()));
        skbplay.setMax(mediaPlayNhac.getDuration());
    }
    private void KhoiTaoMediaPlayer() {
        mediaPlayNhac=MediaPlayer.create(PlaySongActivity.this, musicPlaySong.getFileBaiHat());
        txttenbaihat.setText(musicPlaySong.getTenBaiHat());
        txttencasi.setText(musicPlaySong.getTenCaSi());
        imghinh.setImageResource(musicPlaySong.getHinhAnh());
    }

    private void GetBaiHat() {
        Intent intent=getIntent();
        musicPlaySong=intent.getParcelableExtra("music");
    }

    private void Events() {
        imgbtnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skbplay.setProgress(skbplay.getProgress()-5000);
                mediaPlayNhac.seekTo(skbplay.getProgress());
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
                txtstart.setText(simpleDateFormat.format(mediaPlayNhac.getCurrentPosition()));
            }
        });
        imgbtnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayNhac.isPlaying()){
                    skbplay.setProgress(skbplay.getProgress()+5000);
                    mediaPlayNhac.seekTo(skbplay.getProgress());
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
                    txtstart.setText(simpleDateFormat.format(mediaPlayNhac.getCurrentPosition()));
                }else{
                    doStart();
                }
            }
        });
        imgbtnplaystop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayNhac.isPlaying()){
                    doPause();
                }else{
                    doStart();
                }
            }
        });
        skbplay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayNhac.seekTo(skbplay.getProgress());
            }
        });
    }

    private void anhxa() {
        imghinh=(ImageView) findViewById(R.id.imageViewHinhPlaySong);
        txttenbaihat=(TextView) findViewById(R.id.textviewTenBaiHatPlaySong);
        txttencasi=(TextView) findViewById(R.id.textviewTenCaSiPlaySong);
        txtstart=(TextView) findViewById(R.id.textviewStartPlaySong);
        txtend=(TextView) findViewById(R.id.textviewEndPlaySong);
        skbplay=(SeekBar) findViewById(R.id.seekbarPlaySong);
        imgbtnprev=(ImageButton) findViewById(R.id.imageButtonPrevPlaySong);
        imgbtnnext=(ImageButton) findViewById(R.id.imageButtonNextPlaySong);
        imgbtnplaystop=(ImageButton) findViewById(R.id.imageButtonPlayandStopPlaySong);
    }
}