package MST.io;

import MST.Model.Output.MSTResult;
import MST.Model.Output.OutputEntry;
import MST.Model.Output.OutputWrapper;
import MST.Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsonWriter {
    private final ObjectMapper mapper;

    public JsonWriter() {
        this.mapper = new ObjectMapper();
        // pretty print (similar style to your example)
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void writeResults(String outputPath, List<OutputEntry> results) throws IOException {
        OutputWrapper wrapper = new OutputWrapper(results);
        mapper.writeValue(new File(outputPath), wrapper);
    }

    public static Map<String,Object> mstResultToMap(MSTResult result) {
        Map<String,Object> map = new LinkedHashMap<>();
        // convert edges to simple maps
        List<Map<String,Object>> edges = new ArrayList<>();
        for (var e : result.getMstEdges()) {
            Map<String,Object> em = new LinkedHashMap<>();
            em.put("from", e.getFrom());
            em.put("to", e.getTo());
            em.put("weight", e.getWeight());
            edges.add(em);
        }
        map.put("mst_edges", edges);
        map.put("total_cost", result.getTotalCost());
        map.put("operations_count", result.getOperationsCount());
        map.put("execution_time_ms", result.getExecutionTimeMs());
        return map;
    }

    public static Map<String,Integer> inputStatsMap(Graph g) {
        Map<String,Integer> m = new LinkedHashMap<>();
        m.put("vertices", g.vertexCount());
        m.put("edges", g.edgeCount());
        return m;
    }
}