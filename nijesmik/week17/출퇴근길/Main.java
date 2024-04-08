package nijesmik.week17.출퇴근길;

import java.io.*;
import java.util.*;

public class Main {
    static Node[] nodes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), E = sc.nextInt();

        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node();
        }

        while (E-- > 0) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            nodes[a].next.add(b);
            nodes[b].reverse.add(a);
        }

        int S = sc.nextInt() - 1, T = sc.nextInt() - 1;

        boolean[] stoi = new boolean[N];
        stoi[T] = true;
        dfs(S, false, stoi);

        boolean[] ttoi = new boolean[N];
        ttoi[S] = true;
        dfs(T, false, ttoi);

        boolean[] itos = new boolean[N];
        dfs(S, true, itos);

        boolean[] itot = new boolean[N];
        dfs(T, true, itot);

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (stoi[i] && ttoi[i] && itos[i] && itot[i]) {
                ans++;
            }
        }
        System.out.println(ans - 2);
    }

    static void dfs(int cur, boolean reverse, boolean[] visited) {
        if (visited[cur]) {
            return;
        }
        visited[cur] = true;
        List<Integer> next = reverse ? nodes[cur].reverse : nodes[cur].next;
        for (int idx : next) {
            dfs(idx, reverse, visited);
        }
    }

    static class Node {
        List<Integer> next = new ArrayList<>();
        List<Integer> reverse = new ArrayList<>();
    }
}
