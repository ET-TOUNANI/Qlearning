package com.ettounani;
import java.util.Random;

public class Qlearning {

    // Q Learning Utils
    private final double ALPHA = 0.1;
    private final double GAMMA = 0.9;
    //private final double EPS = 0.4;
    private final int MAX_EPOCH = 90000;
    private final int GRID_SIZE = 4;
    private final int ACTIONS_SIZE = 4;

    private int[][] grid = new int[GRID_SIZE][GRID_SIZE];
    private double[][] qTable = new double[GRID_SIZE*GRID_SIZE][ACTIONS_SIZE];
    private int[][] actions = new int[ACTIONS_SIZE][2];
    private int stateI;
    private int stateJ;

    public Qlearning() {
        actions = new int[][]{
                {0,-1}, //G
                {0,1}, //D
                {1,0}, //B
                {-1,0} //H
        };

        grid = new int[][]{
                {0,0,0,0 },
                {0,-1,0,-1},
                {0,0,-1,0},
                {-1,0,0,1}
        };
    }
    private boolean finished(){
        return  grid[stateI][stateJ]==1;
    }
    private void showResult(){
        System.out.println(" ----- QTable results :");
      for (double []line:qTable){
          System.out.print("[");
          for (double qv :line) {
              System.out.print(qv+" , ");
          }
          System.out.println("]");
      }
        System.out.println("---- Actions : ");
         resetState();
        System.out.println("| States |   Actions    |  ");
        System.out.println("-------------------------");
         while (!finished()){
             int act= chooseAction(0);
             System.out.println("| "+stateI+" | "+stateJ+" |    action : "+act+" |");
             executeAction(act);
         }
    }
    public void run(){
        int it = 0;
        int currentState;
        int nextState;
        int act, act1;
        while (it<MAX_EPOCH){
            resetState();
            while (!finished()){
                currentState = stateI*GRID_SIZE + stateJ;
                act = chooseAction(0.4);
                nextState = executeAction(act);
                act1 = chooseAction(0);
                qTable[currentState][act]=qTable[currentState][act]+ALPHA*(grid[stateI][stateJ]+GAMMA*qTable[nextState][act1]-qTable[currentState][act]);
            }
            it++;
        }
    showResult();
    }

    private void resetState(){
        stateI = 0;
        stateJ = 0;
    }

    private int chooseAction(double eps){
        Random random = new Random();
        double bestQ = 0;
        int act = 0;
        if(random.nextDouble() < eps){
            // Exploration
            act = random.nextInt(ACTIONS_SIZE);
        }else{
            // Exploiatation
            int st = stateI*GRID_SIZE + stateJ;
            for (int i = 0; i < ACTIONS_SIZE; i++) {
                if(qTable[st][i] > bestQ){
                    bestQ = qTable[st][i];
                    act = i;
                }
            }
        }
        return act;
    }

    private int executeAction(int act){
        stateI = Math.max(0, Math.min(actions[act][0]+stateI, GRID_SIZE-1));
        stateJ = Math.max(0, Math.min(actions[act][1]+stateJ, GRID_SIZE-1));
        return stateI*GRID_SIZE + stateJ;
    }
}