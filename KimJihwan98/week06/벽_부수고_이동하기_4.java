package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 벽_부수고_이동하기_4 {
	static int[][] map, group;
	static int N, M;
	static Queue<int[]> q;
	static HashMap<Integer, Integer> hm;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		group = new int[N][M];
		q = new LinkedList<>();
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			for(int c = 0; c < M; c++) {
				int tmp = s.charAt(c) - '0';
				map[r][c] = tmp;
			}
		}
		
		hm = new HashMap<>();
		int group_num = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 0 && group[r][c] == 0) {
					group_num++;
					bfs(r, c, group_num);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				sb.append(getAnswer(r, c)+"");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static void bfs(int r, int c, int group_num) {
		int t = 1;
		group[r][c] = group_num;
		q.add(new int[] {r, c});
		while(!q.isEmpty()) {
			int[] now = q.poll();
			r = now[0];
			c = now[1];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(!(nr>=0 && nr<N && nc>=0 && nc<M)) continue;
				if(group[nr][nc] != 0) continue;
				if(map[nr][nc] != 0) continue;
				group[nr][nc] = group_num;
				q.add(new int[] {nr, nc});
				t++;
			}
		}
		hm.put(group_num, t);
	}
	
	static int getAnswer(int r, int c) {
		int t = 1;
		if(map[r][c] == 0) return 0;
		
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(!(nr>=0 && nr<N && nc>=0 && nc<M)) continue;
			if(group[nr][nc] == 0) continue;
			
			set.add(group[nr][nc]);
		}
		for(int i : set) {
			t += hm.get(i);
		}
		return t%10; // 왜 %10 해줘야되요? 도저히모르겠네요.
	}
}
