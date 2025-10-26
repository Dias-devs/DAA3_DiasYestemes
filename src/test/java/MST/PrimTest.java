package MST;

import MST.Algorithms.PrimAlgorithm;
import MST.Model.Edge;
import MST.Model.Graph;
import MST.Model.Output.MSTResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrimTest {

    @Test
    void primProducesMST() {
        // sample graph (same as example)
        var nodes = List.of("A","B","C","D","E");
        var edges = List.of(
                new Edge("A","B",4),
                new Edge("A","C",3),
                new Edge("B","C",2),
                new Edge("B","D",5),
                new Edge("C","D",7),
                new Edge("C","E",8),
                new Edge("D","E",6)
        );
        Graph g = new Graph(1, nodes, edges);
        PrimAlgorithm prim = new PrimAlgorithm();
        MSTResult res = prim.findMST(g);

        assertNotNull(res);
        assertEquals(g.vertexCount() - 1, res.getMstEdges().size());
        assertEquals(16, res.getTotalCost()); // from earlier example
    }
}
