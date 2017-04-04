package com.optoma.tetris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import org.opencv.android.OpenCVLoader;
import android.util.Log;
/*
  how to use import opencv sdk to android studio, please visit
  http://stackoverflow.com/questions/17767557/how-to-use-opencv-in-android-studio-using-gradle-build-tool/27356635#27356635

 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.ActivityMain_NewGameBtn).setOnClickListener(onNewGameBtnClick);
        findViewById(R.id.ActivityMain_ExitBtn).setOnClickListener(onExitBtnClick);

        /*if (!OpenCVLoader.initDebug()) {
            Log.e(this.getClass().getSimpleName(), "  OpenCVLoader.initDebug(), not working.");
        } else {
            Log.d(this.getClass().getSimpleName(), "  OpenCVLoader.initDebug(), working.");
        }*/
    }

    private View.OnClickListener onNewGameBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent GameIntent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(GameIntent);
        }
    };

    private View.OnClickListener onExitBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
    }

}
