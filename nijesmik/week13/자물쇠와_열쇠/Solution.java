package nijesmik.week13.자물쇠와_열쇠;

class Solution {
    int lockSize, locks[][][], key[][], keySize, margin, test[][];

    public boolean solution(int[][] key, int[][] lock) {
        lockSize = lock.length;
        locks = new int[4][][];
        locks[0] = lock;
        for (int i = 1; i < 4; i++) {
            locks[i] = rotateLock(locks[i - 1]);
        }

        keySize = key.length;
        margin = keySize - 1;
        this.key = key;

        return testAll();
    }

    boolean testAll() {
        int testSize = lockSize + 2 * margin;
        for (int k = 0; k < lockSize + margin; k++) {
            for (int m = 0; m < lockSize + margin; m++) {
                int[][] test = new int[testSize][testSize];
                for (int i = 0; i < keySize; i++) {
                    for (int j = 0; j < keySize; j++) {
                        test[k + i][m + j] = key[i][j];
                    }
                }
                for (int i = 0; i < 4; i++) {
                    if (testOne(test, locks[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean testOne(int[][] test, int[][] lock) {
        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                if (test[margin + i][margin + j] + lock[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    int[][] rotateLock(int[][] origin) {
        int[][] lock = new int[lockSize][lockSize];
        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                lock[i][j] = origin[lockSize - 1 - j][i];
            }
        }
        return lock;
    }
}