import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_15684_사다리조작 {
    static int w, h, m;
    static int[][] check;
    static List<int[]> list;
    static int key = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken()); // 사다리
        m = Integer.parseInt(st.nextToken()); // 줄
        h = Integer.parseInt(st.nextToken());
        check = new int[h + 2][w + 2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            check[a][b] = key++;
            check[a][b + 1] = key++;
        }
        list = new ArrayList<>();
        // 왼쪽에서 오른쪽으로 그을 수 있는 점 모음
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j < w; j++) {
                if (check[i][j] == 0 && check[i][j + 1] == 0) {
                    list.add(new int[]{i, j});
                }
            }
        }
        int len = list.size();
        if (len == 0) {
            if (play()) {
                System.out.println(0);
            } else {
                System.out.println(-1);
            }
            return;
        }

        for (int i = 0; i <= 3; i++) {
            if (len < i) {
                break;
            }
            int[] arr = new int[len];
            for (int j = len - 1; j > len - 1 - i; j--) {
                arr[j] = 1;
            }
            loop:
            do {
                List<Integer> added = new ArrayList<>();
                for (int j = 0; j < len; j++) {
                    if (arr[j] != 0) {
                        int r = list.get(j)[0];
                        int c = list.get(j)[1];
                        if (check[r][c] != 0 || check[r][c + 1] != 0) {
                            rollback(added);
                            continue loop;
                        }
                        check[r][c] = key++;
                        check[r][c + 1] = key++;
                        added.add(j);
                    }
                } // 선 긋기
//                System.out.println(i);
//
//                for (int j = 0; j < check.length; j++) {
//                    for (int k = 0; k < check[0].length; k++) {
//                        System.out.print(check[j][k] + " ");
//                    }
//                    System.out.println();
//                }
//                System.out.println("-------------------------------");
//                System.out.println();

                if (play()) {
                    System.out.println(i);
                    return;
                }
                rollback(added);
            } while (nextPermutation(arr));
        }
        System.out.println(-1);

        br.close();
    }

    static boolean play() {
        for (int i = 1; i <= w; i++) {
            // 시작점
            int r = 1;
            int c = i;
            while (r < h + 1) {
                if (check[r][c] != 0) {
                    int to = (check[r][c] ^ 1);
                    if (check[r][c - 1] == to) {
                        --c;
                    } else {
                        ++c;
                    }
                }
                ++r;
            }
            if (c != i) {
                return false;
            }
        }
        return true;
    }

    static void rollback(List<Integer> added) {
        int len = added.size();
        for (int i = 0; i < len; i++) {
            int[] now = list.get(added.get(i));
            check[now[0]][now[1]] = check[now[0]][now[1] + 1] = 0;
        }
    }

    static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) {
            --i;
        }
        if (i == 0) {
            return false;
        }
        int j = arr.length - 1;
        while (arr[i - 1] >= arr[j]) {
            --j;
        }
        swap(arr, i - 1, j);
        int k = arr.length - 1;
        while (i < k) {
            swap(arr, i++, k--);
        }
        return true;
    }

}
