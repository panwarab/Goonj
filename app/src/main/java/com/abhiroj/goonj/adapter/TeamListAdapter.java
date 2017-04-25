package com.abhiroj.goonj.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abhiroj.goonj.R;
import com.abhiroj.goonj.data.TeamMember;
import com.abhiroj.goonj.utils.Utility;
import com.abhiroj.goonj.viewholder.TeamItemHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import static com.abhiroj.goonj.data.Constants.CALL_REQUEST;

/**
 * Created by ruthless on 25/4/17.
 */

public class TeamListAdapter extends RecyclerView.Adapter<TeamItemHolder> {

    private Context context;
    private ArrayList<TeamMember> members;

    public TeamListAdapter(Context context) {
        this.context = context;
        members = new ArrayList<>();
    }

    @Override
    public TeamItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.team_list_item, parent, false);
        TeamItemHolder teamItemHolder = new TeamItemHolder(rootView);
        return teamItemHolder;
    }

    @Override
    public void onBindViewHolder(final TeamItemHolder holder, int position) {
        TeamMember member = members.get(position);
        holder.mem_name.setText(member.getName());
        holder.mem_committe.setText(member.getComm());
        final String phone = member.getPh() + "";
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                if(checkCallPermission()) {
                    Utility.showToast(context,R.string.makingcall);
                    v.getContext().startActivity(intent);
                }
                else
                {
                    requestPermission();
                }
                }
        });
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("sms:"
                        + phone));
                context.startActivity(sendIntent);
            }
        });
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions((Activity) context, new String[]{
                Manifest.permission.CALL_PHONE}, CALL_REQUEST
        );
    }

    private boolean checkCallPermission() {
        int result = ContextCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.CALL_PHONE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public void addMembers(ArrayList<TeamMember> members) {
       this.members.clear();
        this.members.addAll(members);
        if(this.members.size()>0)
        {
            Collections.sort(this.members, new Comparator<TeamMember>() {
                @Override
                public int compare(TeamMember o1, TeamMember o2) {
                    String typ1=o1.getComm();
                    String typ2=o2.getComm();
                    return typ1.compareTo(typ2);
                }
            });
        }
        notifyDataSetChanged();
    }
}
