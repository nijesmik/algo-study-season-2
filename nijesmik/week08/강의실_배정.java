import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 강의실_배정 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(arr[0][1]);
        for (int i = 1; i < N; i++) {
            if (pq.peek() <= arr[i][0]) {
                pq.poll();
            }
            pq.add(arr[i][1]);
        }
        System.out.println(pq.size());
    }
}
