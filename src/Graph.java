import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
public class Graph {
    private Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();
    public void addSubGraph(String source, String destination, int weight) {
        assert source != null;
        assert destination != null;
        assert weight > 0;
        var sourceVertex = new Vertex(source);
        var destinationVertex = new Vertex(destination);
        adjVertices.putIfAbsent(sourceVertex, new ArrayList<>());
        adjVertices.putIfAbsent(destinationVertex, new ArrayList<>());
        adjVertices.get(sourceVertex).add(new Vertex(destination, weight));
    }
    public void printGraph() {
        for (Vertex v : adjVertices.keySet()) {
            var adjacentList = adjVertices.get(v);
            if (adjacentList != null) {
                for (Vertex adjVertex : adjacentList) {
                    System.out.println(v + "---" + adjVertex.getWeight() + "-->" + adjVertex);
                }
            }
        }
    }

    public int bellmanShortestPath(String between, String and) {
        assert between != null;
        assert and != null;
        var sourceVertex = new Vertex(between);
        var destinationVertex = new Vertex(and);
        var distance = new HashMap<Vertex, Integer>();
        for (Vertex vertex : adjVertices.keySet()) {
            distance.put(vertex, Integer.MAX_VALUE);
        }
        distance.put(sourceVertex, 0);
        for (int i = 0; i < adjVertices.size() - 1; i++) {
            for (Vertex vertex : adjVertices.keySet()) {
                for (Vertex adjVertex : adjVertices.get(vertex)) {
                    if (distance.get(vertex) != Integer.MAX_VALUE && distance.get(vertex) + adjVertex.getWeight() < distance.get(adjVertex)) {
                        distance.put(adjVertex, distance.get(vertex) + adjVertex.getWeight());
                    }
                }
            }
        }
        for (Vertex vertex : adjVertices.keySet()) {
            for (Vertex adjVertex : adjVertices.get(vertex)) {
                if (distance.get(vertex) != Integer.MAX_VALUE && distance.get(vertex) + adjVertex.getWeight() < distance.get(adjVertex)) {
                    throw new RuntimeException("Graph contains negative weight cycle");
                }
            }
        }
        return distance.get(destinationVertex);
    }
}

