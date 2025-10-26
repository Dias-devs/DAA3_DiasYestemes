package MST.Model.Input;

import MST.Model.Graph;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InputWrapper {
    private final List<Graph> graphs;

    @JsonCreator
    public InputWrapper(@JsonProperty("graphs") List<Graph> graphs) {
        this.graphs = graphs;
    }

    public List<Graph> getGraphs() { return graphs; }
}