package MST.Algorithms;

import MST.Model.Edge;
import MST.Model.Graph;
import MST.Model.Output.MSTResult;
import java.util.*;

public class PrimAlgorithm {

    public MSTResult findMST(Graph graph) {
        long start = System.nanoTime();

        List<Edge> mstEdges = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        // pick any start node (first one)
        String startNode = graph.getNodes().getFirst();
        visited.add(startNode);

        // Add all edges connected to the start node
        for (Edge e : graph.getEdges()) {
            if (e.getFrom().equals(startNode) || e.getTo().equals(startNode)) {
                pq.add(e);
            }
        }

        int operations = 0;

        while (!pq.isEmpty() && mstEdges.size() < graph.vertexCount() - 1) {
            Edge edge = pq.poll();
            operations++;

            String from = Objects.requireNonNull(edge).getFrom();
            String to = edge.getTo();

            boolean fromVisited = visited.contains(from);
            boolean toVisited = visited.contains(to);

            // Skip if both vertices are already visited (would form a cycle)
            if (fromVisited && toVisited) {
                continue;
            }

            // Accept edge into MST
            mstEdges.add(edge);

            // Add the newly discovered vertex
            String newVertex = fromVisited ? to : from;
            visited.add(newVertex);

            // Add all edges from this new vertex that connect to unvisited nodes
            for (Edge e : graph.getEdges()) {
                if ((e.getFrom().equals(newVertex) && !visited.contains(e.getTo())) ||
                        (e.getTo().equals(newVertex) && !visited.contains(e.getFrom()))) {
                    pq.add(e);
                }
            }
        }

        long end = System.nanoTime();
        double executionTimeMs = (end - start) / 1_000_000.0;
        int totalCost = mstEdges.stream().mapToInt(Edge::getWeight).sum();

        return new MSTResult(mstEdges, totalCost, operations, executionTimeMs);
    }
}