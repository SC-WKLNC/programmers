package search.dfs_bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class 단어변환_권영찬 {

    public int solution(String begin, String target, String[] words) {

        int answer = 0;

        Graph graph = new Graph(words);
        for (int i = 0; i < words.length; i++) {
            for (int j = i; j < words.length; j++) {
                // TODO begin 처리 해야함
                if(compare(words[i], words[j])) {
                    graph.addEdge(words[i], words[j]);
                }
            }
        }

        return answer;
    }

    private boolean compare(String s1, String s2) {

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

        Map<String, Node> nodes;

        Graph(String[] words) {
            nodes = new HashMap<>((int)(words.length * 1.5));
            for (String s : words) {
                nodes.put(s, new Node(s));
            }
        }

        void addEdge(String s1, String s2) {

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
            LinkedList<Node> adjacent;

            Node(String id) {
                this.id = id;
                marked = false;
                adjacent = new LinkedList<>();
            }
        }
    }
}
