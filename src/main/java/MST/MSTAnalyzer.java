package MST;

import MST.Algorithms.KruskalAlgorithm;
import MST.Algorithms.PrimAlgorithm;
import MST.Model.Graph;
import MST.Model.Output.MSTResult;
import MST.Model.Output.OutputEntry;
import MST.io.JsonReader;
import MST.io.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MSTAnalyzer {

    private final PrimAlgorithm prim = new PrimAlgorithm();
    private final KruskalAlgorithm kruskal = new KruskalAlgorithm();
    private final JsonReader reader = new JsonReader();

    public List<OutputEntry> analyzeFile(String inputPath) throws IOException {
        List<Graph> graphs = reader.readGraphs(inputPath);
        List<OutputEntry> results = new ArrayList<>();

        for (Graph g : graphs) {
            MSTResult primResult = prim.findMST(g);
            MSTResult kruskalResult = kruskal.findMST(g);

            Map<String, Object> primMap = JsonWriter.mstResultToMap(primResult);
            Map<String, Object> kruskalMap = JsonWriter.mstResultToMap(kruskalResult);
            Map<String, Integer> statsMap = JsonWriter.inputStatsMap(g);

            OutputEntry entry = new OutputEntry(
                    g.getId(),
                    statsMap,
                    primMap,
                    kruskalMap
            );
            results.add(entry);
        }

        return results;
    }

    public void runAndWrite(String inputPath, String outputPath) throws IOException {
        List<OutputEntry> results = analyzeFile(inputPath);
        new JsonWriter().writeResults(outputPath, results);
    }
}