package util;
import java.util.*;

class Edge {
    int destination;
    int weight;

    Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph {
    List<List<Edge>> adjacencyList;

    Graph(int numVertices) {
        adjacencyList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    void addEdge(int source, int destination, int weight) {
        adjacencyList.get(source).add(new Edge(destination, weight));
        // 如果是无向图，添加这行
        // adjacencyList.get(destination).add(new Edge(source, weight));
    }

    // Dijkstra算法
    void dijkstra(int start) {
        int numVertices = adjacencyList.size();
        int[] distances = new int[numVertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(start, 0));

        //逻辑在于 使用优先队列，保证每一次计算时取出的都是当前已知的离源点最短的最短路径，最终可以得到
        //单源最短路径(因为后面的较长的路径不会覆盖前面已经形成的较短路径)
        boolean[] visited = new boolean[numVertices];
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentVertex = current.destination;

            // 如果当前顶点已经被访问过，跳过
            if (visited[currentVertex]) continue;
            visited[currentVertex] = true;

            for (Edge neighbor : adjacencyList.get(currentVertex)) {
                int newDist = distances[currentVertex] + neighbor.weight;

                if (newDist < distances[neighbor.destination]) {
                    distances[neighbor.destination] = newDist;
                    pq.add(new Edge(neighbor.destination, newDist));
                }
            }
        }

        // 输出最短路径
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Distance from " + (start + 1) + " to " + (i+1) + " is " + distances[i]);
        }
    }
}

public class Dijkstra {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 12);
        graph.addEdge(1, 2, 9);
        graph.addEdge(1, 3, 3);
        graph.addEdge(3, 2, 4);
        graph.addEdge(2, 4, 5);
        graph.addEdge(3, 4, 13);
        graph.addEdge(3, 5, 15);
        graph.addEdge(4, 5, 4);

        graph.dijkstra(0); // 从节点0出发计算最短路径
    }
}
