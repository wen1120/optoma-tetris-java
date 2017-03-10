package com.optoma.tetris;

/**
 * Created by linweiting on 2017/3/9.
 */

import android.graphics.Color;

public interface TetrisConstants {

    public final int maxRow = 40;
    public final int maxCol = 40;
    public final int cellWidth = 80;
    public final int gridTop = 80;
    public final int gridLeft = 400;
    public int[][] gridBoard = new int[maxRow][maxCol];

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
