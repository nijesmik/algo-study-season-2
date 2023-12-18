import java.util.*;
class Solution {

    public int solution(int n, int[][] edge) {

        List<Integer>[] adjList = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int[] e : edge) {
            int a = e[0];
            int b = e[1];
            adjList[a].add(b);
            adjList[b].add(a);
        }

        int[] minEdges = new int[n+1];
        Arrays.fill(minEdges,Integer.MAX_VALUE);
        boolean[] visited = new boolean[n+1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1]-b[1]);

        pq.add(new int[] {1,0});

        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            int nodeNum = now[0];
            if(visited[now[0]]) {
                continue;
            }
            int cost = now[1];
            visited[nodeNum] = true;
            minEdges[nodeNum] = cost;


            adjList[nodeNum].forEach(e-> {
                if(visited[e])
                    return;
                pq.add(new int[] {e,cost+1});
            });
        }
        int max = 0;
        for(int i = 1; i<n+1; i++) {
            if(minEdges[i] == Integer.MAX_VALUE) {
                continue;
            }
            max = Math.max(max, minEdges[i]);
        }

        int cnt = 0;

        for(int i = 1; i< n+1; i++) {
            if(minEdges[i] == max) {
                cnt++;
            }
        }

        return cnt;








    }
}