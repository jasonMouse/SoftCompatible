package com.mouse.softcompatible;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

/**
 *  用于处理键盘和布局冲突的ScrollView
 */
public class ObserveScrollView extends ScrollView {
  
    private ScrollListener mListener;
    private int mScrolledY;

    public static interface ScrollListener {//声明接口，用于传递数据  
        public void scrollOritention(int l, int t, int oldl, int oldt);  
    }  
  
    public ObserveScrollView(Context context, AttributeSet attrs,
                             int defStyle) {
        super(context, attrs, defStyle);  
    }
  
    public ObserveScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);  
    }
  
    public ObserveScrollView(Context context) {
        super(context);  
    }

    /**
     * This is called in response to an internal scroll in this view (i.e., the
     * view scrolled its own contents). This is typically as a result of
     * {@link #scrollBy(int, int)} or {@link #scrollTo(int, int)} having been
     * called.
     *
     * @param l Current horizontal scroll origin.
     * @param t Current vertical scroll origin.
     * @param oldl Previous horizontal scroll origin.
     * @param oldt Previous vertical scroll origin.
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {  
        super.onScrollChanged(l, t, oldl, oldt);
        mScrolledY = oldt;
        Log.i(getClass().getSimpleName(), "--------------onScrollChanged--mScrolledY: " + mScrolledY + " ----t: " + t);
        if (mListener != null) {  
            mListener.scrollOritention(l, t, oldl, oldt);  
        }  
    }  

    public int getScrolledY() {
        return mScrolledY;
    }

    public void setScrollListener(ScrollListener l) {  
        this.mListener = l;  
    }  
  
}