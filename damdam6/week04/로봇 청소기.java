package y23Dec18Dec24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Baek4991new {

    static int w;
    static int h;
    static int[][] room;
    static ArrayList<pos> dirty;


    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true){
            st = new StringTokenizer(bf.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if(w == 0 && h == 0)break;
            dirty = new ArrayList<>();

            dirty.add(new pos(-1,-1));
            room = new int[h][w];

            // 0 깨끗 1 더러운 칸 2 가구
            for (int i = 0; i < h; i++) {
                String str = bf.readLine();
                for (int j = 0; j < w; j++) {
                    char c = str.charAt(j);
                    switch (c){
                        case '*':
                            room[i][j] = 1;
                            dirty.add(new pos(i,j));
                            break;
                        case 'o':
                            dirty.get(0).x = i;
                            dirty.get(0).y = j;
                        case '.':
                            room[i][j] = 0;
                            break;
                        case 'x':
                            room[i][j] = 2;
                            break;
                    }
                }
            }
            record();
            go = new int[dirty.size()];
            go[0] = 1;
            min = Integer.MAX_VALUE;
            dfs(0,1,0);
            if(min == Integer.MAX_VALUE)min = -1;
            System.out.println(min);


        }

    }
    static int min;
    static int[] go;
    static void dfs(int idx, int cnt, int dis){
        if(cnt == dirty.size()){
            min = Math.min(min, dis);
            return;
        }
        for (int i = 1; i < dirty.size(); i++) {
            if(go[i] == 1)continue;
            if(way[idx][i] == -1)continue;
            go[i] = 1;
            dfs(i, cnt+1, dis + way[idx][i]);
            go[i] = 0;
        }

    }
    static int[][] way;

    static public void record(){
        way = new int[dirty.size()][dirty.size()];

        int cnt =0;
        for (int i = 0; i < dirty.size(); i++) {
            for (int j = i+1; j < dirty.size(); j++) {
                cnt = bfs(dirty.get(i), dirty.get(j));
                way[i][j] = cnt;
                way[j][i] = cnt;
            }
        }
    }

    static int[] dx = new int[]{1,-1,0, 0  };
    static int[] dy = new int[]{0,0,1,-1};
    static public int bfs(pos s, pos e){

        int[][] vt = new int[h][w];
        Deque<pos> qu = new ArrayDeque<>();
        qu.add(new pos(s));
        vt[s.x][s.y] = 1;
        pos tmp;
        while(!qu.isEmpty()){

            tmp = qu.poll();
            if(tmp.x == e.x && tmp.y == e.y){
                return tmp.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= h || ny >= w )continue;
                if(room[nx][ny] == 2 || vt[nx][ny] == 1)continue;
                vt[nx][ny] = 1;
                qu.add(new pos(nx, ny, tmp.cnt+1));
            }

        }

        return -1;
    }

    static class pos{
        int x;
        int y;
        int cnt;

        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        public pos(pos p){
            this.x = p.x;
            this.y = p.y;
            this.cnt = 0;
        }
    }
}
