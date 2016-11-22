package com.android.listviewrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by arunkumar on 22/11/16.
 */

public class SongdetailAdapter extends RecyclerView.Adapter<SongdetailAdapter.ViewHolder>{

    private List<Songdetail>songdetail;

    public SongdetailAdapter(List<Songdetail>songdetail){
        this.songdetail = songdetail;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_songdetail,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Songdetail songdeti = songdetail.get(position);
        holder.song.setText(songdeti.getSongs());
        holder.album.setText(songdeti.getAlbums());
        holder.duration.setText(songdeti.getDurations());

    }

    @Override
    public int getItemCount() {
        return songdetail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView song,album,duration;
        public ViewHolder(View itemView) {
            super(itemView);

            song = (TextView)itemView.findViewById(R.id.song);
            album = (TextView)itemView.findViewById(R.id.album);
            duration = (TextView)itemView.findViewById(R.id.duration);
        }
    }
}
