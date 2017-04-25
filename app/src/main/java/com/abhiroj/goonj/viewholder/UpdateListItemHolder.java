package com.abhiroj.goonj.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.abhiroj.goonj.R;
import com.abhiroj.goonj.adapter.TeamListAdapter;

/**
 * Created by ruthless on 25/4/17.
 */

public class UpdateListItemHolder extends RecyclerView.ViewHolder {

    public TextView utitle;
    public TextView umess;
    public TextView uby;

    public UpdateListItemHolder(View itemView) {
        super(itemView);
        utitle=(TextView) itemView.findViewById(R.id.upd_title);
        umess=(TextView) itemView.findViewById(R.id.upd_mess);
        uby=(TextView) itemView.findViewById(R.id.upd_by);
    }
}
