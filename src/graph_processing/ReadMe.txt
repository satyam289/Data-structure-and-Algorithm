#Link Prediction in an Undirected Graph

(Standford SNAP Graph Processing Package)

Dataset:
https://www.cs.cornell.edu/~arb/data/contact-high-school/index.html

(U - E) is the set of nonexistent edges in Graph. It predicts 'K' important nonexistent edges based on ranking the elements of (U - E)
where U be the universal set and E is the set of edges

1. Common Neighbors (CN)
2. Adamic-Adar Score (AAS)
3. Katz's Score (KS)
Sort the edges by non-increasing value

Program Insight & Execution:

When we run this python script, it prompt the user to enter k(number of each maximum scores point),
It will display their scores and execution time of Sequential and Parallel and Improvement Result in terms of time.

Sequential:
 * First we construct the graph by reading the text file (which contain start vertex, destination vertex, weight associated) using SNAP Api(LoadEdgeList)
   There are 327 nodes in the graph. So, numbers of vertexs = 327 and number of Edges = 5818
 * To compute nonexistanceEdge, From all possible Edges, pick all the edges which is not there in the Graph.
 * For Each in loop, calulate CNScore, AAScore, KSCore using snap Apis like 
   graph.GetCmnNbrs, graph.CntDegNodes, graph.GetNodesAtHop

   Ref:http://snap.stanford.edu/snappy-1.1/

Parallel Approach : Using Pool Technique
   Same as above step but in Parallel
   # Cpu based pool configuration
   # parellelly fetch Non Existance Edge (Medium Effect)
   # parallelly scoring each Edge (Major Effect)
   # parallelly sorting of each metrics (Low Effect)
 
