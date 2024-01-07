package Programmers;

import java.util.*;
public class 합승_택시_요금 {
	class Solution {
	    static int[][] graph;
	    static int answer, N, S, A, B;
	    static int[] ds;
	    public int solution(int n, int s, int a, int b, int[][] fares) {
	        answer = Integer.MAX_VALUE; N = n;
	        graph = new int[n][n];
	        
	        for(int i = 0; i < fares.length; i++) {
	            int t1 = fares[i][0]-1;
	            int t2 = fares[i][1]-1;
	            int w = fares[i][2];
	            
	            graph[t1][t2] = w;
	            graph[t2][t1] = w;
	        }
	        
	        ds = dijkstra(s-1);
	        for(int i = 0; i < n; i++) {
	            int[] tmp = dijkstra(i);
	            answer = Math.min(answer, ds[i]+tmp[a-1]+tmp[b-1]);
	        }
	        
	        return answer;
	    }
	    
	    static int[] dijkstra(int start) {
	        boolean[] visited = new boolean[N];
	        int[] d = new int[N];
	        Arrays.fill(d, Integer.MAX_VALUE);
	        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
	        pq.add(new int[] {start, 0});
	        d[start] = 0;
	        
	        while(!pq.isEmpty()) {
	            int[] now = pq.poll();
	            int v = now[0];
	            int w = now[1];
	            
	            // if(d[v] < w) continue; 이거랑 visited랑 뭐가다름
	            if(visited[v]) continue;
	            visited[v] = true;
	            
	            for(int i = 0; i < N; i++) {
	                if(graph[v][i] == 0) continue;
	                
	                if(graph[v][i] + w < d[i]) {
	                    d[i] = graph[v][i] + w;
	                    pq.offer(new int[] {i, d[i]});
	                }
	            }
	        }
	        return d;
	    }
	}
}
