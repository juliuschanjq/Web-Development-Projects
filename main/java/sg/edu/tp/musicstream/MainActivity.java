package sg.edu.tp.musicstream;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sg.edu.tp.musicstream.RecyclerVIew.AdapterForRecyclerView;
import sg.edu.tp.musicstream.util.AppUtil;

public class MainActivity extends AppCompatActivity {
    private final SongCollection songCollection = new SongCollection();


    String username;
    String ImageUrl;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Toolbar toolbar;
    TextView Username;
    RecyclerView recyclerView;
    List<Song> myList=new ArrayList<>();
    AdapterForRecyclerView adapterForRecyclerView;
    EditText inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Music Streem");

        adapterForRecyclerView=new AdapterForRecyclerView(SongCollection.list,this);


        inputSearch=findViewById(R.id.inputPost);
        recyclerView=findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterForRecyclerView);



        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapterForRecyclerView.getFilter().filter(editable.toString());

            }
        });
    }

    public void handleSelection(View view) {

        //1. Get the ID of the selected song
        String resourceId = AppUtil.getResourceId(this, view);

        //2. Search for the selected song based on the ID so that all information/data of
        // the song can be retrieved from a song list.
        Song selectedSong = songCollection.searchbyId(resourceId);

        //3. Popup a message on the screen to show the title of the song
        AppUtil.popMessage(this, "Streaming song: " + selectedSong.getTitle());

        //4. Send the song data to the player screen to be played.
        sendDataToActivity(selectedSong);

    }

    public void sendDataToActivity(Song song)
    {
        //1. Create a new Intent and specify the source destination screen/activity.
        Intent intent = new Intent(this, PlaySongActivity.class);

        //2. Store the song information into the Intent object to be
        // sent over to the destination screen.
        intent.putExtra("id", song.getId());
        intent.putExtra("title", song.getTitle());
        intent.putExtra("artist", song.getArtist());
        intent.putExtra("fileLink", song.getFileLink());
        intent.putExtra("coverArt", song.getCoverArt());

        //3. Launch the destination screen/activity
        startActivity(intent);
    }
}