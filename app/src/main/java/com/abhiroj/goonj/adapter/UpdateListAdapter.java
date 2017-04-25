package com.abhiroj.goonj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhiroj.goonj.R;
import com.abhiroj.goonj.data.UpdateData;
import com.abhiroj.goonj.viewholder.UpdateListItemHolder;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ruthless on 25/4/17.
 */

public class UpdateListAdapter extends RecyclerView.Adapter<UpdateListItemHolder>{

    private Context context;
    private ArrayList<UpdateData> internal_list;
    public UpdateListAdapter(Context context)
    {
        this.context=context;
        internal_list=new ArrayList<>();
    }

    @Override
    public UpdateListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.update_list_item,parent,false);
        UpdateListItemHolder updateListItemHolder=new UpdateListItemHolder(view);
        return updateListItemHolder;
    }

    @Override
    public void onBindViewHolder(UpdateListItemHolder holder, int position) {
      UpdateData updateData=internal_list.get(position);
        holder.utitle.setText(updateData.getTitle());
        holder.umess.setText(updateData.getMessage());
        holder.uby.append(updateData.getUpdate_by());
    }

    @Override
    public int getItemCount() {
        return internal_list.size();
    }

    public void addNewData(ArrayList<UpdateData> updateDatas) {
    if(internal_list==null)
        internal_list=new ArrayList<>();
        if(internal_list.size()<updateDatas.size())
    {
        internal_list.clear();
        internal_list.addAll(updateDatas);
        Collections.reverse(internal_list);
    }
    }
}
