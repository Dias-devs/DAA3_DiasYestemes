package MST.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Graph {
    private final int id;
    private final List<String> nodes;
    private final List<Edge> edges;

    @JsonCreator
    public Graph(@JsonProperty("id") int id,
                 @JsonProperty("nodes") List<String> nodes,
                 @JsonProperty("edges") List<Edge> edges) {
        this.id = id;
        this.nodes = nodes != null ? nodes : new ArrayList<>();
        this.edges = edges != null ? edges : new ArrayList<>();
    }

    public int getId() { return id; }
    public List<String> getNodes() { return Collections.unmodifiableList(nodes); }
    public List<Edge> getEdges() { return Collections.unmodifiableList(edges); }

    // adjacency map (undirected)
    public Map<String, List<Edge>> adjacencyMap() {
        Map<String, List<Edge>> adj = new HashMap<>();
        for (String n : nodes) adj.put(n, new ArrayList<>());
        for (Edge e : edges) {
            adj.computeIfAbsent(e.getFrom(), k -> new ArrayList<>()).add(e);
            adj.computeIfAbsent(e.getTo(), k -> new ArrayList<>()).add(e);
        }
        return adj;
    }

    public int vertexCount() { return nodes.size(); }
    public int edgeCount() { return edges.size(); }
}