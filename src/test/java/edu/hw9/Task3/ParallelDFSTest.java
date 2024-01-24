package edu.hw9.Task3;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

class ParallelDFSTest {

    @Test
    void testDFS() {
        //Arrange
        Graph graph = new Graph(10);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 7);
        graph.addEdge(4, 7);
        graph.addEdge(4, 8);
        graph.addEdge(5, 8);
        graph.addEdge(6, 9);
        graph.addEdge(7, 9);
        graph.addEdge(8, 9);

        boolean[] visited = new boolean[10];
        ForkJoinPool pool = new ForkJoinPool();

        //Act
        pool.invoke(new DFSAction(graph, visited, 0));

        //Assert
        boolean[] expectedVisited = new boolean[10];
        Arrays.fill(expectedVisited, true);
        assertThat(visited).containsExactly(expectedVisited);
    }
}
