package com.optoma.tetris;

/**
 * Created by linweiting on 2017/3/9.
 */

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.app.Activity;
import android.util.Log;

public class TetrisGrid extends Activity implements TetrisConstants {

    public void init() {
        for (int r=0;r<playMaxRow; r++) {
            for (int c=0; c<playMaxCol; c++) {
                gridBoard[r][c] = 0;
            }
        }
    }

    private int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public void paint(Canvas canvas, Paint paint) {

        // paint grid background
        int sw = getScreenWidth();
        int sh = getScreenHeight();
        int startX;
        int startY;
        int stopX;
        int stopY;
        int gridX;

        startX = 0;
        startY = 0;
        stopX = sw;
        stopY = sh;

        int boxW = (sw/2)/Math.min(playMaxRow,playMaxCol);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(stopX, startY, stopX, stopY, paint);

        // paint tetris grid matrix
        gridX = sw/2;


        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        for(int r=0;r<playMaxRow;r++) {
            for(int c=0;c<playMaxCol;c++) {
                startX = gridX + c * boxW;
                startY = r * boxW;
                stopX = startX;
                stopY = sh;
                canvas.drawLine(startX,startY,stopX,stopY,paint);
            }
        }
    }
}
