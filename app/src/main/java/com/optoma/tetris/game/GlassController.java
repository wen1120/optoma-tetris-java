package com.optoma.tetris.game;


import com.optoma.tetris.game.figures.Figure;


public interface GlassController {
    void onChangeScores(int scores);
    void onChangeLevel(int level);
    void onChangeNextFigure(Figure figure);
    void onClearLines(int yLine, int clearLinesCount);

    void onRefresh();
    void onGameOver();
}
