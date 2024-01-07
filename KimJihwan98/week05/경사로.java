package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 경사로 {
	static int N, L;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;
		int[] tmp1 = new int[N];
		int[] tmp2 = new int[N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				tmp1[j] = map[i][j];
				tmp2[j] = map[j][i];
			}
			if(isRoad(tmp1)) answer++;
			if(isRoad(tmp2)) answer++;
		}
		System.out.println(answer);
	}
	
	static boolean isRoad(int[] road) {
		boolean[] used = new boolean[N];
		int s = road[0];
		int idx = 0;
		while(idx < N) {
			if(road[idx] == s) {
				idx++;
				continue;
			} else if(road[idx] == s-1) {
				s -= 1;
				for(int i = idx; i < idx+L; i++) {
					if(i == N) return false;
					if(road[i] != s) return false;
					used[i] = true;
				}
				idx += L;
			} else if(road[idx] == s+1) {
				s += 1;
				for(int i = idx-1; i > idx-L-1; i--) {
					if(i == -1) return false;
					if(used[i]) return false;
					if(road[i] != s-1) return false;
					used[i] = true;
				}
				idx++;
			} else {
				return false;
			}
		}
		return true;
	}
}
