package com.optoma.tetris;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import java.util.Random;

/**
 * Created by linweiting on 2017/3/9.
 */

public class TetrisView extends View implements TetrisConstants {

    private Activity myActivityHandle;
    private Paint mPaint;
    private int boxW = 0; // box size (width=height)
    private int sw = 0; // screen width
    private int sh = 0; // screen height
    private int gridLeftX = 0; // the left x-position of the tetris grid
    private int scoreX = boxW*2;
    private int scoreY = boxW*2;
    private int levelX = scoreX;
    private int levelY = scoreY + boxW;

    public TetrisView(Activity context) {
        super(context);
        myActivityHandle = context;
        mPaint = new Paint();
        boxW = (getScreenWidth()/2)/Math.min(gridMaxRow,gridMaxCol);
        sw = (getScreenWidth()/boxW)*boxW; // the total screen width by pixel through box
        sh = (getScreenHeight()/boxW)*boxW; // the total screen height by pixel through box
        init();
    }

    private void init() {

        for (int r=0;r<gridMaxRow; r++) {
            for (int c=0; c<gridMaxCol; c++) {
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
        int startX, startY, stopX, stopY;

        // paint tetris grid matrix on the right size (1/2)
        gridLeftX = sw/2;
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);

        for(int r=0;r<gridMaxRow;r++) {
            for(int c=0;c<gridMaxCol;c++) {
                startX = gridLeftX + c * boxW;
                startY = r * boxW;
                stopX = startX + boxW;
                stopY = startY + boxW;
                canvas.drawRect(startX, startY, stopX, stopY, paint);
            }
        }

        // paint tetris scope and level
        paint.setTextSize(40);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText("Score: ", scoreX, scoreY, paint);
        canvas.drawText("Level: ", levelX, levelY, paint);
    }

    /*
    generate a new tetrmino box by random
    draw a tetrimino on the tetris grid
    check if any line is filled, if yes, erase the line
    if a line is erased, move boxs above this line down above
    calculate all values in the gridBoard[]
    do above 3 actions continuously until done
    check if movable, if yes, move down automatically
    repeat above action to check if any line is filled and do the same action
    change the gridBoard value according to the tetrmino current position
    check if all columns with value, if yes, game over
 */
    public void paintBox(Canvas canvas, Paint paint) {
        Random r = new Random();
        int boxType = r.nextInt(7); // there are total 7 kinds of tetrimino
        int boxIndex; // get the random index of current tetrimino
        int tetrimino[][] = {};
        int startX, startY, stopX, stopY;

        // generate a new tetrimino box by random
        switch (boxType) {
            case 0:
                boxIndex = r.nextInt(tetriminoI.length);
                tetrimino = tetriminoI[boxIndex];
                break;
            case 1:
                boxIndex = r.nextInt(tetriminoJ.length);
                tetrimino = tetriminoJ[boxIndex];
                break;
            case 2:
                boxIndex = r.nextInt(tetriminoL.length);
                tetrimino = tetriminoL[boxIndex];
                break;
            case 3:
                boxIndex = r.nextInt(tetriminoO.length);
                tetrimino = tetriminoO[boxIndex];
                break;
            case 4:
                boxIndex = r.nextInt(tetriminoS.length);
                tetrimino = tetriminoS[boxIndex];
                break;
            case 5:
                boxIndex = r.nextInt(tetriminoT.length);
                tetrimino = tetriminoT[boxIndex];
                break;
            case 6:
                boxIndex = r.nextInt(tetriminoZ.length);
                tetrimino = tetriminoZ[boxIndex];
                break;
        }

        for (int i=0;i< tetrimino.length;i++) {
            startX = gridLeftX + boxW * (tetrimino[i][0]+1); // x
            startY = boxW * (tetrimino[i][1] + 1); //y
            stopX = startX + boxW;
            stopY = startY + startY;
            canvas.drawRect(startX, startY, stopX, stopY, paint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint(canvas, mPaint);
    }

    public void restartGame() {

        init();
    }


    public void quitGame() {

        myActivityHandle.finish();
    }
}
