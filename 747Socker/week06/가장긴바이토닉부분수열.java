package w06_1_02;

import java.util.Scanner;

public class 가장긴바이토닉부분수열 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        
        for (int i = 0; i < n; i++) {
            dp1[i] = 1;
            dp2[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp1[i] = Math.max(dp1[i], dp1[j] + 1);
                }
            }
        }

        for(int i=n-1; i>=0; i--) {
        	for(int j=n-1; j>i;j--) {
        		if(arr[j]<arr[i]) {
        			dp2[i]=Math.max(dp2[i], dp2[j]+1);
        		}
        	}
        }
        
        
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (dp1[i]+dp2[i] > max) {
                max = dp1[i]+dp2[i];
            }
        }

        System.out.println(max-1);
    }
}
