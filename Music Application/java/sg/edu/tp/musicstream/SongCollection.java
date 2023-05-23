package sg.edu.tp.musicstream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julius on 19 Nov 2020
 */

public class SongCollection
{
    public static List<Song>list=new ArrayList<>();

    public Song[] songs = new Song[6];

    public SongCollection()
    {
        prepareSongs();
    }

    private void prepareSongs()
    {
        Song RiotVan = new Song("S1001",
                "Riot Van",
                "Arctic Monkeys",
                "274c5f7aa66ca4fed06336de8238975ace0459d3?cid=2afe87a64b0042dabf51f37318616965",
                2.25,
                "am");

        Song BubbleGum = new Song("S1002",
                "Bubble Gum",
                "Clairo",
                "ff28b68667fa1532c270262f64f6e7b12ca7fe7a?cid=2afe87a64b0042dabf51f37318616965",
                2.93,
                "clairo");

        Song Earfquake = new Song("S1003",
                "Earfquake",
                "Tyler, the creator",
                "d6528c193bac76f7a04bdf1ea9453ec016a94f88?cid=2afe87a64b0042dabf51f37318616965",
                3.16,
                "igor");

        Song Dreams = new Song("S1004",
                "Dreams",
                "Fleetwood Mac",
                "eaf795ac6a50234bb12fe87b7d094fa52c692b75?cid=2afe87a64b0042dabf51f37318616965",
                4.3,
                "rumours");

        Song Nights = new Song("S1005",
                "Nights",
                "Frank Ocean",
                "43abb77afc35b8eced064a1b062921a13f5c7b15?cid=2afe87a64b0042dabf51f37318616965",
                5.12,
                "blonde");

        Song Right = new Song("S1006",
                "Right",
                "Mac Miller",
                "1bcb371b4febce7127ff18e9fd65740655e05795?cid=2afe87a64b0042dabf51f37318616965",
                4.8,
                "circles");


        songs[0] = RiotVan;
        songs[1] = BubbleGum;
        songs[2] = Earfquake;
        songs[3] = Dreams;
        songs[4] = Nights;
        songs[5] = Right;

        list.add(RiotVan);
        list.add(BubbleGum);
        list.add(Earfquake);
        list.add(Dreams);
        list.add(Nights);
        list.add(Right);


    }

    public Song searchbyId(String id) {
        // 1. Create a temporary Song object call song and set it to null.
        Song song = null;

        // 2. Starting from index 0 of the songs array to the last one,
        //loop through every song item. Increment the index by one after every loop
        // so that it goes to the next item until the next one.
        for (int index = 0; index < songs.length; index++)
        {
            //3. Store each song to the temporary song object.
            song = songs[index];

            // 4. Compare each song ID to the ID that we want to find.
            // If they are equal, return this as the result.
            if (song.getId().equals(id))
            {
                return song;
            }
        }
        // If the song cannot be found in the Array of songs,
        // the null song object will be returned.
        return song;
    }

    public Song getNextSong(String currentSongId)
    {
        //1. Create a temporary Song object called song and set it to null.
        Song song = null;

        //2. Starting from index 0 of the songs array to the last one,
        // loop through every song item, Increment the index by one after every loop
        // so that the system knows how to go to the next item until the last one.
        for (int index = 0; index < songs.length; index++)
        {
            //3. Create another temporary String variable and name it tempSong,
            // and assign the ID of each song item to tempSong.
            String tempSongId = songs[index].getId();

            //4. Compare the song ID in tempSong with the current song ID using the equals() method
            // and check the current index value. If the ID in tempSong equals to the current song ID,
            // and the index value is less than the last item of songs array,
            if (tempSongId.equals(currentSongId) && (index < songs.length -1))
            {
                //1. Assign the next item in songs array to the song variable.
                song = songs[index + 1];

                //2. Break and exit the loop.
                break;
            }
        }

        //5. Return the song.
        return song;
    }

    public Song getPrevSong(String currentSongId)
    {

        Song song = null;

        for (int index = 0; index < songs.length; index++)
        {

            String tempSongId = songs[index].getId();


            if (tempSongId.equals(currentSongId) && (index > 0)) {

                song = songs[index - 1];


                break;
            }
        }
        return song;
    }
}