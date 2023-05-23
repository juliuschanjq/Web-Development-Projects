package sg.edu.tp.musicstream;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import sg.edu.tp.musicstream.util.AppUtil;

public class PlaySongActivity extends AppCompatActivity {
    // This is the constant variable that contains the website URL
    // Where we will stream the music
    private static final String BASE_URL = "https://p.scdn.co/mp3-preview/";



    // There variables are the song information that we will be
    // using in the codes here
    private String songId = " ";
    private String title = " ";
    private String artist = " ";
    private String fileLink = " ";
    private String coverArt = " ";
    private String url = " ";

    // This is the built-in MediaPlayer object that we will use
    // to play the music
    private MediaPlayer player = null;

    // This is the position of the song in the playback
    // We set it to 0 here so that is starts at the beginning.
    private int musicPosition = 0;

    // This button variable is created to link to the Play button
    // at the playback screen. We need to do this because it will act
    // as both Play and Pause button.
    private ImageView btnPlayPause = null;

    // We need to create and instance of the SongCollection object
    // so that we can get the next and previous song.
    private SongCollection songCollection = new SongCollection();
    private SongCollection originalsongCollection = new SongCollection();

    List<Song> shuffleList = Arrays.asList (songCollection.songs);

    private SeekBar seekbar;

    Handler handler = new Handler();

    private ImageView btnRepeat;
    boolean repeatFlag = false;
    private ImageView btnShuffle;
    boolean shuffleFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songlayout);

        btnPlayPause = findViewById(R.id.btnPlayPause);

        retrieveData();

        displaySong(title, artist, coverArt);

        seekbar = findViewById(R.id.seekBar);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (player != null && player.isPlaying()) {
                    player.seekTo(seekBar.getProgress());
                }
            }
        });

        btnRepeat = findViewById(R.id.btnRepeat);
        btnShuffle = findViewById(R.id.btnShuffle);

    }

    Runnable Playerbar = new Runnable() {
        @Override
        public void run() {
            if (player != null && player.isPlaying()) {
                seekbar.setProgress(player.getCurrentPosition());
            }
            handler.postDelayed(this, 1000);
        }
    };

    private void retrieveData() {
        Bundle songData = this.getIntent().getExtras();

        songId = songData.getString("id");
        title = songData.getString("title");
        artist = songData.getString("artist");
        fileLink = songData.getString("fileLink");
        coverArt = songData.getString("coverArt");

        url = BASE_URL + fileLink;
    }


    private void displaySong(String title, String artist, String coverArt) {
        // This is to retrieve the song title TextView from the UI screen.
        TextView txtTitle = findViewById(R.id.txtSongTitle);

        // This is to set the text of the song title TextView to the selected title.
        txtTitle.setText(title);

        // This is to retrieve the artist TextView from the UI screen.
        TextView txtArtist = findViewById(R.id.txtArtist);

        // This is to set the text of the artist TextView to the selected artist name.
        txtArtist.setText(artist);

        // This is to get the ID of the cover art from the drawable folder.
        int imageId = AppUtil.getImageIdFromDrawable(this, coverArt);

        // This is to retrieve the cover art ImageView from the UI screen.
        ImageView ivCoverArt = findViewById(R.id.imgCoverArt);

        // This is to set the selected cover art image to the ImageView in the screen.
        ivCoverArt.setImageResource(imageId);
    }

    private void preparePlayer() {
        //1. Create a new MediaPlayer.
        player = new MediaPlayer();

        // The try and catch codes are required by the prepare() method.
        // It is to catch any error that may occur and to handle the error.
        // You don't have to worry about this for now.
        // Right here, the error is printed out to the console using the
        // method printStackTrace().
        try {
            //2. This sets the Audio Stream Type to music streaming.
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);

            //3. Set the source of the music.
            // For example, the url of billie jean will look like
            // https://p.sc0dn.co/mp3-preview/f504e6b8e037771318656394f532dede4f9bcaea?cid=2afe87a64b0042dabf51f37318616965
            player.setDataSource(url);

            //4. Prepare the song for playback.
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playOrPauseMusic(View view) {
        // If no MediaPlayer object is created,call
        // preparePlayer method to create it.
        if (player == null)
            preparePlayer();
        // If the player is not working
        if (!player.isPlaying()) {
            // If the position of the music is greater than 0
            if (musicPosition > 0) {
                // Get the player to go to the music position
                player.seekTo(musicPosition);
            }

            player.start();

            seekbar.setMax(player.getDuration());

            handler.removeCallbacks(Playerbar);

            handler.postDelayed(Playerbar, 1000);

            btnPlayPause.setImageResource(R.drawable.ic_pause_symbol);

            setTitle("Now Playing: " + title + " = " + artist);

            //  When the music ends, stop the player.
            gracefullyStopsWhenMusicEnds();
        }
        else
        {    // Pause the music.
            pauseMusic();
        }
    }

    private void pauseMusic() {
        //1. Pause the player.
        player.pause();

        //2. Get the current position of the music that is playing
        musicPosition = player.getCurrentPosition();

        //3. Set the text on the button back to "PLAY".
        btnPlayPause.setImageResource(R.drawable.play_icon);
    }

    private void gracefullyStopsWhenMusicEnds()
    {
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                if (repeatFlag)
                {
                    playOrPauseMusic(null);
                }
                else

                stopActivities();
            }
        });
    }

    private void stopActivities() {
        if (player != null) {
            btnPlayPause.setImageResource(R.drawable.play_icon);
            musicPosition = 0;
            handler.removeCallbacks(Playerbar);
            setTitle("");
            player.stop();
            player.release();
            player = null;
        }
    }

    public void playNext(View view) {
        //1. Create a Song variable called nextSong and assign
        //2. the next song to it that is found using getNextSong() method
        //3. in SongCollection object. The parameter to be passed in is the
        //4. songId variable which contains the current song ID.
        Song nextSong = songCollection.getNextSong(songId);

        //2. If nextSong exist, which is not null,
        if (nextSong != null) {
            //1. Assign all song data to the variables; SongId, title, artist,
            // fileLink, coverArt.
            songId = nextSong.getId();
            title = nextSong.getTitle();
            artist = nextSong.getArtist();
            fileLink = nextSong.getFileLink();
            coverArt = nextSong.getCoverArt();

            //2. Form the full url of the song.
            url = BASE_URL + fileLink;

            //3. Display the next song on the screen.
            displaySong(title, artist, coverArt);

            //4. Call stopActivities() method to stop the current playing song.
            stopActivities();

            //5. Call playOrPauseMusic() method to play the song.
            playOrPauseMusic(view);
        }
    }

    public void playPrevious(View view) {
        Song prevSong = songCollection.getPrevSong(songId);

        if (prevSong != null) {
            songId = prevSong.getId();
            title = prevSong.getTitle();
            artist = prevSong.getArtist();
            fileLink = prevSong.getFileLink();
            coverArt = prevSong.getCoverArt();

            //2. Form the full url of the song.
            url = BASE_URL + fileLink;

            //3. Display the next song on the screen.
            displaySong(title, artist, coverArt);

            //4. Call stopActivities() method to stop the current playing song.
            stopActivities();

            //5. Call playOrPauseMusic() method to play the song.

            playOrPauseMusic(view);
        }
    }

    public void repeatSong(View view)
    {
        if (repeatFlag)
        {
            btnRepeat.setImageResource(R.drawable.repeat_off);

        }
        else
        {
            btnRepeat.setImageResource(R.drawable.repeat_on);
        }
        repeatFlag = !repeatFlag;
    }

    public void shuffleSong(View view)
    {
        if (shuffleFlag)
        {
            btnShuffle.setImageResource(R.drawable.shuffle_off);
            songCollection = new SongCollection();
        }
        else
        {
            btnShuffle.setImageResource(R.drawable.shuffle_on);

            Collections.shuffle(shuffleList);
            shuffleList.toArray(songCollection.songs);
        }
        shuffleFlag = !shuffleFlag;
    }
}