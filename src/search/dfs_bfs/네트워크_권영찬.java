package search.dfs_bfs;

import java.util.LinkedList;

public class 네트워크_권영찬 {

    static class Test {
        public static void main(String[] args) {

            int n1 = 3;
            int[][] arr1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
            int expect1 = 2;
            int result1 = new 네트워크_권영찬().solution(n1, arr1);

            int n2 = 3;
            int[][] arr2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
            int expect2 = 1;
            int result2 = new 네트워크_권영찬().solution(n2, arr2);

            System.out.println("1 : " + (expect1 == result1));
            System.out.println("2 : " + (expect2 == result2));

        }
    }


    public int solution(int n, int[][] computers) {

        int answer = 0;

        Graph graph = new Graph(n);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if(computers[i][j] == 1)
                    graph.addEdge(i, j);
            }
        }
        for (int i = 0; i < n; i++) {
            Node node = graph.nodes[i];
            if(!node.marked) {
                graph.dfs(node);
                answer++;
            }
            if(graph.visitCount == n) break;
        }
        return answer;
    }

    static class Graph {

        private int visitCount = 0;

        Node[] nodes;

        Graph(int size) {
            nodes = new Node[size];
            for (int i = 0; i < size; i++) {
                nodes[i] = new Node(i);
            }
        }

        void addEdge(int i1, int i2) {
            if(i1 == i2) return;
            Node n1 = nodes[i1];
            Node n2 = nodes[i2];
            if(!n1.adjacent.contains(n2)) n1.adjacent.add(n2);
            if(!n2.adjacent.contains(n1)) n2.adjacent.add(n1);
        }

        void dfs(Node r) {
            if(r == null) return;
            r.marked = true;
            visitCount++;
            for(Node n : r.adjacent) if(!n.marked) dfs(n);
        }
    }

    static class Node {

        int id;
        boolean marked;
        LinkedList<Node> adjacent;

        Node(int id) {
            this.id = id;
            this.marked = false;
            this.adjacent = new LinkedList<>();
        }
    }
}
