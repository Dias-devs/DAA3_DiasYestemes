package MST.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Edge implements Comparable<Edge> {
    private final String from;
    private final String to;
    private final int weight;

    @JsonCreator
    public Edge(@JsonProperty("from") String from,
                @JsonProperty("to") String to,
                @JsonProperty("weight") int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String getFrom() { return from; }
    public String getTo() { return to; }
    public int getWeight() { return weight; }

    public String other(String node) {
        if (from.equals(node)) return to;
        if (to.equals(node)) return from;
        return null;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return String.format("{\"from\":\"%s\",\"to\":\"%s\",\"weight\":%d}", from, to, weight);
    }
}