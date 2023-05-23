package sg.edu.tp.musicstream.RecyclerVIew;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import sg.edu.tp.musicstream.PlaySongActivity;
import sg.edu.tp.musicstream.R;
import sg.edu.tp.musicstream.util.AppUtil;
import sg.edu.tp.musicstream.Song;

public class AdapterForRecyclerView extends RecyclerView.Adapter<AdapterForRecyclerView.ViewHolders>implements Filterable {
    List<Song> SongList;
    List<Song> SongListFull;
    Context context;

    public AdapterForRecyclerView(List<Song> songList, Context context) {
        SongList = songList;
        this.context = context;
        SongListFull=new ArrayList<>(SongList);
    }


    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.song_items,parent,false);

        return new ViewHolders(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        int imageId = AppUtil.getImageIdFromDrawable(context, SongList.get(position).getCoverArt());
        holder.image.setImageResource(imageId);
        holder.title.setText(SongList.get(position).getTitle());
        holder.artist.setText(SongList.get(position).getArtist());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlaySongActivity.class);
                intent.putExtra("id", SongList.get(position).getId());
                intent.putExtra("title", SongList.get(position).getTitle());
                intent.putExtra("artist", SongList.get(position).getArtist());
                intent.putExtra("fileLink", SongList.get(position).getFileLink());
                intent.putExtra("coverArt", SongList.get(position).getCoverArt());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public Filter getFilter() {
        return FilterSong;
    }
    private Filter FilterSong = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Song> temList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                temList.addAll(SongListFull);
            } else {
                String filterPatter = charSequence.toString().toLowerCase().toString();
                for (Song items : SongListFull) {
                    if (items.getTitle().toLowerCase().contains(filterPatter) || items.getArtist().toLowerCase().contains(filterPatter)) {
                        temList.add(items);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = temList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            SongList.clear();
            SongList.addAll((Collection<? extends Song>) filterResults.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolders extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title,artist;
        public ViewHolders(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
            artist=itemView.findViewById(R.id.artist);

        }
    }
}
