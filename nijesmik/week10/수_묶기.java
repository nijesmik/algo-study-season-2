package nijesmik.week10;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 수_묶기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        PriorityQueue<Integer> negative = new PriorityQueue<>((a, b) -> a - b);
        PriorityQueue<Integer> positive = new PriorityQueue<>((a, b) -> b - a);
        int zero = 0;
        while (N-- > 0) {
            int num = sc.nextInt();
            if (num < 0) {
                negative.add(num);
            } else if (num > 0) {
                positive.add(num);
            } else {
                zero++;
            }
        }
        int ans = 0;
        ans += deriveAnswer(negative, zero > 0);
        ans += deriveAnswer(positive, false);
        System.out.println(ans);
    }

    static int deriveAnswer(PriorityQueue<Integer> pq, boolean hasZero) {
        int ans = 0;
        while (pq.size() > 1) {
            int num = pq.poll(), num2 = pq.poll();
            if (num == 1 || num2 == 1) {
                ans += num + num2;
            } else {
                ans += num * num2;
            }
        }
        if (pq.size() == 1 && !hasZero) {
            ans += pq.poll();
        }
        return ans;
    }
}