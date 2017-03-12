package com.optoma.tetris;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import com.optoma.tetris.TetrisView;
import android.util.Log;

public class MainActivity extends Activity implements TetrisConstants {

    TetrisView tv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv = new TetrisView(this);
        setContentView(R.layout.activity_main);
        setContentView(tv);
    }
}
