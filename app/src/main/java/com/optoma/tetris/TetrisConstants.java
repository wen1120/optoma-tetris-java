package com.optoma.tetris;

/**
 * Created by linweiting on 2017/3/9.
 */

import android.content.res.Resources;
import android.graphics.Color;

public interface TetrisConstants {

    public final int playMaxRow = 26;
    public final int playMaxCol = 10;
    public final int scoreMaxRow = 10;
    public final int scoreMaxCol = 10;
    public int[][] gridBoard = new int[playMaxRow][playMaxCol];
    public static final String ACTIVITY_LOG="tetrisLog";

    // tetrimino
    public static final int I[][][] = {
            {{0,0},{1,0},{2,0},{3,0}},
            {{0,0},{0,1},{0,2},{0,3}}
    };
    public static final int J[][][] = {
            {{0,2},{1,0},{1,1},{1,2}},
            {{0,0},{1,0},{2,0},{2,1}},
            {{0,0},{0,1},{0,2},{1,0}},
            {{0,0},{0,1},{1,1},{2,1}}
    };
    public static final int L[][][] = {
            {{0,0},{0,1},{0,2},{1,2}},
            {{0,1},{1,1},{2,0},{2,1}},
            {{0,0},{1,0},{1,1},{1,2}},
            {{0,0},{0,1},{1,1},{2,1}}
    };
    public static final int O[][][] = {
            {{0,0},{0,1},{1,0},{1,1}}
    };
    public static final int S[][][] = {
            {{0,1},{1,0},{1,1},{2,0}},
            {{0,0},{0,1},{1,1},{1,2}}
    };
    public static final int T[][][] = {
            {{0,1},{1,0},{1,1},{2,0}},
            {{0,1},{1,0},{1,1},{1,2}},
            {{0,0},{1,0},{1,1},{2,0}},
            {{0,0},{0,1},{0,2},{1,1}}
    };
    public static final int Z[][][] = {
            {{0,0},{1,0},{1,1},{2,1}},
            {{0,1},{0,2},{1,0},{1,1}},
            {{0,0},{1,0},{1,1},{2,1}},
            {{0,1},{0,2},{1,0},{1,1}}
    };

}
