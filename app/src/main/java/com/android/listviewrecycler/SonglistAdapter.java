package com.android.listviewrecycler;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by arunkumar on 21/11/16.
 */

public class SonglistAdapter extends RecyclerView.Adapter<SonglistAdapter.ViewHolder> {

    private List<Songlist>listitems;

    public SonglistAdapter(List<Songlist>listitems){
        this.listitems = listitems;

    }

    Songlist songlist;

    private int lastPosition = -1;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_songlist,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         songlist = listitems.get(position);
        holder.no.setText(songlist.getNo());
        holder.song_name.setText(songlist.getSongnamess());
        holder.duration.setText(songlist.getDuration());
        songlist.getIdno();

        animate(holder.cards1,position);

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView no,song_name,duration;
        CardView cards1;
        public ViewHolder(final View itemView) {
            super(itemView);

            no = (TextView)itemView.findViewById(R.id.song_no);
            song_name = (TextView)itemView.findViewById(R.id.song_name);
            duration = (TextView)itemView.findViewById(R.id.song_duration);



            cards1 = (CardView)itemView.findViewById(R.id.cards1);
            cards1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    songlist = listitems.get(getAdapterPosition());
                    Toast.makeText(itemView.getContext(), "positionID"+songlist.getIdno(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(itemView.getContext(),SongdetailActivity.class);
                    intent.putExtra("SONGID",songlist.getIdno());
                    songlist.getContext().startActivity(intent);

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
