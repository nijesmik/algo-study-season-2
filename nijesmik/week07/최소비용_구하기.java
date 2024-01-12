import java.util.*;

public class 최소비용_구하기 {

    public static void main(String[] args) {
        int INF = 1000 * 100_000;
        Scanner sc = new Scanner(System.in);
        int cityCount = sc.nextInt(), busCount = sc.nextInt();
        Bus[] buses = new Bus[cityCount];
        for (int i = 0; i < cityCount; i++) {
            buses[i] = new Bus();
        }
        for (int i = 0; i < busCount; i++) {
            int st = sc.nextInt() - 1, ed = sc.nextInt() - 1;
            buses[st].routes.add(new Route(ed, sc.nextInt()));
        }
        int start = sc.nextInt() - 1, end = sc.nextInt() - 1;

        int[] dp = new int[cityCount];
        Arrays.fill(dp, INF);

        PriorityQueue<Route> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.add(new Route(start, 0));
        dp[start] = 0;
        while (!pq.isEmpty()) {
            Route cur = pq.poll();
            if (cur.cost > dp[cur.idx])
                continue;
            for (Route next : buses[cur.idx].routes) {
                int cost = cur.cost + next.cost;
                if (cost < dp[next.idx]) {
                    dp[next.idx] = cost;
                    pq.add(new Route(next.idx, cost));
                }
            }
        }
        System.out.println(dp[end]);
    }

    static class Bus {
        List<Route> routes = new ArrayList<>();
    }

    static class Route {
        int idx, cost;

        Route(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
}
