package com.optoma.tetris;

/**
 * Created by linweiting on 2017/3/9.
 */

import android.content.res.Resources;
import android.graphics.Color;

public interface TetrisConstants {

    public int gridMaxRow = 20;
    public int gridMaxCol = 14;
    public int[][] gridBoard = new int[gridMaxRow][gridMaxCol];
    public static String ACTIVITY_LOG="tetrisLog";

    // tetrimino
    public static int tetriminoI[][][] = {
            {{0,0},{1,0},{2,0},{3,0}},
            {{0,0},{0,1},{0,2},{0,3}}
    };
    public static int tetriminoJ[][][] = {
            {{0,2},{1,0},{1,1},{1,2}},
            {{0,0},{1,0},{2,0},{2,1}},
            {{0,0},{0,1},{0,2},{1,0}},
            {{0,0},{0,1},{1,1},{2,1}}
    };
    public static int tetriminoL[][][] = {
            {{0,0},{0,1},{0,2},{1,2}},
            {{0,1},{1,1},{2,0},{2,1}},
            {{0,0},{1,0},{1,1},{1,2}},
            {{0,0},{0,1},{1,1},{2,1}}
    };
    public static int tetriminoO[][][] = {
            {{0,0},{0,1},{1,0},{1,1}}
    };
    public static int tetriminoS[][][] = {
            {{0,1},{1,0},{1,1},{2,0}},
            {{0,0},{0,1},{1,1},{1,2}}
    };
    public static int tetriminoT[][][] = {
            {{0,1},{1,0},{1,1},{2,0}},
            {{0,1},{1,0},{1,1},{1,2}},
            {{0,0},{1,0},{1,1},{2,0}},
            {{0,0},{0,1},{0,2},{1,1}}
    };
    public static int tetriminoZ[][][] = {
            {{0,0},{1,0},{1,1},{2,1}},
            {{0,1},{0,2},{1,0},{1,1}},
            {{0,0},{1,0},{1,1},{2,1}},
            {{0,1},{0,2},{1,0},{1,1}}
    };
}
