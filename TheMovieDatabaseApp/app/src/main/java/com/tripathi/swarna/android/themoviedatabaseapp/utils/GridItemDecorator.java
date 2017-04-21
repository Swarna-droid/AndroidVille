package com.tripathi.swarna.android.themoviedatabaseapp.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
/*
* Designed for use with grids, will be used once the GridLayoutManger will be used.
* */

public class GridItemDecorator extends RecyclerView.ItemDecoration
{
    private int offset;

    public GridItemDecorator(int offset) {
        this.offset = offset;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,RecyclerView parent, RecyclerView.State state) {
        outRect.left = offset;
        outRect.right = offset;
        outRect.bottom = offset;
        if (parent.getChildAdapterPosition(view) == 0)
        {
            outRect.top = offset;
        }
    }
}
