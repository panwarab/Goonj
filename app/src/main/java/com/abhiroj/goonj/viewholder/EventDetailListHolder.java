package com.abhiroj.goonj.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.abhiroj.goonj.R;

/**
 * Created by ruthless on 20/4/17.
 */

public class EventDetailListHolder extends RecyclerView.ViewHolder {


    public TextView event_name;
    public TextView event_rules;
    public TextView event_time;
    public TextView event_date;
    public TextView event_venue;


    /**
     * Event Type is not used here. Maybe we can use it to set the toolbar name.
     * @param itemView
     */
    public EventDetailListHolder(View itemView) {
        super(itemView);
        event_name=(TextView) itemView.findViewById(R.id.event_name);
        event_rules=(TextView) itemView.findViewById(R.id.event_rules);
        event_time=(TextView) itemView.findViewById(R.id.event_time);
        event_date=(TextView) itemView.findViewById(R.id.event_date);
        event_venue=(TextView) itemView.findViewById(R.id.event_venue);
    }
}
