package com.swarna.android.matrixapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Swarna Tripathi on 3/28/2017.
 */

public class GridViewCustomAdapter extends BaseAdapter {


    Activity mActivity;
    ArrayList<Integer> items;

    private static LayoutInflater inflater = null;

    public GridViewCustomAdapter(Activity activity ,ArrayList<Integer> items ) {
        mActivity = activity;
        items = items;
    }

    @Override
    public final int getCount() {

        return items.size();

    }

    @Override
    public final Object getItem(int position)
    {
        return items.get(position);
    }

    @Override
    public final long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null) inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item, null);

        TextView tv_item = (TextView) view.findViewById(R.id.tv_item);
        tv_item.setText(items.get(position));

        return view;
    }

}