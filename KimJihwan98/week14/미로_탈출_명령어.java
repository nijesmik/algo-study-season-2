import java.util.HashMap;
import java.util.*;

public class 미로_탈출_명령어 {
    class Solution {
        static String answer = null;
        // 하(d) 좌(l) 우(r) 상(u) 순서
        static int[] dr = {1,0,0,-1};
        static int[] dc = {0,-1,1,0};
        static HashMap<Integer, String> hm;
        static StringBuilder sb;
        public String solution(int n, int m, int x, int y, int r, int c, int k) {
            x--; y--; r--; c--;
            hm = new HashMap<>();
            hm.put(0, "d");
            hm.put(1, "l");
            hm.put(2, "r");
            hm.put(3, "u");
            sb = new StringBuilder();
            if((getDistance(x,y,r,c)%2) + (k%2) == 1) return "impossible";
            else if(getDistance(x,y,r,c) > k) return "impossible";
            // 이동 불가능 예외처리

            dfs(n,m,x,y,r,c,k,0);
            return answer;
        }

        static int getDistance(int x, int y, int r, int c) {
            return Math.abs(x-r) + Math.abs(y-c);
        }

        static void dfs(int n, int m, int x, int y, int r, int c, int k, int depth) {
            if(answer!=null) return;
            if(depth + getDistance(x,y,r,c) > k) return;
            if(depth==k && x==r && y==c) {
                answer = sb.toString();
                return;
            }
            for(int i = 0; i < 4; i++) {
                int nx = x + dr[i];
                int ny = y + dc[i];
                if(!(nx>=0&&nx<n&&ny>=0&&ny<m)) continue;
                if(answer!=null) return;
                sb.append(hm.get(i));
                dfs(n, m, nx, ny, r, c, k, depth+1);
                sb.delete(depth, depth+1);
            }
        }
    }
}
