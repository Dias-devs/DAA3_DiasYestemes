package MST.Model.Output;

import MST.Model.Edge;

import java.util.List;

public class MSTResult {
    private final List<Edge> mstEdges;
    private final long totalCost;
    private final long operationsCount;
    private final double executionTimeMs;

    public MSTResult(List<Edge> mstEdges, long totalCost, long operationsCount, double executionTimeMs) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.operationsCount = operationsCount;
        this.executionTimeMs = executionTimeMs;
    }

    public List<Edge> getMstEdges() { return mstEdges; }
    public long getTotalCost() { return totalCost; }
    public long getOperationsCount() { return operationsCount; }
    public double getExecutionTimeMs() { return executionTimeMs; }
}
