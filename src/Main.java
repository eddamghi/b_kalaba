import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addSubGraph("A", "B", 2);
        graph.addSubGraph("A", "D", 6);
        graph.addSubGraph("A", "E", 5);
        graph.addSubGraph("B", "C", 1);
        graph.addSubGraph("C", "F", 7);
        graph.addSubGraph("C", "E", 1);
        graph.addSubGraph("D", "F", 6);
        graph.addSubGraph("E", "F", 1);
        graph.printGraph();
        System.out.println("Shortest path between A and F: " + graph.bellmanShortestPath("A", "F"));

    }
}