package nijesmik.week13;
import java.util.*;

public class 테트로미노 {
    static int sizeR, sizeC, map[][], ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sizeR = sc.nextInt();
        sizeC = sc.nextInt();
        map = new int[sizeR][sizeC];
        for (int i = 0; i < sizeR; i++) {
            for (int j = 0; j < sizeC; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        Stack<Tetro> tetros = new Stack<>();
        tetros.add(new Tetro(new int[][] { { 1, 1 }, { 1, 1 } }));
        tetros.add(new Tetro(new int[][] { { 1, 1, 1, 1 } }));
        tetros.add(new Tetro(tetros.peek()));
        tetros.add(new Tetro(new int[][] { { 1, 1, 1 }, { 1, 0, 0 } }));
        tetros.add(new Tetro(tetros.peek()));
        tetros.add(new Tetro(tetros.peek()));
        tetros.add(new Tetro(tetros.peek()));
        tetros.add(new Tetro(new int[][] { { 1, 0, 0 }, { 1, 1, 1 } }));
        tetros.add(new Tetro(tetros.peek()));
        tetros.add(new Tetro(tetros.peek()));
        tetros.add(new Tetro(tetros.peek()));
        tetros.add(new Tetro(new int[][] { { 1, 1, 0 }, { 0, 1, 1 } }));
        tetros.add(new Tetro(tetros.peek()));
        tetros.add(new Tetro(new int[][] { { 0, 1, 1 }, { 1, 1, 0 } }));
        tetros.add(new Tetro(tetros.peek()));
        tetros.add(new Tetro(new int[][] { { 1, 1, 1 }, { 0, 1, 0 } }));
        tetros.add(new Tetro(tetros.peek()));
        tetros.add(new Tetro(tetros.peek()));
        tetros.add(new Tetro(tetros.peek()));
        for (int i = 0; i < sizeR; i++) {
            for (int j = 0; j < sizeC; j++) {
                for (Tetro t : tetros) {
                    paste(i, j, t);
                }
            }
        }
        System.out.println(ans);
    }

    static void paste(int r, int c, Tetro t) {
        if (r + t.sizeR > sizeR || c + t.sizeC > sizeC) {
            return;
        }
        int score = 0;
        for (int i = 0; i < t.sizeR; i++) {
            for (int j = 0; j < t.sizeC; j++) {
                if (t.shape[i][j]) {
                    score += map[r + i][c + j];
                }
            }
        }
        ans = Math.max(ans, score);
    }

    static class Tetro {
        int sizeR, sizeC;
        boolean[][] shape;

        Tetro(int[][] shape) {
            sizeR = shape.length;
            sizeC = shape[0].length;
            this.shape = new boolean[sizeR][sizeC];
            for (int i = 0; i < sizeR; i++) {
                for (int j = 0; j < sizeC; j++) {
                    this.shape[i][j] = (shape[i][j] == 1);
                }
            }
        }

        Tetro(Tetro t) {
            sizeR = t.sizeC;
            sizeC = t.sizeR;
            shape = new boolean[sizeR][sizeC];
            for (int i = 0; i < sizeC; i++) {
                for (int j = 0; j < sizeR; j++) {
                    shape[j][sizeC - 1 - i] = t.shape[i][j];
                }
            }
        }
    }
}