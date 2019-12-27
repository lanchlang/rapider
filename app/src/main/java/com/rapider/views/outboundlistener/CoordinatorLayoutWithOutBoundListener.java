package com.rapider.views.outboundlistener;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class CoordinatorLayoutWithOutBoundListener extends CoordinatorLayout {
    private OutBoundClickListener outBoundClickListener;
    public CoordinatorLayoutWithOutBoundListener(@NonNull Context context) {
        super(context);
    }

    public CoordinatorLayoutWithOutBoundListener(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CoordinatorLayoutWithOutBoundListener(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (this.outBoundClickListener!=null && this.outBoundClickListener.needDetectOutBoundClick() && this.outBoundClickListener.isDownEventInOutBound(ev)){
            this.outBoundClickListener.onOutBoundClick(ev);
            return true;
        }else{
            return super.dispatchTouchEvent(ev);
        }
    }

    public void setOutBoundClickListener(OutBoundClickListener outBoundClickListener) {
        this.outBoundClickListener = outBoundClickListener;
    }
}
