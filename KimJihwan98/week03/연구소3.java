package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Virus {
	int r, c, t;

	public Virus(int r, int c, int t) {
		this.r = r;
		this.c = c;
		this.t = t;
	}
}

public class 연구소3 {
	static int N, M, count, tmp_cnt, answer;
	static int[][] map;
	static boolean[] selected;
	static List<Virus> virus;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		virus = new ArrayList<>();
		count = 0;
		tmp_cnt = 0;
		answer = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j <N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp == 2) {
					virus.add(new Virus(i, j, 0));
				}
				else if(tmp == 0) count++; 
				map[i][j] = tmp;
			}
		}
		
		selected = new boolean[virus.size()];
		comb(0, 0);
		
		if(count==0) System.out.println(0);
		else System.out.println(answer==Integer.MAX_VALUE ? -1 : answer);
	}
	
	static void comb(int idx, int sidx) {
		if(sidx == M) {
			bfs(selected);
			return;
		}
		
		if(idx == virus.size()) return;
		
		selected[idx] = true;
		comb(idx+1, sidx+1);
		selected[idx] = false;
		comb(idx+1, sidx);
	}
	
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static void bfs(boolean[] selected) {
		tmp_cnt = 0;
		Queue<Virus> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		for(int i = 0; i < virus.size(); i++) {
			if(selected[i]) {
				q.add(new Virus(virus.get(i).r, virus.get(i).c, 0));
				visited[virus.get(i).r][virus.get(i).c] = true; 
			}
		}
		
		int time = Integer.MAX_VALUE;
		loop : while(!q.isEmpty()) {
			Virus now = q.poll();
			int r = now.r;
			int c = now.c;
			time = now.t;
			
			if(time >= answer) return;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(!(nr>=0 && nc>=0 && nr<N && nc<N)) continue;
				if(map[nr][nc] == 1 || visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				if(map[nr][nc] == 0) tmp_cnt++;
				
				// 비활성화 바이러스도 만나면 활성화가 됨  문제 똑바로읽자
				if(tmp_cnt == count) {
					time += 1;
					break loop;
				}
				
				q.add(new Virus(nr, nc, time+1));
			}
		}
		
		
		if(tmp_cnt == count) {
			answer = Math.min(answer, time);
		}
	}
}
