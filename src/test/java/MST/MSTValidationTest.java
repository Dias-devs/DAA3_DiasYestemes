package MST;

import MST.Algorithms.KruskalAlgorithm;
import MST.Algorithms.PrimAlgorithm;
import MST.Model.Edge;
import MST.Model.Graph;
import MST.Model.Output.MSTResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MSTValidationTest {

    @Test
    void primAndKruskalReturnSameCost() {
        var nodes = List.of("A","B","C","D");
        var edges = List.of(
                new Edge("A","B",1),
                new Edge("A","C",4),
                new Edge("B","C",2),
                new Edge("C","D",3),
                new Edge("B","D",5)
        );
        Graph g = new Graph(2, nodes, edges);

        PrimAlgorithm p = new PrimAlgorithm();
        KruskalAlgorithm k = new KruskalAlgorithm();

        MSTResult rp = p.findMST(g);
        MSTResult rk = k.findMST(g);

        assertEquals(rp.getTotalCost(), rk.getTotalCost());
        assertEquals(g.vertexCount() - 1, rp.getMstEdges().size());
        assertEquals(g.vertexCount() - 1, rk.getMstEdges().size());
    }
}