package com.swarna.android.mysdscannerapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.swarna.android.mysdscannerapp.R;

/**
 * Created by Swarna Tripathi on 1/19/2017.
 */

public class FilesViewHolder extends RecyclerView.ViewHolder
{
    public TextView tv_name;
    public TextView tv_size;

    public FilesViewHolder(View itemView)
    {
        super(itemView);

        tv_name = (TextView) itemView.findViewById(R.id.tvName);
        tv_size = (TextView) itemView.findViewById(R.id.tv_Size);
    }
}
