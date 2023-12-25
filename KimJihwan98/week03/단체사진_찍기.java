package Programmers;

public class 단체사진_찍기 {
	class Solution {
	    static String[] datas;
	    static int answer;
	    static boolean[] visited;
	    static char[] friends;
	    public int solution(int n, String[] data) {
	        answer = 0;
	        datas = data;
	        friends = new char[] {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
	        visited = new boolean[8];
	        
	        dfs("", 0);
	        
	        return answer;
	    }
	    
	    static void dfs(String order, int depth) {
	        if(depth == 8) {
	            if(check(order)) answer++;
	            return;
	        }
	        
	        for(int i = 0; i < 8; i++) {
	            if(!visited[i]) {
	                visited[i] = true;
	                dfs(order+friends[i], depth+1);
	                visited[i] = false;
	            }
	        }
	    }
	    
	    static boolean check(String order) {
	        for(String condition : datas) {
	            int idx1=0, idx2=0;
	            
	            for(int i = 0; i<8; i++) {
	                if(order.charAt(i)==condition.charAt(0)) idx1 = i;
	                if(order.charAt(i)==condition.charAt(2)) idx2 = i;
	            }
	            int dist = Math.abs(idx1-idx2)-1;
	            
	            if(condition.charAt(3) == '=') {
	                if(dist != condition.charAt(4)-'0') return false;
	            } else if(condition.charAt(3) == '<'){
	                if(dist >= condition.charAt(4)-'0') return false;
	            } else if(condition.charAt(3) == '>'){
	                if(dist <= condition.charAt(4)-'0') return false;
	            }
	        }
	        return true;
	    }
	}
}
