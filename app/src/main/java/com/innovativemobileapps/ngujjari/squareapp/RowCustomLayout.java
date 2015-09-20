package com.innovativemobileapps.ngujjari.squareapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by ngujjari on 8/31/15.
 */
public class RowCustomLayout  extends LinearLayout{

    public RowCustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setLayoutParams(new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, 150));

    }


}
