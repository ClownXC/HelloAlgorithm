package com.algorithm.graph.learning;

import java.util.*;

public class HelloGraph {


    public static void main(String[] args) {

        int n = 8;
        String v[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Graph graph = new Graph(n);
        for (String item : v) {
            graph.insertVertex(item);
        }

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 5);
        graph.insertEdge(1, 3, 6);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 6);
        graph.insertEdge(4, 7, 6);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        graph.show();
//        // 1 2 4 8 5 3 6 7
        graph.DFS();
        // 1 2 3 4 5 6 7 8
        graph.BFS();
//        graph.DFSNoRecursive();
    }

}


class Graph {

    private List<String> vertexList;
    private int[][] edges;
    private int numOfEdges;

    /**
     * 记录节点是否已被遍历
     */
    private boolean[] visited;


    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        visited = new boolean[n];
    }


    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * @param v1
     * @param v2
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;

        numOfEdges++;

    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public String getValue(int i) {
        return vertexList.get(i);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }


    /**
     * 打印遍历节点
     */
    public void visit(int i) {
        System.out.print(vertexList.get(i) + " ");
    }


    /**
     * 从第i节点开始深度优先遍历
     */
    public void traverse(int i) {
        // 标记第i个节点已遍历
        visited[i] = true;
        // 打印当前已经遍历的节点
        visit(i);
        // 遍历邻接矩阵中第i个节点的直接连通节点
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[i][j] != 0 && visited[j] == false) {
                traverse(j);
            }
        }
    }

    public void DFS() {
        // 初始化节点访问标记
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        // 从没有被遍历的节点开始深度优先遍历
        for (int i = 0; i < vertexList.size(); i++) {
            // 如果没有被访问过.
            if (visited[i] == false) {
                traverse(i);
            }
        }
        // 输出二维矩阵
    }


    public void DFSNoRecursive() {
        // 初始化所有的节点的访问标志
        for (int v = 0; v < visited.length; v++) {
            visited[v] = false;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < vertexList.size(); i++) {
            if (visited[i] == false) {
                visit(i);
                visited[i] = true;
                stack.push(i);
            }
            while (! stack.isEmpty()) {
                // 当前出栈的节点
                int k = stack.pop();
                for (int j = 0; j < vertexList.size(); j++) {
                    // 如果是相邻的节点且没有访问过.
                    if (edges[k][j] == 1 && visited[j] == false) {
                        visited[j] = true;
                        stack.push(j);
                        visit(j);
                        // 这条路结束,返回上一个节点.
                        break;
                    }
                }

            }
        }
        System.out.println();
    }






    /**
     * 实现广度优先遍历
     */
    public void BFS() {
        // 初始化所有的节点的访问标志
        for (int v = 0; v < visited.length; v++) {
            visited[v] = false;
        }
        Queue<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i < vertexList.size(); i++) {
            if (visited[i] == false) {
                visited[i] = true;
                // 打印当前已经遍历的节点
                visit(i);
                // 添加到队列里面
                queue.add(i);
                // 只要队列不为空
                while (!queue.isEmpty()) {
                    // 出队节点,也就是这一层的节点.
                    int k = queue.poll();
                    // 遍历所有未被访问的邻接节点,放入队列
                    for (int j = 0; j < vertexList.size(); j++) {
                        // 也就是访问这一层剩下的未被访问的节点
                        if (edges[k][j] != -1 && visited[j] == false) {
                            visited[j] = true;
                            visit(j);
                            queue.add(j);
                        }
                    }
                }
            }
        }
        System.out.println();
    }









    public void show() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }


}
