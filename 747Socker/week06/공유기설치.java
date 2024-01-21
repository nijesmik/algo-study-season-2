package w06_1_02;

import java.util.Arrays;
import java.util.Scanner;

public class 공유기설치 {
	static int answer;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = scanner.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        Arrays.sort(array);

        int start = 1;
        int end = array[n - 1] - array[0];
        answer = 0;

        binarySearch(array, start, end, c);

        System.out.println(answer);
    }

    static void binarySearch(int[] array, int start, int end, int c) {
        while (start <= end) {
            int mid = (start + end) / 2;
            int current = array[0];
            int count = 1;

            for (int i = 1; i < array.length; i++) {
                if (array[i] >= current + mid) {
                    count++;
                    current = array[i];
                }
            }

            if (count >= c) {
                start = mid + 1;
                answer = mid;
            } else {
                end = mid - 1;
            }
        }
    }
}

