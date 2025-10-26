package MST.io;

import MST.Model.Edge;
import MST.Model.Graph;
import MST.Model.Output.MSTResult;
import MST.Model.Output.OutputEntry;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsonWriter {

    private final ObjectMapper mapper;

    public JsonWriter() {
        this.mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void writeResults(String outputPath, List<OutputEntry> results) throws IOException {
        Map<String, Object> root = new LinkedHashMap<>();
        root.put("results", results);

        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(outputPath), root);
    }

    public static Map<String, Object> mstResultToMap(MSTResult result) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Map<String, Object>> edges = new ArrayList<>();

        for (Edge e : result.getMstEdges()) {
            Map<String, Object> edgeObj = new LinkedHashMap<>();
            edgeObj.put("from", e.getFrom());
            edgeObj.put("to", e.getTo());
            edgeObj.put("weight", e.getWeight());
            edges.add(edgeObj);
        }

        map.put("mst_edges", edges);
        map.put("total_cost", result.getTotalCost());
        map.put("operations_count", result.getOperationsCount());
        map.put("execution_time_ms",
                Math.round(result.getExecutionTimeMs() * 100.0) / 100.0);
        return map;
    }

    public static Map<String, Integer> inputStatsMap(Graph g) {
        Map<String, Integer> stats = new LinkedHashMap<>();
        stats.put("vertices", g.vertexCount());
        stats.put("edges", g.edgeCount());
        return stats;
    }
}