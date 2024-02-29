public class 프로그래머스_자물쇠와열쇠 {
    public boolean solution(int[][] key, int[][] lock) {
        int keyLen = key.length;
        int lockLen = lock.length;
        int[][] map = new int[(keyLen - 1) * 2 + lockLen][(keyLen - 1) * 2 + lockLen];
        for (int i = 0; i < lockLen; i++) {
            for (int j = 0; j < lockLen; j++) {
                map[keyLen - 1 + i][keyLen - 1 + j] = lock[i][j];
            }
        }
        for (int tc = 0; tc < 4; tc++) {
            for (int i = 0; i < keyLen + lockLen - 1; i++) {
                for (int j = 0; j < keyLen + lockLen - 1; j++) {
                    match(map, key, i, j);
                    if (check(map, keyLen - 1, keyLen - 1, lockLen)) {
                        return true;
                    }
                    init(map, lock, keyLen - 1, keyLen - 1);
                }
            }
            lock = rotate(lock);
        }
        return false;

    }

    static int[][] rotate(int[][] arr) {
        int len = arr.length;
        int[][] res = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                res[j][len - 1 - i] = arr[i][j];
            }
        }
        return res;
    }

    static void init(int[][] map, int[][] arr, int r, int c) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                map[r + i][c + j] = arr[i][j];
            }
        }

    }

    static boolean check(int[][] map, int r, int c, int len) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (map[r + i][c + j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static void match(int[][] map, int[][] key, int r, int c) {
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                map[r + i][c + j] ^= key[i][j];
            }
        }
    }

}
