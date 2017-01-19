package com.swarna.android.mysdscannerapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swarna.android.mysdscannerapp.R;
import com.swarna.android.mysdscannerapp.ViewHolder.FilesViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.swarna.android.mysdscannerapp.AppData.updatedMap;

/**
 * Created by Swarna Tripathi on 1/19/2017.
 */

public class FilesAdapter extends RecyclerView.Adapter<FilesViewHolder> {

    private List<Double> hashKeys = new ArrayList<>();
    private Context context;

    public FilesAdapter(Context context) {
       this.context = context;
    }

    @Override
    public FilesViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View view = li.inflate(R.layout.row_item, parent, false);

        return new FilesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilesViewHolder holder, int position)
    {
        hashKeys.clear();
        for (Object o : updatedMap.keySet()) {
            Double key = (Double) o;
            hashKeys.add(key);
        }
        holder.tv_size.setText(String.valueOf(hashKeys.get(position)));
        holder.tv_name.setText(updatedMap.get(hashKeys.get(position)));

        System.out.println("KEYS" + hashKeys.get(position) + "VAlue " + updatedMap.get(hashKeys.get(position)));

    }

    @Override
    public int getItemCount()
    {
        return updatedMap.size();
    }
}
