package com.optoma.tetris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.optoma.tetris.game.Events;
import com.optoma.tetris.game.GlassController;
import com.optoma.tetris.game.GlassModel;
import com.optoma.tetris.game.GlassView;
import com.optoma.tetris.game.PlayField;
import com.optoma.tetris.game.figures.Figure;

public class GameActivity extends Activity {

    /** UI elements */
    private TextView mScoreTxt;
    private TextView mLevelTxt;
    private ImageView NextTetriminoImg;
    private Button mExitBtn;

    private FrameLayout mMessageBoxLyt;
    private TextView mMessageTxt;
    private TextView mAddInfoTxt;

    private View mBtnLeft;
    private View mBtnRight;
    private View mBtnRotate;

    private GlassView mGlassView;

    /** Logic elements */
    private GlassModel mGlassModel;

    private final int[] mMusicFiles = {R.raw.music1, R.raw.music0, R.raw.music2, R.raw.music3};
    private final int[] sounds = {R.raw.rotate, R.raw.clear_line};
    private int mMusicIndex = 0;

    private MediaPlayer mMusicPlayer;
    private MediaPlayer mSoundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        mScoreTxt = (TextView) findViewById(R.id.ActivityGame_ScoreTxt);
        mLevelTxt = (TextView) findViewById(R.id.ActivityGame_LevelTxt);
        NextTetriminoImg = (ImageView) findViewById(R.id.ActivityGame_NextTetriminoImg);
        mExitBtn = (Button) findViewById(R.id.ActivityGame_OnExitBtn);
        mExitBtn.setOnClickListener(onExitBtnClick);

        mMessageBoxLyt = (FrameLayout) findViewById(R.id.ActivityGame_MessageBox);
        mMessageTxt = (TextView) findViewById(R.id.ActivityGame_MessageTxt);
        mAddInfoTxt = (TextView) findViewById(R.id.ActivityGame_additionalInfoTxt);

        mBtnLeft = findViewById(R.id.ActivityGame_LeftBtn);
        mBtnLeft.setOnClickListener(onLeftBtnClick);
        mBtnRight = findViewById(R.id.ActivityGame_RightBtn);
        mBtnRight.setOnClickListener(onRightBtnClick);
        mBtnRotate = findViewById(R.id.ActivityGame_RotateBtn);
        mBtnRotate.setOnClickListener(onRotateBtnClick);

        mGlassModel = new GlassModel(mGlassController);
        mGlassModel.onStart();
        playMusic();

        mGlassView = (GlassView) findViewById(R.id.ActivityGame_GlassView);

        setControlButtons(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mGlassModel.onEndGame();
    }

    private void setControlButtons(boolean enabled) {
        mBtnLeft.setEnabled(enabled);
        mBtnRight.setEnabled(enabled);
        mBtnRotate.setEnabled(enabled);
        mExitBtn.setEnabled(enabled);
    }

    private View.OnClickListener onExitBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            System.exit(0);
        }
    };

    private View.OnClickListener onLeftBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mGlassModel.onAction(Events.MOVE_LEFT);
        }
    };

    private View.OnClickListener onRightBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mGlassModel.onAction(Events.MOVE_RIGHT);
        }
    };

    private View.OnClickListener onRotateBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mGlassModel.onAction(Events.ROTATE);
        }
    };

    private View.OnTouchListener mGlassTouchListener = new View.OnTouchListener() {
        private int mPreviousAction = MotionEvent.ACTION_CANCEL;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                final int marginX = view.getWidth() / PlayField.WIDTH;
                final int moveCell = (int)(motionEvent.getX() / marginX);
                final int currCell = mGlassModel.getCurrentFigure().getX();
                if (currCell > moveCell) {
                    final int repeat = currCell - moveCell;
                    for (int i = 0; i < repeat; i++) {
                        mGlassModel.onAction(Events.MOVE_LEFT);
                    }
                } else {
                    final int repeat = moveCell - currCell;
                    for (int i = 0; i < repeat; i++) {
                        mGlassModel.onAction(Events.MOVE_RIGHT);
                    }
                }
                mPreviousAction = MotionEvent.ACTION_MOVE;
                return true;
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP
                    && mPreviousAction == MotionEvent.ACTION_MOVE) {
                mPreviousAction = MotionEvent.ACTION_UP;
                return true;
            }
            mPreviousAction = motionEvent.getAction();
            return false;
        }
    };

    /** GlassController interface implementation */
    private GlassController mGlassController = new GlassController() {
        @Override
        public void onChangeScores(int scores) {
            mScoreTxt.setText(Integer.toString(scores));
        }

        @Override
        public void onChangeLevel(int level) {
            mLevelTxt.setText(Integer.toString(level));
        }

        @Override
        public void onChangeNextFigure(Figure figure) {
            NextTetriminoImg.setImageBitmap(figure.draw(NextTetriminoImg.getWidth(), NextTetriminoImg.getHeight()));
            mGlassView.setCurrentFigure(mGlassModel.getCurrentFigure());
        }

        @Override
        public void onClearLines(int yLine,int clearLinesCount) {
            mGlassView.clearLines(yLine, clearLinesCount);
        }

        @Override
        public void onRefresh() {
            mGlassView.invalidate();
        }

        @Override
        public void onGameOver() {
            mMessageBoxLyt.setVisibility(View.VISIBLE);
            mMessageTxt.setText(R.string.game_message_gameOver);
            mAddInfoTxt.setText(null);

            setControlButtons(false);
        }
    };

    private void playMusic() {
        releaseMediaPlayer(mMusicPlayer);
        mMusicPlayer = MediaPlayer.create(GameActivity.this, mMusicFiles[mMusicIndex]);
        mMusicPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMusicPlayer.start();
        mMusicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (mMusicIndex == mMusicFiles.length) {
                    mMusicIndex = 0;
                } else {
                    mMusicIndex++;
                }
                playMusic();
            }
        });
    }

    private void playSound(int type) {
        releaseMediaPlayer(mSoundPlayer);
        mSoundPlayer = MediaPlayer.create(GameActivity.this, sounds[type]);
        mSoundPlayer.start();
    }

    private void releaseMediaPlayer(MediaPlayer mp) {
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }
}
