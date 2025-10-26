package MST.Algorithms;

import MST.Model.Edge;
import MST.Model.Graph;
import MST.Model.Output.MSTResult;
import MST.Utils.Timer;

import java.util.*;

public class PrimAlgorithm {

    public MSTResult findMST(Graph graph) {
        Timer timer = new Timer();
        timer.start();

        long ops = 0;

        if (graph.vertexCount() == 0) {
            return new MSTResult(Collections.emptyList(), 0, ops, timer.elapsedMillis());
        }

        Map<String, List<Edge>> adj = graph.adjacencyMap();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        List<Edge> mst = new ArrayList<>();
        long totalCost = 0L;

        // start from first node
        String start = graph.getNodes().getFirst();
        visited.add(start);
        if (adj.get(start) != null) {
            pq.addAll(adj.get(start));
            ops += adj.get(start).size();
        }

        while (!pq.isEmpty() && mst.size() < graph.vertexCount() - 1) {
            Edge e = pq.poll();
            ops++; // poll cost
            String u = Objects.requireNonNull(e).getFrom();
            String v = e.getTo();
            String next = visited.contains(u) ? v : u;
            if (visited.contains(next)) {
                // both ends visited -> skip
                ops++;
                continue;
            }
            // accept edge
            mst.add(e);
            totalCost += e.getWeight();
            visited.add(next);
            ops++; // acceptance

            List<Edge> edges = adj.get(next);
            if (edges != null) {
                for (Edge out : edges) {
                    // add edges that lead to unvisited nodes
                    if (!visited.contains(out.other(next))) {
                        pq.add(out);
                        ops++;
                    }
                }
            }
        }

        double timeMs = timer.elapsedMillis();
        return new MSTResult(mst, totalCost, ops, timeMs);
    }
}