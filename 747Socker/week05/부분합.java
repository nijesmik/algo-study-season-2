package bj;

import java.util.Scanner;

public class ºÎºÐÇÕ {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int s = scanner.nextInt();
        int[] seq = new int[n];

        for (int i = 0; i < n; i++) {
            seq[i] = scanner.nextInt();
        }

        int[] count = new int[n];
        int sum = 0;
        int end = 0;

        for (int start = 0; start < n; start++) {
            while (sum < s && end < n) {
                sum += seq[end];
                end++;
            }

            if (sum >= s) {
                count[start] = end - start;
            }

            sum -= seq[start];
        }

        int result = 0;
        for (int length : count) {
            if (length != 0) {
                result = (result == 0) ? length : Math.min(result, length);
            }
        }

        System.out.println(result);
    }
}
