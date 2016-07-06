package xyz.carlesllobet.swissknife.UI;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

import xyz.carlesllobet.swissknife.DB.UserFunctions;
import xyz.carlesllobet.swissknife.R;

public class MediaPlayerActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton btnBegin;
    private ImageButton btnStartPause;
    private ImageButton btnStop;
    private ImageButton btnLoop;
    private ImageButton btnSelect;

    private TextView audioTitle;

    private Uri uriSound;

    private String path;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        path = null;

        setContentView(R.layout.activity_mediaplayer);

        btnSelect = (ImageButton) findViewById(R.id.btnSelect);
        btnSelect.setOnClickListener(this);
        btnBegin = (ImageButton) findViewById(R.id.btnBegin);
        btnBegin.setOnClickListener(this);
        btnStartPause = (ImageButton) findViewById(R.id.btnStartPause);
        btnStartPause.setOnClickListener(this);
        btnStop = (ImageButton) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(this);
        btnLoop = (ImageButton) findViewById(R.id.btnLoop);
        btnLoop.setOnClickListener(this);

        setButtons(false);

        setTitle(R.string.tituloMediaPlayer);

        audioTitle = (TextView) findViewById(R.id.audioTitle);

        if (uriSound != null) {
            audioTitle.setText(path);
            Log.d("mediaPlayer", "playing!");
            play(this, uriSound);
        }
    }

    private void play(Context context, Uri uri) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(context, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.seekTo(0);
                btnStartPause.setImageResource(R.drawable.ic_play_grey600_48dp);
            }
        });
        mediaPlayer.prepareAsync();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBegin:
                mediaPlayer.seekTo(0);
                break;
            case R.id.btnStartPause:
                if (mediaPlayer.isPlaying()) {
                    Log.d("mediaPlayer!", "isPlaying");
                    btnStartPause.setImageResource(R.drawable.ic_play_grey600_48dp);
                    mediaPlayer.pause();
                } else {
                    btnStartPause.setImageResource(R.drawable.ic_pause_grey600_48dp);
                    mediaPlayer.start();
                }
                break;
            case R.id.btnStop:
                btnStartPause.setImageResource(R.drawable.ic_play_grey600_48dp);
                mediaPlayer.seekTo(0);
                mediaPlayer.pause();
                break;
            case R.id.btnLoop:
                if (!mediaPlayer.isLooping()) {
                    btnLoop.setImageResource(R.drawable.ic_repeat_off_grey600_48dp);
                    mediaPlayer.setLooping(true);
                } else {
                    btnLoop.setImageResource(R.drawable.ic_repeat_grey600_48dp);
                    mediaPlayer.setLooping(false);
                }
                break;
            case R.id.btnSelect:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            uriSound = data.getData();
            String path = uriSound.getPath();
            btnStartPause.setImageResource(R.drawable.ic_pause_grey600_48dp);
            setButtons(true);
            audioTitle.setText(path);
            play(this, uriSound);
        }
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    protected void onPause() {
        super.onPause();
        if (mediaPlayer.getAudioSessionId() != 0) {
            mediaPlayer.pause();
            if (isFinishing()) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_others, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                Intent intent = new Intent(this, HelpMPActivity.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (uriSound != null) {
            outState.putString("selectedSongUri", uriSound.toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("selectedSongUri")) {
            uriSound = Uri.parse(savedInstanceState.getString("selectedSongUri"));
        }
    }

    private void setButtons(boolean state) {
        if (state) {
            btnStartPause.setAlpha(1f);
            btnStop.setAlpha(1f);
            btnLoop.setAlpha(1f);
            btnBegin.setAlpha(1f);

            btnStartPause.setClickable(true);
            btnStop.setClickable(true);
            btnLoop.setClickable(true);
            btnStartPause.setClickable(true);
        } else {
            btnStartPause.setAlpha(0.1f);
            btnStop.setAlpha(0.1f);
            btnLoop.setAlpha(0.1f);
            btnBegin.setAlpha(0.1f);

            btnStartPause.setClickable(false);
            btnStop.setClickable(false);
            btnLoop.setClickable(false);
            btnStartPause.setClickable(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkMenuItem(4);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
