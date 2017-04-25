package com.abhiroj.goonj.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abhiroj.goonj.R;
import com.abhiroj.goonj.listener.OnCardTappedListener;
import com.abhiroj.goonj.viewholder.CardHolder;
import com.squareup.picasso.Picasso;

import static com.abhiroj.goonj.data.Constants.card_titles;
import static com.abhiroj.goonj.data.Constants.image_placeholder;

/**
 * Created by ruthless on 20/4/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardHolder> {

    Context context;
    private static final String TAG=CardAdapter.class.getSimpleName();

    public CardAdapter(Context context)
    {
        this.context=context;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);

        // Root view for card elements
        View view=inflater.inflate(R.layout.grid_elements,parent,false);
        CardHolder holder=new CardHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(CardHolder holder, final int position) {
        holder.card_title.setText(card_titles[position]);
        holder.card_image.setImageDrawable(getImage(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnCardTappedListener tappedListener=(OnCardTappedListener) context;
                tappedListener.onCardTapped(card_titles[position]);
            }
        });
    }

    private Drawable getImage(int position) {
    if(position==0)
    {
        return context.getDrawable(R.drawable.ic_event_available);
    }
    if(position==1){
        return context.getDrawable(R.drawable.ic_update);
    }
    if(position==2)
    {
        return context.getDrawable(R.drawable.ic_group);
    }
    return context.getDrawable(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return card_titles.length;
    }
}
