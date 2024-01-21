package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class loc1 {
	int h;
	int r;
	int c;
	int time;
	public loc1(int h, int r, int c, int time) {
		this.h = h;
		this.r = r;
		this.c = c;
		this.time = time;
	}
}

public class 상범_빌딩 {
	static int H, R, C;
	static char[][][] building;
	static loc1 start, end;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			H = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if(H == 0 && R == 0 && C == 0) break;
			
			building = new char[H][R][C];
			
			for(int h = 0; h < H; h++) {
				for(int r = 0; r < R; r++) {
					String s = br.readLine();
					for(int c = 0; c < C; c++) {
						building[h][r][c] = s.charAt(c);
						if(s.charAt(c) == 'S') start = new loc1(h, r, c, 0);
						if(s.charAt(c) == 'E') end = new loc1(h, r, c, 0);
					}
				}
				String trash = br.readLine();
			}
			
			int tmp = bfs(start, end);
			if(tmp == -1) System.out.println("Trapped!");
			else System.out.println("Escaped in " + tmp + " minute(s).");
		}
	}
	
	static int[] dh = {-1, 1, 0, 0, 0, 0};
	static int[] dr = {0, 0, 0, 1, 0, -1};
	static int[] dc = {0, 0, 1, 0, -1, 0};
	static int bfs(loc1 start, loc1 end) {
		int time = 0;
		Queue<loc1> q = new LinkedList<>();
		q.add(start);
		boolean[][][] visited = new boolean[H][R][C];
		visited[start.h][start.r][start.c] = true;
		while(!q.isEmpty()) {
			loc1 now = q.poll();
			int h = now.h;
			int r = now.r;
			int c = now.c;
			time = now.time;
			
			// visited[h][r][c] = true; 이렇게 하면 메모리 초과 왜?
			
			if(h == end.h && r == end.r && c == end.c) return time;
			
			for(int i = 0; i < 6; i++) {
				int nh = h + dh[i];
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(!(nh>=0 && nh < H && nr>=0 && nr < R && nc>=0 && nc < C )) continue;
				if(visited[nh][nr][nc]) continue;
				if(building[nh][nr][nc] == '#') continue;
				visited[nh][nr][nc] = true;
				q.add(new loc1(nh, nr, nc, time+1));
			}
		}
		return -1;
	}
}
