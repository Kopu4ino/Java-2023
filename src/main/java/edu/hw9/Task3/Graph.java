package edu.hw9.Task3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Graph {
    private final List<List<Integer>> adj;

    Graph(int vCnt) {
        adj = new ArrayList<>(vCnt);
        for (int i = 0; i < vCnt; ++i) {
            adj.add(new LinkedList<>());
        }
    }

    public void addEdge(int v, int w) {
        adj.get(v).add(w);
    }

    public List<Integer> getAdj(int v) {
        return adj.get(v);
    }
}
