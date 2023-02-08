package com.team.name.bestrestservice.algo;

import java.util.ArrayList;

public class State implements Cloneable {

    int rows, cols;
    char[][] board;
    char player;
    char computer;

    /* basic methods for constructing and proper hashing of State objects */
    public State(char[][] board, String letterToMove) {
        this.rows = 6;
        this.cols = 9;
        this.board = board;
        if (letterToMove.charAt(0) == 'F') {
            computer = 'F';
            player = 'S';
        } else {
            computer = 'S';
            player = 'F';
        }
    }

    /* returns a list of actions that can be taken from the current state
    actions are integers representing the column where a coin can be dropped */
    public ArrayList<Integer> getLegalActions() {
        ArrayList<Integer> actions = new ArrayList<Integer>();
        for (int j = 0; j < this.cols; j++) {if (this.board[0][j] == '_') {
            actions.add(j);}
        }
        return actions;
    }

    /* returns a State object that is obtained by the agent (parameter)
    performing an action (parameter) on the current state */
    public State generateSuccessor(char agent, int action) throws CloneNotSupportedException {

        int row;
        for (row = 0; row < this.rows && notObtainedByAi(row, action) && notObtainedByPlayer(row, action); row++);

        State newState = (State) this.clone();
        if(row - 1 < 0) {
            return newState;
        }
        newState.board[row-1][action] = agent;

        return newState;
    }

    private boolean notObtainedByAi(int row, int action) {
        return this.board[row][action] != computer;
    }

    private boolean notObtainedByPlayer(int row, int action) {
        return this.board[row][action] != player;
    }

    public Object clone() {
        return new State(this.board, String.valueOf(this.computer));
    }
}
