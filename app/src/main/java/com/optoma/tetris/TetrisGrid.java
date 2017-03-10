package com.optoma.tetris;

/**
 * Created by linweiting on 2017/3/9.
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.app.Activity;
import android.util.Log;

public class TetrisGrid extends Activity implements TetrisConstants {

    private int mLeft = gridLeft;
    private int mTop = gridTop;
    private int mRight = mLeft + cellWidth * maxRow;
    private int mBottom = mTop + cellWidth * maxCol;

    public void init() {
        for (int r=0;r<maxRow; r++) {
            for (int c=0; c<maxCol; c++) {
                gridBoard[r][c] = 0;
            }
        }
    }

    public void paint(Canvas canvas, Paint paint) {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        double wi=(double)width/(double)dm.xdpi;
        double hi=(double)height/(double)dm.ydpi;
        double x = Math.pow(wi,2);
        double y = Math.pow(hi,2);
        double screenInches = Math.sqrt(x+y);
        Log.d("D","wi="+String.valueOf(wi)+",hi="+String.valueOf(hi)+"x="+String.valueOf(x)+"y="+String.valueOf(y));
        // paint background
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(mLeft, mTop, mRight, mBottom, paint);
    }
}
