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
        Map<String, List<Edge>> adjacencyList = buildAdjacencyList(graph);

        // Priority queue for edges by weight
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        // Start from the first node
        String startNode = graph.getNodes().getFirst();
        visited.add(startNode);
        pq.addAll(adjacencyList.getOrDefault(startNode, List.of()));

        int operations = 0;

        while (!pq.isEmpty() && mstEdges.size() < graph.vertexCount() - 1) {
            Edge edge = pq.poll();
            operations++;

            String u = Objects.requireNonNull(edge).getFrom();
            String v = edge.getTo();

            // pick the unvisited vertex of this edge
            String newVertex = visited.contains(u) ? v : u;

            if (visited.contains(newVertex)) {
                continue; // both already visited
            }

            // accept the edge
            mstEdges.add(edge);
            visited.add(newVertex);

            // add all outgoing edges from the new vertex
            for (Edge e : adjacencyList.getOrDefault(newVertex, List.of())) {
                if (!visited.contains(e.getFrom()) || !visited.contains(e.getTo())) {
                    pq.add(e);
                }
            }
        }

        long end = System.nanoTime();
        double executionTimeMs = (end - start) / 1_000_000.0;
        int totalCost = mstEdges.stream().mapToInt(Edge::getWeight).sum();

        return new MSTResult(mstEdges, totalCost, operations, executionTimeMs);
    }

    private Map<String, List<Edge>> buildAdjacencyList(Graph graph) {
        Map<String, List<Edge>> adj = new HashMap<>();
        for (Edge e : graph.getEdges()) {
            adj.computeIfAbsent(e.getFrom(), k -> new ArrayList<>()).add(e);
            adj.computeIfAbsent(e.getTo(), k -> new ArrayList<>()).add(e);
        }
        return adj;
    }
}