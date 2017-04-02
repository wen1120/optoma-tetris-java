package com.optoma.tetris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.ActivityMain_NewGameBtn).setOnClickListener(onNewGameBtnClick);
        findViewById(R.id.ActivityMain_ExitBtn).setOnClickListener(onExitBtnClick);
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
