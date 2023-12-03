package bj;

import java.util.Arrays;
import java.util.Scanner;

public class 용액 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] arr = new long[n];
		for(int i=0; i<n;i++) {
			arr[i]=sc.nextLong();
		}
		
		int lt = 0;
		int rt = n-1;
		
		int anslt =0;
		int ansrt=0;
		long min = Long.MAX_VALUE;
		while(lt<rt) {
			long sum = arr[lt]+arr[rt];
			if(min>Math.abs(sum)) {
				min = Math.abs(sum);
				anslt = lt; 
				ansrt = rt;
			}
			if(sum>=0) {
				rt--;
			}else {
				lt++;
			}
		}
		System.out.println(arr[anslt]+" "+arr[ansrt]);
	}
}
