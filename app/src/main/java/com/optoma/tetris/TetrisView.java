package com.optoma.tetris;

import android.app.Activity;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by linweiting on 2017/3/9.
 */

public class TetrisView extends View implements TetrisConstants {

    private Activity myActivityHandle;
    private TetrisGrid grid;
    private Paint mPaint;

    public TetrisView(Activity context) {
        super(context);
        myActivityHandle = context;
        grid = new TetrisGrid();
        init();
    }

    private void init() {
        grid.init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        grid.paint(canvas, mPaint);
    }

    public void restartGame() {

        init();
    }

    public void quitGame() {

        myActivityHandle.finish();
    }
}
