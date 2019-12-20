package com.rapider.views.recyclerview;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

//https://stackoverflow.com/questions/28531996/android-recyclerview-gridlayoutmanager-column-spacing
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacingLeftRight;
    private int spacingTopBottom;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacingLeftRight, int spacingTopBottom, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacingLeftRight = spacingLeftRight;
        this.spacingTopBottom = spacingTopBottom;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = spacingLeftRight - column * spacingLeftRight / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacingLeftRight / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacingTopBottom;
            }
            outRect.bottom = spacingTopBottom; // item bottom
        } else {
            outRect.left = column * spacingLeftRight / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacingLeftRight - (column + 1) * spacingLeftRight / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacingTopBottom; // item top
            }
        }
    }
}
