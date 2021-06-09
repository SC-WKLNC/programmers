package search.dfs_bfs;

import java.util.*;

public class 여행경로_권영찬 {

    static class Test {
        public static void main(String[] args) {
            String[][] ticket = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
            final String[] solution = new 여행경로_권영찬().solution(ticket);
        }
    }

    public String[] solution(String[][] tickets) {
        String[] answer = {};

        // 1. 노드에 담길 정보 : 공항이름, 방문여부, 갈수있는 공항 리스트, 경로가 담긴 리스트
        // 2. 모든 노드를 해시맵에 저장
        // 3. 같은 출발지인 노드의 경우 기존 노드에 추가
        // 4. dfs 로 모든 공항을 방문했을 경우에만 경로를 리스트에 추가
        // 5. dfs 가 끝났을 경우 경로를 순회해 알파벳 순서가 앞서는 경로를 return

        Graph graph = new Graph(tickets);
        final List<List<String>> dfs = graph.dfs();
        // TODO 알파벳 순으로 가장 앞서는 리스트만 배열로 반환

        System.out.println(dfs.size());

        return answer;
    }

    static class Graph {

        final String ROOT = "ICN";
        final int maxSize;
        final Map<String, Node> nodes;
        final List<String> pathList;
        final List<List<String>> resultPathList;

        Graph(String[][] tickets) {
            maxSize = tickets.length;
            nodes = new HashMap<>();
            pathList = new ArrayList<>();
            resultPathList = new ArrayList<>();
            for (String[] ticket : tickets) {
                final String start = ticket[0];
                final String end = ticket[1];
                Node n1;
                Node n2;
                if (nodes.get(start) == null) {
                    n1 = new Node(start);
                } else {
                    n1 = nodes.get(start);
                }
                if (nodes.get(end) == null) {
                    n2 = new Node(end);
                } else {
                    n2 = nodes.get(end);
                }
                n1.add(n2);
            }
        }

        List<List<String>> dfs() {
            dfs(nodes.get((ROOT)));
            return resultPathList;
        }

        private void dfs(Node root) {
            if(root == null) return;
//            root.visited();
            for (Node r : root.adjacent) {
                if(!r.marked) {
                    root.visit(r);
                    dfs(r);
                }
            }
        }

        class Node {

            private final String id;
            private boolean marked;
            private final LinkedList<Node> adjacent;
            int visitCount = 0;

            Node(String id) {
                this.id = id;
                this.marked = false;
                this.adjacent = new LinkedList<>();
            }

            void add(Node node) {
                adjacent.add(node);
                visitCount++;
            }

            void visited() {
                if(--visitCount < 1) {
                    marked = true;
                }
                pathList.add(id);
                if(adjacent.size() == 0) {
                    if(pathList.size() == maxSize) {
                        resultPathList.add(new ArrayList<>(pathList));
                    }
                    removeCurrPath();
                }
            }

            void visit(Node node) {
                node.visited();
                adjacent.remove(node);
                if(adjacent.size() < 1) marked = true;
            }

            void removeCurrPath() {
                pathList.remove(pathList.size()-1);
            }
        }
    }
}
