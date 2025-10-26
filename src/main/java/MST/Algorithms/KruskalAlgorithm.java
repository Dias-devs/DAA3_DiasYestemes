package MST.Algorithms;

import MST.Model.Edge;
import MST.Model.Graph;
import MST.Model.Output.MSTResult;
import MST.Utils.DisjointSet;
import MST.Utils.Timer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KruskalAlgorithm {

    public MSTResult findMST(Graph graph) {
        Timer timer = new Timer();
        timer.start();

        long ops = 0; // operation counter (comparisons, unions)

        List<Edge> edges = new ArrayList<>(graph.getEdges());
        // sort edges by weight
        Collections.sort(edges, Comparator.comparingInt(Edge::getWeight));
        ops += edges.size() * (long)Math.log(Math.max(2, edges.size())); // approximate cost accounted

        DisjointSet<String> ds = new DisjointSet<>();
        ds.makeSet(graph.getNodes());

        List<Edge> mst = new ArrayList<>();
        long totalCost = 0L;
        for (Edge e : edges) {
            ops++; // comparison for union-find
            String u = e.getFrom(), v = e.getTo();
            String ru = ds.find(u), rv = ds.find(v);
            ops += 2; // two finds
            if (!ru.equals(rv)) {
                ds.union(ru, rv);
                ops++; // union
                mst.add(e);
                totalCost += e.getWeight();
                if (mst.size() == graph.vertexCount() - 1) break;
            }
        }

        double timeMs = timer.elapsedMillis();
        return new MSTResult(mst, totalCost, ops, timeMs);
    }
}