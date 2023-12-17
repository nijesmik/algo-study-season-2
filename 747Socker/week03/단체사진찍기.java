package bj;

public class s {
	private int answer = 0;
    private String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};
    private String name;
    public int solution(int n, String[] data) {
        boolean[] chk = new boolean[8];
        dfs(chk, data);
        return answer;
    }
private void dfs(boolean[] chk, String[] datas) {
        if (name.length() == 7) {
            
            return;
        }
        for (int i = 0; i < 8; i++) { 
            if (!chk[i]) {
                chk[i] = true;
                dfs(chk, datas);
                chk[i] = false;
            }
        }
    }
}
