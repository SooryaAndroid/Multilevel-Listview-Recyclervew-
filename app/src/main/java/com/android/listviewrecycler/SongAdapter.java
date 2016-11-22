package com.android.listviewrecycler;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by arunkumar on 21/11/16.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    Song song;
    private List<Song>songlist;

    public SongAdapter(List<Song>songlist){
        this.songlist = songlist;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_carditem,parent,false);

        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        song = songlist.get(position);
        holder.name.setText(song.getSongnames());
        holder.count.setText(song.getSongcounts());
        //holder.cards.setOnClickListener(show(position,holder));
        song.getIds();
        animate(holder.cards,position);


    }
//    public View.OnClickListener show(final int position, final ViewHolder holder)
//    {
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // showEditDialog(position, holder);
//                  song = songlist.get(position);
//                String idss = song.getIds();
//                Toast.makeText(song.getContext(), ""+idss, Toast.LENGTH_SHORT).show();
//                //  holder.cardView.setBackgroundColor(Color.GREEN);
//
//            }
//        };
//    }



    @Override
    public int getItemCount() {
        return songlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,count;
        CardView cards;
        public ViewHolder(final View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.song_name);
            count = (TextView)itemView.findViewById(R.id.song_count);
            cards = (CardView)itemView.findViewById(R.id.cards);




            cards.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    song = songlist.get(getAdapterPosition());

                    Toast.makeText(itemView.getContext(), "positionID"+song.getIds(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(itemView.getContext(),SonglistActivity.class);
                    intent.putExtra("ALBUMID",song.getIds());
                    song.getContext().startActivity(intent);



                }
            });


        }
    }

    private void animate(View view, final int pos) {
        view.animate().cancel();
        view.setTranslationY(100);
        view.setAlpha(0);
        view.animate().alpha(1.0f).translationY(0).setDuration(300).setStartDelay(pos * 100);
    }
}
