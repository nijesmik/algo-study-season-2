package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 놀이공원 {
	static int M;
	static int N;
	static int[] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int answer = 0;
		if (N <= M) {
			answer = N;
			System.out.println(answer);
		}
		else {
			int[] time = new int[M];
			int max = 0;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				time[i] = Integer.parseInt(st.nextToken());
				if(time[i] > max) max = time[i];
			}
			long start = 0;
			long end = (long) max * N / M;
			
			while(start <= end) {
				long sum = M;
				long mid = (start+end)/2;
				for(int i = 0; i < M; i++) {
					sum += mid/time[i];
				}
				
				if(sum >= N) end = mid-1;
				else start = mid+1;
			}
			
			start -= 1;
			
			N -= M;
			for(int i = 0; i < M; i++) {
				N -= start / time[i];
			}
			
			start += 1;
			for(int i = 0; i < M; i++) {
				if(start % time[i] == 0) N -=1;
				if(N == 0) {
					answer = i;
					break;
				}
			}
			System.out.println(answer+1);
		}
	}
}
