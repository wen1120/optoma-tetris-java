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

        int sw = getScreenWidth(); // the total screen width by pixel
        int sh = getScreenHeight(); // the total screen height by pixel
        int gridX; // the left x-position of the tetris grid
        int startX, startY, stopX, stopY;
        int boxW = (sw/2)/Math.min(playMaxRow,playMaxCol);
        int gridHighGap = sh - (sh/boxW) * boxW;
        int gridWidthGap = sw - (sw/boxW) * boxW;
        sh = sh - gridHighGap; // new grid high by box width
        sw = sw - gridWidthGap; // new grid width by box width

        // paint tetris grid matrix
        gridX = sw/2;

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        int totalRow = sw/boxW;
        int totalCol = sh/boxW;
        for(int r=0;r<totalRow;r++) {
            for(int c=0;c<totalCol;c++) {
                startX = gridX + c * boxW;
                startY = r * boxW;
                stopX = startX;
                stopY = sh;
                canvas.drawLine(startX,startY,stopX,stopY,paint); // x-line
                startX = gridX;
                startY = c * boxW;
                stopX = sw;
                stopY = startY;
                canvas.drawLine(startX,startY,stopX,stopY,paint); // y-line
            }
        }
    }
}
