package com.abhiroj.goonj.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.abhiroj.goonj.R;

import static com.abhiroj.goonj.data.Constants.fragtag;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {


    public static String TAG=EventsFragment.class.getSimpleName();
    private Context context;

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    context=getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toolbar toolbar=(Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Events");
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_events, container,false);
        return rootView;
    }

    public static EventsFragment newInstance() {

        EventsFragment eventsFragment=new EventsFragment();
        fragtag.put(EventsFragment.TAG, eventsFragment);
        return eventsFragment;
    }
}
