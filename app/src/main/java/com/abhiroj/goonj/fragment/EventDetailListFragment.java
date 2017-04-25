package com.abhiroj.goonj.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.abhiroj.goonj.R;
import com.abhiroj.goonj.adapter.EventDetailListAdapter;
import com.abhiroj.goonj.data.EventData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.abhiroj.goonj.data.Constants.EVENT_PATH;
import static com.abhiroj.goonj.data.Constants.KEY_EVENT_LIST;
import static com.abhiroj.goonj.data.Constants.fragtag;
import static com.abhiroj.goonj.data.EventDataContract.category;
import static com.abhiroj.goonj.utils.Utility.checkNotNull;
import static com.abhiroj.goonj.utils.Utility.detectConnection;
import static com.abhiroj.goonj.utils.Utility.showSnackBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailListFragment extends Fragment {


    private RecyclerView eventdetailist;
    public static final String TAG=EventDetailListFragment.class.getSimpleName();
    private EventDetailListAdapter eventDetailListAdapter;
    public int position;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ValueEventListener dataAdd;
    private ArrayList<EventData> events;
    private String eventype;
    private ProgressBar progressBar;
    private GridLayoutManager layoutManager;


    @Override
    public void onDetach() {
        super.onDetach();
        if(checkNotNull(eventDetailListAdapter))
        eventDetailListAdapter.restoreState();
    }

    public EventDetailListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventype=getArguments().getString(KEY_EVENT_LIST);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child(EVENT_PATH).child(eventype);
        events=new ArrayList<>();
        eventDetailListAdapter = new EventDetailListAdapter(getContext());
        dataAdd=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int totalevents= (int) dataSnapshot.getChildrenCount();
                Log.d(TAG,"Total Events:"+totalevents+" Data Snapshot details "+dataSnapshot.getKey());
                progressBar.setVisibility(View.INVISIBLE);
                for (DataSnapshot data:dataSnapshot.getChildren())
                {
                    EventData eventData=data.getValue(EventData.class);
                    eventDetailListAdapter.addEvent(eventData);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(dataAdd);
    }

    @Override
    public void onResume() {
        super.onResume();
        Toolbar toolbar=(Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(eventype);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_event_detail_list, container, false);
        progressBar=(ProgressBar)rootView.findViewById(R.id.load_bar);
        eventdetailist=(RecyclerView) rootView.findViewById(R.id.event_detail_list);
        eventDetailListAdapter=new EventDetailListAdapter(getContext());
        layoutManager=new GridLayoutManager(getContext(),1);
        if(detectConnection(getContext()))
        {
            if(events.size()==0 )
            {
                progressBar.setVisibility(View.VISIBLE);
            }
            else
                progressBar.setVisibility(View.INVISIBLE);
            eventdetailist.setAdapter(eventDetailListAdapter);
            eventdetailist.setLayoutManager(layoutManager);

        }
        else
        {
            showSnackBar(getActivity(),R.string.no_internet);
        }
        return rootView;
    }

    public static EventDetailListFragment newInstance() {
    EventDetailListFragment eventDetailListFragment=new EventDetailListFragment();
        fragtag.put(EventDetailListFragment.TAG,eventDetailListFragment);
        return eventDetailListFragment;
    }
}
