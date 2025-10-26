# **Design and Analysis of Algorithms: Assignment 3. Optimization of a City Transportation Network (Minimum Spanning Tree)**
## **Name: Dias Yestemes ----- Group: SE-2421**
### Introduction
The purpose of this project is to apply Prim’s and Kruskal’s algorithms to optimize a simulated city transportation network by finding the Minimum Spanning Tree (MST) — the minimal-cost set of roads connecting all districts.
This implementation analyzes performance, compares algorithm efficiency, and records both theoretical and practical differences in terms of execution time, operation count, and graph complexity.


### Input Files (JSON)


To test and compare both algorithms on different graph sizes and densities, several JSON input files were created programmatically using ChatGPT as generator.
Each file contains a list of weighted undirected graphs that simulate city districts and potential road connections.
The Structure for those inputs can be seen in this table:

| File                          | Graph Type  | Number of Graphs | Vertices per Graph | Weight Range |
|-------------------------------|-------------|------------------|--------------------|--------------|
| `ass_3_input_small.json`      | Small       | 5                | 5–10               | 1–30         |
| `ass_3_input_medium.json`     | Medium      | 10               | 10–30              | 10–100       |
| `ass_3_input_large.json`      | Large       | 10               | 30–100             | 100–500      |
| `ass_3_input_extralarge.json` | Extra Large | 3                | 100–200            | 500–1000     |

All input files were automatically generated with random yet reproducible structure, ensuring:
1) Every graph is connected
2) No negative or duplicate weights
3) Each dataset represents increasing graph complexity for a fair performance comparison


### Algorithm Results


In both theory and practice, Prim’s and Kruskal’s algorithms have comparable 
asymptotic complexity — approximately 
O(ElogV) for dense graphs.
However, the observed results in this project consistently show that 
Kruskal’s algorithm outperforms Prim’s algorithm in terms of 
execution time and operation count across multiple test cases.

**Theoretical Efficiency (General)**

| Algorithm     | Typical Time Complexity        | Main Data Structures                    | Theoretical Strength   |
| ------------- | ------------------------------ | --------------------------------------- | ---------------------- |
| **Prim’s**    | (O(E \log V)) using a heap     | Priority Queue (Min Heap), Visited Set  | Best for dense graphs  |
| **Kruskal’s** | (O(E \log E)) or (O(E \log V)) | Edge sorting, Disjoint Set (Union-Find) | Best for sparse graphs |


**Kruskal’s Algorithm**

1) Sorts all edges once and then uses an efficient Union-Find data structure to merge sets.

2) The find and union operations are nearly
O(1) with path compression.

3) Loops are simple and predictable, minimizing branching and object overhead.

4) Memory access patterns are sequential, making it cache-efficient and fast in Java.

**Prim’s Algorithm**

1) Uses a PriorityQueue (min-heap) and a set of visited vertices.

2) After adding each vertex to the MST, the algorithm loops through all edges in 
the graph to find those connected to the new vertex, which 
results in multiple redundant edge scans, effectively increasing runtime to around
O(V×E) in practice.

3) Java’s PriorityQueue also involves frequent object allocations and Comparator calls, 
adding extra overhead compared to Kruskal’s lightweight union-find operations.

Lamb (2023) explains that Kruskal’s algorithm tends to perform better on sparse graphs 
because it requires sorting fewer edges and performs nearly constant-time union–find 
operations, whereas Prim’s algorithm becomes more efficient on dense graphs since it does 
not need to examine all edges globally. This aligns with the results observed in our 
experiments, where Kruskal’s algorithm consistently executed faster on graphs with 
relatively low edge density.

JSON Database Outputs show that Kruskal’s algorithm had shorter execution times and 
required fewer operations than Prim’s algorithm for all test graphs.
This is consistent with Lamb’s (2023) findings that Kruskal’s approach benefits from 
simple iteration over sorted edges, while Prim’s implementation incurs overhead due to 
frequent priority-queue updates and repeated edge scans.

***Conclusion: The performance loss in Prim’s algorithm stems from repeated 
full-edge scans and expensive priority queue operations, not from incorrect logic.***


### Summary


- Kruskal’s algorithm is faster in your current implementation 
because it performs fewer iterations and uses simpler data structures.

- Prim’s algorithm could be optimized to achieve better performance, 
especially for dense graphs, by improving how connected edges are retrieved and managed.

- Both algorithms produce the same MST cost, proving correctness — the difference lies purely 
in implementation and data structure efficiency.

  
### References


- Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2009). 
Introduction to algorithms (3rd ed.). MIT Press.

- GeeksforGeeks. (2024, May 14). Prim’s algorithm (greedy algorithm). 
GeeksforGeeks. https://www.geeksforgeeks.org/prims-algorithm-greedy-algo-5/

- GeeksforGeeks. (2024, May 14). Kruskal’s algorithm (minimum spanning tree). 
GeeksforGeeks. https://www.geeksforgeeks.org/kruskals-algorithm-simple-implementation-for-adjacency-matrix/

- Lamb, J. (2023, April 11). Kruskal’s vs Prim’s Algorithm. 
Baeldung. https://www.baeldung.com/cs/kruskals-vs-prims-algorithm