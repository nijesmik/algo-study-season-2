import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_9466_텀프로젝트 {

    static boolean[] checked, visited;
    static int[] arr;
    static int ans;

    static void dfs(int next) {
        if(checked[next]) {
            return;
        }

        if (visited[next]) {
//            if (checked[next]) {
//                return;
//            }
            checked[next] = true;
            ans++;

        }
        visited[next] = true;
        dfs(arr[next]);
        visited[next] = false;
        checked[next] = true;


    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCases = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCases; tc++) {
            int n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            arr = new int[n + 1]; // 학생 정보 배열
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }


            checked = new boolean[n + 1];
            visited = new boolean[n + 1];
            ans = 0;

            for (int i = 1; i < arr.length; i++) {
                if (checked[i])
                    continue;
                dfs(i);

            }

            sb.append(n - ans).append("\n");


        }
        System.out.print(sb);

        br.close();


    }
}
