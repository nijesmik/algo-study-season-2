package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구슬_탈출_2 {
	static int N, M;
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int r = 0; r < N; r++) {
			String s = br.readLine();
			for(int c = 0; c < M; c++) {
				map[r][c] = s.charAt(c);
			}
		}
		
		// 상하좌우 네가지나눠서 
		// .일때만 움직이기 , O만나면 멈추기 while로
				
	}
}
