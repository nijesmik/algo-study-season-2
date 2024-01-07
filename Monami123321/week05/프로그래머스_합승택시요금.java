import java.util.*;
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {

        int[][] dist = new int[n+1][n+1];
        int INF = 1 << 29;

        for(int i = 1; i< n+1 ; i++) {
            Arrays.fill(dist[i],INF);
            dist[i][i] = 0;
        }

        for(int[] fare: fares) {
            dist[fare[0]][fare[1]] = fare[2];
            dist[fare[1]][fare[0]] = fare[2];
        }

        for(int mid = 1; mid<n+1; mid++) {
            for(int start = 1; start<n+1; start++) {
                for(int end = 1; end<n+1; end++) {
                    if(dist[start][end] > dist[start][mid] + dist[mid][end]) {
                        dist[start][end] = dist[start][mid] + dist[mid][end];
                    }
                }
            }
        }
        int ans = dist[s][a] + dist[s][b];
        for(int i = 1; i<n+1; i++) {
            ans = Math.min(ans,dist[s][i] + dist[i][a] + dist[i][b]);
        }
        return ans;
    }
}