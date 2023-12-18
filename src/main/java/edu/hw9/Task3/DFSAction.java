package edu.hw9.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class DFSAction extends RecursiveAction {
    private static final Logger LOGGER = LogManager.getLogger(DFSAction.class);

    private final Graph graph;
    private final boolean[] visited;
    private final int currentVertex;

    DFSAction(Graph graph, boolean[] visited, int currentVertex) {
        this.graph = graph;
        this.visited = visited;
        this.currentVertex = currentVertex;
    }

    @Override
    protected void compute() {
        if (!visited[currentVertex]) {
            visited[currentVertex] = true;
            LOGGER.info(
                "Посещаем узел: " + currentVertex + ", Работаем в потоке: " + Thread.currentThread().getName());
        }

        List<DFSAction> actions = new ArrayList<>();
        for (int n : graph.getAdj(currentVertex)) {
            if (!visited[n]) {
                DFSAction action = new DFSAction(graph, visited, n);
                actions.add(action);
                action.fork();
            }
        }

        for (DFSAction action : actions) {
            action.join();
        }
    }
}
