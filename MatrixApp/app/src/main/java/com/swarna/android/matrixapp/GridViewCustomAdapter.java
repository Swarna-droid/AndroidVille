package com.swarna.android.matrixapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Swarna Tripathi on 3/28/2017.
 */

public class GridViewCustomAdapter extends BaseAdapter {

    Activity mActivity;
    int[][] gridItems;
    int count, rowPosition, columnPosition;

    private static LayoutInflater inflater = null;

    public GridViewCustomAdapter(Activity activity ,int[][] items ) {
        mActivity = activity;

        count = 0;
        gridItems = new int[items.length][items[0].length];
        for (int i = 0; i < gridItems.length; i++)
        {
            for (int j = 0; j < gridItems[i].length; j++)
            {
                gridItems[i][j] = items[i][j];
                count++;
            }
        }
        rowPosition = 0;
        columnPosition = 0;
    }

    @Override
    public final int getCount() {

        return count;
    }

    public int getRowCount() {
        return gridItems.length;
    }

    public int getColumnCount() {
        return gridItems[0].length;
    }

    @Override
    public final Object getItem(int position)
    {
        return null;
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
        tv_item.setText(String.valueOf(gridItems[position][position]));

        System.out.println("MATRIX : " + String.valueOf(gridItems[position][position]));

        return view;
    }

}