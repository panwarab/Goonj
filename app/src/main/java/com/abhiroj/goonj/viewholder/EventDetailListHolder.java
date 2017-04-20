package com.abhiroj.goonj.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.abhiroj.goonj.R;

/**
 * Created by ruthless on 20/4/17.
 */

public class EventDetailListHolder extends RecyclerView.ViewHolder {


    public TextView event_title;
    public TextView event_description;
    public TextView event_from_time;
    public TextView event_to_time;
    public TextView event_venue;
    public TextView event_day;


    /**
     * Event Type is not used here. Maybe we can use it to set the toolbar name.
     * @param itemView
     */
    public EventDetailListHolder(View itemView) {
        super(itemView);
        event_title=(TextView) itemView.findViewById(R.id.event_title);
        event_description=(TextView) itemView.findViewById(R.id.event_description);
        event_from_time=(TextView) itemView.findViewById(R.id.event_from_time);
        event_to_time=(TextView) itemView.findViewById(R.id.event_to_time);
        event_venue=(TextView) itemView.findViewById(R.id.event_venue);
        event_day=(TextView) itemView.findViewById(R.id.event_day);
    }
}
