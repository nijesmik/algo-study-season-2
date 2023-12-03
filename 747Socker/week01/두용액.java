package bj;

import java.util.Arrays;
import java.util.Scanner;

public class 두용액 {
	public static void main(String[] args) {
		
	Scanner sc = new Scanner(System.in);
	int N = sc.nextInt();
	
	int[] arr = new int[N];
	for(int i=0; i<N;i++) {
		arr[i]=sc.nextInt();
	}
	int[] res = new int[2];
	int S=0;
	int E=N-1;
	int min = Integer.MAX_VALUE;
	Arrays.sort(arr);
	while(S<E) {
		int sum = arr[S]+arr[E];
		if(min>Math.abs(sum)) {
			min = Math.abs(sum);
			res[0]=arr[S];
			res[1]=arr[E];
			if(sum==0) break;
		}
		if(sum<0) S++;
		else E--;
	}
	System.out.println(res[0]+" "+res[1]);
	
}
}
