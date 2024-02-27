import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 테트로미노 {
    static int N, M;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] t1 = {{0,1},{0,2},{0,3}};
    static int[][] t2 = {{0,1},{1,1},{1,0}};
    static int[][] t3 = {{1,0},{2,0},{2,1}};
    static int[][] t4 = {{1,0},{1,1},{2,1}};
    static int[][] t5 = {{0,1},{1,1},{0,2}};
    static int[][] t6 = {{1,0},{2,0},{2,-1}};
    static int[][] t7 = {{1,0},{1,-1},{2,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                answer = Math.max(answer, rotate(r, c, t1));
                answer = Math.max(answer, rotate(r, c, t2));
                answer = Math.max(answer, rotate(r, c, t3));
                answer = Math.max(answer, rotate(r, c, t4));
                answer = Math.max(answer, rotate(r, c, t5));
                answer = Math.max(answer, rotate(r, c, t6));
                answer = Math.max(answer, rotate(r, c, t7));
            }
        }
        System.out.println(answer);
    }
    static int rotate(int r, int c, int[][] type) {
        int tmp_ans = 0;
        int sum = map[r][c];
        // 기본이 동
        for(int i = 0; i < 3; i++) {
            int nr = r + type[i][0];
            int nc = c + type[i][1];
            if(!(nr>=0&&nr<N&&nc>=0&&nc<M)) {
                sum=map[r][c];
                break;
            }
            sum+=map[nr][nc];
        }
        tmp_ans = Math.max(sum, tmp_ans);
        sum = map[r][c];
        // 남
        for(int i = 0; i < 3; i++) {
            int nr = r + type[i][1];
            int nc = c - type[i][0];
            if(!(nr>=0&&nr<N&&nc>=0&&nc<M)) {
                sum=map[r][c];
                break;
            }
            sum+=map[nr][nc];
        }
        tmp_ans = Math.max(sum, tmp_ans);
        sum = map[r][c];
        // 서
        for(int i = 0; i < 3; i++) {
            int nr = r - type[i][0];
            int nc = c - type[i][1];
            if(!(nr>=0&&nr<N&&nc>=0&&nc<M)) {
                sum=map[r][c];
                break;
            }
            sum+=map[nr][nc];
        }
        tmp_ans = Math.max(sum, tmp_ans);
        sum = map[r][c];
        // 북
        for(int i = 0; i < 3; i++) {
            int nr = r - type[i][1];
            int nc = c + type[i][0];
            if(!(nr>=0&&nr<N&&nc>=0&&nc<M)) {
                sum=map[r][c];
                break;
            }
            sum+=map[nr][nc];
        }
        tmp_ans = Math.max(sum, tmp_ans);
        return tmp_ans;
    }
}
