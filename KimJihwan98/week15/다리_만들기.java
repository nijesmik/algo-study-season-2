import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 다리_만들기 {
    static int N, nIsland;
    static int[][] map;
    static List<List<Node>> islands;
    static Queue<Node> q;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        q = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j= 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        islands = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) getIsland(i, j);
            }
        }

        nIsland = islands.size();
//        System.out.println("섬 수 : " + nIsland);
        int answer = Integer.MAX_VALUE;
        for(int i = 0 ;i < nIsland; i++) {
            for(int k = 0; k < islands.get(i).size();k++) {
                for(int j = i+1; j < nIsland; j++) {
                    for(int l = 0; l < islands.get(j).size(); l++) {
                        int dist = getDistance(islands.get(i).get(k).r, islands.get(i).get(k).c,islands.get(j).get(l).r, islands.get(j).get(l).c);
                        if(answer > dist) {
                            answer = dist;
//                            System.out.println("좌표 : " + islands.get(i).get(k).r + " " + islands.get(i).get(k).c + " 좌표 : " + islands.get(j).get(l).r + " " + islands.get(j).get(l).c);
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0 ,-1, 0};
    static void getIsland(int r, int c) {
        islands.add(new ArrayList<>());
        islands.get(islands.size()-1).add(new Node(r,c));
        q.add(new Node(r, c));
        visited[r][c] = true;
        while(!q.isEmpty()) {
            Node now = q.poll();
            for(int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if(!(nr>=0&&nr<N&&nc>=0&&nc<N)) continue;
                if(visited[nr][nc] || map[nr][nc] == 0) continue;
                visited[nr][nc] = true;
                Node tmp = new Node(nr, nc);
                q.add(tmp);
                islands.get(islands.size()-1).add(tmp);
            }
        }
    }

    static int getDistance(int x, int y, int r, int c) {
        return Math.abs(x-r)+Math.abs(y-c)-1;
    }
}
class Node {
    int r;
    int c ;
    public Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
