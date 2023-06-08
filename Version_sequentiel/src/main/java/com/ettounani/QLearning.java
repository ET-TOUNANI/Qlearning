package com.ettounani;

import java.util.Random;

import static com.ettounani.QLUtils.*;
/*
Coded with love by Abderrahmane Ettounani
 */

public class QLearning {

    private int[][] grid;
    private double[][] qTable = new double[GRID_SIZE * GRID_SIZE][ACTIONS_SIZE];
    private int[][] actions;

    // Define the starting point
    private int stateI;
    private int stateJ;

    public QLearning() {
        actions = new int[][] {
                { 0, 1 }, // right
                { 1, 0 }, // down
                { 0, -1 }, // left
                { -1, 0 } // up
        };
        grid = new int[][] {
                // 0 = nothing, -1 = obstacle, 1 = goal
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, -1, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { -1, -1, -1, -1, -1, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1 }
        };
    }

    private void resetState() {
        stateI = 0;
        stateJ = 0;
    }

    private int chooseAction(double epsilon) {
        Random random = new Random();
        int bestAction = 0;
        double bestQValue = Double.MIN_VALUE;
        if (random.nextDouble() < epsilon) {
            // Exploration: Choose a random action
            bestAction = random.nextInt(ACTIONS_SIZE);
            int[] chosenAction = actions[bestAction];
        } else {
            // Exploitation: Choose the action with the highest reward
            int state = stateI * GRID_SIZE + stateJ; // Convert the position to a unique number
            for (int i = 0; i < ACTIONS_SIZE; i++) {
                if (qTable[state][i] > bestQValue) {
                    bestQValue = qTable[state][i];
                    bestAction = i;
                }
            }

        }
        return bestAction;
    }

    private boolean finished() {
        return grid[stateI][stateJ] == 1;
    }

    private int executeAction(int action) {
        // Bounce back if action is not valid
        stateI = Math.max(0, Math.min(stateI + actions[action][0], GRID_SIZE - 1));
        stateJ = Math.max(0, Math.min(stateJ + actions[action][1], GRID_SIZE - 1));
        return stateI * GRID_SIZE + stateJ;
    }

    private void printQTable() {
        System.out.println("***********qTable***********");
        for (double[] row : qTable) {
            System.out.print("|");
            for (double value : row) {
                System.out.print(value + "|\t");
            }
            System.out.println("|");
        }
        // System.out.println(Arrays.deepToString(qTable));
        resetState();
        while (!finished()) {
            int currentState = stateI * GRID_SIZE + stateJ;
            int action = chooseAction(0); // No exploration
            System.out.println("State: (" + currentState + ") | Best action: " + action);

            int newState = executeAction(action);
            System.out.println("New state: (" + newState + ")");

        }
        // System.out.println("Final State: ("+stateI+" "+stateJ+")");
    }

    public void runQLearning() {
        // IN MAS: This is a One Shot Behavior
        int iteration = 0;
        int currentState;
        int newState;
        resetState();
        // This is a Cycling Behavior
        while (iteration < MAX_EPOCHS) {
            resetState();
            while (!finished()) {
                // Current state and best action
                currentState = stateI * GRID_SIZE + stateJ;
                int action = chooseAction(0.3);

                // Next state and its best action
                newState = executeAction(action);
                int action2 = chooseAction(0);

                // Update Q-Table
                qTable[currentState][action] = qTable[currentState][action]
                        + ALPHA * (grid[stateI][stateJ]
                                + GAMMA * qTable[newState][action2]
                                - qTable[currentState][action]);
            }
            iteration++;
        }
        printQTable();
    }

}
