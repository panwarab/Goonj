package com.abhiroj.goonj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhiroj.goonj.R;
import com.abhiroj.goonj.data.EventData;
import com.abhiroj.goonj.viewholder.EventDetailListHolder;
import com.abhiroj.goonj.viewholder.EventListsElementHolder;

import java.util.ArrayList;
import java.util.Collection;

import static com.abhiroj.goonj.utils.Utility.checkNotNull;

/**
 * Created by ruthless on 20/4/17.
 */

public class EventDetailListAdapter extends RecyclerView.Adapter<EventDetailListHolder> {

    Context context;
    ArrayList<EventData> events;

    public EventDetailListAdapter(Context context)
    {
        this.context=context;
        events=new ArrayList<>();
    }

    public void addEvent(EventData eventData)
    {
        if(checkNotNull(events))
        {
            events.add(eventData);
            notifyDataSetChanged();
        }
    }

    public void addEventList(Collection<EventData> eventDatas)
    {
        if(checkNotNull(events))
        {
            events.addAll(eventDatas);
            notifyDataSetChanged();
        }
    }

    public void restoreState()
    {
        if(checkNotNull(events))
        {
            events.clear();
            events=null;
        }
    }



    @Override
    public EventDetailListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.fragment_event_detail_list_item,parent,false);
        EventDetailListHolder elementHolder= new EventDetailListHolder(view);

        return elementHolder;

    }

    @Override
    public void onBindViewHolder(EventDetailListHolder holder, int position) {
     EventData incoming=events.get(position);
        holder.event_title.setText(incoming.getEvent_name());
        holder.event_description.setText(incoming.getEvent_description());
        holder.event_day.setText(incoming.getEvent_day());
        holder.event_from_time.setText(incoming.getEvent_from_time());
        holder.event_to_time.setText(incoming.getEvent_to_time());
        holder.event_venue.setText(incoming.getEvent_venue());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
