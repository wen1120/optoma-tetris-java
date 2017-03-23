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
    private int scoreX; // x position of score
    private int scoreY; // y position of score
    private int levelX; // x position of level
    private int levelY; // y position of level
    private int currentLevel = 1; // current level, default is 1
    private int currentScore = 0; // current score, default is 0

    public TetrisView(Activity context) {
        super(context);
        myActivityHandle = context;
        mPaint = new Paint();
        boxW = (getScreenWidth()/2)/Math.min(gridMaxRow,gridMaxCol);
        scoreX = boxW*2;
        scoreY = boxW*2;
        levelX = scoreX;
        levelY = scoreY + boxW;

        Log.d(ACTIVITY_LOG,"TetrisView: constructor");

        sw = (getScreenWidth()/boxW)*boxW; // the total screen width by pixel through box
        sh = (getScreenHeight()/boxW)*boxW; // the total screen height by pixel through box
        init();
    }

    private void init() {
        Log.d(ACTIVITY_LOG,"TetrisView: init()");

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

    /*
     paint tetris grid matrix on the right size (1/2)
     */
    public void paint(Canvas canvas, Paint paint) {
        int startX, startY, stopX, stopY;
        Log.d(ACTIVITY_LOG,"TetrisView: paint grid");

        gridLeftX = sw/2;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);

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
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawText("Score: "+currentScore, scoreX, scoreY, paint);
        canvas.drawText("Level: "+currentLevel, levelX, levelY, paint);
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
        int boxIndex = 0; // get the random index of current tetrimino
        int tetrimino[][] = {};
        int startX, startY, stopX, stopY;

        Log.d(ACTIVITY_LOG, "TetrisView: paint tetrimino, boxType="+boxType+",boxIndex="+boxIndex);

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

        if ( isMovable() && isFilled() ) {
            paint.setColor(Color.CYAN);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            for (int i = 0; i < tetrimino.length; i++) {
                startX = gridLeftX + boxW * boxStarCol + boxW * (tetrimino[i][0] + 1); // x
                startY = boxW * (tetrimino[i][1] + 1); //y
                stopX = startX + boxW;
                stopY = startY + startY;
                canvas.drawRect(startX + 1, startY + 1, stopX - 1, stopY - 1, mPaint);
            }
        }
    }

    private boolean isMovable() {
        return true;

    }
    private boolean isFilled() {
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint(canvas, mPaint);
        paintBox(canvas, mPaint);
    }

    public void restartGame() {

        init();
    }


    public void quitGame() {

        myActivityHandle.finish();
    }
}
