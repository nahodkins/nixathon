package com.team.name.bestrestservice.algo;

import java.awt.Point;
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

    public boolean isGoal(char agent){

        String find=""+agent+""+agent+""+agent+""+agent;

        //check rows
        for(int i=0; i<this.rows; i++)
            if(String.valueOf(this.board[i]).contains(find))
                return true;

        //check cols
        for(int j=0; j<this.cols; j++){
            String col="";
            for(int i=0; i<this.rows; i++)
                col+=this.board[i][j];

            if(col.contains(find))
                return true;
        }

        //check diags
        ArrayList<Point> pos_right=new ArrayList<Point>();
        ArrayList<Point> pos_left=new ArrayList<Point>();

        for(int j=0; j<this.cols-4+1; j++)
            pos_right.add(new Point(0,j));
        for(int j=4-1; j<this.cols; j++)
            pos_left.add(new Point(0,j));
        for(int i=1; i<this.rows-4+1; i++){
            pos_right.add(new Point(i,0));
            pos_left.add(new Point(i,this.cols-1));
        }

        //check right diags
        for (Point p : pos_right) {
            String d="";
            int x=p.x, y=p.y;
            while(true){
                if (x>=this.rows||y>=this.cols)
                    break;
                d+=this.board[x][y];
                x+=1; y+=1;
            }
            if(d.contains(find))
                return true;
        }

        //check left diags
        for (Point p : pos_left) {
            String d="";
            int x=p.x, y=p.y;
            while(true){
                if(y<0||x>=this.rows||y>=this.cols)
                    break;
                d+=this.board[x][y];
                x+=1; y-=1;
            }
            if(d.contains(find))
                return true;
        }

        return false;

    }



    /* returns the value of each state for minimax to min/max over at
    zero depth. Right now it's pretty trivial, looking for only goal states.
    (This would be perfect for infinite depth minimax. Not so great for d=2) */
    public int evaluationFunction(){

        if (this.isGoal(computer))
            return 1000;
        if (this.isGoal(player))
            return -1000;

        return 0;
    }

}
