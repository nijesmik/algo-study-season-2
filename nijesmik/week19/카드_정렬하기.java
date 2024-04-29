package nijesmik.week19;
import java.util.*;

public class 카드_정렬하기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (n-- > 0) {
            pq.add(sc.nextInt());
        }
        int ans = 0;
        while (pq.size() > 1) {
            int card = pq.poll() + pq.poll();
            ans += card;
            pq.add(card);
        }
        System.out.println(ans);
    }
}