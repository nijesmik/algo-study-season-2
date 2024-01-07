package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장_긴_바이토닉_부분_수열 {
	static int N;
	static int[] A;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp1 = new int[N];
		for(int i = 0; i < N; i++) {
			dp1[i] = 1;
			for(int j = i-1; j >=0; j--) {
				if(A[j] < A[i]) {
					dp1[i] = Math.max(dp1[j] + 1, dp1[i]);
				}
			}
		}
		
		int[] dp2 = new int[N];
		for(int i = N-1; i >= 0; i--) {
			dp2[i] = 1;
			for(int j = i+1; j < N; j++) {
				if(A[i] > A[j]) {
					dp2[i] = Math.max(dp2[j] + 1, dp2[i]);
				}
			}
		}
		
		int answer = 0;
		for(int i = 0; i < N; i++) {
			answer = Math.max(dp1[i]+dp2[i]-1, answer);
		}
		System.out.println(answer);
	}
}
