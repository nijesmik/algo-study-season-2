package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미세먼지_안녕 {
	static int R, C, T;
	static int[] sr;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		sr = new int[2];
		sr[0] = -1; sr[1] = -1;
		map = new int[R][C];
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == -1) {
					if(sr[0] == -1) sr[0] = r;
					sr[1] = r;
				}
			}
		}
		
		for(int i = 0; i < T; i++) {
			bfs();
		}
		
		int answer = 0;
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] != 0 && map[r][c] != -1) {
					answer+= map[r][c];
				}
			}
		}
		System.out.println(answer);
	}
	
	static Queue<int[]> q;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static void bfs() {
		q = new LinkedList<>();
		int[][] tmp = new int[R][C];
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] != 0 && map[r][c] != -1) {
					q.add(new int[] {r, c, map[r][c]});
				} else if(map[r][c] == -1) {
					tmp[r][c] = -1;
				}
			}
		}
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int r = temp[0];
			int c = temp[1];
			int w = temp[2];
			if(w < 5) continue;
			int ww = w/5;
			
			//미세먼지 뿌리기
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(!(nr>=0&&nr<R&&nc>=0&&nc<C)) continue;
				if(tmp[nr][nc] == -1) continue;
				tmp[nr][nc] += ww;
				tmp[r][c] -= ww;
			}
		}
		
		//미세먼지 뿌리고 난 뒤 방에 남아있는 미세먼지
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == -1) continue;
				map[r][c] += tmp[r][c];
			}
		}
		
		// 공기청정기 작동
		for(int i = sr[0]-1; i > 0; i--) {
			map[i][0] = map[i-1][0];
		}
		for(int i = 0; i < C-1; i++) {
			map[0][i] = map[0][i+1];
		}
		for(int i = 0; i < sr[0]; i++) {
			map[i][C-1] = map[i+1][C-1];
		}
		for(int i = C-1; i > 1; i--) {
			map[sr[0]][i] = map[sr[0]][i-1];
		}
		map[sr[0]][1] = 0;
		map[sr[0]][0] = -1;
		
		for(int i = sr[1]+1; i < R-1; i++) {
			map[i][0] = map[i+1][0];
		}
		for(int i = 0; i < C-1; i++) {
			map[R-1][i] = map[R-1][i+1];
		}
		for(int i = R-1; i > sr[1]; i--) {
			map[i][C-1] = map[i-1][C-1];
		}
		for(int i = C-1; i > 1; i--) {
			map[sr[1]][i] = map[sr[1]][i-1];
		}
		map[sr[1]][1] = 0;
		map[sr[1]][0] = -1;
	}
}
