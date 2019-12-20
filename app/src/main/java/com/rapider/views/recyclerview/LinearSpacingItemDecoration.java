package com.rapider.views.recyclerview;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

//https://stackoverflow.com/questions/28531996/android-recyclerview-gridlayoutmanager-column-spacing
public class LinearSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spacingTopBottom;
    private boolean includeEdge;

    public LinearSpacingItemDecoration(int spacingTopBottom, boolean includeEdge) {
        this.spacingTopBottom = spacingTopBottom;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        if (includeEdge) {
            if (position==0){
                outRect.top=spacingTopBottom;
            }
            outRect.bottom = spacingTopBottom; // item bottom
        } else {
               if (position >= 1) {
                outRect.top = spacingTopBottom; // item top
            }
        }
    }
}
