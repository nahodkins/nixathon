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
            max_value(st, depth);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return x;

    }

    public double max_value(State st, int depth) throws CloneNotSupportedException {
        ArrayList<Integer> children = new ArrayList<>();

        children = st.getLegalActions();
        double v = -10000000;

        double z;
        //double z;
        for (int i = 0; i < children.size(); i++) {
            z = min_value(st.generateSuccessor(st.computer, children.get(i)), depth);
            if (z >= v) {
                v = z;
                this.x = i;
            }
        }
        return v;
    }

    public double min_value(State st, int d) throws CloneNotSupportedException {

        ArrayList<Integer> children = st.getLegalActions();

        double v = 10000000;
        int x = 0;
        double z;
        for (int i = 0; i < children.size(); i++) {
            z = max_value(st.generateSuccessor(st.player, children.get(i)), d - 1);
            if (z <= v) {
                v = z;
            }

        }
        return v;

    }
}
