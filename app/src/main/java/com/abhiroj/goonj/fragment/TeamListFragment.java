package com.abhiroj.goonj.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhiroj.goonj.R;
import com.abhiroj.goonj.adapter.TeamListAdapter;
import com.abhiroj.goonj.data.TeamMember;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import static com.abhiroj.goonj.data.Constants.TEAM_ROOT;
import static com.abhiroj.goonj.data.Constants.fragtag;
import static com.abhiroj.goonj.utils.Utility.showSnackBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamListFragment extends Fragment {


    public static final String TAG=TeamListFragment.class.getSimpleName();
    private RecyclerView teamlist;
    private ArrayList<TeamMember> members;
    private TeamListAdapter teamListAdapter;
    private GridLayoutManager layoutManager;

    public TeamListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DatabaseReference database=FirebaseDatabase.getInstance().getReference(TEAM_ROOT);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int total = (int) dataSnapshot.getChildrenCount();
                Log.d(TAG, "Total Team types " + total);
                members=new ArrayList<TeamMember>();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    for (DataSnapshot d1 : d.getChildren()) {
                        TeamMember member = new TeamMember();
                        member.setName((String) d1.child("name").getValue());
                        member.setPh( d1.child("ph").getValue().toString());
                       member.setComm(d.getKey()+"");
                        members.add(member);
                    }
                    teamListAdapter.addMembers(members);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
               showSnackBar(getActivity(),databaseError.getMessage());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Toolbar toolbar=(Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Goonj'17 Team");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView=inflater.inflate(R.layout.fragment_team_list, container, false);
        teamlist=(RecyclerView) rootView.findViewById(R.id.list_container);
        layoutManager=new GridLayoutManager(getContext(),1);
        teamlist.setLayoutManager(layoutManager);
        teamListAdapter=new TeamListAdapter(getContext());
        teamlist.setAdapter(teamListAdapter);
        return rootView;
    }

    public static TeamListFragment newInstance() {
        TeamListFragment teamListFragment=new TeamListFragment();
        fragtag.put(TeamListFragment.TAG, teamListFragment);
        return teamListFragment;
    }
}
