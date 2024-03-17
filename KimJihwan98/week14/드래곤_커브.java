import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 드래곤_커브 {
    static int N;
    static List<int[]>[] dragon;
    static boolean[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dragon = new ArrayList[N];
        map = new boolean[101][101];
        for(int i = 0; i < N; i++) {
            dragon[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int gen = Integer.parseInt(st.nextToken());
            int[] start = {a,b};
            dragon[i].add(start);
            if(dir==0) dragon[i].add(new int[] {a+1, b});
            else if(dir==1) dragon[i].add(new int[] {a, b-1});
            else if(dir==2) dragon[i].add(new int[] {a-1, b});
            else dragon[i].add(new int[] {a, b+1});

            map[a][b] = true;
            map[dragon[i].get(1)[0]][dragon[i].get(1)[1]] = true;
            if(gen == 0) continue;

            curve(i, 1, gen, dragon[i].get(1)[0],dragon[i].get(1)[1]);

        }

        int answer = 0;
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) answer++;
            }
        }
        System.out.println(answer);

    }
    static void curve(int dIdx, int nowGen, int gen, int lx, int ly) {
        while(nowGen++<=gen) {
            int size = dragon[dIdx].size();
            for (int i = 0; i < size; i++) {
                int[] tmp = dragon[dIdx].get(i);
                int[] newPoint = movePoint(tmp[0], tmp[1], lx, ly);
                map[newPoint[0]][newPoint[1]] = true;
                dragon[dIdx].add(newPoint);
            }
            int[] tmp = movePoint(dragon[dIdx].get(0)[0], dragon[dIdx].get(0)[1], lx, ly);
            lx = tmp[0];
            ly = tmp[1];
        }
    }
    static int[] movePoint(int x, int y, int lx, int ly) {
        return new int[] {lx-(y-ly), ly+(x-lx)};
    }
}
