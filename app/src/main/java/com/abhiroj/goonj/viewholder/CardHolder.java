package com.abhiroj.goonj.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhiroj.goonj.R;

/**
 * Created by ruthless on 20/4/17.
 */

public class CardHolder extends RecyclerView.ViewHolder{

    public TextView card_title;
    public ImageView card_image;

    public CardHolder(View itemView) {
        super(itemView);
        card_title= (TextView) itemView.findViewById(R.id.card_title);
        card_image= (ImageView) itemView.findViewById(R.id.card_image);
    }
}
