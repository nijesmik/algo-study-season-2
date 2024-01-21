package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node2 {
	int end;
	int weight;
	public Node2(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
}

public class 최소비용_구하기 {
	static int N, M;
	static List<Node2>[] graph;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		
		for(int i= 0;i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[a].add(new Node2(b, w));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[start] = 0;
		dijkstra(start);
		
		System.out.println(dp[end]);
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Node2> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		boolean[] visited = new boolean[N+1];
		
		pq.add(new Node2(start, 0));
		
		while(!pq.isEmpty()) {
			Node2 now = pq.poll();
			int e = now.end;
			
			if(visited[e]) continue;
			visited[e] = true;
			for(int i = 0; i < graph[e].size(); i++) {
				if(dp[graph[e].get(i).end] >= dp[e] + graph[e].get(i).weight) {
					dp[graph[e].get(i).end]= dp[e] + graph[e].get(i).weight;
					pq.add(new Node2(graph[e].get(i).end, dp[graph[e].get(i).end]));
				}
			}
		}
		
	}
}
