import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_17140_이차원배열과연산 {
    static int[][] map = new int[100][100];
    static int[] hLen = new int[100];
    static int[] wLen = new int[100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            hLen[i] = 3;
            wLen[i] = 3;
        }
        if (map[r][c] == k) {
            System.out.println(0);
            return;
        }

        int limit = 100;
        int hNum = 3;
        int wNum = 3;
        int hMaxLen = 3;
        int wMaxLen = 3;
        while (limit > 0) {
//            if (limit == 94) {
//                break;
//            }
//            for (int i = 0; i < hNum; i++) {
//                for (int j = 0; j < wNum; j++) {
//                    System.out.printf("%d ",map[i][j]);
//
//                }
//                System.out.println();
//
//            }
//            System.out.println(wNum + " wNum");
//            System.out.println(hNum + " hNum");

            if (hNum >= wNum) {
                for (int i = 0; i < hNum; i++) {
                    int[] tmp = new int[wNum];
                    for (int j = 0; j < tmp.length; j++) {
                        tmp[j] = map[i][j];
                    }
                    List<Integer> res = cal(tmp);
                    hLen[i] = Math.min(res.size(), 100);

                    for (int j = 0; j < hLen[i]; j++) {
                        map[i][j] = res.get(j);
                    }
                }
                int prevLen = hMaxLen;
                hMaxLen = 0;
                for (int i = 0; i < hNum; i++) {
                    hMaxLen = Math.max(hMaxLen, hLen[i]);
                }
                if (prevLen > hMaxLen) {
                    for (int i = 0; i < hNum; i++) {
                        for (int j = hLen[i]; j < prevLen; j++) {
                            map[i][j] = 0;
                        }
                    }
                } else {
                    for (int i = 0; i < hNum; i++) {
                        for (int j = hLen[i]; j < hMaxLen; j++) {
                            map[i][j] = 0;
                        }
                    }
                }
                wNum = hMaxLen;
            } else {
                for (int i = 0; i < wNum; i++) {
                    int[] tmp = new int[hNum];
                    for (int j = 0; j < tmp.length; j++) {
                        tmp[j] = map[j][i];
                    }
                    List<Integer> res = cal(tmp);
                    wLen[i] = Math.min(res.size(), 100);

                    for (int j = 0; j < wLen[i]; j++) {
                        map[j][i] = res.get(j);
                    }
                }
                int prevLen = wMaxLen;
                wMaxLen = 0;
                for (int i = 0; i < wNum; i++) {
                    wMaxLen = Math.max(wMaxLen, wLen[i]);
                }
                if (prevLen > wMaxLen) {
                    for (int i = 0; i < wNum; i++) {
                        for (int j = wLen[i]; j < prevLen; j++) {
                            map[j][i] = 0;
                        }
                    }
                } else {
                    for (int i = 0; i < wNum; i++) {
                        for (int j = wLen[i]; j < wMaxLen; j++) {
                            map[j][i] = 0;
                        }
                    }
                }
                hNum = wMaxLen;
            }
            if (map[r][c] == k) {
                System.out.println(101 - limit);
                return;
            }
            limit--;
        }
        System.out.println(-1);
        br.close();
    }

    static List<Integer> cal(int[] arr) {
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                continue;
            }
            hm.put(arr[i], hm.getOrDefault(arr[i], 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> tmp = new ArrayList<>(hm.entrySet());
        tmp.sort((a, b) -> {
            if (a.getValue().intValue() == b.getValue().intValue()) {
                return a.getKey() - b.getKey();
            }
            return a.getValue() - b.getValue();
        });
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : tmp) {
            res.add(entry.getKey());
            res.add(entry.getValue());
        }
        return res;
    }
}