package Programmers;

public class 표_편집 {
	import java.util.*;

	class Node {
	    Node prev = null;
	    Node next = null;
	    boolean d = false;
	}

	class Solution {
	    public String solution(int n, int k, String[] cmd) {
	        String answer = "";
	        
	        Node[] graph = new Node[n+1];
	        graph[0] = new Node();
	        for(int i = 1;i < n; i++) {
	            graph[i] = new Node();
	            graph[i].prev = graph[i-1];
	            graph[i-1].next = graph[i];
	        }
	        Node now = graph[k];
	        Stack<Node> deleted = new Stack<>(); 
	        for(String s : cmd) {
	            char c = s.charAt(0);
	            if(c == 'U') {
	                int tmp = Integer.parseInt(s.substring(2));
	                for(int i = 0; i < tmp; i++) {
	                    now = now.prev;
	                }
	            }else if(c == 'D') {
	                int tmp = Integer.parseInt(s.substring(2));
	                for(int i = 0; i < tmp; i++) {
	                    now = now.next;
	                }
	            } else if ( c == 'C') {
	                now.d = true;
	                deleted.push(now);
	                Node prev = now.prev;
	                Node next = now.next;
	                if(prev != null) {
	                    prev.next = next;
	                }
	                if ( next != null) {
	                    next.prev = prev;
	                    now = next;
	                } else {
	                    now = prev;
	                }
	            } else {
	                Node temp = deleted.pop();
	                temp.d = false;
	                Node prev = temp.prev;
	                Node next = temp.next;
	                
	                if(prev != null) {
	                    prev.next = temp;
	                }
	                if(next != null) {
	                    next.prev = temp;
	                }
	            }
	        }
	        StringBuilder sb = new StringBuilder();
	        for(int i = 0; i < n; i++) {
	            if(graph[i].d) sb.append('X');
	            else sb.append('O');
	        }
	        answer = sb.toString();
	        return answer;
	    }
	}

}
