# singleSrcMinDistance

Dijkstra：适用于权值为非负的图的单源最短路径，复杂度O((E+V)log V)

BellmanFord：适用于权值有负值边的图的单源最短路径，并且能够检测负圈，缺点是复杂度高，O(VE)

SPFA：适用于权值有负值， 队列优化版的BellmanFord，可以检测负圈，但无法处理带负环的图，最坏情况O(VE)，但通常效率比BellmanFord高
