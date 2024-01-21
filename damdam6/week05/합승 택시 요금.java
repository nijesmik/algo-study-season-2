class Solution {
    final int INF = 20000001; // 충분히 큰 값 설정

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] graph = new int[n+1][n+1];

        // 그래프 초기화
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                if(i == j) graph[i][j] = 0;
                else graph[i][j] = INF;
            }
        }

        // 요금 정보 입력
        for(int[] fare : fares) {
            graph[fare[0]][fare[1]] = fare[2];
            graph[fare[1]][fare[0]] = fare[2];
        }

        // 플로이드-워셜 알고리즘 실행
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        // 최소 비용 계산
        int answer = INF;
        for(int i = 1; i <= n; i++) {
            answer = Math.min(answer, graph[s][i] + graph[i][a] + graph[i][b]);
        }

        return answer;
    }
}
