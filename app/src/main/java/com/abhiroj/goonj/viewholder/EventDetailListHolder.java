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
    public TextView event_crit;
    public TextView event_point;


    /**
     * Event Type is not used here. Maybe we can use it to set the toolbar name.
     * @param itemView
     */
    public EventDetailListHolder(View itemView) {
        super(itemView);
        event_name=(TextView) itemView.findViewById(R.id.event_name);
        event_rules=(TextView) itemView.findViewById(R.id.event_rules);
        event_crit=(TextView) itemView.findViewById(R.id.event_criteria);
        event_point=(TextView) itemView.findViewById(R.id.event_points);
    }
}
