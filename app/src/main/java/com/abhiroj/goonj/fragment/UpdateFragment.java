package com.abhiroj.goonj.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.abhiroj.goonj.R;
import com.abhiroj.goonj.activity.MainActivity;
import com.abhiroj.goonj.adapter.UpdateListAdapter;
import com.abhiroj.goonj.data.UpdateData;
import com.abhiroj.goonj.utils.Utility;
import com.bumptech.glide.util.Util;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.abhiroj.goonj.data.Constants.UPD_ROOT;
import static com.abhiroj.goonj.data.Constants.fragtag;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {


    private RecyclerView update_List;
    private GridLayoutManager gridLayoutManager;
    private UpdateListAdapter listAdapter;
    public static final String TAG=UpdateFragment.class.getSimpleName();
    private ProgressBar progressBar;
    private ArrayList<UpdateData> updateDatas;

    public UpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        Toolbar toolbar= (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Updates");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateDatas=new ArrayList<UpdateData>();
        final DatabaseReference database= FirebaseDatabase.getInstance().getReference(UPD_ROOT);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.INVISIBLE);
                if(dataSnapshot.getChildrenCount()==0)
                {
                    Utility.showSnackBar(getActivity(),R.string.upd_tuned);
                }
                for (DataSnapshot d:dataSnapshot.getChildren())
               {
                   UpdateData updateData=d.getValue(UpdateData.class);
                   updateDatas.add(updateData);
               }
               listAdapter.addNewData(updateDatas);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_team_list, container, false);
        update_List=(RecyclerView) view.findViewById(R.id.list_container);
        gridLayoutManager=new GridLayoutManager(getContext(),1);
        listAdapter=new UpdateListAdapter(getContext());
        progressBar=(ProgressBar) view.findViewById(R.id.load_bar);
        if( Utility.detectConnection(getContext())) {
            if(updateDatas!=null && updateDatas.size()>0)
            {
                progressBar.setVisibility(View.INVISIBLE);
            }
            else
                progressBar.setVisibility(View.VISIBLE);
            update_List.setAdapter(listAdapter);
            update_List.setLayoutManager(gridLayoutManager);
        }
        else
        {
            Utility.showSnackBar(getActivity(),R.string.no_internet);
        }
            return view;
    }

    public static UpdateFragment newInstance() {
    UpdateFragment updateFragment=new UpdateFragment();
        fragtag.put(UpdateFragment.TAG,updateFragment);
        return updateFragment;
    }
}
