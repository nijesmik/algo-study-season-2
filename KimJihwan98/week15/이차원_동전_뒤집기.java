class Solution {
    int[][] map;
    int[][] targetMap;
    int N, M, answer;
    public int solution(int[][] beginning, int[][] target) {
        answer = Integer.MAX_VALUE;
        map = beginning;
        targetMap = target;
        N = map.length;
        M = map[0].length;

        dfs(0,0);
        if(answer == Integer.MAX_VALUE) return -1;
        return answer;
    }

    public void flipRow(int idx) {
        //행
        for(int i = 0; i < M; i++) {
            map[idx][i] = map[idx][i]==0? 1 : 0;
        }
    }

    public int checkCol(int idx) {
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            if(targetMap[i][idx] == map[i][idx]) cnt++;
        }
        if(cnt == N) return 0;
        else if(cnt == 0) return 1;
        else return -1;
    }

    public void dfs(int idx, int count) {
        if(idx == N) {
            for(int i = 0; i < M; i++) {
                int tmp = checkCol(i);
                if(tmp==-1) return;
                count += tmp;
            }
            answer = Math.min(answer, count);
            return;
        }

        flipRow(idx);
        dfs(idx+1, count+1);
        flipRow(idx);
        dfs(idx+1, count);
    }
}