package Programmers;
import java.util.*;

public class 가장_먼_노드 {
	class Solution {
	    static List<ArrayList<Integer>> graph;
	    static boolean[] visited;
	    static int answer;
	    public int solution(int n, int[][] edge) {
	        answer = 0;
	        graph = new ArrayList<>();
	        for(int i = 0;  i <= n; i++) {
	            graph.add(new ArrayList<>());
	        }
	        
	        for(int i = 0; i < edge.length; i++) {
	            graph.get(edge[i][0]).add(edge[i][1]);
	            graph.get(edge[i][1]).add(edge[i][0]);
	        }
	        
	        visited = new boolean[n+1];
	        bfs();
	        
	        return answer;
	    }
	    
	    static void bfs() {
	        Queue<int[]> q = new LinkedList<>();
	        q.add(new int[] {1, 0});
	        visited[1] = true;
	        int max = 0;
	        
	        while(!q.isEmpty()) {
	            int[] now = q.poll();
	            int v = now[0];
	            int dist = now[1];
	            
	            if(max == dist) answer++;
	            else if(max < dist) {
	                max = dist;
	                answer = 1;
	            }
	            
	            for(int i = 0; i < graph.get(v).size(); i++) {
	                int next = graph.get(v).get(i);
	                if(!visited[next]) {
	                    q.add(new int[] {next, dist+1});
	                    visited[next] = true;
	                }
	            }
	        }
	    }
	}
}
