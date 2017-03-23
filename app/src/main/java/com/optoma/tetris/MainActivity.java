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
import android.content.pm.ActivityInfo;

public class MainActivity extends Activity implements TetrisConstants {

    TetrisView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(ACTIVITY_LOG,"MainActivity onCreate()");

        // for SDK < 16, need to use below method to hide the navigation & action bars
        if (Build.VERSION.SDK_INT < 16) {
            Log.d(ACTIVITY_LOG, "MainActivity SDK < 16");
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            // for SDK >= 16, we can use below method and plus a listener for UI changes
            Log.d(ACTIVITY_LOG, "MainActivity SDK >= 16");
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
                                Log.d(ACTIVITY_LOG,"MainActivity: the navigation & action bars are visible");
                                // the navigation & action bars are visible
                                View decoview = getWindow().getDecorView();
                                int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN;
                                decoview.setSystemUiVisibility(uiOptions);
                            } else {
                                // the navigation & action bars are not visible
                                Log.d(ACTIVITY_LOG,"MainActivity: the navigation & action bars are not visible");
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
        if (getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            Log.d(ACTIVITY_LOG,"MainActivity: is portrait, let's set it to landspace");
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        //setContentView(R.layout.activity_main);

        tv = new TetrisView(this);
        setContentView(tv);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(ACTIVITY_LOG,"MainActivity onStar()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(ACTIVITY_LOG,"MainActivity onResume()");
    }
}
