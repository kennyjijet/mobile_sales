/**
 * Copyright 2010 Mark Wyszomierski
 */

package com.salesforce.android.restsample.Tablet.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.salesforce.android.restsample.R;

import java.util.ArrayList;
import java.util.List;

//import com.joelapenna.foursquared.R;

/**
 * A multi-button control which can only have one pressed button at
 * any given time. Its functionality is quite similar to a tab control.
 * Tabs can't be skinned in android 1.5, and our main frame is a tab
 * host - which causes some different android problems, thus this control
 * was created.
 * 
 * @date September 15, 2010
 * @author Mark Wyszomierski (markww@gmail.com)
 */
public class SegmentedButton2 extends LinearLayout {

    private StateListDrawable mBgLeftOn;
    private StateListDrawable mBgRightOn;
    private StateListDrawable mBgCenterOn;
    private StateListDrawable mBgLeftOff;
    private StateListDrawable mBgRightOff;
    private StateListDrawable mBgCenterOff;
    private int mSelectedButtonIndex = 0;

    private List<String> mButtonTitles = new ArrayList<String>();
    private int mColorOnStart;
    private int mColorOnEnd;
    private int mColorOffStart;
    private int mColorOffEnd;
    private int mColorSelectedStart;
    private int mColorSelectedEnd;
    private int mColorStroke;
    private int mStrokeWidth;
    private int mCornerRadius;
    private int mTextStyle;
    private int mBtnPaddingTop;
    private int mBtnPaddingBottom;

    private OnClickListenerSegmentedButton mOnClickListenerExternal;

    private int numClickBtn0 = 0;
    private int numClickBtn1 = 1;
    private int numClickBtn2 = 1;
//    private int numClickBtn1 = 0;
//    private int numClickBtn2 = 0;


    public SegmentedButton2(Context context) {
        super(context);
    }

    public SegmentedButton2(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SegmentedButton, 0, 0);

        CharSequence btnText1 = a.getString(R.styleable.SegmentedButton_btnText1);
        CharSequence btnText2 = a.getString(R.styleable.SegmentedButton_btnText2);
        if (btnText1 != null) {
            mButtonTitles.add(btnText1.toString());
        }
        if (btnText2 != null) {
            mButtonTitles.add(btnText2.toString());
        }
      
        mColorOnStart = a.getColor(R.styleable.SegmentedButton_gradientColorOnStart, 0xFF0000);
        mColorOnEnd = a.getColor(R.styleable.SegmentedButton_gradientColorOnEnd, 0xFF0000);
        mColorOffStart = a.getColor(R.styleable.SegmentedButton_gradientColorOffStart, 0xFF0000);
        mColorOffEnd = a.getColor(R.styleable.SegmentedButton_gradientColorOffEnd, 0xFF0000);
        mColorStroke = a.getColor(R.styleable.SegmentedButton_strokeColor, 0xFF0000);
        mColorSelectedEnd = a.getColor(R.styleable.SegmentedButton_gradientColorSelectedEnd, 0xFF0000);
        mColorSelectedStart = a.getColor(R.styleable.SegmentedButton_gradientColorSelectedStart, 0xFF0000);
        mStrokeWidth = a.getDimensionPixelSize(R.styleable.SegmentedButton_strokeWidth, 1); //1
        mCornerRadius = a.getDimensionPixelSize(R.styleable.SegmentedButton_cornerRadius, 4);
        mTextStyle = a.getResourceId(R.styleable.SegmentedButton_textStyle, -1);
        mBtnPaddingTop = a.getDimensionPixelSize(R.styleable.SegmentedButton_btnPaddingTop, 0);
        mBtnPaddingBottom = a.getDimensionPixelSize(R.styleable.SegmentedButton_btnPaddingBottom, 0);
        
    
        a.recycle();
    
        buildDrawables(mColorOnStart, mColorOnEnd, mColorOffStart, mColorOffEnd, 
                mColorSelectedStart, mColorSelectedEnd, mCornerRadius, mColorStroke, 
                mStrokeWidth);
        
        if (mButtonTitles.size() > 0) {
            _addButtons(new String[mButtonTitles.size()]);
        }
    }
    
    public void clearButtons() {
        removeAllViews();
    }
    
    public void addButtons(String ... titles) {
        _addButtons(titles);
    }
    
    private void _addButtons(String[] titles) {
        
        for (int i = 0; i < titles.length; i++) {

            Button button = new Button(getContext());
            button.setText(titles[i]);
            button.setTag(new Integer(i));
            button.setOnClickListener(mOnClickListener);
            if (mTextStyle != -1) {
                button.setTextAppearance(getContext(), mTextStyle);
            }
            
            if (titles.length == 1) {
                // Don't use a segmented button with one button.
                return;
            } else if (titles.length == 2) {
                if (i == 0) {
                    button.setBackgroundDrawable(mBgLeftOff);
                } else {
                    button.setBackgroundDrawable(mBgRightOn);
                }
            } else {
                if (i == 0) {
                    button.setBackgroundDrawable(mBgLeftOff);
                } else if (i == titles.length-1) {
                    button.setBackgroundDrawable(mBgRightOn);
                } else {
                    button.setBackgroundDrawable(mBgCenterOn);
                }
            }
            LayoutParams llp =
                new LayoutParams(
                        0,
                        LayoutParams.WRAP_CONTENT,
                        1);
            addView(button, llp);
            button.setPadding(0, mBtnPaddingTop, 0, mBtnPaddingBottom);
        }
    }
    
    private void buildDrawables(int colorOnStart,
                                int colorOnEnd,
                                int colorOffStart,
                                int colorOffEnd,
                                int colorSelectedStart,
                                int colorSelectedEnd,
                                float crad,
                                int strokeColor,
                                int strokeWidth) 
    {
        // top-left, top-right, bottom-right, bottom-left
        float[] radiiLeft = new float[] {
            crad, crad, 0, 0, 0, 0, crad, crad        
        };
        float[] radiiRight = new float[] {
            0, 0, crad, crad, crad, crad, 0, 0        
        };
        float[] radiiCenter = new float[] {
            0, 0, 0, 0, 0, 0, 0, 0        
        };
        
        GradientDrawable leftOn = buildGradientDrawable(colorOnStart, colorOnEnd, strokeWidth, strokeColor);
        leftOn.setCornerRadii(radiiLeft);
        GradientDrawable leftOff = buildGradientDrawable(colorOffStart, colorOffEnd, strokeWidth, strokeColor);
        leftOff.setCornerRadii(radiiLeft);
        GradientDrawable leftSelected = buildGradientDrawable(colorSelectedStart, colorSelectedEnd, strokeWidth, strokeColor);
        leftSelected.setCornerRadii(radiiLeft);
        
        GradientDrawable rightOn = buildGradientDrawable(colorOnStart, colorOnEnd, strokeWidth, strokeColor);
        rightOn.setCornerRadii(radiiRight);
        GradientDrawable rightOff = buildGradientDrawable(colorOffStart, colorOffEnd, strokeWidth, strokeColor);
        rightOff.setCornerRadii(radiiRight);
        GradientDrawable rightSelected = buildGradientDrawable(colorSelectedStart, colorSelectedEnd, strokeWidth, strokeColor);
        rightSelected.setCornerRadii(radiiRight);

        GradientDrawable centerOn = buildGradientDrawable(colorOnStart, colorOnEnd, strokeWidth, strokeColor);
        centerOn.setCornerRadii(radiiCenter);
        GradientDrawable centerOff = buildGradientDrawable(colorOffStart, colorOffEnd, strokeWidth, strokeColor);
        centerOff.setCornerRadii(radiiCenter);
        GradientDrawable centerSelected = buildGradientDrawable(colorSelectedStart, colorSelectedEnd, strokeWidth, strokeColor);
        centerSelected.setCornerRadii(radiiCenter);
        
        List<int[]> onStates = buildOnStates();
        List<int[]> offStates = buildOffStates();
        
        mBgLeftOn = new StateListDrawable();
        mBgRightOn = new StateListDrawable();
        mBgCenterOn = new StateListDrawable();
        mBgLeftOff = new StateListDrawable();
        mBgRightOff = new StateListDrawable();
        mBgCenterOff = new StateListDrawable();
        for (int[] it : onStates) {
            mBgLeftOn.addState(it, leftSelected);
            mBgRightOn.addState(it, rightSelected);
            mBgCenterOn.addState(it, centerSelected);
            mBgLeftOff.addState(it, leftSelected);
            mBgRightOff.addState(it, rightSelected);
            mBgCenterOff.addState(it, centerSelected);
        }
        for (int[] it : offStates) {
            mBgLeftOn.addState(it, leftOn);
            mBgRightOn.addState(it, rightOn);
            mBgCenterOn.addState(it, centerOn);
            mBgLeftOff.addState(it, leftOff);
            mBgRightOff.addState(it, rightOff);
            mBgCenterOff.addState(it, centerOff);
        }
    }

    private List<int[]> buildOnStates() {
        List<int[]> res = new ArrayList<int[]>();
        res.add(new int[] { 
            android.R.attr.state_focused, android.R.attr.state_enabled});
        res.add(new int[] { 
                android.R.attr.state_focused, android.R.attr.state_selected, android.R.attr.state_enabled});
        res.add(new int[] { 
                android.R.attr.state_pressed});
        return res;
    }
    
    private List<int[]> buildOffStates() {
        List<int[]> res = new ArrayList<int[]>();
        res.add(new int[] { 
            android.R.attr.state_enabled});
        res.add(new int[] { 
                android.R.attr.state_selected, android.R.attr.state_enabled});
        return res;
    }

    private GradientDrawable buildGradientDrawable(int colorStart, int colorEnd, int strokeWidth, int strokeColor) {
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] { colorStart, colorEnd });
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setStroke(strokeWidth, strokeColor);
        return gd;
    }
    
//    private OnClickListener mOnClickListener = new OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Button btnNext = (Button)v;
//            int btnNextIndex = ((Integer)btnNext.getTag()).intValue();
//            Log.e("test", "chk:_index2-0:_" + btnNextIndex);
//            Log.e("test", "chk:_index2-1:_" + mSelectedButtonIndex);
//            if (btnNextIndex == mSelectedButtonIndex) {
//                return;
//            }
//
//            handleStateChange2(mSelectedButtonIndex, btnNextIndex);
//
//            if (mOnClickListenerExternal != null) {
//                mOnClickListenerExternal.onClick(mSelectedButtonIndex);
//            }
//        }
//    };

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Button btnNext = (Button)v;
            int btnNextIndex = ((Integer)btnNext.getTag()).intValue();
            Log.e("test", "chk:_index2-0:_" + btnNextIndex);
            Log.e("test", "chk:_index2-1:_" + mSelectedButtonIndex);

            handleStateChange2(mSelectedButtonIndex, btnNextIndex);

            if (mOnClickListenerExternal != null) {
                mOnClickListenerExternal.onClick(btnNextIndex);
            }
            return;
        }
    };

    private void handleStateChange2(int btnLastIndex, int btnClickIndex) {
        int count = getChildCount();
        //Button btnLast = (Button)getChildAt(btnLastIndex);
        Button btnClick = (Button)getChildAt(btnClickIndex);

        Log.e("test", "chk_btn:_" + btnClickIndex);
        Log.e("test", "chk_btn:_count:_on_" + count);

        Log.e("test", "chk_case -:_ +++ +++ +++ +++ +++ _ +++ +++  ");
        Log.e("test", "chk_btn0:_numClickBtn0:_" + numClickBtn0);
        Log.e("test", "chk_btn0:_numClickBtn1:_" + numClickBtn1);
        Log.e("test", "chk_btn0:_numClickBtn2:_" + numClickBtn2);
        Log.e("test", "chk_btn0:_ +++ +++ +++ +++ +++ _ +++ +++  ");

        switch (btnClickIndex){
            case 0: numClickBtn0++;
                Log.e("test", "chk_case 0:_ +++ +++ +++ +++ +++ _ +++ +++  ");
                Log.e("test", "chk_btn2-0:_numClickBtn0:_" + numClickBtn0);
                Log.e("test", "chk_btn2-0:_numClickBtn1:_" + numClickBtn1);
                Log.e("test", "chk_btn2-0:_numClickBtn2:_" + numClickBtn2);

                if(numClickBtn0 == 1){
                    btnClick.setTextColor(getResources().getColor(R.color.color_bright_text_on));
                    btnClick.setBackgroundColor(getResources().getColor(R.color.color_bright_pin1));
                } else if(numClickBtn0 == 2){
//                    btnClick.setBackgroundColor(getResources().getColor(R.color.color_bright_off));
                    numClickBtn0 = 0;
                }
                break;

            case 1: numClickBtn1++;
                Log.e("test", "chk_case 1:_ +++ +++ +++ +++ +++ _ +++ +++  ");
                Log.e("test", "chk_btn2-1:_numClickBtn0:_" + numClickBtn0);
                Log.e("test", "chk_btn2-1:_numClickBtn1:_" + numClickBtn1);
                Log.e("test", "chk_btn2-1:_numClickBtn2:_" + numClickBtn2);

                if(numClickBtn1 == 1){
                    btnClick.setTextColor(getResources().getColor(R.color.color_bright_text_on));
                    btnClick.setBackgroundColor(getResources().getColor(R.color.color_bright_pin2));
//                    numClickBtn1 = 0;
                } else if(numClickBtn1 == 2){
                    btnClick.setTextColor(getResources().getColor(R.color.color_bright_text_off));
                    btnClick.setBackgroundColor(getResources().getColor(R.color.color_bright_off));
                    numClickBtn1 = 0;
                }
                break;

            case 2: numClickBtn2++;

                Log.e("test", "chk_case 2:_ +++ +++ +++ +++ +++ _ +++ +++  ");
                Log.e("test", "chk_btn2-2:_numClickBtn0:_" + numClickBtn0);
                Log.e("test", "chk_btn2-2:_numClickBtn1:_" + numClickBtn1);
                Log.e("test", "chk_btn2-2:_numClickBtn2:_" + numClickBtn2);

                if(numClickBtn2 == 1){
                    btnClick.setTextColor(getResources().getColor(R.color.color_bright_text_on));
                    btnClick.setBackgroundColor(getResources().getColor(R.color.color_bright_pin3));
                } else if(numClickBtn2 == 2){
                    btnClick.setTextColor(getResources().getColor(R.color.color_bright_text_off));
                    btnClick.setBackgroundColor(getResources().getColor(R.color.color_bright_off));
                    numClickBtn2 = 0;
                }
                break;
        }

        //btnLast.setPadding(0, mBtnPaddingTop, 0, mBtnPaddingBottom);
        btnClick.setPadding(0, mBtnPaddingTop, 0, mBtnPaddingBottom);

        //mSelectedButtonIndex = btnClickIndex;
    }
    
    public int getSelectedButtonIndex() {
        return mSelectedButtonIndex;
    }
    
    public void setPushedButtonIndex(int index) {
        handleStateChange2(mSelectedButtonIndex, index);

        switch (index){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;

        }
    }
    
    public void setOnClickListener(OnClickListenerSegmentedButton listener) {
        mOnClickListenerExternal = listener;
    }
    
    public interface OnClickListenerSegmentedButton {
        public void onClick(int index);
    }
}
