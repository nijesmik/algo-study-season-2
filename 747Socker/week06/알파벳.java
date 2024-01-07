package w06_1_02;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 알파벳 {
    static int R,C;
    static char[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static ArrayList<Character> used = new ArrayList<Character>();
    static int res;
    static int max;
static boolean chk(int x, int y){
	for(int i=0; i<used.size();i++) {
		if(map[x][y]==used.get(i)) {
			return false;
		}
	}
    return true;
}

static void dfs(int x, int y){
	
	used.add(map[x][y]);
	if(x==R && y==C) {
		max = Math.max(max, res);
		used.clear();
		res =1;
		return;
	}else {
		
	
    for(int d=0; d<4; d++){
        int nx = x + dx[d];
        int ny = y + dy[d];
        if(nx>=0 && nx<R && ny>=0 && ny<C && chk(nx,ny)){
        	res++;
        	dfs(nx,ny);
        }
    }
}
}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    R = sc.nextInt();
    C = sc.nextInt();

    map = new char[R][C];

    for(int i=0; i<R; i++){
        String tmp = sc.next();
        for(int j=0; j<C; j++){
            map[i][j] = tmp.charAt(j);
        }
    }
    max = Integer.MIN_VALUE;
    res = 1;
    dfs(0,0);
    System.out.println(res);

}
}

