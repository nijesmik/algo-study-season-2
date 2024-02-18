package Programmers;

public class 양과_늑대 {
	class Solution {
	    static int[] Info;
	    static int[][] Edges;
	    static int maxSheep;
	    public int solution(int[] info, int[][] edges) {
	        Info = info;
	        Edges = edges;
	        maxSheep = 0;
	        boolean[] visited = new boolean[info.length];
	        dfs(0,visited,0,0);
	        return maxSheep;
	    }
	    static void dfs(int idx, boolean[] visited, int sheepCnt, int wolfCnt){
	        visited[idx] = true;
	        if(Info[idx] == 0) {
	            sheepCnt++;
	            maxSheep = Math.max(sheepCnt, maxSheep);
	        }
	        else wolfCnt++;
	        
	        if(sheepCnt <= wolfCnt) {
	            // visited[idx] = false; 전역변수로 visited하고 이러면 왜안되징?
	            return; 
	        }
	        
	        for(int[] edge : Edges) {
	            if(visited[edge[0]] && !visited[edge[1]]) {
	                boolean[] newVisited = visited.clone();
	                dfs(edge[1],newVisited, sheepCnt, wolfCnt);
	            }
	        }
	        
	    }
	}
}
