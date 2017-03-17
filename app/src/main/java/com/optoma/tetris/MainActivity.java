package com.optoma.tetris;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import com.optoma.tetris.TetrisView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity implements TetrisConstants {

    TetrisView tv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv = new TetrisView(this);

        // for SDK < 16, need to use below method to hide the navigation & action bars
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            // for SDK > 16, we can use below method and plus a listener for UI changes
            View decoview = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decoview.setSystemUiVisibility(uiOptions);
            //ActionBar actionBar = getActionBar();
            //actionBar.hide();

            // register a listener to get notification of system UI visibility changes
            decoview.setOnSystemUiVisibilityChangeListener(
                    new View.OnSystemUiVisibilityChangeListener() {
                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                // the navigation & action bars are visible
                                View decoview = getWindow().getDecorView();
                                int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN;
                                decoview.setSystemUiVisibility(uiOptions);
                            } else {
                                // the navigation & action bars are not visible
                                View decoview = getWindow().getDecorView();
                                int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE
                                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN;
                                decoview.setSystemUiVisibility(uiOptions);
                            }
                        }
                    }
            );
        }
        setContentView(R.layout.activity_main);
        setContentView(tv);
    }
}
