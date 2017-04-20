package com.abhiroj.goonj.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhiroj.goonj.R;

/**
 * Created by ruthless on 20/4/17.
 */

public class EventListsElementHolder extends RecyclerView.ViewHolder {

    public ImageView event_card_image;
    public TextView  event_card_text;

    public EventListsElementHolder(View itemView) {
        super(itemView);
        event_card_image=(ImageView) itemView.findViewById(R.id.event_card_image);
        event_card_text=(TextView) itemView.findViewById(R.id.event_card_text);

    }
}
