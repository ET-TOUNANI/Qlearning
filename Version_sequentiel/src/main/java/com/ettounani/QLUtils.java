package com.ettounani;
/*
Coded with love by Abderrahmane Ettounani
 */
public class QLUtils {
    public static final double ALPHA = 0.1;
    public static final double GAMMA = 0.9;
    public static final double EPSILON = 0.3;
    public static final double MAX_EPOCHS = 2000;
    public static final int GRID_SIZE = 6;
    public static final int ACTIONS_SIZE = 4;

    public static final int[][] ACTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    public static final int[][] GRID = {
            // 0=nothing, -1 obstacle, 1=goal
            { 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, -1, 0 },
            { 0, 0, 0, 0, 0, 0 },
            { 0, 0, -1, -1, 0, 0 },
            { 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 1 }
    };

    public static final int NUMBER_OF_AGENTS = 5;

}
