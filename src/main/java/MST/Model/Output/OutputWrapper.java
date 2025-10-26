package MST.Model.Output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OutputWrapper {
    private final List<OutputEntry> results;

    public OutputWrapper(@JsonProperty("results") List<OutputEntry> results) {
        this.results = results;
    }

    public List<OutputEntry> getResults() { return results; }
}