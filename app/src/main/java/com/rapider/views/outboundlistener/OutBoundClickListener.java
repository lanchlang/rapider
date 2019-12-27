package com.rapider.views.outboundlistener;

import android.view.MotionEvent;

public interface OutBoundClickListener {
    boolean needDetectOutBoundClick();
    boolean isDownEventInOutBound(MotionEvent event);
    void onOutBoundClick(MotionEvent event);
}
