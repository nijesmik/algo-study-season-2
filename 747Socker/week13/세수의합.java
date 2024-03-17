package jechul;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class 세수의합 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); 
        int[] nums = new int[N];
        Set<Integer> set = new HashSet<>(); 

        for (int i = 0; i < N; i++) {
        	nums[i] = scanner.nextInt();
            set.add(nums[i]);
        }

        Arrays.sort(nums); 

        int maxSum = -1; 

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) { 
                for (int k = j; k < N; k++) { 
                    int sum = nums[i] + nums[j] + nums[k];
                    if (set.contains(sum)) {
                        maxSum = Math.max(maxSum, sum); 
                    }
                }
            }
        }

        System.out.println(maxSum); 
    }
}
