package com.mouse.softcompatible;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * 记得Manifest.xml中一定要设置
 * android:windowSoftInputMode="adjustResize"
 * 不然不起作用
 */
public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private FrameLayout fl_rootView;
    private ObserveScrollView svDetailContent;
    private LinearLayout llDetailContent;

    private EditText et_index_1;
    private EditText et_index_2;
    private EditText et_index_3;
    private EditText et_index_4;
    private EditText et_index_5;
    private EditText et_index_6;
    private EditText et_index_7;
    private EditText et_index_8;
    private EditText et_index_9;
    private EditText et_index_10;
    private EditText et_index_11;
    private EditText et_index_12;
    private EditText et_index_13;
    private EditText et_index_14;
    private EditText et_index_15;
    private EditText et_index_16;
    private EditText et_index_17;
    private EditText et_index_18;
    private EditText et_index_19;
    private EditText et_index_20;

    private int mSoftIntputHeight;                             // 软键盘的高度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        fl_rootView = (FrameLayout) this.findViewById(R.id.fl_rootView);
        svDetailContent = (ObserveScrollView) this.findViewById(R.id.sv_detail_content);
        llDetailContent = (LinearLayout) this.findViewById(R.id.ll_detail_content);
        et_index_1 = (EditText) this.findViewById(R.id.et_index_1);
        et_index_2 = (EditText) this.findViewById(R.id.et_index_2);
        et_index_3 = (EditText) this.findViewById(R.id.et_index_3);
        et_index_4 = (EditText) this.findViewById(R.id.et_index_4);
        et_index_5 = (EditText) this.findViewById(R.id.et_index_5);
        et_index_6 = (EditText) this.findViewById(R.id.et_index_6);
        et_index_7 = (EditText) this.findViewById(R.id.et_index_7);
        et_index_8 = (EditText) this.findViewById(R.id.et_index_8);
        et_index_9 = (EditText) this.findViewById(R.id.et_index_9);
        et_index_10 = (EditText) this.findViewById(R.id.et_index_10);
        et_index_11 = (EditText) this.findViewById(R.id.et_index_11);
        et_index_12 = (EditText) this.findViewById(R.id.et_index_12);
        et_index_13 = (EditText) this.findViewById(R.id.et_index_13);
        et_index_14 = (EditText) this.findViewById(R.id.et_index_14);
        et_index_15 = (EditText) this.findViewById(R.id.et_index_15);
        et_index_16 = (EditText) this.findViewById(R.id.et_index_16);
        et_index_17 = (EditText) this.findViewById(R.id.et_index_17);
        et_index_18 = (EditText) this.findViewById(R.id.et_index_18);
        et_index_19 = (EditText) this.findViewById(R.id.et_index_19);
        et_index_20 = (EditText) this.findViewById(R.id.et_index_20);
    }

    private void initData() {
    }

    private void initListener() {
        et_index_2.setOnTouchListener(this);
        et_index_3.setOnTouchListener(this);
        et_index_4.setOnTouchListener(this);
        et_index_5.setOnTouchListener(this);
        et_index_6.setOnTouchListener(this);
        et_index_7.setOnTouchListener(this);
        et_index_8.setOnTouchListener(this);
        et_index_9.setOnTouchListener(this);
        et_index_10.setOnTouchListener(this);
        et_index_11.setOnTouchListener(this);
        et_index_12.setOnTouchListener(this);
        et_index_13.setOnTouchListener(this);
        et_index_14.setOnTouchListener(this);
        et_index_15.setOnTouchListener(this);
        et_index_16.setOnTouchListener(this);
        et_index_17.setOnTouchListener(this);
        et_index_18.setOnTouchListener(this);
        et_index_19.setOnTouchListener(this);
        et_index_20.setOnTouchListener(this);
        et_index_1.setOnTouchListener(this);
    }

    // 添加布局监听器
    private void addGlobeListener() {
        fl_rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //获取软键盘的高度.
                int softIntputHeight = getKeyboardHeight(fl_rootView);
                Log.e("", "------------Keyboard mSoftIntputHeight: " + mSoftIntputHeight + ", softIntputHeight: " + softIntputHeight);
                //判断新获取的软键盘高度是否与之前的相等，如果相等则表示软键盘的状态没有发生变化.
                if (mSoftIntputHeight == softIntputHeight) {
                    return;
                }
                mSoftIntputHeight = softIntputHeight;

                View v = getCurrentFocus(); //获取当前有焦点的控件
                int[] leftTop = {0, 0};
                //获取输入框当前的location位置
                v.getLocationInWindow(leftTop); //获取控件的位置
                int top = leftTop[1];
                int bottom = top + v.getHeight();
                //计算控件距离屏幕底部的像数值（屏幕的高度-输入框底部在窗口的位置+软键盘弹出前scrollview滚动的距离-软键盘弹出后scrollview滚动的距离）
                final int height = fl_rootView.getHeight() - bottom + ((ObserveScrollView) svDetailContent).getScrolledY() - svDetailContent.getScrollY();
                final int scrollDis = ((ObserveScrollView) svDetailContent).getScrolledY() + mSoftIntputHeight - height; //计算scrollview应该滚动到的位置
                LinearLayout.LayoutParams params = null;
                View view = llDetailContent.findViewById(R.id.v_detail_softintput_view);
                params = (LinearLayout.LayoutParams) view.getLayoutParams();
                if (mSoftIntputHeight > 0) {
                    params.height = mSoftIntputHeight;
                    view.setLayoutParams(params);
                    if (scrollDis > 0) {
                        llDetailContent.post(new Runnable() {
                            @Override
                            public void run() {
                                svDetailContent.smoothScrollTo(0, scrollDis);
                                llDetailContent.removeCallbacks(this);
                            }
                        });
                    }
                } else {
                    params.height = 0;
                    view.setLayoutParams(params);
                }
                //主体UI动态添加与软键盘高度相等view
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                    fl_rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.setFocusable(true);
        v.setFocusableInTouchMode(true);
        // 请求焦点
        v.requestFocus();
        //设置显示光标
        ((EditText) v).setCursorVisible(true);
        // 添加布局监听器
        addGlobeListener();
        return false;
    }

    /**
     * 获取软键盘的高度
     *
     * @param v 软键盘弹出页面的View
     * @return
     */
    public static int getKeyboardHeight(View v) {
        //获取窗口可见区域大小
        Rect r = new Rect();
        v.getWindowVisibleDisplayFrame(r);

        //获取整个屏幕的高度
        int screenHeight = v.getRootView().getHeight();
        Log.e("ViewUtils", "------------Keyboard Size, bottom: " + r.bottom + " -top: " + r.top);
        Log.e("ViewUtils", "------------Keyboard Size, screenHeight: " + screenHeight + " -v.getHeight(): " + v.getHeight());
        int heightDifference = screenHeight - r.bottom - (screenHeight - v.getHeight());
        Log.e("ViewUtils", "------------Keyboard Size, Size: " + heightDifference);
        return heightDifference;
    }
}