package com.abhiroj.goonj.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhiroj.goonj.R;
import com.abhiroj.goonj.data.EventData;
import com.abhiroj.goonj.viewholder.EventDetailListHolder;
import com.abhiroj.goonj.viewholder.EventListsElementHolder;
import com.squareup.picasso.Picasso;

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

    /**
     * Adds an event if it is not present
     * @param eventData
     */
    public void addEvent(EventData eventData)
    {
        if(checkNotNull(events) && !checkIfEventPresent(eventData.getName()))
        {
            events.add(eventData);
            notifyDataSetChanged();
        }
    }

    private boolean checkIfEventPresent(String name) {
    for(EventData event:events)
    {
        if(event.getName().equals(name))
        {
            return true;
        }
    }
    return false;
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
        holder.event_name.setText(incoming.getName());
        holder.event_rules.setText(incoming.getRules());
        holder.event_time.setText(get12HourConversion(incoming.getTime()));
        holder.event_date.setText(getNormailizedDate(incoming.getDate()));
        holder.event_venue.setText(incoming.getVenue());
    }

    private String getNormailizedDate(String date) {
        String[] d=date.split("\\.");
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(getMonthName(d[1])).append(" \'").append(d[0]);
        return stringBuffer.toString();
    }

    private String getMonthName(String s) {
    switch (Integer.parseInt(s))
    {
        case 1:
            return "Jan";
        case 2:
            return "Feb";
        case 3:
            return "Mar";
        case 4:
            return "Apr";
        case 5:
            return "May";
        case 6:
            return "Jun";
        case 7:
            return "July";
        case 8:
            return "Aug";
        case 9:
            return "Sep";
        case 10:
            return "Oct";
        case 11:
            return "Nov";
        case 12:
            return "Dec";
    }

    return "Apr";
    }

    private String get12HourConversion(String time) {
    String hhmm[]=time.split(":");
        boolean am=false;
        StringBuffer stringBuffer=new StringBuffer();
        int hour=Integer.parseInt(hhmm[0]);
        if(hour<12)
        {

            am=true;
        }
        else
        {
            hour=hour%12;
            am=false;
        }
        stringBuffer.append(hour).append(":").append((hhmm[1].length()==1)?("0"+hhmm[1]):hhmm[1]).append((am)?" AM":" PM");
        return stringBuffer.toString();
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void addEventListFromStart(ArrayList<EventData> events) {

    }
}
