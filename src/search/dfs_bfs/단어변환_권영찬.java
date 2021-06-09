package search.dfs_bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class 단어변환_권영찬 {

    static class Test {
        public static void main(String[] args) {
            String begin1 = "hit";
            String target1 = "cog";
            String[] words1 = {"hot", "dot", "dog", "lot", "log", "cog"};
            int expect1 = 4;
            int result1 = new 단어변환_권영찬().solution(begin1, target1, words1);

            String begin2 = "hit";
            String target2 = "cog";
            String[] words2 = {"hot", "dot", "dog", "lot", "log"};
            int expect2 = 0;
            int result2 = new 단어변환_권영찬().solution(begin2, target2, words2);

            System.out.println("1 : expect(" + expect1 + ") result(" + result1 + ") " + (expect1 == result1));
            System.out.println("2 : expect(" + expect2 + ") result(" + result2 + ") " + (expect2 == result2));
        }
    }

    public int solution(String begin, String target, String[] words) {

        Graph graph = new Graph(begin, target, words);
        for (int i = 0; i < words.length; i++) {
            for (int j = i; j < words.length; j++) {
                if(compare(words[i], words[j])) {
                    graph.addEdge(words[i], words[j]);
                }
            }
            if(compare(begin, words[i])) {
                graph.addEdge(begin, words[i]);
            }
        }
        return graph.bfs();
    }

    private boolean compare(String s1, String s2) { // 각 단어의 비교 메서드

        int diffCount = 0;
        for (int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                if(++diffCount > 1)
                    return false;
            }
        }
        return diffCount == 1;
    }

    static class Graph {

        String begin;
        String target;
        Map<String, Node> nodes; // 노드의 String 으로 접근하기에 HashMap 사용

        Graph(String begin, String target , String[] words) {
            this.begin = begin;
            this.target = target;
            this.nodes = new HashMap<>((int)(words.length * 1.5)); // 해시 버킷 개수를 지정. 임계점(0.75)을 넘기지 않게 하기위해 1.5를 곱합
            for (String s : words) { // 모든 노드를 그래프에 추가
                nodes.put(s, new Node(s));
            }
            nodes.put(begin, new Node(begin));
        }

        int bfs() { // 넓이 우선 탐색
            Node root = nodes.get(begin);
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            root.marked = true;
            while(!queue.isEmpty()) {
                Node r = queue.poll();
                if(r.id.equals(target)) { // 각 노드에 데이터를 증가시켜 target 과 일치하는 가장 작은 노드의 값을 리턴
                    return r.depth;
                }
                for(Node n : r.adjacent) {
                    if(!n.marked) {
                        r.visit(n); // 다음 노드를 방문할 경우 깊이를 (방문자 노드의 깊이 + 1) 로 지정
                        queue.add(n);
                    }
                }
            }
            return 0; // 변환할 수 없는 경우 0 리턴
        }

        void addEdge(String s1, String s2) { // 간선 연결

            Node n1 = nodes.get(s1);
            Node n2 = nodes.get(s2);

            if(!n1.adjacent.contains(n2)) {
                n1.adjacent.add(n2);
            }
            if(!n2.adjacent.contains(n1)) {
                n2.adjacent.add(n1);
            }
        }

        static class Node {

            String id;
            boolean marked;
            int depth;
            LinkedList<Node> adjacent;

            Node(String id) {
                this.id = id;
                marked = false;
                depth = 0;
                adjacent = new LinkedList<>();
            }

            void visit(Node node) { // 방문 처리
                node.marked = true;
                node.depth = depth + 1;
            }
        }
    }
}
