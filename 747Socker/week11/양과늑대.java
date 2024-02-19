package jechul;

import java.util.*;

class Solution {
    static class Node {
        int pos;
        int sheepCnt;
        int wolfCnt;
        Set<Integer> visited;
        
        public Node(int pos, int sheepCnt, int wolfCnt, Set<Integer> visited) {
            this.pos = pos;
            this.sheepCnt = sheepCnt;
            this.wolfCnt = wolfCnt;
            this.visited = new HashSet<>(visited);
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < info.length; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        
        Queue<Node> queue = new LinkedList<>();
        int sheep = info[0] == 0 ? 1 : 0;
        int wolf = info[0] == 1 ? 1 : 0;

        Set<Integer> chk = new HashSet<>();
        chk.add(0);
        queue.offer(new Node(0, sheep, wolf, chk));
        
        int maxSheep = 0;
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            maxSheep = Math.max(maxSheep, current.sheepCnt);
            
            for (int next : graph.get(current.pos)) {
                if (!current.visited.contains(next)) {
                    Set<Integer> nextVisited = new HashSet<>(current.visited);
                    nextVisited.add(next); 
                    int nextSheep = current.sheepCnt + (info[next] == 0 ? 1 : 0);
                    int nextWolf = current.wolfCnt + (info[next] == 1 ? 1 : 0);
                    if (nextSheep > nextWolf) {
                        queue.offer(new Node(next, nextSheep, nextWolf, nextVisited));
                    }
                }
            }
        }
        
        return maxSheep;
    }
}
