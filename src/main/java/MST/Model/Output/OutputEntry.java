package MST.Model.Output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class OutputEntry {
    private final int graphId;
    private final Map<String,Integer> inputStats;
    private final Map<String, Object> prim;
    private final Map<String, Object> kruskal;

    public OutputEntry(@JsonProperty("graph_id") int graphId,
                       @JsonProperty("input_stats") Map<String,Integer> inputStats,
                       @JsonProperty("prim") Map<String,Object> prim,
                       @JsonProperty("kruskal") Map<String,Object> kruskal) {
        this.graphId = graphId;
        this.inputStats = inputStats;
        this.prim = prim;
        this.kruskal = kruskal;
    }

    public int getGraphId() { return graphId; }
    public Map<String,Integer> getInputStats() { return inputStats; }
    public Map<String,Object> getPrim() { return prim; }
    public Map<String,Object> getKruskal() { return kruskal; }
}