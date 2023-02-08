package com.team.name.bestrestservice.algo;

import java.util.ArrayList;

public class MiniMaxAgent {
    int depth;
    int x = 0;

    public MiniMaxAgent(int depth) {
        this.depth = depth;
    }

    public int getAction(State st) {
        try {
            double v = max_value(st, depth);
            return x;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int max_value(State st, int depth) throws CloneNotSupportedException {

        if(depth ==0)
            return st.evaluationFunction();

        ArrayList<Integer> children = new ArrayList<>();

        children = st.getLegalActions();
        int v = -10000000;

        int z;
        //double z;
        for (int i = 0; i < children.size(); i++) {
            z = min_value(st.generateSuccessor(st.player, children.get(i)), depth);
            if (z >= v) {
                v = z;
                this.x = i;
            }
        }
        return v;
    }

    public int min_value(State st, int depth) throws CloneNotSupportedException {
        if(depth ==0)
            return st.evaluationFunction();

        ArrayList<Integer> children = st.getLegalActions();

        int v = 10000000;
        int x = 0;
        int z;
        for (int i = 0; i < children.size(); i++) {
            z = max_value(st.generateSuccessor(st.computer, children.get(i)), depth - 1);
            if (z <= v) {
                v = z;
            }

        }
        return v;

    }
}
