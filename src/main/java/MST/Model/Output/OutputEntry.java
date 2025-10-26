package MST.Model.Output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Map;

@JsonPropertyOrder({ "graph_id", "input_stats", "prim", "kruskal" })
public class OutputEntry {

    @JsonProperty("graph_id")
    private final int graphId;

    @JsonProperty("input_stats")
    private final Map<String, Integer> inputStats;

    @JsonProperty("prim")
    private final Map<String, Object> prim;

    @JsonProperty("kruskal")
    private final Map<String, Object> kruskal;

    public OutputEntry(int graphId,
                       Map<String, Integer> inputStats,
                       Map<String, Object> prim,
                       Map<String, Object> kruskal) {
        this.graphId = graphId;
        this.inputStats = inputStats;
        this.prim = prim;
        this.kruskal = kruskal;
    }

    public int getGraphId() { return graphId; }
    public Map<String, Integer> getInputStats() { return inputStats; }
    public Map<String, Object> getPrim() { return prim; }
    public Map<String, Object> getKruskal() { return kruskal; }
}