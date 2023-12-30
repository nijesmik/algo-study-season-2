package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Loc{
	int r;
	int c;
	int n_walls;
	int n_break;
	public Loc(int r, int c, int n_walls, int n_break) {
		this.r = r;
		this.c = c;
		this.n_walls = n_walls;
		this.n_break = n_break;
	}
}
public class 벽_부수고_이동하기2 {
	static int[][] map;
	static int N, M, K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			for(int c = 0; c < M; c++) {
				map[r][c] = s.charAt(c) - '0';
			}
		}
		System.out.println(bfs());
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int bfs() {
		boolean[][][] visited = new boolean[N][M][K+1];
		Queue<Loc> q = new LinkedList<>();
		q.add(new Loc(0,0,1,0));
		visited[0][0][0] = true;
		while(!q.isEmpty()){
			Loc now = q.poll();
			int r = now.r;
			int c = now.c;
			
			if(r == N-1 && c == M-1) return now.n_walls;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(!(nr>=0 && nr<N && nc>=0 && nc<M)) continue;
				
				if(map[nr][nc] == 0 && !visited[nr][nc][now.n_break]) {
					q.add(new Loc(nr, nc, now.n_walls+1, now.n_break));
					visited[nr][nc][now.n_break] = true;
				}
				else if(map[nr][nc] == 1 && now.n_break<K && !visited[nr][nc][now.n_break+1]){
					q.add(new Loc(nr, nc, now.n_walls+1, now.n_break+1));
					visited[nr][nc][now.n_break+1] = true;
				}
			}
			
		}
		return -1;
	}
}


