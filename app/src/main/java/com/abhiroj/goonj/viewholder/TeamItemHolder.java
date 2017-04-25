package com.abhiroj.goonj.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhiroj.goonj.R;

import org.w3c.dom.Text;

/**
 * Created by ruthless on 25/4/17.
 */

public class TeamItemHolder extends RecyclerView.ViewHolder {

    public TextView mem_name;
    public TextView mem_committe;
    public ImageView call;
    public ImageView message;
    public TeamItemHolder(View itemView) {
        super(itemView);
        mem_name=(TextView) itemView.findViewById(R.id.mem_name);
        mem_committe=(TextView) itemView.findViewById(R.id.mem_comm);
        call=(ImageView) itemView.findViewById(R.id.phone);
        message=(ImageView) itemView.findViewById(R.id.message);
    }
}
